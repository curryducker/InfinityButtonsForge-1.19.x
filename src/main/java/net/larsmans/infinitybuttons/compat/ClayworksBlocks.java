package net.larsmans.infinitybuttons.compat;

import net.larsmans.infinitybuttons.InfinityButtons;
import net.larsmans.infinitybuttons.InfinityButtonsUtil;
import net.larsmans.infinitybuttons.block.InfinityButtonsBlocks;
import net.larsmans.infinitybuttons.block.custom.secretbutton.compat.ChiseledCompatSecretButton;
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

public class ClayworksBlocks {

    public static final RegistryObject<Block> CHISELED_BRICK_SECRET_BUTTON = registerBlock("chiseled_brick_secret_button",
            () -> new ChiseledCompatSecretButton(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_ORANGE).strength(2.0f, 6.0f).noOcclusion().sound(SoundType.STONE).requiresCorrectToolForDrops(), byName("chiseled_bricks")));

    public static final RegistryObject<Block> TERRACOTTA_BRICK_SECRET_BUTTON = registerBlock("terracotta_brick_secret_button",
            () -> new FullCompatBrickSecretButton(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_ORANGE).strength(1.25f, 4.2f).noOcclusion().requiresCorrectToolForDrops(), byName("terracotta_bricks")));

    public static final RegistryObject<Block> WHITE_TERRACOTTA_BRICK_SECRET_BUTTON = registerTerracotta("white", MaterialColor.TERRACOTTA_WHITE);
    public static final RegistryObject<Block> LIGHT_GRAY_TERRACOTTA_BRICK_SECRET_BUTTON = registerTerracotta("light_gray", MaterialColor.TERRACOTTA_LIGHT_GRAY);
    public static final RegistryObject<Block> GRAY_TERRACOTTA_BRICK_SECRET_BUTTON = registerTerracotta("gray", MaterialColor.TERRACOTTA_GRAY);
    public static final RegistryObject<Block> BLACK_TERRACOTTA_BRICK_SECRET_BUTTON = registerTerracotta("black", MaterialColor.TERRACOTTA_BLACK);
    public static final RegistryObject<Block> BROWN_TERRACOTTA_BRICK_SECRET_BUTTON = registerTerracotta("brown", MaterialColor.TERRACOTTA_BROWN);
    public static final RegistryObject<Block> RED_TERRACOTTA_BRICK_SECRET_BUTTON = registerTerracotta("red", MaterialColor.TERRACOTTA_RED);
    public static final RegistryObject<Block> ORANGE_TERRACOTTA_BRICK_SECRET_BUTTON = registerTerracotta("orange", MaterialColor.TERRACOTTA_ORANGE);
    public static final RegistryObject<Block> YELLOW_TERRACOTTA_BRICK_SECRET_BUTTON = registerTerracotta("yellow", MaterialColor.TERRACOTTA_YELLOW);
    public static final RegistryObject<Block> LIME_TERRACOTTA_BRICK_SECRET_BUTTON = registerTerracotta("lime", MaterialColor.TERRACOTTA_LIGHT_GREEN);
    public static final RegistryObject<Block> GREEN_TERRACOTTA_BRICK_SECRET_BUTTON = registerTerracotta("green", MaterialColor.TERRACOTTA_GREEN);
    public static final RegistryObject<Block> CYAN_TERRACOTTA_BRICK_SECRET_BUTTON = registerTerracotta("cyan", MaterialColor.TERRACOTTA_CYAN);
    public static final RegistryObject<Block> LIGHT_BLUE_TERRACOTTA_BRICK_SECRET_BUTTON = registerTerracotta("light_blue", MaterialColor.TERRACOTTA_LIGHT_BLUE);
    public static final RegistryObject<Block> BLUE_TERRACOTTA_BRICK_SECRET_BUTTON = registerTerracotta("blue", MaterialColor.TERRACOTTA_BLUE);
    public static final RegistryObject<Block> PURPLE_TERRACOTTA_BRICK_SECRET_BUTTON = registerTerracotta("purple", MaterialColor.TERRACOTTA_PURPLE);
    public static final RegistryObject<Block> MAGENTA_TERRACOTTA_BRICK_SECRET_BUTTON = registerTerracotta("magenta", MaterialColor.TERRACOTTA_MAGENTA);
    public static final RegistryObject<Block> PINK_TERRACOTTA_BRICK_SECRET_BUTTON = registerTerracotta("pink", MaterialColor.TERRACOTTA_PINK);

    public static final RegistryObject<Block> CHISELED_TERRACOTTA_BRICK_SECRET_BUTTON = registerBlock("chiseled_terracotta_brick_secret_button",
            () -> new ChiseledCompatSecretButton(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_ORANGE).strength(1.25f, 4.2f).noOcclusion().requiresCorrectToolForDrops(), byName("chiseled_terracotta_bricks")));

    public static final RegistryObject<Block> CHISELED_WHITE_TERRACOTTA_BRICK_SECRET_BUTTON = registerChiseledTerracotta("white", MaterialColor.TERRACOTTA_WHITE);
    public static final RegistryObject<Block> CHISELED_LIGHT_GRAY_TERRACOTTA_BRICK_SECRET_BUTTON = registerChiseledTerracotta("light_gray", MaterialColor.TERRACOTTA_LIGHT_GRAY);
    public static final RegistryObject<Block> CHISELED_GRAY_TERRACOTTA_BRICK_SECRET_BUTTON = registerChiseledTerracotta("gray", MaterialColor.TERRACOTTA_GRAY);
    public static final RegistryObject<Block> CHISELED_BLACK_TERRACOTTA_BRICK_SECRET_BUTTON = registerChiseledTerracotta("black", MaterialColor.TERRACOTTA_BLACK);
    public static final RegistryObject<Block> CHISELED_BROWN_TERRACOTTA_BRICK_SECRET_BUTTON = registerChiseledTerracotta("brown", MaterialColor.TERRACOTTA_BROWN);
    public static final RegistryObject<Block> CHISELED_RED_TERRACOTTA_BRICK_SECRET_BUTTON = registerChiseledTerracotta("red", MaterialColor.TERRACOTTA_RED);
    public static final RegistryObject<Block> CHISELED_ORANGE_TERRACOTTA_BRICK_SECRET_BUTTON = registerChiseledTerracotta("orange", MaterialColor.TERRACOTTA_ORANGE);
    public static final RegistryObject<Block> CHISELED_YELLOW_TERRACOTTA_BRICK_SECRET_BUTTON = registerChiseledTerracotta("yellow", MaterialColor.TERRACOTTA_YELLOW);
    public static final RegistryObject<Block> CHISELED_LIME_TERRACOTTA_BRICK_SECRET_BUTTON = registerChiseledTerracotta("lime", MaterialColor.TERRACOTTA_LIGHT_GREEN);
    public static final RegistryObject<Block> CHISELED_GREEN_TERRACOTTA_BRICK_SECRET_BUTTON = registerChiseledTerracotta("green", MaterialColor.TERRACOTTA_GREEN);
    public static final RegistryObject<Block> CHISELED_CYAN_TERRACOTTA_BRICK_SECRET_BUTTON = registerChiseledTerracotta("cyan", MaterialColor.TERRACOTTA_CYAN);
    public static final RegistryObject<Block> CHISELED_LIGHT_BLUE_TERRACOTTA_BRICK_SECRET_BUTTON = registerChiseledTerracotta("light_blue", MaterialColor.TERRACOTTA_LIGHT_BLUE);
    public static final RegistryObject<Block> CHISELED_BLUE_TERRACOTTA_BRICK_SECRET_BUTTON = registerChiseledTerracotta("blue", MaterialColor.TERRACOTTA_BLUE);
    public static final RegistryObject<Block> CHISELED_PURPLE_TERRACOTTA_BRICK_SECRET_BUTTON = registerChiseledTerracotta("purple", MaterialColor.TERRACOTTA_PURPLE);
    public static final RegistryObject<Block> CHISELED_MAGENTA_TERRACOTTA_BRICK_SECRET_BUTTON = registerChiseledTerracotta("magenta", MaterialColor.TERRACOTTA_MAGENTA);
    public static final RegistryObject<Block> CHISELED_PINK_TERRACOTTA_BRICK_SECRET_BUTTON = registerChiseledTerracotta("pink", MaterialColor.TERRACOTTA_PINK);

    /**
     * Methods
     */

    private static Block byName(String block) {
        return ForgeRegistries.BLOCKS.getValue(new ResourceLocation("clayworks", block));
    }

    private static RegistryObject<Block> registerTerracotta(String color, MaterialColor materialColor) {
        return registerBlock(color + "_terracotta_brick_secret_button",
                () -> new FullCompatBrickSecretButton(BlockBehaviour.Properties.of(Material.STONE, materialColor).strength(1.25f, 4.2f).noOcclusion().requiresCorrectToolForDrops(), byName(color + "_terracotta_bricks")));
    }

    private static RegistryObject<Block> registerChiseledTerracotta(String color, MaterialColor materialColor) {
        return registerBlock("chiseled_" + color + "_terracotta_brick_secret_button",
                () -> new ChiseledCompatSecretButton(BlockBehaviour.Properties.of(Material.STONE, materialColor).strength(1.25f, 4.2f).noOcclusion().requiresCorrectToolForDrops(), byName("chiseled_" + color + "_terracotta_bricks")));
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
        InfinityButtons.LOGGER.debug("Registering Clayworks Compat Blocks for Infinity Buttons");
    }
}
