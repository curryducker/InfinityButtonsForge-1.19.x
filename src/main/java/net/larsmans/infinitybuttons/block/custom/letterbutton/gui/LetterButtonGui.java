package net.larsmans.infinitybuttons.block.custom.letterbutton.gui;

import com.mojang.blaze3d.vertex.PoseStack;
import net.larsmans.infinitybuttons.block.custom.letterbutton.LetterButton;
import net.larsmans.infinitybuttons.block.custom.letterbutton.LetterButtonEnum;
import net.larsmans.infinitybuttons.network.IBPacketHandler;
import net.larsmans.infinitybuttons.network.packets.LetterButtonStatePacket;
import net.minecraft.client.gui.components.PlainTextButton;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class LetterButtonGui extends Screen {

    private static final ResourceLocation LETTER_TEXTURE = new ResourceLocation("infinitybuttons", "textures/block/letter_button/characters.png");

    private static final int BUTTON_WIDTH = 24;
    private static final int BUTTON_HEIGHT = 24;
    private static final int BUTTON_MARGIN = 4;
    private static final int BUTTONS_PER_ROW = 7;
    private static final int NUM_BUTTONS = 49;

    private final LetterButton letterButton;
    private final BlockState state;
    private final Level world;
    private final BlockPos pos;
    private static int selectedButton;

    public LetterButtonGui(LetterButton letterButton, BlockState state, Level worldIn, BlockPos pos) {
        super(Component.translatable("block.infinitybuttons.letter_button"));
        this.letterButton = letterButton;
        this.state = state;
        this.world = worldIn;
        this.pos = pos;
    }

    @Override
    protected void init() {
        selectedButton = letterButton.getEnumId(state);
        super.init();
        int startX = (width - (BUTTONS_PER_ROW * (BUTTON_WIDTH + BUTTON_MARGIN))) / 2;
        int startY = (height - (((NUM_BUTTONS - 1) / BUTTONS_PER_ROW + 1) * (BUTTON_HEIGHT + BUTTON_MARGIN))) / 2;

        for (int i = 0; i < NUM_BUTTONS; i++) {
            int row = i / BUTTONS_PER_ROW;
            int col = i % BUTTONS_PER_ROW;
            int x = startX + col * (BUTTON_WIDTH + BUTTON_MARGIN);
            int y = startY + row * (BUTTON_HEIGHT + BUTTON_MARGIN);
            int button = i;
            int TexX = col * 20;
            int TexY = row * 20;

            addRenderableWidget(new ImageLetterButton(x, y, BUTTON_WIDTH, BUTTON_HEIGHT, TexX, TexY, 0, LETTER_TEXTURE, pButton -> onClick(button), button));
        }

        int buttonWidth = font.width(Component.translatable("gui.done"));
        int buttonHeight = font.lineHeight;
        addRenderableWidget(new PlainTextButton((width - buttonWidth) / 2, startY + (((NUM_BUTTONS - 1) / BUTTONS_PER_ROW) + 1) * (BUTTON_HEIGHT + BUTTON_MARGIN),
                buttonWidth, buttonHeight, Component.translatable("gui.done"), pButton -> onClose(), font));
    }

    @Override
    public void render(@NotNull PoseStack matrices, int mouseX, int mouseY, float delta) {
        renderBackground(matrices);
        super.render(matrices, mouseX, mouseY, delta);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    @Override
    public void onClose() {
        for (LetterButtonEnum buttonEnum : LetterButtonEnum.values()) {
            if (selectedButton == buttonEnum.ordinal()) {
                letterButton.setState(state, world, pos, buttonEnum);
                IBPacketHandler.sendToServer(new LetterButtonStatePacket(pos, buttonEnum));
            }
        }
        getMinecraft().setScreen(null);
    }

    public static int getSelectedButton() {
        return selectedButton;
    }

    protected void onClick(int button) {
        selectedButton = button;
    }
}