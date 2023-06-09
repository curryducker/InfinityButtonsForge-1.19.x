package net.larsmans.infinitybuttons.compat;

import net.larsmans.infinitybuttons.InfinityButtons;
import net.larsmans.infinitybuttons.block.InfinityButtonsBlocks;
import net.larsmans.infinitybuttons.block.custom.secretbutton.compat.BigCompatSecretButton;
import net.larsmans.infinitybuttons.block.custom.secretbutton.compat.ChiseledStonepatBrickSecretButton;
import net.larsmans.infinitybuttons.block.custom.secretbutton.compat.CompatBookshelfSecretButton;
import net.larsmans.infinitybuttons.block.custom.secretbutton.compat.FullCompatBrickSecretButton;
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

public class QuarkBlocks {

    public static final RegistryObject<Block> SPRUCE_BOOKSHELF_SECRET_BUTTON = registerBookshelf("spruce");
    public static final RegistryObject<Block> BIRCH_BOOKSHELF_SECRET_BUTTON = registerBookshelf("birch");
    public static final RegistryObject<Block> JUNGLE_BOOKSHELF_SECRET_BUTTON = registerBookshelf("jungle");
    public static final RegistryObject<Block> ACACIA_BOOKSHELF_SECRET_BUTTON = registerBookshelf("acacia");
    public static final RegistryObject<Block> DARK_OAK_BOOKSHELF_SECRET_BUTTON = registerBookshelf("dark_oak");
    public static final RegistryObject<Block> MANGROVE_BOOKSHELF_SECRET_BUTTON = registerBookshelf("mangrove");
    public static final RegistryObject<Block> CRIMSON_BOOKSHELF_SECRET_BUTTON = registerBookshelf("crimson");
    public static final RegistryObject<Block> WARPED_BOOKSHELF_SECRET_BUTTON = registerBookshelf("warped");
    public static final RegistryObject<Block> AZALEA_BOOKSHELF_SECRET_BUTTON = registerBookshelf("azalea");
    public static final RegistryObject<Block> BLOSSOM_BOOKSHELF_SECRET_BUTTON = registerBookshelf("blossom");

