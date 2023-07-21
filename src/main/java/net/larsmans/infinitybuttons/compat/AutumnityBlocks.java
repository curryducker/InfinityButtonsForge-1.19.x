package net.larsmans.infinitybuttons.compat;

import net.larsmans.infinitybuttons.InfinityButtons;
import net.larsmans.infinitybuttons.InfinityButtonsUtil;
import net.larsmans.infinitybuttons.block.InfinityButtonsBlocks;
import net.larsmans.infinitybuttons.block.custom.secretbutton.compat.CompatBookshelfSecretButton;
import net.larsmans.infinitybuttons.item.InfinityButtonsItemGroup;
import net.larsmans.infinitybuttons.item.InfinityButtonsItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class AutumnityBlocks {

    public static final RegistryObject<Block> MAPLE_BOOKSHELF_SECRET_BUTTON = registerBlock("maple_bookshelf_secret_button",
            () -> new CompatBookshelfSecretButton(BlockBehaviour.Properties.of(Material.WOOD).strength(1.5f).noOcclusion().sound(SoundType.WOOD), ForgeRegistries.BLOCKS.getValue(new ResourceLocation("autumnity", "maple_bookshelf"))));

    /**
     * Methods
     */

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = InfinityButtonsBlocks.BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block) {
        RegistryObject<Item> register = InfinityButtonsItems.ITEMS.register(name, () -> new BlockItem(block.get(),
                new Item.Properties().tab(InfinityButtonsItemGroup.INFINITYBUTTONS)));
        InfinityButtonsUtil.REGISTRY_FOR_TAB.add(register);
    }

    public static void registerCompatBlocks() {
        InfinityButtons.LOGGER.debug("Registering Autumnity Compat Blocks for Infinity Buttons");
    }
}
