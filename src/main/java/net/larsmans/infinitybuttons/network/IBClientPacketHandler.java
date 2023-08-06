package net.larsmans.infinitybuttons.network;

import me.shedaniel.autoconfig.AutoConfig;
import net.larsmans.infinitybuttons.InfinityButtonsUtil;
import net.larsmans.infinitybuttons.block.custom.letterbutton.LetterButton;
import net.larsmans.infinitybuttons.block.custom.letterbutton.gui.LetterButtonGui;
import net.larsmans.infinitybuttons.config.AlarmEnum;
import net.larsmans.infinitybuttons.config.InfinityButtonsConfig;
import net.larsmans.infinitybuttons.network.packets.AlarmPacket;
import net.larsmans.infinitybuttons.network.packets.LetterButtonScreenPacket;
import net.larsmans.infinitybuttons.sounds.InfinityButtonsSounds;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class IBClientPacketHandler {

    public static void handleScreenPacket(LetterButtonScreenPacket packet) {
        BlockPos pos = packet.getPos();
        Level world = Minecraft.getInstance().level;
        assert world != null;
        BlockState state = world.getBlockState(pos);
        if (state.getBlock() instanceof LetterButton letterButton) {
            Minecraft.getInstance().setScreen(new LetterButtonGui(letterButton, state, world, pos));
        }
    }

    public static void handleAlarmPacket(AlarmPacket packet) {
        InfinityButtonsConfig config = AutoConfig.getConfigHolder(InfinityButtonsConfig.class).getConfig();
        if (config.muteAlarmSound) {
            return;
        }
        BlockPos pos = packet.getPos();
        AlarmEnum alarmEnum = packet.getAlarmEnum();
        if (alarmEnum == AlarmEnum.GLOBAL) {
            InfinityButtonsUtil.playGlobalSound(Minecraft.getInstance().level, pos, InfinityButtonsSounds.ALARM.get(), SoundSource.BLOCKS);
        } else {
            assert Minecraft.getInstance().level != null;
            Minecraft.getInstance().level.playSound(Minecraft.getInstance().player, pos, InfinityButtonsSounds.ALARM.get(), SoundSource.BLOCKS, (float) config.alarmSoundRange, 1);
        }
    }
}
