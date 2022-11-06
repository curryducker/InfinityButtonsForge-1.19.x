package net.larsmans.infinitybuttons.block.custom.torch;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

public class RedstoneWallTorchButton extends RedstoneTorchButton {
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

    public RedstoneWallTorchButton(BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState((BlockState)((BlockState)((BlockState)this.stateDefinition.any()).setValue(LIT, false)).setValue(FACING, Direction.NORTH));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        return WallTorchBlock.getShape(state);
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader worldIn, BlockPos pos) {
        return Blocks.WALL_TORCH.canSurvive(state, worldIn, pos);
    }

    @Override
    public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, LevelAccessor worldIn, BlockPos currentPos, BlockPos facingPos) {
        return Blocks.WALL_TORCH.updateShape(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }

    @Override
    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockState blockState = Blocks.WALL_TORCH.getStateForPlacement(context);
        return blockState == null ? null : (BlockState)this.defaultBlockState().setValue(FACING, blockState.getValue(FACING));
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState stateIn, Level worldIn, BlockPos pos, RandomSource rand) {
        if (!stateIn.getValue(LIT)) {
            return;
        }
        Direction direction = stateIn.getValue(FACING).getOpposite();
        double d0 = (double)pos.getX() + 0.5D + (rand.nextDouble() - 0.5D) * 0.2D + 0.27D * (double)direction.getStepX();
        double d1 = (double)pos.getY() + 0.7D + (rand.nextDouble() - 0.5D) * 0.2D + 0.22D;
        double d2 = (double)pos.getZ() + 0.5D + (rand.nextDouble() - 0.5D) * 0.2D + 0.27D * (double)direction.getStepZ();
        worldIn.addParticle(this.flameParticle, d0, d1, d2, 0.0D, 0.0D, 0.0D);
    }

    @Override
    public int getSignal(BlockState blockState, BlockGetter blockAccess, BlockPos pos, Direction side) {
        if (blockState.getValue(LIT) && blockState.getValue(FACING) != side) {
            return 15;
        }
        return 0;
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rot) {
        return Blocks.WALL_TORCH.rotate(state, rot);
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirrorIn) {
        return Blocks.WALL_TORCH.mirror(state, mirrorIn);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, LIT);
    }
}
