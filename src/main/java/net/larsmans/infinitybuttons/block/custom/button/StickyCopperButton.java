package net.larsmans.infinitybuttons.block.custom.button;

import net.larsmans.infinitybuttons.InfinityButtonsUtil;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;

public class StickyCopperButton extends AbstractSmallButton {

    public StickyCopperButton(Properties properties, boolean large) {
        super(false, large, properties);
    }

    @Override
    public int getPressDuration() {
        return 0;
    }

    @Override
    public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
        ItemStack itemStack = player.getItemInHand(handIn);
        if (itemStack.getItem() instanceof AxeItem) {
            return WeatheringButton.getUnsticky(state).map((waxedBlockState) -> {
                if (player instanceof ServerPlayer) {
                    CriteriaTriggers.ITEM_USED_ON_BLOCK.trigger((ServerPlayer) player, pos, itemStack);
                }
                if (!player.getAbilities().instabuild) {
                    itemStack.hurtAndBreak(1, player, (entity) -> entity.broadcastBreakEvent(player.getUsedItemHand()));
                }
                worldIn.setBlock(pos, waxedBlockState.setValue(PRESSED, false), Block.UPDATE_ALL_IMMEDIATE);
                worldIn.levelEvent(player, 3004, pos, 0);
                worldIn.playSound(player, pos, SoundEvents.AXE_WAX_OFF, SoundSource.BLOCKS, 1.0f, 1.0f);
                return InteractionResult.sidedSuccess(worldIn.isClientSide);
            }).orElse(InteractionResult.PASS);
        } else {
            if (state.getValue(PRESSED)) {
                this.unpowerBlock(state, worldIn, pos);
                this.playSound(player, worldIn, pos, false);
            } else {
                this.powerBlock(state, worldIn, pos);
                this.playSound(player, worldIn, pos, true);
            }
        }
        return InteractionResult.sidedSuccess(worldIn.isClientSide);
    }

    @Override
    public void powerBlock(BlockState state, Level world, BlockPos pos) {
        world.setBlock(pos, state.setValue(PRESSED, true), 3);
        this.updateNeighbors(state, world, pos);
    }

    public void unpowerBlock(BlockState state, Level world, BlockPos pos) {
        world.setBlock(pos, state.setValue(PRESSED, false), 3);
        this.updateNeighbors(state, world, pos);
    }

    @Override
    protected void playSound(@Nullable Player playerIn, LevelAccessor worldIn, BlockPos pos, boolean hitByArrow) {
        worldIn.playSound(hitByArrow ? playerIn : null, pos, this.getSoundEvent(hitByArrow), SoundSource.BLOCKS, 1F, hitByArrow ? 0.6F : 0.5F);
    }

    @Override
    protected SoundEvent getSoundEvent(boolean isOn) {
        return SoundEvents.COPPER_BREAK;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack pStack, @org.jetbrains.annotations.Nullable BlockGetter pLevel, List<Component> pTooltip, TooltipFlag pFlag) {
        InfinityButtonsUtil.tooltip(pTooltip, "sticky_copper_button");
        super.appendHoverText(pStack, pLevel, pTooltip, pFlag);
    }
}
