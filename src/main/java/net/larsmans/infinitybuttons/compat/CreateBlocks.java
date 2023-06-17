package net.larsmans.infinitybuttons.compat;

import net.larsmans.infinitybuttons.InfinityButtons;
import net.larsmans.infinitybuttons.block.InfinityButtonsBlocks;
import net.larsmans.infinitybuttons.block.custom.secretbutton.compat.BigCompatSecretButton;
import net.larsmans.infinitybuttons.block.custom.secretbutton.compat.FullCompatBrickSecretButton;
import net.larsmans.infinitybuttons.block.custom.secretbutton.compat.TileCompatSecretButton;
import net.larsmans.infinitybuttons.item.InfinityButtonsItemGroup;
import net.larsmans.infinitybuttons.item.InfinityButtonsItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class CreateBlocks {

    public static final RegistryObject<Block> ROSE_QUARTZ_TILE_SECRET_BUTTON = registerBlock("rose_quartz_tile_secret_button",
            () -> new TileCompatSecretButton(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE), byName("rose_quartz_tiles")));
    public static final RegistryObject<Block> SMALL_ROSE_QUARTZ_TILE_SECRET_BUTTON = registerBlock("small_rose_quartz_tile_secret_button",
            () -> new FullCompatBrickSecretButton(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE), byName("small_rose_quartz_tiles")));

    public static final RegistryObject<Block> CUT_GRANITE_BRICK_SECRET_BUTTON = registerCut("granite", Blocks.GRANITE);
    public static final RegistryObject<Block> SMALL_GRANITE_BRICK_SECRET_BUTTON = registerSmall("granite", Blocks.GRANITE);

    public static final RegistryObject<Block> CUT_DIORITE_BRICK_SECRET_BUTTON = registerCut("diorite", Blocks.DIORITE);
    public static final RegistryObject<Block> SMALL_DIORITE_BRICK_SECRET_BUTTON = registerSmall("diorite", Blocks.DIORITE);

    public static final RegistryObject<Block> CUT_ANDESITE_BRICK_SECRET_BUTTON = registerCut("andesite", Blocks.ANDESITE);
    public static final RegistryObject<Block> SMALL_ANDESITE_BRICK_SECRET_BUTTON = registerSmall("andesite", Blocks.ANDESITE);

    public static final RegistryObject<Block> CUT_CALCITE_BRICK_SECRET_BUTTON = registerCut("calcite", Blocks.CALCITE);
    public static final RegistryObject<Block> SMALL_CALCITE_BRICK_SECRET_BUTTON = registerSmall("calcite", Blocks.CALCITE);

    public static final RegistryObject<Block> CUT_DRIPSTONE_BRICK_SECRET_BUTTON = registerCut("dripstone", Blocks.DRIPSTONE_BLOCK);
    public static final RegistryObject<Block> SMALL_DRIPSTONE_BRICK_SECRET_BUTTON = registerSmall("dripstone", Blocks.DRIPSTONE_BLOCK);

    public static final RegistryObject<Block> CUT_DEEPSLATE_BRICK_SECRET_BUTTON = registerCut("deepslate", Blocks.DEEPSLATE);
    public static final RegistryObject<Block> SMALL_DEEPSLATE_BRICK_SECRET_BUTTON = registerSmall("deepslate", Blocks.DEEPSLATE);

    public static final RegistryObject<Block> CUT_TUFF_BRICK_SECRET_BUTTON = registerCut("tuff", Blocks.TUFF);
    public static final RegistryObject<Block> SMALL_TUFF_BRICK_SECRET_BUTTON = registerSmall("tuff", Blocks.TUFF);

    public static final RegistryObject<Block> CUT_ASURINE_BRICK_SECRET_BUTTON = registerCutMineral("asurine", Blocks.DEEPSLATE, MaterialColor.COLOR_BLUE);
    public static final RegistryObject<Block> SMALL_ASURINE_BRICK_SECRET_BUTTON = registerSmallMineral("asurine", Blocks.DEEPSLATE, MaterialColor.COLOR_BLUE);

    public static final RegistryObject<Block> CUT_CRIMSITE_BRICK_SECRET_BUTTON = registerCutMineral("crimsite", Blocks.DEEPSLATE, MaterialColor.COLOR_RED);
    public static final RegistryObject<Block> SMALL_CRIMSITE_BRICK_SECRET_BUTTON = registerSmallMineral("crimsite", Blocks.DEEPSLATE, MaterialColor.COLOR_RED);

    public static final RegistryObject<Block> CUT_LIMESTONE_BRICK_SECRET_BUTTON = registerCutMineral("limestone", Blocks.SANDSTONE, MaterialColor.SAND);
    public static final RegistryObject<Block> SMALL_LIMESTONE_BRICK_SECRET_BUTTON = registerSmallMineral("limestone", Blocks.SANDSTONE, MaterialColor.SAND);

    public static final RegistryObject<Block> CUT_OCHRUM_BRICK_SECRET_BUTTON = registerCutMineral("ochrum", Blocks.CALCITE, MaterialColor.TERRACOTTA_YELLOW);
    public static final RegistryObject<Block> SMALL_OCHRUM_BRICK_SECRET_BUTTON = registerSmallMineral("ochrum", Blocks.CALCITE, MaterialColor.TERRACOTTA_YELLOW);

    public static final RegistryObject<Block> CUT_SCORIA_BRICK_SECRET_BUTTON = registerCut("scoria", BlockBehaviour.Properties.copy(Blocks.BLACKSTONE).color(MaterialColor.COLOR_BROWN));
    public static final RegistryObject<Block> SMALL_SCORIA_BRICK_SECRET_BUTTON = registerSmall("scoria", BlockBehaviour.Properties.copy(Blocks.BLACKSTONE).color(MaterialColor.COLOR_BROWN));

    public static final RegistryObject<Block> CUT_SCORCHIA_BRICK_SECRET_BUTTON = registerCut("scorchia", BlockBehaviour.Properties.copy(Blocks.BLACKSTONE).color(MaterialColor.TERRACOTTA_GRAY));
    public static final RegistryObject<Block> SMALL_SCORCHIA_BRICK_SECRET_BUTTON = registerSmall("scorchia", BlockBehaviour.Properties.copy(Blocks.BLACKSTONE).color(MaterialColor.TERRACOTTA_GRAY));

    public static final RegistryObject<Block> CUT_VERIDIUM_BRICK_SECRET_BUTTON = registerCutMineral("veridium", Blocks.TUFF, MaterialColor.WARPED_NYLIUM);
    public static final RegistryObject<Block> SMALL_VERIDIUM_BRICK_SECRET_BUTTON = registerSmallMineral("veridium", Blocks.TUFF, MaterialColor.WARPED_NYLIUM);

    /**
     * Methods
     */

    private static RegistryObject<Block> registerCutMineral(String type, Block properties, MaterialColor color) {
        return registerCut(type, BlockBehaviour.Properties.copy(properties).destroyTime(1.25F).color(color));
    }

    private static RegistryObject<Block> registerSmallMineral(String type, Block properties, MaterialColor color) {
        return registerSmall(type, BlockBehaviour.Properties.copy(properties).destroyTime(1.25F).color(color));
    }

    private static RegistryObject<Block> registerCut(String type, Block properties) {
        return registerBlock("cut_" + type + "_brick_secret_button",
                () -> new BigCompatSecretButton(BlockBehaviour.Properties.copy(properties), byName("cut_" + type + "_bricks")));
    }

    private static RegistryObject<Block> registerCut(String type, BlockBehaviour.Properties properties) {
        return registerBlock("cut_" + type + "_brick_secret_button",
                () -> new BigCompatSecretButton(properties, byName("cut_" + type + "_bricks")));
    }

    private static RegistryObject<Block> registerSmall(String type, Block properties) {
        return registerBlock("small_" + type + "_brick_secret_button",
                () -> new FullCompatBrickSecretButton(BlockBehaviour.Properties.copy(properties), byName("small_" + type + "_bricks")));
    }

    private static RegistryObject<Block> registerSmall(String type, BlockBehaviour.Properties properties) {
        return registerBlock("small_" + type + "_brick_secret_button",
                () -> new FullCompatBrickSecretButton(properties, byName("small_" + type + "_bricks")));
    }

    private static Block byName(String block) {
        return ForgeRegistries.BLOCKS.getValue(new ResourceLocation("create", block));
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
        InfinityButtons.LOGGER.debug("Registering Create Compat Blocks for Infinity Buttons");
    }
}
