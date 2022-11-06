package net.larsmans.infinitybuttons.block.custom.button;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;

public class WoodenButton extends AbstractButton{
    protected WoodenButton(Properties properties) {
        super(true, properties);
    }

    @Override
    public int getPressDuration() {
        return 30;
    }

    @Override
    protected SoundEvent getSoundEvent(boolean isOn) {
        return isOn ? SoundEvents.WOODEN_BUTTON_CLICK_ON : SoundEvents.WOODEN_BUTTON_CLICK_OFF;
    }
}
