package net.larsmans.infinitybuttons.block.custom.button;

import net.larsmans.infinitybuttons.advancement.InfinityButtonsTriggers;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

import javax.annotation.Nullable;

public class WaxedCopperButton extends AbstractSmallButton {

    public WaxedCopperButton(Properties properties, boolean large) {
        super(false, large, properties);
    }

    @Override
    protected void playSound(@Nullable Player playerIn, LevelAccessor worldIn, BlockPos pos, boolean hitByArrow) {
        worldIn.playSound(hitByArrow ? playerIn : null, pos, this.getSoundEvent(hitByArrow), SoundSource.BLOCKS, 1F, hitByArrow ? 0.6F : 0.5F);
    }

    @Override
    public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
        ItemStack itemStack = player.getItemInHand(handIn);

        if (itemStack.getItem() instanceof AxeItem && !(this instanceof CopperButton)) {
            return state.getValue(PRESSED) ? InteractionResult.CONSUME : scrapeWax(state, worldIn, pos, player, itemStack);
        } else {
            return (!(itemStack.getItem() instanceof HoneyBottleItem) || this instanceof CopperButton) ? super.use(state, worldIn, pos, player, handIn, hit) : sticky(state, worldIn, pos, player, handIn, itemStack);
        }
    }

    public InteractionResult scrapeWax(BlockState blockState, Level level, BlockPos blockpos, Player player, ItemStack itemStack) {
        return WeatheringButton.getUnwaxed(blockState).map((waxedBlockState) -> {
            if (player instanceof ServerPlayer) {
                CriteriaTriggers.ITEM_USED_ON_BLOCK.trigger((ServerPlayer) player, blockpos, itemStack);
            }
            if (!player.getAbilities().instabuild) {
                itemStack.hurtAndBreak(1, player, (entity) -> entity.broadcastBreakEvent(player.getUsedItemHand()));
            }
            level.setBlock(blockpos, waxedBlockState, Block.UPDATE_ALL_IMMEDIATE);
            level.levelEvent(player, 3004, blockpos, 0);
            level.playSound(player, blockpos, SoundEvents.AXE_WAX_OFF, SoundSource.BLOCKS, 1.0f, 1.0f);
            if (player instanceof ServerPlayer serverPlayer)
                InfinityButtonsTriggers.WAX_OFF_TRIGGER.trigger(serverPlayer);
            return InteractionResult.sidedSuccess(level.isClientSide);
        }).orElse(InteractionResult.sidedSuccess(level.isClientSide));
    }

    public InteractionResult sticky(BlockState blockState, Level level, BlockPos blockpos, Player player, InteractionHand hand, ItemStack itemStack) {
        return WeatheringButton.getSticky(blockState).map((waxedBlockState) -> {
            if (player instanceof ServerPlayer) {
                CriteriaTriggers.ITEM_USED_ON_BLOCK.trigger((ServerPlayer) player, blockpos, itemStack);
            }
            if (!player.getAbilities().instabuild) {
                player.setItemInHand(hand, ItemUtils.createFilledResult(itemStack, player, new ItemStack(Items.GLASS_BOTTLE)));
            }
            level.setBlock(blockpos, waxedBlockState, Block.UPDATE_ALL_IMMEDIATE);
            level.levelEvent(player, 3003, blockpos, 0);
            level.playSound(player, blockpos, SoundEvents.HONEYCOMB_WAX_ON, SoundSource.BLOCKS, 1.0f, 1.0f);
            return InteractionResult.sidedSuccess(level.isClientSide);
        }).orElse(InteractionResult.sidedSuccess(level.isClientSide));
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