    public static final RegistryObject<Block> PERMAFROST_BRICK_SECRET_BUTTON = registerBlock("permafrost_brick_secret_button",
            () -> new BigCompatSecretButton(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_LIGHT_BLUE).strength(1.5f, 10.0f).noOcclusion().sound(SoundType.STONE).requiresCorrectToolForDrops(), byName("permafrost_bricks")));

    public static final RegistryObject<Block> BLUE_NETHER_BRICK_SECRET_BUTTON = registerBlock("blue_nether_brick_secret_button",
            () -> new FullCompatBrickSecretButton(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.NETHER).strength(2.0f, 6.0f).noOcclusion().sound(SoundType.NETHER_BRICKS).requiresCorrectToolForDrops(), byName("blue_nether_bricks")));

    public static final RegistryObject<Block> POLISHED_GRANITE_BRICK_SECRET_BUTTON = registerPolishedButton("granite", MaterialColor.DIRT);
    public static final RegistryObject<Block> POLISHED_DIORITE_BRICK_SECRET_BUTTON = registerPolishedButton("diorite", MaterialColor.QUARTZ);
    public static final RegistryObject<Block> POLISHED_ANDESITE_BRICK_SECRET_BUTTON = registerPolishedButton("andesite", MaterialColor.STONE);
    public static final RegistryObject<Block> POLISHED_CALCITE_BRICK_SECRET_BUTTON = registerPolishedButton("calcite", MaterialColor.TERRACOTTA_WHITE);
    public static final RegistryObject<Block> POLISHED_DRIPSTONE_BRICK_SECRET_BUTTON = registerPolishedButton("dripstone", MaterialColor.TERRACOTTA_BROWN);
    public static final RegistryObject<Block> POLISHED_TUFF_BRICK_SECRET_BUTTON = registerPolishedButton("tuff", MaterialColor.TERRACOTTA_GRAY);
    public static final RegistryObject<Block> POLISHED_LIMESTONE_BRICK_SECRET_BUTTON = registerPolishedButton("limestone", MaterialColor.STONE);
    public static final RegistryObject<Block> POLISHED_JASPER_BRICK_SECRET_BUTTON = registerPolishedButton("jasper", MaterialColor.TERRACOTTA_RED);
    public static final RegistryObject<Block> POLISHED_SHALE_BRICK_SECRET_BUTTON = registerPolishedButton("shale", MaterialColor.ICE);

    public static final RegistryObject<Block> CHISELED_POLISHED_GRANITE_BRICK_SECRET_BUTTON = registerChiseledPolishedButton("granite", MaterialColor.DIRT);
    public static final RegistryObject<Block> CHISELED_POLISHED_DIORITE_BRICK_SECRET_BUTTON = registerChiseledPolishedButton("diorite", MaterialColor.QUARTZ);
    public static final RegistryObject<Block> CHISELED_POLISHED_ANDESITE_BRICK_SECRET_BUTTON = registerChiseledPolishedButton("andesite", MaterialColor.STONE);
    public static final RegistryObject<Block> CHISELED_POLISHED_CALCITE_BRICK_SECRET_BUTTON = registerChiseledPolishedButton("calcite", MaterialColor.TERRACOTTA_WHITE);
    public static final RegistryObject<Block> CHISELED_POLISHED_DRIPSTONE_BRICK_SECRET_BUTTON = registerChiseledPolishedButton("dripstone", MaterialColor.TERRACOTTA_BROWN);
    public static final RegistryObject<Block> CHISELED_POLISHED_TUFF_BRICK_SECRET_BUTTON = registerChiseledPolishedButton("tuff", MaterialColor.TERRACOTTA_GRAY);
    public static final RegistryObject<Block> CHISELED_POLISHED_LIMESTONE_BRICK_SECRET_BUTTON = registerChiseledPolishedButton("limestone", MaterialColor.STONE);
    public static final RegistryObject<Block> CHISELED_POLISHED_JASPER_BRICK_SECRET_BUTTON = registerChiseledPolishedButton("jasper", MaterialColor.TERRACOTTA_RED);
    public static final RegistryObject<Block> CHISELED_POLISHED_SHALE_BRICK_SECRET_BUTTON = registerChiseledPolishedButton("shale", MaterialColor.ICE);

    /**
     * Methods
     */

    private static Block byName(String block) {
        return ForgeRegistries.BLOCKS.getValue(new ResourceLocation("quark", block));
    }

    private static RegistryObject<Block> registerChiseledPolishedButton(String type, MaterialColor materialColor) {
        return registerBlock("chiseled_polished_" + type + "_brick_secret_button", () -> new ChiseledStonepatBrickSecretButton(BlockBehaviour.Properties.of(Material.STONE, materialColor).strength(1.5f, 6.0f).noOcclusion().sound(SoundType.STONE).requiresCorrectToolForDrops(), byName("chiseled_" + type + "_bricks")));
    }

    private static RegistryObject<Block> registerPolishedButton(String type, MaterialColor materialColor) {
        return registerBlock("polished_" + type + "_brick_secret_button", () -> new BigCompatSecretButton(BlockBehaviour.Properties.of(Material.STONE, materialColor).strength(1.5f, 6.0f).noOcclusion().sound(SoundType.STONE).requiresCorrectToolForDrops(), byName(type + "_bricks")));
    }

    private static RegistryObject<Block> registerBookshelf(String wood) {
        return registerBlock(wood + "_bookshelf_secret_button", () -> new CompatBookshelfSecretButton(BlockBehaviour.Properties.of(Material.WOOD).strength(1.5f).noOcclusion().sound(SoundType.WOOD), byName(wood + "_bookshelf")));
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
        InfinityButtons.LOGGER.debug("Registering Quark Compat Blocks for Infinity Buttons");
    }
}
