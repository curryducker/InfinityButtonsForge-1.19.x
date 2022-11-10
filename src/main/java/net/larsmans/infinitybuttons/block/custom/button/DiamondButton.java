package net.larsmans.infinitybuttons.block.custom.button;

import me.shedaniel.autoconfig.AutoConfig;
import net.larsmans.infinitybuttons.InfinityButtonsConfig;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.List;

public class DiamondButton extends AbstractButton{
    InfinityButtonsConfig config = AutoConfig.getConfigHolder(InfinityButtonsConfig.class).getConfig();

    public DiamondButton(Properties properties) {
        super(false, properties);
    }

    @Override
    public int getPressDuration() {
        return 20;
    }

    @Override
    protected SoundEvent getSoundEvent(boolean isOn) {
        return isOn ? SoundEvents.STONE_BUTTON_CLICK_ON : SoundEvents.STONE_BUTTON_CLICK_OFF;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState stateIn, Level worldIn, BlockPos pos, RandomSource rand) {
        if (config.diamond_particles) {
            if (rand.nextInt(3) == 0) {
                switch (stateIn.getValue(FACE)) {
                    case FLOOR -> {
                        switch (stateIn.getValue(FACING)) {
                            case NORTH, SOUTH -> worldIn.addParticle(ParticleTypes.SCRAPE,
                                    (double) pos.getX() + 0.25 + (double)rand.nextFloat() * 0.5,
                                    (double) pos.getY() + 0.125 + (double) rand.nextFloat() * 0.0625,
                                    (double) pos.getZ() + 0.3125 + (double)rand.nextFloat() * 0.375,
                                    0, 0, 0);
                            case EAST, WEST -> worldIn.addParticle(ParticleTypes.SCRAPE,
                                    (double) pos.getX() + 0.3125 + (double)rand.nextFloat() * 0.375,
                                    (double) pos.getY() + 0.125 + (double) rand.nextFloat() * 0.0625,
                                    (double) pos.getZ() + 0.25 + (double)rand.nextFloat() * 0.5,
                                    0, 0, 0);
                        }
                    }
                    case WALL -> {
                        switch (stateIn.getValue(FACING)) {
                            case NORTH -> worldIn.addParticle(ParticleTypes.SCRAPE,
                                    (double)pos.getX() + 0.25 + (double)rand.nextFloat() * 0.5,
                                    (double)pos.getY() + 0.3125 + (double)rand.nextFloat() * 0.375,
                                    (double)pos.getZ() + 0.8125 + (double)rand.nextFloat() * 0.0625,
                                    0, 0, 0);

                            case EAST -> worldIn.addParticle(ParticleTypes.SCRAPE,
                                    (double)pos.getX() + 0.125 + (double)rand.nextFloat() * 0.0625,
                                    (double)pos.getY() + 0.3125 + (double)rand.nextFloat() * 0.375,
                                    (double)pos.getZ() + 0.25 + (double)rand.nextFloat() * 0.5,
                                    0, 0, 0);

                            case SOUTH -> worldIn.addParticle(ParticleTypes.SCRAPE,
                                    (double)pos.getX() + 0.25 + (double)rand.nextFloat() * 0.5,
                                    (double)pos.getY() + 0.3125 + (double)rand.nextFloat() * 0.375,
                                    (double)pos.getZ() + 0.125 + (double)rand.nextFloat() * 0.0625,
                                    0, 0, 0);

                            case WEST -> worldIn.addParticle(ParticleTypes.SCRAPE,
                                    (double)pos.getX() + 0.8125 + (double)rand.nextFloat() * 0.0625,
                                    (double)pos.getY() + 0.3125 + (double)rand.nextFloat() * 0.375,
                                    (double)pos.getZ() + 0.25 + (double)rand.nextFloat() * 0.5,
                                    0, 0, 0);
                        }
                    }
                    case CEILING -> {
                        switch (stateIn.getValue(FACING)) {
                            case NORTH, SOUTH -> worldIn.addParticle(ParticleTypes.SCRAPE,
                                    (double) pos.getX() + 0.25 + (double) rand.nextFloat() * 0.5,
                                    (double) pos.getY() + 0.8125 + (double) rand.nextFloat() * 0.0625,
                                    (double) pos.getZ() + 0.3125 + (double) rand.nextFloat() * 0.375,
                                    0, 0, 0);
                            case EAST, WEST -> worldIn.addParticle(ParticleTypes.SCRAPE,
                                    (double) pos.getX() + 0.3125 + (double) rand.nextFloat() * 0.375,
                                    (double) pos.getY() + 0.8125 + (double) rand.nextFloat() * 0.0625,
                                    (double) pos.getZ() + 0.25 + (double) rand.nextFloat() * 0.5,
                                    0, 0, 0);
                        }
                    }
                }
            }
        }
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack pStack, @org.jetbrains.annotations.Nullable BlockGetter pLevel, List<Component> pTooltip, TooltipFlag pFlag) {
        if (config.tooltips) {
            if(Screen.hasShiftDown()) {
                pTooltip.add(Component.translatable("infinitybuttons.tooltip.diamond_button"));
            } else {
                pTooltip.add(Component.translatable("infinitybuttons.tooltip.hold_shift"));
            }
        }
        super.appendHoverText(pStack, pLevel, pTooltip, pFlag);
    }
}
