package net.larsmans.infinitybuttons.block.custom.button;

import me.shedaniel.autoconfig.AutoConfig;
import net.larsmans.infinitybuttons.InfinityButtonsConfig;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.List;

public class EmeraldButton extends AbstractButton {
    InfinityButtonsConfig config = AutoConfig.getConfigHolder(InfinityButtonsConfig.class).getConfig();

    public EmeraldButton(BlockBehaviour.Properties properties) {
        super(false, properties);
    }

    @Override
    public int getPressDuration() {
        return (int)Math.floor(Math.random()*(90-10+1)+10);
    }

    @Override
    protected SoundEvent getSoundEvent(boolean isOn) {
        return isOn ? SoundEvents.STONE_BUTTON_CLICK_ON : SoundEvents.STONE_BUTTON_CLICK_OFF;
    }

    @Override
    public void appendHoverText(ItemStack pStack, @org.jetbrains.annotations.Nullable BlockGetter pLevel, List<Component> pTooltip, TooltipFlag pFlag) {
        if (config.tooltips) {
            if(Screen.hasShiftDown()) {
                pTooltip.add(Component.translatable("infinitybuttons.tooltip.emerald_button1"));
                pTooltip.add(Component.translatable("infinitybuttons.tooltip.emerald_button2"));
            } else {
                pTooltip.add(Component.translatable("infinitybuttons.tooltip.hold_shift"));
            }
        }
        super.appendHoverText(pStack, pLevel, pTooltip, pFlag);
    }
}
