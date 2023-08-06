package net.larsmans.infinitybuttons.events;

import net.larsmans.infinitybuttons.InfinityButtons;
import net.larsmans.infinitybuttons.particle.InfinityButtonsParticleTypes;
import net.larsmans.infinitybuttons.particle.custom.DiamondSparkleParticle;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = InfinityButtons.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ParticleEvent {
    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public static void registerParticleFactories(final RegisterParticleProvidersEvent event ) {
        event.register(InfinityButtonsParticleTypes.DIAMOND_SPARKLE.get(), DiamondSparkleParticle.DiamondSparkleProvider::new);
    }
}
