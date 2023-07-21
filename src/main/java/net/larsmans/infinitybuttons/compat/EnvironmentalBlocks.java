package net.larsmans.infinitybuttons.compat;

import net.larsmans.infinitybuttons.InfinityButtons;
import net.larsmans.infinitybuttons.InfinityButtonsUtil;
import net.larsmans.infinitybuttons.block.InfinityButtonsBlocks;
import net.larsmans.infinitybuttons.block.custom.secretbutton.compat.ChiseledCompatSecretButton;
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
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class EnvironmentalBlocks {

    public static final RegistryObject<Block> WILLOW_BOOKSHELF_SECRET_BUTTON = registerBookshelf("willow");
    public static final RegistryObject<Block> CHERRY_BOOKSHELF_SECRET_BUTTON = registerBookshelf("cherry");
    public static final RegistryObject<Block> WISTERIA_BOOKSHELF_SECRET_BUTTON = registerBookshelf("wisteria");

    public static final RegistryObject<Block> CHISELED_MUD_BRICK_SECRET_BUTTON = registerBlock("chiseled_mud_brick_secret_button",
            () -> new ChiseledCompatSecretButton(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_BROWN).strength(1.5f, 2.5f).noOcclusion().sound(SoundType.STONE).requiresCorrectToolForDrops(), byName("chiseled_mud_bricks")));

    /**
     * Methods
     */

    private static Block byName(String block) {
        return ForgeRegistries.BLOCKS.getValue(new ResourceLocation("environmental", block));
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
        RegistryObject<Item> register = InfinityButtonsItems.ITEMS.register(name, () -> new BlockItem(block.get(),
                new Item.Properties().tab(InfinityButtonsItemGroup.INFINITYBUTTONS)));
        InfinityButtonsUtil.REGISTRY_FOR_TAB.add(register);
    }

    public static void registerCompatBlocks() {
        InfinityButtons.LOGGER.debug("Registering Environmental Compat Blocks for Infinity Buttons");
    }
}
