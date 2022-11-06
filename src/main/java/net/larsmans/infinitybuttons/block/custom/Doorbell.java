package net.larsmans.infinitybuttons.block.custom;

import net.larsmans.infinitybuttons.sounds.InfinityButtonsSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;

public class Doorbell extends HorizontalDirectionalBlock {
    public static final BooleanProperty PRESSED = BooleanProperty.create("pressed");

    protected static final VoxelShape NORTH_PRESSED_SHAPE =
            Block.box(6, 4, 14, 10, 12, 16);

    protected static final VoxelShape EAST_PRESSED_SHAPE =
            Block.box(0, 4, 6, 2, 12, 10);

    protected static final VoxelShape SOUTH_PRESSED_SHAPE =
            Block.box(6, 4, 0, 10, 12, 2);

    protected static final VoxelShape WEST_PRESSED_SHAPE =
            Block.box(14, 4, 6, 16, 12, 10);

    protected static final VoxelShape NORTH_SHAPE = Shapes.or(NORTH_PRESSED_SHAPE,
            Block.box(7, 6, 13, 9, 10, 14));

    protected static final VoxelShape EAST_SHAPE = Shapes.or(EAST_PRESSED_SHAPE,
            Block.box(2, 6, 7, 3, 10, 9));

    protected static final VoxelShape SOUTH_SHAPE = Shapes.or(SOUTH_PRESSED_SHAPE,
            Block.box(7, 6, 2, 9, 10, 3));

    protected static final VoxelShape WEST_SHAPE = Shapes.or(WEST_PRESSED_SHAPE,
            Block.box(13, 6, 7, 14, 10, 9));

    public Doorbell(BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(PRESSED, false));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        boolean bl = state.getValue(PRESSED);
        switch (state.getValue(FACING)) {
            case NORTH: {
                return bl ? NORTH_PRESSED_SHAPE : NORTH_SHAPE;
            }
            case EAST: {
                return bl ? EAST_PRESSED_SHAPE : EAST_SHAPE;
            }
            case SOUTH: {
                return bl ? SOUTH_PRESSED_SHAPE : SOUTH_SHAPE;
            }
        }
        return bl ? WEST_PRESSED_SHAPE : WEST_SHAPE;
    }

    @Override
    public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
        if (state.getValue(PRESSED)) {
            return InteractionResult.CONSUME;
        }
        this.powerBlock(state, worldIn, pos);
        this.playSound(player, worldIn, pos, true);
        return InteractionResult.sidedSuccess(worldIn.isClientSide);
    }


    public void powerBlock(BlockState state, Level world, BlockPos pos) {
        world.setBlock(pos, state.setValue(PRESSED, true), 3);
        this.updateNeighbors(state, world, pos);
        world.scheduleTick(pos, this, 15);
    }

    protected void playSound(@Nullable Player playerIn, LevelAccessor worldIn, BlockPos pos, boolean hitByArrow) {
        worldIn.playSound(hitByArrow ? playerIn : null, pos, InfinityButtonsSounds.DOORBELL.get(), SoundSource.BLOCKS, 0.3F, 1f);
    }

    @Override
    public void onRemove(BlockState state, Level worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        if (isMoving || state.is(newState.getBlock())) {
            return;
        }
        if (state.getValue(PRESSED)) {
            this.updateNeighbors(state, worldIn, pos);
        }
        super.onRemove(state, worldIn, pos, newState, isMoving);
    }

    @Override
    public void tick(BlockState state, ServerLevel worldIn, BlockPos pos, RandomSource rand) {
        if (!state.getValue(PRESSED)) {
            return;
        }
        worldIn.setBlock(pos, state.setValue(PRESSED, false), 3);
        this.updateNeighbors(state, worldIn, pos);
    }

    public void updateNeighbors(BlockState state, Level worldIn, BlockPos pos) {
        worldIn.updateNeighborsAt(pos, this);
        worldIn.updateNeighborsAt(pos.relative(state.getValue(FACING).getOpposite()), this);
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, PRESSED);
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader worldIn, BlockPos pos) {
        Direction direction = state.getValue(FACING);
        BlockPos blockPos = pos.relative(direction.getOpposite());
        BlockState blockState = worldIn.getBlockState(blockPos);
        return blockState.isFaceSturdy(worldIn, blockPos, direction);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        Direction[] directions;
        BlockState blockState = this.defaultBlockState();
        Level worldView = ctx.getLevel();
        BlockPos blockPos = ctx.getClickedPos();
        for (Direction direction : directions = ctx.getNearestLookingDirections()) {
            Direction direction2;
            if (!direction.getAxis().isHorizontal() || !(blockState = blockState.setValue(FACING, direction2 = direction.getOpposite())).canSurvive(worldView, blockPos)) continue;
            return blockState;
        }
        return null;
    }

    @Override
    public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, LevelAccessor worldIn, BlockPos currentPos, BlockPos facingPos) {
        if (facing.getOpposite() == stateIn.getValue(FACING) && !stateIn.canSurvive(worldIn, currentPos)) {
            return Blocks.AIR.defaultBlockState();
        }
        return stateIn;
    }
}
