package net.larsmans.infinitybuttons.item;

import net.larsmans.infinitybuttons.InfinityButtons;
import net.larsmans.infinitybuttons.block.InfinityButtonsBlocks;
import net.larsmans.infinitybuttons.item.custom.SafeEmergencyButtonItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.StandingAndWallBlockItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class InfinityButtonsItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, InfinityButtons.MOD_ID);

    /**
     * Torches
     */

    public static final RegistryObject<Item> TORCH_BUTTON = registerItem("torch_button",
            () -> new StandingAndWallBlockItem(InfinityButtonsBlocks.TORCH_BUTTON.get(), InfinityButtonsBlocks.WALL_TORCH_BUTTON.get(), new Item.Properties().tab(InfinityButtonsItemGroup.INFINITYBUTTONS)));

    public static final RegistryObject<Item> TORCH_LEVER = registerItem("torch_lever",
            () -> new StandingAndWallBlockItem(InfinityButtonsBlocks.TORCH_LEVER.get(), InfinityButtonsBlocks.WALL_TORCH_LEVER.get(), new Item.Properties().tab(InfinityButtonsItemGroup.INFINITYBUTTONS)));

    public static final RegistryObject<Item> SOUL_TORCH_BUTTON = registerItem("soul_torch_button",
            () -> new StandingAndWallBlockItem(InfinityButtonsBlocks.SOUL_TORCH_BUTTON.get(), InfinityButtonsBlocks.SOUL_WALL_TORCH_BUTTON.get(), new Item.Properties().tab(InfinityButtonsItemGroup.INFINITYBUTTONS)));

    public static final RegistryObject<Item> SOUL_TORCH_LEVER = registerItem("soul_torch_lever",
            () -> new StandingAndWallBlockItem(InfinityButtonsBlocks.SOUL_TORCH_LEVER.get(), InfinityButtonsBlocks.SOUL_WALL_TORCH_LEVER.get(), new Item.Properties().tab(InfinityButtonsItemGroup.INFINITYBUTTONS)));

    public static final RegistryObject<Item> REDSTONE_TORCH_BUTTON = registerItem("redstone_torch_button",
            () -> new StandingAndWallBlockItem(InfinityButtonsBlocks.REDSTONE_TORCH_BUTTON.get(), InfinityButtonsBlocks.REDSTONE_WALL_TORCH_BUTTON.get(), new Item.Properties().tab(InfinityButtonsItemGroup.INFINITYBUTTONS)));

    public static final RegistryObject<Item> REDSTONE_TORCH_LEVER = registerItem("redstone_torch_lever",
            () -> new StandingAndWallBlockItem(InfinityButtonsBlocks.REDSTONE_TORCH_LEVER.get(), InfinityButtonsBlocks.REDSTONE_WALL_TORCH_LEVER.get(), new Item.Properties().tab(InfinityButtonsItemGroup.INFINITYBUTTONS)));

    public static final RegistryObject<Item> PROPELPLANT_TORCH_BUTTON = registerCompatItem("nethersdelight", "propelplant_torch_button",
            () -> new StandingAndWallBlockItem(InfinityButtonsBlocks.PROPELPLANT_TORCH_BUTTON.get(), InfinityButtonsBlocks.PROPELPLANT_WALL_TORCH_BUTTON.get(), new Item.Properties().tab(InfinityButtonsItemGroup.INFINITYBUTTONSCOMPAT)));

    public static final RegistryObject<Item> PROPELPLANT_TORCH_LEVER = registerCompatItem("nethersdelight", "propelplant_torch_lever",
            () -> new StandingAndWallBlockItem(InfinityButtonsBlocks.PROPELPLANT_TORCH_LEVER.get(), InfinityButtonsBlocks.PROPELPLANT_WALL_TORCH_LEVER.get(), new Item.Properties().tab(InfinityButtonsItemGroup.INFINITYBUTTONSCOMPAT)));

    /**
     * Safe Emergency Buttons
     */

    public static final RegistryObject<Item> SAFE_EMERGENCY_BUTTON = registerItem("safe_emergency_button",
            () -> new SafeEmergencyButtonItem(InfinityButtonsBlocks.SAFE_EMERGENCY_BUTTON.get(), new Item.Properties().tab(InfinityButtonsItemGroup.INFINITYBUTTONS)));

    /**
     * Methods
     */

    private static <T extends Item> RegistryObject<T> registerItem(String name, Supplier<T> item) {
        return ITEMS.register(name, item);
    }

    private static <T extends Item> RegistryObject<T> registerCompatItem(String modid, String name, Supplier<T> item) {
        RegistryObject<T> toReturn = null;
        if (ModList.get().isLoaded(modid)) {
            toReturn = ITEMS.register(name, item);
        }
        return toReturn;
    }

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
