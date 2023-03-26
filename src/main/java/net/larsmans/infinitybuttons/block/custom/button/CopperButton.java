package net.larsmans.infinitybuttons.block.custom.button;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.LevelAccessor;

import javax.annotation.Nullable;

public class CopperButton extends AbstractSmallButton {
    public CopperButton(Properties properties, boolean large) {
        super(false, large, properties);
    }

    @Override
    protected void playSound(@Nullable Player playerIn, LevelAccessor worldIn, BlockPos pos, boolean hitByArrow) {
        worldIn.playSound(hitByArrow ? playerIn : null, pos, this.getSoundEvent(hitByArrow), SoundSource.BLOCKS, 1F, hitByArrow ? 0.6F : 0.5F);
    }

    @Override
    public int getPressDuration() {
        return 50;
    }

    @Override
    protected SoundEvent getSoundEvent(boolean isOn) {
        return SoundEvents.COPPER_BREAK;
    }
}
