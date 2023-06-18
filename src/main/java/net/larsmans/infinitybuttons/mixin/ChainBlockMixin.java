package net.larsmans.infinitybuttons.mixin;

import net.larsmans.infinitybuttons.block.custom.LanternButton;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.ChainBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ChainBlock.class)
public abstract class ChainBlockMixin extends RotatedPillarBlock implements SimpleWaterloggedBlock {

    public ChainBlockMixin(Properties properties) {
        super(properties);
    }

    @Override
    public int getSignal(BlockState blockState, BlockGetter blockAccess, BlockPos pos, Direction side) {
        return checkAround(blockState, blockAccess, pos) ? 15 : 0;
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

    @Override
    public void playerWillDestroy(Level world, BlockPos pos, BlockState state, Player player) {
        world.neighborChanged(pos.above(), this, pos);
        super.playerWillDestroy(world, pos, state, player);
    }
}