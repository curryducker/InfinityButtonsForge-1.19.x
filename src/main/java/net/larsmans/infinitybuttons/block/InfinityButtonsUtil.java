package net.larsmans.infinitybuttons.block;

import me.shedaniel.autoconfig.AutoConfig;
import net.larsmans.infinitybuttons.InfinityButtonsConfig;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.List;

public class InfinityButtonsUtil {
    public static final BooleanProperty PRESSED = BooleanProperty.create("pressed");
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

    @OnlyIn(Dist.CLIENT)
    public static void tooltip(List<Component> tooltip, String name) {
        if (AutoConfig.getConfigHolder(InfinityButtonsConfig.class).getConfig().tooltips) {
            if (Screen.hasShiftDown()) {
                tooltip.add(Component.translatable("infinitybuttons.tooltip." + name));
            } else {
                tooltip.add(Component.translatable("infinitybuttons.tooltip.hold_shift"));
            }
        }
    }

    @OnlyIn(Dist.CLIENT)
    public static void tooltip(List<Component> tooltip, String name1, String name2) {
        if (AutoConfig.getConfigHolder(InfinityButtonsConfig.class).getConfig().tooltips) {
            if (Screen.hasShiftDown()) {
                tooltip.add(Component.translatable("infinitybuttons.tooltip." + name1));
                tooltip.add(Component.translatable("infinitybuttons.tooltip." + name2));
            } else {
                tooltip.add(Component.translatable("infinitybuttons.tooltip.hold_shift"));
            }
        }
    }
}
