package net.larsmans.infinitybuttons.particle.custom;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class DiamondSparkleParticle extends TextureSheetParticle {

    protected DiamondSparkleParticle(ClientLevel world, double x, double y, double z, SpriteSet sprites) {
        super(world, x, y, z);
        this.setPower(0.96f);
        this.speedUpWhenYMotionIsBlocked = true;
        this.quadSize *= 0.75F;
        this.hasPhysics = false;
        this.setSpriteFromAge(sprites);
    }

    @Override
    public void tick() {
        super.tick();
        float fadeInAge = getLifetime() * 0.2f;
        float fadeOutAge = getLifetime() * 0.6f;
        if (age <= fadeInAge) {
            setAlpha(age / fadeInAge);
        } else if (age >= fadeOutAge) {
            setAlpha(1 - (age - fadeOutAge) / fadeOutAge);
        } else {
            setAlpha(1);
        }
    }

    @Override
    public int getLightColor(float partialTick) {
        return LightTexture.FULL_BLOCK;
    }

    @Override
    public @NotNull ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    @OnlyIn(Dist.CLIENT)
    public static class DiamondSparkleProvider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprite;

        public DiamondSparkleProvider(SpriteSet sprites) {
            this.sprite = sprites;
        }

        @Override
        public Particle createParticle(@NotNull SimpleParticleType typeIn, @NotNull ClientLevel worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            DiamondSparkleParticle glowParticle = new DiamondSparkleParticle(worldIn, x, y, z, this.sprite);
            glowParticle.setLifetime(worldIn.getRandom().nextInt(30) + 10);
            glowParticle.setAlpha(0);
            return glowParticle;
        }
    }
}
