package net.larsmans.infinitybuttons;

import com.mojang.logging.LogUtils;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;
import net.larsmans.infinitybuttons.block.InfinityButtonsBlocks;
import net.larsmans.infinitybuttons.compat.NethersDelightBlocks;
import net.larsmans.infinitybuttons.compat.NethersDelightItems;
import net.larsmans.infinitybuttons.compat.QuarkBlocks;
import net.larsmans.infinitybuttons.item.InfinityButtonsItems;
import net.larsmans.infinitybuttons.sounds.InfinityButtonsSounds;
import net.minecraft.client.Minecraft;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(InfinityButtons.MOD_ID)
public class InfinityButtons
{
    public static final String MOD_ID = "infinitybuttons";

    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();

    public InfinityButtons()
    {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        InfinityButtonsItems.register(eventBus);
        InfinityButtonsBlocks.register(eventBus);
        InfinityButtonsSounds.register(eventBus);

        if (ModList.get().isLoaded("nethers_delight")) {
            NethersDelightItems.registerCompatItems();
            NethersDelightBlocks.registerCompatBlocks();
        }
        if (ModList.get().isLoaded("quark")){
            QuarkBlocks.registerCompatBlocks();
        }

        AutoConfig.register(InfinityButtonsConfig.class, Toml4jConfigSerializer::new);
        DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> InfinityButtonsConfigMenu::registerConfigMenu);

        // Register the commonSetup method for modloading
        eventBus.addListener(this::commonSetup);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        // Some common setup code
        LOGGER.info("HELLO FROM COMMON SETUP");
        LOGGER.info("DIRT BLOCK >> {}", ForgeRegistries.BLOCKS.getKey(Blocks.DIRT));
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
        // Do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            // Some client setup code
            LOGGER.info("HELLO FROM CLIENT SETUP");
            LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
        }
    }
}
