package net.larsmans.infinitybuttons.block.custom.largebutton;

import me.shedaniel.autoconfig.AutoConfig;
import net.larsmans.infinitybuttons.InfinityButtonsConfig;
import net.larsmans.infinitybuttons.block.custom.button.DiamondButton;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class DiamondLargeButton extends DiamondButton {
    InfinityButtonsConfig config = AutoConfig.getConfigHolder(InfinityButtonsConfig.class).getConfig();

    public DiamondLargeButton(Properties properties) {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        return LargeButtonShape.outlineShape(state);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState stateIn, Level worldIn, BlockPos pos, RandomSource rand) {
        if (config.diamond_particles) {
            if (rand.nextInt(3) == 0) {
                switch (stateIn.getValue(FACE)) {
                    case FLOOR -> worldIn.addParticle(ParticleTypes.SCRAPE,
                            (double) pos.getX() + 0.1875 + (double) rand.nextFloat() * 0.625,
                            (double) pos.getY() + 0.125 + (double) rand.nextFloat() * 0.0625,
                            (double) pos.getZ() + 0.1875 + (double) rand.nextFloat() * 0.625,
                            0, 0, 0);
                    case WALL -> {
                        switch (stateIn.getValue(FACING)) {
                            case NORTH -> worldIn.addParticle(ParticleTypes.SCRAPE,
                                    (double) pos.getX() + 0.1875 + (double) rand.nextFloat() * 0.625,
                                    (double) pos.getY() + 0.1875 + (double) rand.nextFloat() * 0.625,
                                    (double) pos.getZ() + 0.8125 + (double) rand.nextFloat() * 0.0625,
                                    0, 0, 0);

                            case EAST -> worldIn.addParticle(ParticleTypes.SCRAPE,
                                    (double) pos.getX() + 0.125 + (double) rand.nextFloat() * 0.0625,
                                    (double) pos.getY() + 0.1875 + (double) rand.nextFloat() * 0.625,
                                    (double) pos.getZ() + 0.1875 + (double) rand.nextFloat() * 0.625,
                                    0, 0, 0);

                            case SOUTH -> worldIn.addParticle(ParticleTypes.SCRAPE,
                                    (double) pos.getX() + 0.1875 + (double) rand.nextFloat() * 0.625,
                                    (double) pos.getY() + 0.1875 + (double) rand.nextFloat() * 0.625,
                                    (double) pos.getZ() + 0.125 + (double) rand.nextFloat() * 0.0625,
                                    0, 0, 0);

                            case WEST -> worldIn.addParticle(ParticleTypes.SCRAPE,
                                    (double) pos.getX() + 0.8125 + (double) rand.nextFloat() * 0.0625,
                                    (double) pos.getY() + 0.1875 + (double) rand.nextFloat() * 0.625,
                                    (double) pos.getZ() + 0.1875 + (double) rand.nextFloat() * 0.625,
                                    0, 0, 0);
                        }
                    }
                    case CEILING -> worldIn.addParticle(ParticleTypes.SCRAPE,
                            (double) pos.getX() + 0.1875 + (double) rand.nextFloat() * 0.625,
                            (double) pos.getY() + 0.8125 + (double) rand.nextFloat() * 0.0625,
                            (double) pos.getZ() + 0.1875 + (double) rand.nextFloat() * 0.625,
                            0, 0, 0);
                }
            }
        }
    }
}