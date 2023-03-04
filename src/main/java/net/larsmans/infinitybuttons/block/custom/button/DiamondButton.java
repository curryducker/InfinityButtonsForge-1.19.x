package net.larsmans.infinitybuttons.block.custom.button;

import net.larsmans.infinitybuttons.block.InfinityButtonsUtil;
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

    private final boolean large;

    public DiamondButton(Properties properties, boolean large) {
        super(false, large, properties);
        this.large = large;
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
    public void animateTick(BlockState state, Level world, BlockPos pos, RandomSource random) {
        if (config.diamondParticles && random.nextInt(3) == 0) {
            if (large) {
                switch (state.getValue(FACE)) {
                    case FLOOR -> world.addParticle(ParticleTypes.SCRAPE,
                            (double) pos.getX() + 0.1875 + (double) random.nextFloat() * 0.625,
                            (double) pos.getY() + 0.125 + (double) random.nextFloat() * 0.0625,
                            (double) pos.getZ() + 0.1875 + (double) random.nextFloat() * 0.625,
                            0, 0, 0);
                    case WALL -> {
                        switch (state.getValue(FACING)) {
                            case NORTH -> world.addParticle(ParticleTypes.SCRAPE,
                                    (double) pos.getX() + 0.1875 + (double) random.nextFloat() * 0.625,
                                    (double) pos.getY() + 0.1875 + (double) random.nextFloat() * 0.625,
                                    (double) pos.getZ() + 0.8125 + (double) random.nextFloat() * 0.0625,
                                    0, 0, 0);

                            case EAST -> world.addParticle(ParticleTypes.SCRAPE,
                                    (double) pos.getX() + 0.125 + (double) random.nextFloat() * 0.0625,
                                    (double) pos.getY() + 0.1875 + (double) random.nextFloat() * 0.625,
                                    (double) pos.getZ() + 0.1875 + (double) random.nextFloat() * 0.625,
                                    0, 0, 0);

                            case SOUTH -> world.addParticle(ParticleTypes.SCRAPE,
                                    (double) pos.getX() + 0.1875 + (double) random.nextFloat() * 0.625,
                                    (double) pos.getY() + 0.1875 + (double) random.nextFloat() * 0.625,
                                    (double) pos.getZ() + 0.125 + (double) random.nextFloat() * 0.0625,
                                    0, 0, 0);

                            case WEST -> world.addParticle(ParticleTypes.SCRAPE,
                                    (double) pos.getX() + 0.8125 + (double) random.nextFloat() * 0.0625,
                                    (double) pos.getY() + 0.1875 + (double) random.nextFloat() * 0.625,
                                    (double) pos.getZ() + 0.1875 + (double) random.nextFloat() * 0.625,
                                    0, 0, 0);
                        }
                    }
                    case CEILING -> world.addParticle(ParticleTypes.SCRAPE,
                            (double) pos.getX() + 0.1875 + (double) random.nextFloat() * 0.625,
                            (double) pos.getY() + 0.8125 + (double) random.nextFloat() * 0.0625,
                            (double) pos.getZ() + 0.1875 + (double) random.nextFloat() * 0.625,
                            0, 0, 0);
                }
            } else {
                switch (state.getValue(FACE)) {
                    case FLOOR -> {
                        switch (state.getValue(FACING)) {
                            case NORTH, SOUTH -> world.addParticle(ParticleTypes.SCRAPE,
                                    (double) pos.getX() + 0.25 + (double) random.nextFloat() * 0.5,
                                    (double) pos.getY() + 0.125 + (double) random.nextFloat() * 0.0625,
                                    (double) pos.getZ() + 0.3125 + (double) random.nextFloat() * 0.375,
                                    0, 0, 0);
                            case EAST, WEST -> world.addParticle(ParticleTypes.SCRAPE,
                                    (double) pos.getX() + 0.3125 + (double) random.nextFloat() * 0.375,
                                    (double) pos.getY() + 0.125 + (double) random.nextFloat() * 0.0625,
                                    (double) pos.getZ() + 0.25 + (double) random.nextFloat() * 0.5,
                                    0, 0, 0);
                        }
                    }
                    case WALL -> {
                        switch (state.getValue(FACING)) {
                            case NORTH -> world.addParticle(ParticleTypes.SCRAPE,
                                    (double) pos.getX() + 0.25 + (double) random.nextFloat() * 0.5,
                                    (double) pos.getY() + 0.3125 + (double) random.nextFloat() * 0.375,
                                    (double) pos.getZ() + 0.8125 + (double) random.nextFloat() * 0.0625,
                                    0, 0, 0);

                            case EAST -> world.addParticle(ParticleTypes.SCRAPE,
                                    (double) pos.getX() + 0.125 + (double) random.nextFloat() * 0.0625,
                                    (double) pos.getY() + 0.3125 + (double) random.nextFloat() * 0.375,
                                    (double) pos.getZ() + 0.25 + (double) random.nextFloat() * 0.5,
                                    0, 0, 0);

                            case SOUTH -> world.addParticle(ParticleTypes.SCRAPE,
                                    (double) pos.getX() + 0.25 + (double) random.nextFloat() * 0.5,
                                    (double) pos.getY() + 0.3125 + (double) random.nextFloat() * 0.375,
                                    (double) pos.getZ() + 0.125 + (double) random.nextFloat() * 0.0625,
                                    0, 0, 0);

                            case WEST -> world.addParticle(ParticleTypes.SCRAPE,
                                    (double) pos.getX() + 0.8125 + (double) random.nextFloat() * 0.0625,
                                    (double) pos.getY() + 0.3125 + (double) random.nextFloat() * 0.375,
                                    (double) pos.getZ() + 0.25 + (double) random.nextFloat() * 0.5,
                                    0, 0, 0);
                        }
                    }
                    case CEILING -> {
                        switch (state.getValue(FACING)) {
                            case NORTH, SOUTH -> world.addParticle(ParticleTypes.SCRAPE,
                                    (double) pos.getX() + 0.25 + (double) random.nextFloat() * 0.5,
                                    (double) pos.getY() + 0.8125 + (double) random.nextFloat() * 0.0625,
                                    (double) pos.getZ() + 0.3125 + (double) random.nextFloat() * 0.375,
                                    0, 0, 0);
                            case EAST, WEST -> world.addParticle(ParticleTypes.SCRAPE,
                                    (double) pos.getX() + 0.3125 + (double) random.nextFloat() * 0.375,
                                    (double) pos.getY() + 0.8125 + (double) random.nextFloat() * 0.0625,
                                    (double) pos.getZ() + 0.25 + (double) random.nextFloat() * 0.5,
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
        InfinityButtonsUtil.tooltip(pTooltip, "diamond_button");
        super.appendHoverText(pStack, pLevel, pTooltip, pFlag);
    }
}
