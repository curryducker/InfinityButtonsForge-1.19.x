package net.larsmans.infinitybuttons.particle;

import net.larsmans.infinitybuttons.InfinityButtons;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class InfinityButtonsParticleTypes {
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES =
            DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, InfinityButtons.MOD_ID);

    public static RegistryObject<SimpleParticleType> DIAMOND_SPARKLE = registerParticleType("diamond_sparkle", true);

    private static RegistryObject<SimpleParticleType> registerParticleType(String name, boolean alwaysShow) {
        return PARTICLE_TYPES.register(name, () -> new SimpleParticleType(alwaysShow));
    }

    public static void register(IEventBus eventBus) {
        PARTICLE_TYPES.register(eventBus);
    }
}
