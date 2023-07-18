package net.larsmans.infinitybuttons.block.custom.button;

import net.larsmans.infinitybuttons.InfinityButtonsUtil;
import net.larsmans.infinitybuttons.particle.InfinityButtonsParticleTypes;
import net.minecraft.core.BlockPos;
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

public class DiamondButton extends AbstractSmallButton {

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
                    case FLOOR -> addParticle(3, 10, 2, 1, 3, 10, world, pos, random);
                    case WALL -> {
                        switch (state.getValue(FACING)) {
                            case NORTH -> addParticle(3, 10, 3, 10, 13, 1, world, pos, random);
                            case EAST -> addParticle(2, 1, 3, 10, 3, 10, world, pos, random);
                            case SOUTH -> addParticle(3, 10, 3, 10, 2, 1, world, pos, random);
                            case WEST -> addParticle(13, 1, 3, 10, 3, 10, world, pos, random);
                        }
                    }
                    case CEILING -> addParticle(3, 10, 13, 1, 3, 10, world, pos, random);
                }
            } else {
                switch (state.getValue(FACE)) {
                    case FLOOR -> {
                        switch (state.getValue(FACING)) {
                            case NORTH, SOUTH -> addParticle(4, 8, 2, 1, 5, 6, world, pos, random);
                            case EAST, WEST -> addParticle(5, 6, 2, 1, 4, 8, world, pos, random);
                        }
                    }
                    case WALL -> {
                        switch (state.getValue(FACING)) {
                            case NORTH -> addParticle(4, 8, 5, 6, 13, 1, world, pos, random);
                            case EAST -> addParticle(2, 1, 5, 6, 4, 8, world, pos, random);
                            case SOUTH -> addParticle(4, 8, 5, 6, 2, 1, world, pos, random);
                            case WEST -> addParticle(13, 1, 5, 6, 4, 8, world, pos, random);
                        }
                    }
                    case CEILING -> {
                        switch (state.getValue(FACING)) {
                            case NORTH, SOUTH -> addParticle(4, 8, 13, 1, 5, 6, world, pos, random);
                            case EAST, WEST -> addParticle(5, 6, 13, 1, 4, 8, world, pos, random);
                        }
                    }
                }
            }
        }
    }

    public void addParticle(int x1, int x2, int y1, int y2, int z1, int z2, Level world, BlockPos pos, RandomSource random) {
        world.addParticle(InfinityButtonsParticleTypes.DIAMOND_SPARKLE.get(),
                pos.getX() + (double) x1 / 16 + random.nextFloat() * (double) x2 / 16,
                pos.getY() + (double) y1 / 16 + random.nextFloat() * (double) y2 / 16,
                pos.getZ() + (double) z1 / 16 + random.nextFloat() * (double) z2 / 16,
                0, 0, 0);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack pStack, @org.jetbrains.annotations.Nullable BlockGetter pLevel, List<Component> pTooltip, TooltipFlag pFlag) {
        InfinityButtonsUtil.tooltip(pTooltip, "diamond_button");
        super.appendHoverText(pStack, pLevel, pTooltip, pFlag);
    }
}
