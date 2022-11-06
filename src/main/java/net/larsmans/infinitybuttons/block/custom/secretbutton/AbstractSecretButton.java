package net.larsmans.infinitybuttons.block.custom.secretbutton;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;
import java.util.Random;

public abstract class AbstractSecretButton extends HorizontalDirectionalBlock {
    public static final BooleanProperty PRESSED = BooleanProperty.create("pressed");

    public final VoxelShape NORTH_SHAPE;
    public final VoxelShape EAST_SHAPE;
    public final VoxelShape SOUTH_SHAPE;
    public final VoxelShape WEST_SHAPE;
    public final VoxelShape OFF_SHAPE;

    public AbstractSecretButton(BlockBehaviour.Properties properties, VoxelShape north_shape, VoxelShape east_shape, VoxelShape south_shape, VoxelShape west_shape, VoxelShape off_shape) {
        super(properties);
        NORTH_SHAPE = north_shape;
        EAST_SHAPE = east_shape;
        SOUTH_SHAPE = south_shape;
        WEST_SHAPE = west_shape;
        OFF_SHAPE = off_shape;
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(PRESSED, false));
    }

    public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        if (state.getValue(PRESSED)) {
            switch (state.getValue(FACING)) {
                case NORTH: {
                    return NORTH_SHAPE;
                }
                case EAST: {
                    return EAST_SHAPE;
                }
                case SOUTH: {
                    return SOUTH_SHAPE;
                }
                case WEST: {
                    return WEST_SHAPE;
                }
            }
        }
        return OFF_SHAPE;
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, PRESSED);
    }

    public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
        if (state.getValue(PRESSED)) {
            return InteractionResult.CONSUME;
        } else {
            this.powerBlock(state, worldIn, pos);
            this.playSound(player, worldIn, pos, true);
            return InteractionResult.sidedSuccess(worldIn.isClientSide);
        }
    }

    public void powerBlock(BlockState state, Level world, BlockPos pos) {
        world.setBlock(pos, state.setValue(PRESSED, true), 3);
        this.updateNeighbors(state, world, pos);
        world.scheduleTick(pos, this, 50);
    }

    protected void playSound(@Nullable Player playerIn, LevelAccessor worldIn, BlockPos pos, boolean hitByArrow) {
        worldIn.playSound(hitByArrow ? playerIn : null, pos, this.getSoundEvent(hitByArrow), SoundSource.BLOCKS, 0.3F, hitByArrow ? 0.6F : 0.5F);
    }

    protected abstract SoundEvent getSoundEvent(boolean isOn);

    public void onRemove(BlockState state, Level worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        if (!isMoving && !state.is(newState.getBlock())) {
            if (state.getValue(PRESSED)) {
                this.updateNeighbors(state, worldIn, pos);
            }

            super.onRemove(state, worldIn, pos, newState, isMoving);
        }
    }

    public int getSignal(BlockState blockState, BlockGetter blockAccess, BlockPos pos, Direction side) {
        return blockState.getValue(PRESSED) ? 15 : 0;
    }

    public int getDirectSignal(BlockState blockState, BlockGetter blockAccess, BlockPos pos, Direction side) {
        if (blockState.getValue(PRESSED) && blockState.getValue(FACING) == side) {
            return 15;
        }
        return 0;
    }

    public boolean isSignalSource(BlockState state) {
        return true;
    }

    public void tick(BlockState state, ServerLevel worldIn, BlockPos pos, RandomSource rand) {
        if (state.getValue(PRESSED)) {
            worldIn.setBlock(pos, state.setValue(PRESSED, Boolean.FALSE), 3);
            this.updateNeighbors(state, worldIn, pos);
            this.playSound((Player)null, worldIn, pos, false);
        }
    }

    public void updateNeighbors(BlockState state, Level worldIn, BlockPos pos) {
        worldIn.updateNeighborsAt(pos, this);
        worldIn.updateNeighborsAt(pos.relative(state.getValue(FACING).getOpposite()), this);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        return this.defaultBlockState().setValue(BlockStateProperties.HORIZONTAL_FACING, ctx.getHorizontalDirection().getOpposite());
    }
}
