package net.larsmans.infinitybuttons.block.custom.emergencybutton;

import me.shedaniel.autoconfig.AutoConfig;
import net.larsmans.infinitybuttons.InfinityButtonsConfig;
import net.larsmans.infinitybuttons.sounds.InfinityButtonsSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FaceAttachedHorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.AttachFace;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;

public class EmergencyButton extends FaceAttachedHorizontalDirectionalBlock {
    InfinityButtonsConfig config = AutoConfig.getConfigHolder(InfinityButtonsConfig.class).getConfig();

    public static final BooleanProperty PRESSED = BooleanProperty.create("pressed");
    
    private static final VoxelShape STONE_DOWN  = Block.box(4, 0, 4, 12, 1, 12);
    private static final VoxelShape STONE_UP    = Block.box(4, 15, 4, 12, 16, 12);
    private static final VoxelShape STONE_NORTH = Block.box(4, 4, 15, 12, 12, 16);
    private static final VoxelShape STONE_EAST  = Block.box(0, 4, 4, 1, 12, 12);
    private static final VoxelShape STONE_SOUTH = Block.box(4, 4, 0, 12, 12, 1);
    private static final VoxelShape STONE_WEST  = Block.box(15, 4, 4, 16, 12, 12);

    private static final VoxelShape FLOOR_X_SHAPE = Shapes.or(
            Block.box(5, 1, 5, 11, 5, 11), STONE_DOWN).optimize();
    private static final VoxelShape FLOOR_Z_SHAPE = Shapes.or(
            Block.box(5, 1, 5, 11, 5, 11), STONE_DOWN).optimize();
    private static final VoxelShape FLOOR_X_PRESSED_SHAPE = Shapes.or(
            Block.box(5, 1, 5, 11, 3, 11), STONE_DOWN).optimize();
    private static final VoxelShape FLOOR_Z_PRESSED_SHAPE = Shapes.or(
            Block.box(5, 1, 5, 11, 3, 11), STONE_DOWN).optimize();
    private static final VoxelShape NORTH_SHAPE = Shapes.or(
            Block.box(5, 5, 11, 11, 11, 15), STONE_NORTH).optimize();
    private static final VoxelShape NORTH_PRESSED_SHAPE = Shapes.or(
            Block.box(5, 5, 13, 11, 11, 15), STONE_NORTH).optimize();
    private static final VoxelShape EAST_SHAPE = Shapes.or(
            Block.box(1, 5, 5, 5, 11, 11), STONE_EAST).optimize();
    private static final VoxelShape EAST_PRESSED_SHAPE = Shapes.or(
            Block.box(1, 5, 5, 3, 11, 11), STONE_EAST).optimize();
    private static final VoxelShape SOUTH_SHAPE = Shapes.or(
            Block.box(5, 5, 1, 11, 11, 5), STONE_SOUTH).optimize();
    private static final VoxelShape SOUTH_PRESSED_SHAPE = Shapes.or(
            Block.box(5, 5, 1, 11, 11, 3), STONE_SOUTH).optimize();
    private static final VoxelShape WEST_SHAPE = Shapes.or(
            Block.box(11, 5, 5, 15, 11, 11), STONE_WEST).optimize();
    private static final VoxelShape WEST_PRESSED_SHAPE = Shapes.or(
            Block.box(13, 5, 5, 15, 11, 11), STONE_WEST).optimize();
    private static final VoxelShape CEILING_X_SHAPE = Shapes.or(
            Block.box(5, 11, 5, 11, 15, 11), STONE_UP).optimize();
    private static final VoxelShape CEILING_Z_SHAPE = Shapes.or(
            Block.box(5, 11, 5, 11, 15, 11), STONE_UP).optimize();
    private static final VoxelShape CEILING_X_PRESSED_SHAPE = Shapes.or(
            Block.box(5, 13, 5, 11, 15, 11), STONE_UP).optimize();
    private static final VoxelShape CEILING_Z_PRESSED_SHAPE = Shapes.or(
            Block.box(5, 13, 5, 11, 15, 11), STONE_UP).optimize();

