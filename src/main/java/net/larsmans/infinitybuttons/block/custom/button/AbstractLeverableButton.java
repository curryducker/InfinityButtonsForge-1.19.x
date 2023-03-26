package net.larsmans.infinitybuttons.block.custom.button;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;

public abstract class AbstractLeverableButton extends AbstractButton {

    public final boolean lever;

    public AbstractLeverableButton(boolean lever, Properties properties) {
        super(false, properties);
        this.lever = lever;
    }

    @Override
    public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
        if (lever) {
            if (state.getValue(PRESSED)) {
                this.unpowerBlock(state, worldIn, pos);
                this.playSound(player, worldIn, pos, false);
                worldIn.gameEvent(player, GameEvent.BLOCK_DEACTIVATE, pos);
            } else {
                this.powerBlock(state, worldIn, pos);
                this.playSound(player, worldIn, pos, true);
                worldIn.gameEvent(player, GameEvent.BLOCK_ACTIVATE, pos);
            }
        } else {
            if (state.getValue(PRESSED)) {
                return InteractionResult.CONSUME;
            }
            this.powerBlock(state, worldIn, pos);
            this.playSound(player, worldIn, pos, true);
            worldIn.gameEvent(player, GameEvent.BLOCK_ACTIVATE, pos);
        }
        return InteractionResult.sidedSuccess(worldIn.isClientSide);
    }

    @Override
    public void powerBlock(BlockState state, Level world, BlockPos pos) {
        world.setBlock(pos, state.setValue(PRESSED, true), 3);
        this.updateNeighbors(state, world, pos);
        if (!lever) {
            world.scheduleTick(pos, this, this.getPressDuration());
        }
    }

    public void unpowerBlock(BlockState state, Level world, BlockPos pos) {
        world.setBlock(pos, state.setValue(PRESSED, false), 3);
        this.updateNeighbors(state, world, pos);
    }
}
