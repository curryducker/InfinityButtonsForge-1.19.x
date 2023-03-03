package net.larsmans.infinitybuttons.block.custom.emergencybutton;

import me.shedaniel.autoconfig.AutoConfig;
import net.larsmans.infinitybuttons.InfinityButtonsConfig;
import net.larsmans.infinitybuttons.block.custom.button.AbstractButton;
import net.larsmans.infinitybuttons.sounds.InfinityButtonsSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
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
import org.jetbrains.annotations.Nullable;

public class EmergencyButton extends AbstractButton {
    
    private static final VoxelShape STONE_DOWN  = Block.box(4, 0, 4, 12, 1, 12);
    private static final VoxelShape STONE_UP    = Block.box(4, 15, 4, 12, 16, 12);
    private static final VoxelShape STONE_NORTH = Block.box(4, 4, 15, 12, 12, 16);
    private static final VoxelShape STONE_EAST  = Block.box(0, 4, 4, 1, 12, 12);
    private static final VoxelShape STONE_SOUTH = Block.box(4, 4, 0, 12, 12, 1);
    private static final VoxelShape STONE_WEST  = Block.box(15, 4, 4, 16, 12, 12);

    private static final VoxelShape FLOOR_SHAPE = Shapes.or(
            Block.box(5, 1, 5, 11, 5, 11), STONE_DOWN).optimize();
    private static final VoxelShape FLOOR_PRESSED_SHAPE = Shapes.or(
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
    private static final VoxelShape CEILING_SHAPE = Shapes.or(
            Block.box(5, 11, 5, 11, 15, 11), STONE_UP).optimize();
    private static final VoxelShape CEILING_PRESSED_SHAPE = Shapes.or(
            Block.box(5, 13, 5, 11, 15, 11), STONE_UP).optimize();

    public EmergencyButton(BlockBehaviour.Properties properties) {
        super(false, properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(PRESSED, false).setValue(FACE, AttachFace.FLOOR));
    }

    @Override
    public int getPressDuration() {
        return 10;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        Direction direction = state.getValue(FACING);
        boolean flag = state.getValue(PRESSED);
        switch(state.getValue(FACE)) {
            case FLOOR -> {
                return flag ? FLOOR_PRESSED_SHAPE : FLOOR_SHAPE;
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
        return flag ? CEILING_PRESSED_SHAPE : CEILING_SHAPE;
    }

    @Override
    public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
        if (state.getValue(PRESSED)) {
            return InteractionResult.CONSUME;
        }
        this.powerBlock(state, worldIn, pos);
        this.playSound(player, worldIn, pos, true);
        if (config.alarmSound) {
            worldIn.playSound(player, pos, InfinityButtonsSounds.ALARM.get(), SoundSource.BLOCKS, 1, 1);
        }
        worldIn.gameEvent(player, GameEvent.BLOCK_ACTIVATE, pos);
        return InteractionResult.sidedSuccess(worldIn.isClientSide);
    }

    @Override
    protected void playSound(@Nullable Player playerIn, LevelAccessor worldIn, BlockPos pos, boolean pressed) {
        worldIn.playSound(pressed ? playerIn : null, pos, this.getSoundEvent(pressed), SoundSource.BLOCKS, 1, pressed ? 0.6f : 0.5f);
    }

    @Override
    protected SoundEvent getSoundEvent(boolean pressed) {
        return SoundEvents.BONE_BLOCK_BREAK;
    }
}