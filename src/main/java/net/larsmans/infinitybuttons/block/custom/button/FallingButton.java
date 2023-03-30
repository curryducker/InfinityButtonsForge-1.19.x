package net.larsmans.infinitybuttons.block.custom.button;

import net.larsmans.infinitybuttons.InfinityButtonsUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.List;

public class FallingButton extends AbstractSmallButton{

    public boolean gravel;

    public FallingButton(boolean gravel, Properties properties, boolean large) {
        super(false, large, properties);
        this.gravel = gravel;
    }

    @Override
    public int getPressDuration() {
        return 10;
    }

    @Override
    protected SoundEvent getSoundEvent(boolean pressed) {
        return gravel ? SoundEvents.GRAVEL_BREAK : SoundEvents.SAND_BREAK;
    }

    @Override
    public void tick(BlockState state, ServerLevel worldIn, BlockPos pos, RandomSource rand) {
        if (state.getValue(PRESSED)) {
            worldIn.setBlock(pos, state.setValue(PRESSED, false), 3);
            this.updateNeighbors(state, worldIn, pos);
            this.playSound(null, worldIn, pos, false);
            worldIn.destroyBlock(pos, false);
        }
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack pStack, @org.jetbrains.annotations.Nullable BlockGetter pLevel, List<Component> pTooltip, TooltipFlag pFlag) {
        InfinityButtonsUtil.tooltip(pTooltip, "falling_button");
        super.appendHoverText(pStack, pLevel, pTooltip, pFlag);
    }
}
