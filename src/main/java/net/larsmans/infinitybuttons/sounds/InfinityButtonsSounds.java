package net.larsmans.infinitybuttons.sounds;

import net.larsmans.infinitybuttons.InfinityButtons;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class InfinityButtonsSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, InfinityButtons.MOD_ID);

    public static RegistryObject<SoundEvent> ALARM = registerSoundEvent("alarm");
    public static RegistryObject<SoundEvent> STONE_SCRAPE = registerSoundEvent("stone_scrape");
    public static RegistryObject<SoundEvent> WOOD_SCRAPE = registerSoundEvent("wood_scrape");
    public static RegistryObject<SoundEvent> DOORBELL = registerSoundEvent("doorbell");

    private static RegistryObject<SoundEvent> registerSoundEvent(String name) {
        return SOUND_EVENTS.register(name, () -> new SoundEvent(new ResourceLocation(InfinityButtons.MOD_ID, name)));
    }

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}
