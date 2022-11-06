package net.larsmans.infinitybuttons.block.custom.torch;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class TorchLever extends TorchButton{
    public TorchLever(BlockBehaviour.Properties properties, ParticleOptions particleData) {
        super(properties, particleData);
        this.registerDefaultState((BlockState)((BlockState)this.stateDefinition.any()).setValue(FACING, Direction.NORTH).setValue(PRESSED, false));
    }

    @Override
    public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
        if (state.getValue(PRESSED)) {
            this.unpowerBlock(state, worldIn, pos);
            this.playSound(player, worldIn, pos, false);
        } else {
            this.powerBlock(state, worldIn, pos);
            this.playSound(player, worldIn, pos, true);
        }
        return InteractionResult.sidedSuccess(worldIn.isClientSide);
    }

    @Override
    public void powerBlock(BlockState state, Level worldIn, BlockPos pos) {
        worldIn.setBlock(pos, (BlockState)state.setValue(PRESSED, true), 3);
        this.updateNeighbors(state, worldIn, pos);
    }

    public void unpowerBlock(BlockState state, Level worldIn, BlockPos pos) {
        worldIn.setBlock(pos, (BlockState)state.setValue(PRESSED, false), 3);
        this.updateNeighbors(state, worldIn, pos);
    }
}