    public EmergencyButton(BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(PRESSED, false).setValue(FACE, AttachFace.FLOOR));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        Direction direction = state.getValue(FACING);
        boolean flag = state.getValue(PRESSED);
        switch(state.getValue(FACE)) {
            case FLOOR -> {
                if (direction.getAxis() == Direction.Axis.X) {
                    return flag ? FLOOR_X_PRESSED_SHAPE : FLOOR_X_SHAPE;
                }
                return flag ? FLOOR_Z_PRESSED_SHAPE : FLOOR_Z_SHAPE;
            }
            case WALL -> {
                switch (direction) {
                    case EAST -> {
                        return flag ? EAST_PRESSED_SHAPE : EAST_SHAPE;
                    }
                    case WEST -> {
                        return flag ? WEST_PRESSED_SHAPE : WEST_SHAPE;
                    }
                    case SOUTH -> {
                        return flag ? SOUTH_PRESSED_SHAPE : SOUTH_SHAPE;
                    }
                }
                return flag ? NORTH_PRESSED_SHAPE : NORTH_SHAPE;
            }
        }
        if (direction.getAxis() == Direction.Axis.X) {
            return flag ? CEILING_X_PRESSED_SHAPE : CEILING_X_SHAPE;
        }
        return flag ? CEILING_Z_PRESSED_SHAPE : CEILING_Z_SHAPE;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FACING, PRESSED, FACE);
    }

    @Override
    public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
        if (state.getValue(PRESSED)) {
            return InteractionResult.CONSUME;
        }
        this.powerBlock(state, worldIn, pos);
        this.playSound(player, worldIn, pos, true);
        if (config.alarmSound) {
            worldIn.playSound(player, pos, InfinityButtonsSounds.ALARM.get(), SoundSource.BLOCKS, 2f, 0.6f);
        }
        worldIn.gameEvent(player, GameEvent.BLOCK_ACTIVATE, pos);
        return InteractionResult.sidedSuccess(worldIn.isClientSide);
    }

    public void powerBlock(BlockState state, Level world, BlockPos pos) {
        world.setBlock(pos, state.setValue(PRESSED, true), 3);
        this.updateNeighbors(state, world, pos);
        world.scheduleTick(pos, this, 10);
    }

    protected void playSound(@Nullable Player playerIn, LevelAccessor worldIn, BlockPos pos, boolean pressed) {
        worldIn.playSound(pressed ? playerIn : null, pos, SoundEvents.BONE_BLOCK_BREAK, SoundSource.BLOCKS, 0.75F, pressed ? 0.6F : 0.5F);
    }

    @Override
    public void onRemove(BlockState state, Level worldIn, BlockPos pos, BlockState newState, boolean moved) {
        if (moved || state.is(newState.getBlock())) {
            return;
        }
        if (state.getValue(PRESSED)) {
            this.updateNeighbors(state, worldIn, pos);
        }
        super.onRemove(state, worldIn, pos, newState, moved);
    }

    @Override
    public int getSignal(BlockState blockState, BlockGetter blockAccess, BlockPos pos, Direction side) {
        return blockState.getValue(PRESSED) ? 15 : 0;
    }

    @Override
    public int getDirectSignal(BlockState blockState, BlockGetter blockAccess, BlockPos pos, Direction side) {
        if (blockState.getValue(PRESSED) && getConnectedDirection(blockState) == side) {
            return 15;
        }
        return 0;
    }

    @Override
    public boolean isSignalSource(BlockState state) {
        return true;
    }

    @Override
    public void tick(BlockState state, ServerLevel worldIn, BlockPos pos, RandomSource rand) {
        if (state.getValue(PRESSED)) {
            worldIn.setBlock(pos, state.setValue(PRESSED, false), 3);
            this.updateNeighbors(state, worldIn, pos);
            this.playSound(null, worldIn, pos, false);
            worldIn.gameEvent(null, GameEvent.BLOCK_DEACTIVATE, pos);
        }
    }

    private void updateNeighbors(BlockState state, Level worldIn, BlockPos pos) {
        worldIn.updateNeighborsAt(pos, this);
        worldIn.updateNeighborsAt(pos.relative(getConnectedDirection(state).getOpposite()), this);
    }
}