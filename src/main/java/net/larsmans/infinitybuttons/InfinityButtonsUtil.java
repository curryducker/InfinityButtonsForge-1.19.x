package net.larsmans.infinitybuttons;

import me.shedaniel.autoconfig.AutoConfig;
import net.larsmans.infinitybuttons.config.InfinityButtonsConfig;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.List;

public class InfinityButtonsUtil {
    public static final BooleanProperty PRESSED = BooleanProperty.create("pressed");
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

    public static final MutableComponent HOLD_SHIFT_TEXT = Component.translatable("infinitybuttons.tooltip.hold_shift").withStyle(ChatFormatting.GRAY);
    public static final MutableComponent SAFE_EMERGENCY_BUTTON_ACTIONBAR_TEXT = Component.translatable("infinitybuttons.actionbar.closed_safety_button");

    @OnlyIn(Dist.CLIENT)
    public static void tooltip(List<Component> tooltip, String name) {
        if (AutoConfig.getConfigHolder(InfinityButtonsConfig.class).getConfig().tooltips) {
            if (Screen.hasShiftDown()) {
                tooltip.add(Component.translatable("infinitybuttons.tooltip." + name).withStyle(ChatFormatting.GRAY));
            } else {
                tooltip.add(HOLD_SHIFT_TEXT);
            }
        }
    }

    @OnlyIn(Dist.CLIENT)
    public static void tooltip(List<Component> tooltip, String name1, String name2) {
        if (AutoConfig.getConfigHolder(InfinityButtonsConfig.class).getConfig().tooltips) {
            if (Screen.hasShiftDown()) {
                tooltip.add(Component.translatable("infinitybuttons.tooltip." + name1).withStyle(ChatFormatting.GRAY));
                tooltip.add(Component.translatable("infinitybuttons.tooltip." + name2).withStyle(ChatFormatting.GRAY));
            } else {
                tooltip.add(HOLD_SHIFT_TEXT);
            }
        }
    }

    public static void playGlobalSound (Level level, BlockPos pos, SoundEvent soundEvent, SoundSource soundSource) {
        Camera cam = Minecraft.getInstance().gameRenderer.getMainCamera();
        if (cam.isInitialized()) {
            double x = cam.getPosition().x;
            double y = cam.getPosition().y;
            double z = cam.getPosition().z;
            double d0 = (double)pos.getX() - x;
            double d1 = (double)pos.getY() - y;
            double d2 = (double)pos.getZ() - z;
            double d3 = Math.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
            if (d3 > 0.0D) {
                x += d0 / d3 * 2.0D;
                y += d1 / d3 * 2.0D;
                z += d2 / d3 * 2.0D;
            }
            level.playLocalSound(x, y, z, soundEvent, soundSource, 1.0F, 1.0F, false);
        }
    }
}
