package net.larsmans.infinitybuttons.block.custom.letterbutton;

import net.larsmans.infinitybuttons.InfinityButtonsUtil;
import net.larsmans.infinitybuttons.block.custom.button.AbstractLeverableButton;
import net.larsmans.infinitybuttons.block.custom.button.LargeButtonShape;
import net.larsmans.infinitybuttons.network.IBPacketHandler;
import net.larsmans.infinitybuttons.network.packets.LetterButtonScreenPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.AttachFace;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;

public class LetterButton extends AbstractLeverableButton {

    public static final EnumProperty<LetterButtonEnum> CHARACTER = EnumProperty.create("character", LetterButtonEnum.class);

    public LetterButton(Properties properties, boolean lever) {
        super(lever, properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(PRESSED, false).setValue(FACE, AttachFace.FLOOR).setValue(CHARACTER, LetterButtonEnum.NONE));
    }

    @Override
    public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
        GameType gameMode = GameType.DEFAULT_MODE;
        if (worldIn.isClientSide) {
            ClientPacketListener connection = Minecraft.getInstance().getConnection();
            assert connection != null;
            gameMode = Objects.requireNonNull(connection.getPlayerInfo(player.getGameProfile().getId())).getGameMode();
        } else if (player instanceof ServerPlayer serverPlayer) {
            gameMode = serverPlayer.gameMode.getGameModeForPlayer();
        }
        if (player.isShiftKeyDown() && gameMode != GameType.ADVENTURE) {
            openScreen(pos, player);
            return InteractionResult.sidedSuccess(worldIn.isClientSide);
        }
        return super.use(state, worldIn, pos, player, handIn, hit);
    }

    @Override
    public void setPlacedBy(@NotNull Level pLevel, @NotNull BlockPos pPos, @NotNull BlockState pState, @Nullable LivingEntity pPlacer, @NotNull ItemStack pStack) {
        super.setPlacedBy(pLevel, pPos, pState, pPlacer, pStack);
        openScreen(pPos, pPlacer);

    }

    public void openScreen(BlockPos pos, LivingEntity entity) {
        if (entity instanceof ServerPlayer player) {
            IBPacketHandler.sendToPlayer(new LetterButtonScreenPacket(pos), player);
        }
    }

    @Override
    public @NotNull VoxelShape getShape(@NotNull BlockState pState, @NotNull BlockGetter pLevel, @NotNull BlockPos pPos, @NotNull CollisionContext pContext) {
        return LargeButtonShape.outlineShape(pState);
    }

    @Override
    public int getPressDuration() {
        return 30;
    }

    @Override
    protected SoundEvent getSoundEvent(boolean pressed) {
        return SoundEvents.STONE_BUTTON_CLICK_ON;
    }

    public int getEnumId(BlockState state) {
        return state.getValue(CHARACTER).ordinal();
    }

    public void setState(BlockState state, Level worldIn, BlockPos pos, LetterButtonEnum buttonEnum) {
        worldIn.setBlock(pos, state.setValue(CHARACTER, buttonEnum), 3);
        this.updateNeighbors(state, worldIn, pos);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(CHARACTER);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(@NotNull ItemStack pStack, @Nullable BlockGetter pLevel, @NotNull List<Component> pTooltip, @NotNull TooltipFlag pFlag) {
        InfinityButtonsUtil.tooltip(pTooltip, "letter_button");
        super.appendHoverText(pStack, pLevel, pTooltip, pFlag);
    }
}
