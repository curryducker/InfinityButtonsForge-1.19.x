package net.larsmans.infinitybuttons.compat;

import net.larsmans.infinitybuttons.InfinityButtons;
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

public class AtmosphericBlocks {

    public static final RegistryObject<Block> ROSEWOOD_BOOKSHELF_SECRET_BUTTON = registerBookshelf("rosewood");
    public static final RegistryObject<Block> MORADO_BOOKSHELF_SECRET_BUTTON = registerBookshelf("morado");
    public static final RegistryObject<Block> YUCCA_BOOKSHELF_SECRET_BUTTON = registerBookshelf("yucca");
    public static final RegistryObject<Block> KOUSA_BOOKSHELF_SECRET_BUTTON = registerBookshelf("kousa");
    public static final RegistryObject<Block> ASPEN_BOOKSHELF_SECRET_BUTTON = registerBookshelf("aspen");
    public static final RegistryObject<Block> GRIMWOOD_BOOKSHELF_SECRET_BUTTON = registerBookshelf("grimwood");

    /**
     * Methods
     */

    private static Block byName(String block) {
        return ForgeRegistries.BLOCKS.getValue(new ResourceLocation("atmospheric", block));
    }

    private static RegistryObject<Block> registerBookshelf(String wood) {
        return registerBlock(wood + "_bookshelf_secret_button",
                () -> new CompatBookshelfSecretButton(BlockBehaviour.Properties.of(Material.WOOD).strength(1.5f).noOcclusion().sound(SoundType.WOOD), byName(wood + "_bookshelf")));
    }

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = InfinityButtonsBlocks.BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block) {
        InfinityButtonsItems.ITEMS.register(name, () -> new BlockItem(block.get(),
                new Item.Properties().tab(InfinityButtonsItemGroup.INFINITYBUTTONS)));
    }

    public static void registerCompatBlocks() {
        InfinityButtons.LOGGER.debug("Registering Atmospheric Compat Blocks for Infinity Buttons");
    }
}
