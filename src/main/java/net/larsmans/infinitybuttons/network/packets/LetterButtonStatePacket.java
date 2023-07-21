package net.larsmans.infinitybuttons.network.packets;

import net.larsmans.infinitybuttons.block.custom.letterbutton.LetterButton;
import net.larsmans.infinitybuttons.block.custom.letterbutton.LetterButtonEnum;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.network.NetworkEvent;

import java.util.Objects;
import java.util.function.Supplier;

import static net.larsmans.infinitybuttons.block.custom.letterbutton.LetterButton.CHARACTER;

public class LetterButtonStatePacket {
    private final BlockPos pos;
    private final LetterButtonEnum buttonEnum;

    public LetterButtonStatePacket(BlockPos pos, LetterButtonEnum buttonEnum) {
        this.pos = pos;
        this.buttonEnum = buttonEnum;
    }

    public static void encode(LetterButtonStatePacket packet, FriendlyByteBuf buffer) {
        buffer.writeBlockPos(packet.pos);
        buffer.writeEnum(packet.buttonEnum);
    }

    public static LetterButtonStatePacket decode(FriendlyByteBuf buffer) {
        BlockPos pos = buffer.readBlockPos();
        LetterButtonEnum buttonEnum = buffer.readEnum(LetterButtonEnum.class);
        return new LetterButtonStatePacket(pos, buttonEnum);
    }

    public static void handle(LetterButtonStatePacket packet, Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> {
            Level world = Objects.requireNonNull(context.getSender()).getLevel();
            BlockPos pos = packet.pos;
            BlockState state = world.getBlockState(pos);
            LetterButtonEnum buttonEnum = packet.buttonEnum;
            // Execute the code to update the block state on the server here
            // You can access the packet data using 'world', 'state', 'pos', and 'buttonEnum'
            if (state.getBlock() instanceof LetterButton)
                world.setBlock(pos, state.setValue(CHARACTER, buttonEnum), 3);
        });
        context.setPacketHandled(true);
    }
}
