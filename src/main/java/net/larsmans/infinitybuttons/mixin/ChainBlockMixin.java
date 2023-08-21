package net.larsmans.infinitybuttons.mixin;

import net.larsmans.infinitybuttons.block.custom.LanternButton;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ChainBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;

import static net.larsmans.infinitybuttons.InfinityButtonsUtil.checkChains;

@Mixin(ChainBlock.class)
public abstract class ChainBlockMixin extends RotatedPillarBlock implements SimpleWaterloggedBlock {

    public ChainBlockMixin(Properties properties) {
        super(properties);
    }

    @Override
    public int getSignal(BlockState blockState, BlockGetter blockAccess, BlockPos pos, Direction side) {
        return checkAround(blockState, blockAccess, pos) && side == Direction.DOWN ? 15 : 0;
    }

    @Override
    public int getDirectSignal(BlockState blockState, BlockGetter blockAccess, BlockPos pos, Direction side) {
        return checkAround(blockState, blockAccess, pos) && side == Direction.DOWN ? 15 : 0;
    }

    @Override
    public boolean isSignalSource(BlockState state) {
        return true;
    }

    public boolean checkAround(BlockState state, BlockGetter world, BlockPos pos) {
        if (state.getValue(AXIS) != Direction.Axis.Y || world.getBlockState(pos.above()).getBlock() instanceof ChainBlock) {
            return false;
        }
        int i = 1;
        while (world.getBlockState(pos.below(i)).getBlock() instanceof ChainBlock) {
            if (world.getBlockState(pos.below(i)).getValue(AXIS) != Direction.Axis.Y) return false;
            i++;
        }
        BlockState blockState = world.getBlockState(pos.below(i));
        if (!(blockState.getBlock() instanceof LanternButton)) {
            return false;
        }
        return blockState.getValue(LanternButton.PRESSED);
    }

    // Update the top chain too if this chain is updated. If this is the top chain, update the redstone power positions
    @Override
    public void neighborChanged(BlockState state, Level worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) {
        int distance = checkChains(worldIn, pos);
        if (distance > 0) {
            worldIn.neighborChanged(pos.above(distance), this, pos);
        } else {
            worldIn.neighborChanged(pos.above(), this, pos);
            worldIn.updateNeighborsAtExceptFromFacing(pos.above(), this, Direction.DOWN);
        }

        super.neighborChanged(state, worldIn, pos, blockIn, fromPos, isMoving);
    }

    // Update the redstone power positions if this is the top chain
    @Override
    public void onRemove(BlockState state, Level world, BlockPos pos, BlockState newState, boolean moved) {
        int distance = checkChains(world, pos);
        if (distance == 0) {
            world.updateNeighborsAtExceptFromFacing(pos.above(), this, Direction.DOWN);
        }

        super.onRemove(state, world, pos, newState, moved);
    }
}