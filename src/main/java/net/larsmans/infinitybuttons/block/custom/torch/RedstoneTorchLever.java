package net.larsmans.infinitybuttons.block.custom.torch;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class RedstoneTorchLever extends RedstoneTorchButton{
    public RedstoneTorchLever(BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState((BlockState)((BlockState)this.stateDefinition.any()).setValue(LIT, false));
    }

    @Override
    public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
        if (state.getValue(LIT)) {
            this.unpowerBlock(state, worldIn, pos);
            this.playSound(player, worldIn, pos, false);
            return InteractionResult.sidedSuccess(worldIn.isClientSide);
        } else {
            this.powerBlock(state, worldIn, pos);
            this.playSound(player, worldIn, pos, true);
            return InteractionResult.sidedSuccess(worldIn.isClientSide);
        }
    }

    @Override
    public void powerBlock(BlockState state, Level worldIn, BlockPos pos) {
        worldIn.setBlock(pos, (BlockState)state.setValue(LIT, true), 3);
        this.updateNeighbors(state, worldIn, pos);
    }

    public void unpowerBlock(BlockState state, Level worldIn, BlockPos pos) {
        worldIn.setBlock(pos, (BlockState)state.setValue(LIT, false), 3);
        this.updateNeighbors(state, worldIn, pos);
    }
}
