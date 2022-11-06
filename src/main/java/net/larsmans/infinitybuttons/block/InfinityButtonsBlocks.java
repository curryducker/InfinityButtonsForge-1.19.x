package net.larsmans.infinitybuttons.block;

import net.larsmans.infinitybuttons.InfinityButtons;
import net.larsmans.infinitybuttons.block.custom.Doorbell;
import net.larsmans.infinitybuttons.block.custom.DoorbellButton;
import net.larsmans.infinitybuttons.block.custom.emergencybutton.EmergencyButton;
import net.larsmans.infinitybuttons.block.custom.HoglinMountButton;
import net.larsmans.infinitybuttons.block.custom.button.*;
import net.larsmans.infinitybuttons.block.custom.largebutton.*;
import net.larsmans.infinitybuttons.block.custom.secretbutton.*;
import net.larsmans.infinitybuttons.block.custom.torch.*;
import net.larsmans.infinitybuttons.item.InfinityButtonsItemGroup;
import net.larsmans.infinitybuttons.item.InfinityButtonsItems;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class InfinityButtonsBlocks {

    public static final DeferredRegister<Block> BLOCKS
            = DeferredRegister.create(ForgeRegistries.BLOCKS, InfinityButtons.MOD_ID);

    /**
     * Buttons
     */

    public static final RegistryObject<Block> COPPER_BUTTON = registerBlock("copper_button",
            () -> new CopperButton(BlockBehaviour.Properties.of(Material.DECORATION).strength(0.5f).noCollission().sound(SoundType.COPPER)));

    public static final RegistryObject<Block> EXPOSED_COPPER_BUTTON = registerBlock("exposed_copper_button",
            () -> new CopperButton(BlockBehaviour.Properties.of(Material.DECORATION).strength(0.5f).noCollission().sound(SoundType.COPPER)));

    public static final RegistryObject<Block> WEATHERED_COPPER_BUTTON = registerBlock("weathered_copper_button",
            () -> new CopperButton(BlockBehaviour.Properties.of(Material.DECORATION).strength(0.5f).noCollission().sound(SoundType.COPPER)));

    public static final RegistryObject<Block> OXIDIZED_COPPER_BUTTON = registerBlock("oxidized_copper_button",
            () -> new CopperButton(BlockBehaviour.Properties.of(Material.DECORATION).strength(0.5f).noCollission().sound(SoundType.COPPER)));

    public static final RegistryObject<Block> STICKY_COPPER_BUTTON = registerBlock("sticky_copper_button",
            () -> new StickyCopperButton(BlockBehaviour.Properties.of(Material.DECORATION).strength(0.5f).noCollission().sound(SoundType.COPPER)));

    public static final RegistryObject<Block> STICKY_EXPOSED_COPPER_BUTTON = registerBlock("sticky_exposed_copper_button",
            () -> new StickyCopperButton(BlockBehaviour.Properties.of(Material.DECORATION).strength(0.5f).noCollission().sound(SoundType.COPPER)));

    public static final RegistryObject<Block> STICKY_WEATHERED_COPPER_BUTTON = registerBlock("sticky_weathered_copper_button",
            () -> new StickyCopperButton(BlockBehaviour.Properties.of(Material.DECORATION).strength(0.5f).noCollission().sound(SoundType.COPPER)));

    public static final RegistryObject<Block> STICKY_OXIDIZED_COPPER_BUTTON = registerBlock("sticky_oxidized_copper_button",
            () -> new StickyCopperButton(BlockBehaviour.Properties.of(Material.DECORATION).strength(0.5f).noCollission().sound(SoundType.COPPER)));

    public static final RegistryObject<Block> EMERALD_BUTTON = registerBlock("emerald_button",
            () -> new EmeraldButton(BlockBehaviour.Properties.of(Material.DECORATION).strength(0.5f).noCollission().sound(SoundType.METAL)));

    public static final RegistryObject<Block> IRON_BUTTON = registerBlock("iron_button",
            () -> new ArrowButton(BlockBehaviour.Properties.of(Material.DECORATION).strength(0.5f).noCollission().sound(SoundType.METAL)));

    public static final RegistryObject<Block> GOLD_BUTTON = registerBlock("gold_button",
            () -> new ArrowButton(BlockBehaviour.Properties.of(Material.DECORATION).strength(0.5f).noCollission().sound(SoundType.METAL)));

    public static final RegistryObject<Block> DIAMOND_BUTTON = registerBlock("diamond_button",
            () -> new DiamondButton(BlockBehaviour.Properties.of(Material.DECORATION).strength(0.5f).noCollission().sound(SoundType.METAL)));

    public static final RegistryObject<Block> PRISMARINE_BUTTON = registerBlock("prismarine_button",
            () -> new PrismarineButton(BlockBehaviour.Properties.of(Material.DECORATION).strength(0.5f).noCollission().sound(SoundType.STONE)));

    public static final RegistryObject<Block> PRISMARINE_BRICK_BUTTON = registerBlock("prismarine_brick_button",
            () -> new PrismarineButton(BlockBehaviour.Properties.of(Material.DECORATION).strength(0.5f).noCollission().sound(SoundType.STONE)));

    public static final RegistryObject<Block> DARK_PRISMARINE_BUTTON = registerBlock("dark_prismarine_button",
            () -> new PrismarineButton(BlockBehaviour.Properties.of(Material.DECORATION).strength(0.5f).noCollission().sound(SoundType.STONE)));

    public static final RegistryObject<Block> SAND_BUTTON = registerBlock("sand_button",
            () -> new FallingButton(false, BlockBehaviour.Properties.of(Material.DECORATION).strength(0.5f).noCollission().sound(SoundType.SAND)));

    public static final RegistryObject<Block> RED_SAND_BUTTON = registerBlock("red_sand_button",
            () -> new FallingButton(false, BlockBehaviour.Properties.of(Material.DECORATION).strength(0.5f).noCollission().sound(SoundType.SAND)));

    public static final RegistryObject<Block> GRAVEL_BUTTON = registerBlock("gravel_button",
            () -> new FallingButton(true, BlockBehaviour.Properties.of(Material.DECORATION).strength(0.5f).noCollission().sound(SoundType.GRAVEL)));

    /**
     * Large Buttons
     */

    public static final RegistryObject<Block> OAK_LARGE_BUTTON = registerBlock("oak_large_button",
            () -> new WoodenLargeButton(BlockBehaviour.Properties.of(Material.DECORATION).strength(0.5f).noCollission().sound(SoundType.WOOD)));

    public static final RegistryObject<Block> BIRCH_LARGE_BUTTON = registerBlock("birch_large_button",
            () -> new WoodenLargeButton(BlockBehaviour.Properties.of(Material.DECORATION).strength(0.5f).noCollission().sound(SoundType.WOOD)));

    public static final RegistryObject<Block> SPRUCE_LARGE_BUTTON = registerBlock("spruce_large_button",
            () -> new WoodenLargeButton(BlockBehaviour.Properties.of(Material.DECORATION).strength(0.5f).noCollission().sound(SoundType.WOOD)));

    public static final RegistryObject<Block> ACACIA_LARGE_BUTTON = registerBlock("acacia_large_button",
            () -> new WoodenLargeButton(BlockBehaviour.Properties.of(Material.DECORATION).strength(0.5f).noCollission().sound(SoundType.WOOD)));

    public static final RegistryObject<Block> DARK_OAK_LARGE_BUTTON = registerBlock("dark_oak_large_button",
            () -> new WoodenLargeButton(BlockBehaviour.Properties.of(Material.DECORATION).strength(0.5f).noCollission().sound(SoundType.WOOD)));

    public static final RegistryObject<Block> JUNGLE_LARGE_BUTTON = registerBlock("jungle_large_button",
            () -> new WoodenLargeButton(BlockBehaviour.Properties.of(Material.DECORATION).strength(0.5f).noCollission().sound(SoundType.WOOD)));

    public static final RegistryObject<Block> WARPED_LARGE_BUTTON = registerBlock("warped_large_button",
            () -> new WoodenLargeButton(BlockBehaviour.Properties.of(Material.DECORATION).strength(0.5f).noCollission().sound(SoundType.WOOD)));

    public static final RegistryObject<Block> CRIMSON_LARGE_BUTTON = registerBlock("crimson_large_button",
            () -> new WoodenLargeButton(BlockBehaviour.Properties.of(Material.DECORATION).strength(0.5f).noCollission().sound(SoundType.WOOD)));

    public static final RegistryObject<Block> STONE_LARGE_BUTTON = registerBlock("stone_large_button",
            () -> new StoneLargeButton(BlockBehaviour.Properties.of(Material.DECORATION).strength(0.5f).noCollission()));

    public static final RegistryObject<Block> POLISHED_BLACKSTONE_LARGE_BUTTON = registerBlock("polished_blackstone_large_button",
            () -> new StoneLargeButton(BlockBehaviour.Properties.of(Material.DECORATION).strength(0.5f).noCollission()));

    public static final RegistryObject<Block> COPPER_LARGE_BUTTON = registerBlock("copper_large_button",
            () -> new CopperLargeButton(BlockBehaviour.Properties.of(Material.DECORATION).strength(0.5f).noCollission().sound(SoundType.COPPER)));

    public static final RegistryObject<Block> EXPOSED_COPPER_LARGE_BUTTON = registerBlock("exposed_copper_large_button",
            () -> new CopperLargeButton(BlockBehaviour.Properties.of(Material.DECORATION).strength(0.5f).noCollission().sound(SoundType.COPPER)));

    public static final RegistryObject<Block> WEATHERED_COPPER_LARGE_BUTTON = registerBlock("weathered_copper_large_button",
            () -> new CopperLargeButton(BlockBehaviour.Properties.of(Material.DECORATION).strength(0.5f).noCollission().sound(SoundType.COPPER)));

    public static final RegistryObject<Block> OXIDIZED_COPPER_LARGE_BUTTON = registerBlock("oxidized_copper_large_button",
            () -> new CopperLargeButton(BlockBehaviour.Properties.of(Material.DECORATION).strength(0.5f).noCollission().sound(SoundType.COPPER)));

    public static final RegistryObject<Block> STICKY_COPPER_LARGE_BUTTON = registerBlock("sticky_copper_large_button",
            () -> new StickyCopperLargeButton(BlockBehaviour.Properties.of(Material.DECORATION).strength(0.5f).noCollission().sound(SoundType.COPPER)));

    public static final RegistryObject<Block> STICKY_EXPOSED_COPPER_LARGE_BUTTON = registerBlock("sticky_exposed_copper_large_button",
            () -> new StickyCopperLargeButton(BlockBehaviour.Properties.of(Material.DECORATION).strength(0.5f).noCollission().sound(SoundType.COPPER)));

    public static final RegistryObject<Block> STICKY_WEATHERED_COPPER_LARGE_BUTTON = registerBlock("sticky_weathered_copper_large_button",
            () -> new StickyCopperLargeButton(BlockBehaviour.Properties.of(Material.DECORATION).strength(0.5f).noCollission().sound(SoundType.COPPER)));

    public static final RegistryObject<Block> STICKY_OXIDIZED_COPPER_LARGE_BUTTON = registerBlock("sticky_oxidized_copper_large_button",
            () -> new StickyCopperLargeButton(BlockBehaviour.Properties.of(Material.DECORATION).strength(0.5f).noCollission().sound(SoundType.COPPER)));

    public static final RegistryObject<Block> EMERALD_LARGE_BUTTON = registerBlock("emerald_large_button",
            () -> new EmeraldLargeButton(BlockBehaviour.Properties.of(Material.DECORATION).strength(0.5f).noCollission().sound(SoundType.METAL)));

    public static final RegistryObject<Block> IRON_LARGE_BUTTON = registerBlock("iron_large_button",
            () -> new ArrowLargeButton(BlockBehaviour.Properties.of(Material.DECORATION).strength(0.5f).noCollission().sound(SoundType.METAL)));

    public static final RegistryObject<Block> GOLD_LARGE_BUTTON = registerBlock("gold_large_button",
            () -> new ArrowLargeButton(BlockBehaviour.Properties.of(Material.DECORATION).strength(0.5f).noCollission().sound(SoundType.METAL)));

    public static final RegistryObject<Block> DIAMOND_LARGE_BUTTON = registerBlock("diamond_large_button",
            () -> new DiamondLargeButton(BlockBehaviour.Properties.of(Material.DECORATION).strength(0.5f).noCollission().sound(SoundType.METAL)));

    public static final RegistryObject<Block> PRISMARINE_LARGE_BUTTON = registerBlock("prismarine_large_button",
            () -> new PrismarineLargeButton(BlockBehaviour.Properties.of(Material.DECORATION).strength(0.5f).noCollission().sound(SoundType.STONE)));

    public static final RegistryObject<Block> PRISMARINE_BRICK_LARGE_BUTTON = registerBlock("prismarine_brick_large_button",
            () -> new PrismarineLargeButton(BlockBehaviour.Properties.of(Material.DECORATION).strength(0.5f).noCollission().sound(SoundType.STONE)));

    public static final RegistryObject<Block> DARK_PRISMARINE_LARGE_BUTTON = registerBlock("dark_prismarine_large_button",
            () -> new PrismarineLargeButton(BlockBehaviour.Properties.of(Material.DECORATION).strength(0.5f).noCollission().sound(SoundType.STONE)));

    public static final RegistryObject<Block> SAND_LARGE_BUTTON = registerBlock("sand_large_button",
            () -> new FallingLargeButton(false, BlockBehaviour.Properties.of(Material.DECORATION).strength(0.5f).noCollission().sound(SoundType.SAND)));

    public static final RegistryObject<Block> RED_SAND_LARGE_BUTTON = registerBlock("red_sand_large_button",
            () -> new FallingLargeButton(false, BlockBehaviour.Properties.of(Material.DECORATION).strength(0.5f).noCollission().sound(SoundType.SAND)));

    public static final RegistryObject<Block> GRAVEL_LARGE_BUTTON = registerBlock("gravel_large_button",
            () -> new FallingLargeButton(true, BlockBehaviour.Properties.of(Material.DECORATION).strength(0.5f).noCollission().sound(SoundType.GRAVEL)));

    /**
     * Emergency Buttons
     */

    public static final RegistryObject<Block> RED_EMERGENCY_BUTTON = registerBlock("red_emergency_button",
            () -> new EmergencyButton(BlockBehaviour.Properties.of(Material.DECORATION).strength(0.5f).noOcclusion().sound(SoundType.METAL)));

    public static final RegistryObject<Block> ORANGE_EMERGENCY_BUTTON = registerBlock("orange_emergency_button",
            () -> new EmergencyButton(BlockBehaviour.Properties.of(Material.DECORATION).strength(0.5f).noOcclusion().sound(SoundType.METAL)));

    public static final RegistryObject<Block> YELLOW_EMERGENCY_BUTTON = registerBlock("yellow_emergency_button",
            () -> new EmergencyButton(BlockBehaviour.Properties.of(Material.DECORATION).strength(0.5f).noOcclusion().sound(SoundType.METAL)));

    public static final RegistryObject<Block> LIME_EMERGENCY_BUTTON = registerBlock("lime_emergency_button",
            () -> new EmergencyButton(BlockBehaviour.Properties.of(Material.DECORATION).strength(0.5f).noOcclusion().sound(SoundType.METAL)));

    public static final RegistryObject<Block> GREEN_EMERGENCY_BUTTON = registerBlock("green_emergency_button",
            () -> new EmergencyButton(BlockBehaviour.Properties.of(Material.DECORATION).strength(0.5f).noOcclusion().sound(SoundType.METAL)));

    public static final RegistryObject<Block> CYAN_EMERGENCY_BUTTON = registerBlock("cyan_emergency_button",
            () -> new EmergencyButton(BlockBehaviour.Properties.of(Material.DECORATION).strength(0.5f).noOcclusion().sound(SoundType.METAL)));

    public static final RegistryObject<Block> LIGHT_BLUE_EMERGENCY_BUTTON = registerBlock("light_blue_emergency_button",
            () -> new EmergencyButton(BlockBehaviour.Properties.of(Material.DECORATION).strength(0.5f).noOcclusion().sound(SoundType.METAL)));

    public static final RegistryObject<Block> BLUE_EMERGENCY_BUTTON = registerBlock("blue_emergency_button",
            () -> new EmergencyButton(BlockBehaviour.Properties.of(Material.DECORATION).strength(0.5f).noOcclusion().sound(SoundType.METAL)));

    public static final RegistryObject<Block> PURPLE_EMERGENCY_BUTTON = registerBlock("purple_emergency_button",
            () -> new EmergencyButton(BlockBehaviour.Properties.of(Material.DECORATION).strength(0.5f).noOcclusion().sound(SoundType.METAL)));

    public static final RegistryObject<Block> MAGENTA_EMERGENCY_BUTTON = registerBlock("magenta_emergency_button",
            () -> new EmergencyButton(BlockBehaviour.Properties.of(Material.DECORATION).strength(0.5f).noOcclusion().sound(SoundType.METAL)));

    public static final RegistryObject<Block> PINK_EMERGENCY_BUTTON = registerBlock("pink_emergency_button",
            () -> new EmergencyButton(BlockBehaviour.Properties.of(Material.DECORATION).strength(0.5f).noOcclusion().sound(SoundType.METAL)));

    public static final RegistryObject<Block> BROWN_EMERGENCY_BUTTON = registerBlock("brown_emergency_button",
            () -> new EmergencyButton(BlockBehaviour.Properties.of(Material.DECORATION).strength(0.5f).noOcclusion().sound(SoundType.METAL)));

    public static final RegistryObject<Block> WHITE_EMERGENCY_BUTTON = registerBlock("white_emergency_button",
            () -> new EmergencyButton(BlockBehaviour.Properties.of(Material.DECORATION).strength(0.5f).noOcclusion().sound(SoundType.METAL)));

    public static final RegistryObject<Block> LIGHT_GRAY_EMERGENCY_BUTTON = registerBlock("light_gray_emergency_button",
            () -> new EmergencyButton(BlockBehaviour.Properties.of(Material.DECORATION).strength(0.5f).noOcclusion().sound(SoundType.METAL)));

    public static final RegistryObject<Block> GRAY_EMERGENCY_BUTTON = registerBlock("gray_emergency_button",
            () -> new EmergencyButton(BlockBehaviour.Properties.of(Material.DECORATION).strength(0.5f).noOcclusion().sound(SoundType.METAL)));

    public static final RegistryObject<Block> BLACK_EMERGENCY_BUTTON = registerBlock("black_emergency_button",
            () -> new EmergencyButton(BlockBehaviour.Properties.of(Material.DECORATION).strength(0.5f).noOcclusion().sound(SoundType.METAL)));

    public static final RegistryObject<Block> FANCY_EMERGENCY_BUTTON = registerBlock("fancy_emergency_button",
            () -> new EmergencyButton(BlockBehaviour.Properties.of(Material.DECORATION).strength(0.5f).noOcclusion().sound(SoundType.METAL)));

    /**
     * Secret Buttons
     */

    public static final RegistryObject<Block> BOOKSHELF_SECRET_BUTTON = registerBlock("bookshelf_secret_button",
            () -> new BookshelfSecretButton(BlockBehaviour.Properties.of(Material.WOOD).strength(1.5f).noOcclusion().sound(SoundType.WOOD)));

    public static final RegistryObject<Block> STONE_BRICK_SECRET_BUTTON = registerBlock("stone_brick_secret_button",
            () -> new BigBrickSecretButton(BlockBehaviour.Properties.of(Material.STONE).strength(1.5f, 6.0f).noOcclusion().sound(SoundType.STONE).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> MOSSY_STONE_BRICK_SECRET_BUTTON = registerBlock("mossy_stone_brick_secret_button",
            () -> new BigBrickSecretButton(BlockBehaviour.Properties.of(Material.STONE).strength(1.5f, 6.0f).noOcclusion().sound(SoundType.STONE).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> CRACKED_STONE_BRICK_SECRET_BUTTON = registerBlock("cracked_stone_brick_secret_button",
            () -> new BigBrickSecretButton(BlockBehaviour.Properties.of(Material.STONE).strength(1.5f, 6.0f).noOcclusion().sound(SoundType.STONE).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> CHISELED_STONE_BRICK_SECRET_BUTTON = registerBlock("chiseled_stone_brick_secret_button",
            () -> new ChiseledStoneBrickSecretButton(BlockBehaviour.Properties.of(Material.STONE).strength(1.5f, 6.0f).noOcclusion().sound(SoundType.STONE).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> DEEPSLATE_BRICK_SECRET_BUTTON = registerBlock("deepslate_brick_secret_button",
            () -> new BigBrickSecretButton(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY).strength(3.5f, 6.0f).noOcclusion().sound(SoundType.DEEPSLATE_BRICKS).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> CRACKED_DEEPSLATE_BRICK_SECRET_BUTTON = registerBlock("cracked_deepslate_brick_secret_button",
            () -> new BigBrickSecretButton(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY).strength(3.5f, 6.0f).noOcclusion().sound(SoundType.DEEPSLATE_BRICKS).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> DEEPSLATE_TILE_SECRET_BUTTON = registerBlock("deepslate_tile_secret_button",
            () -> new DeepslateTileSecretButton(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY).strength(3.5f, 6.0f).noOcclusion().sound(SoundType.DEEPSLATE_TILES).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> CRACKED_DEEPSLATE_TILE_SECRET_BUTTON = registerBlock("cracked_deepslate_tile_secret_button",
            () -> new DeepslateTileSecretButton(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY).strength(3.5f, 6.0f).noOcclusion().sound(SoundType.DEEPSLATE_TILES).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> END_STONE_BRICK_SECRET_BUTTON = registerBlock("end_stone_brick_secret_button",
            () -> new BigBrickSecretButton(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.SAND).strength(3.0f, 9.0f).noOcclusion().sound(SoundType.STONE).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> QUARTZ_BRICK_SECRET_BUTTON = registerBlock("quartz_brick_secret_button",
            () -> new BigBrickSecretButton(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.QUARTZ).strength(0.8f).noOcclusion().sound(SoundType.STONE).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> POLISHED_BLACKSTONE_BRICK_SECRET_BUTTON = registerBlock("polished_blackstone_brick_secret_button",
            () -> new BigBrickSecretButton(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_BLACK).strength(2.0f, 6.0f).noOcclusion().sound(SoundType.STONE).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> CRACKED_POLISHED_BLACKSTONE_BRICK_SECRET_BUTTON = registerBlock("cracked_polished_blackstone_brick_secret_button",
            () -> new BigBrickSecretButton(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_BLACK).strength(2.0f, 6.0f).noOcclusion().sound(SoundType.STONE).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> CHISELED_POLISHED_BLACKSTONE_SECRET_BUTTON = registerBlock("chiseled_polished_blackstone_secret_button",
            () -> new ChiseledStoneBrickSecretButton(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_BLACK).strength(2.0f, 6.0f).noOcclusion().sound(SoundType.STONE).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> BRICK_SECRET_BUTTON = registerBlock("brick_secret_button",
            () -> new FullBlockBrickSecretButton(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_RED).strength(2.0f, 6.0f).noOcclusion().sound(SoundType.STONE).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> NETHER_BRICK_SECRET_BUTTON = registerBlock("nether_brick_secret_button",
            () -> new FullBlockBrickSecretButton(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.NETHER).strength(2.0f, 6.0f).noOcclusion().sound(SoundType.NETHER_BRICKS).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> CRACKED_NETHER_BRICK_SECRET_BUTTON = registerBlock("cracked_nether_brick_secret_button",
            () -> new FullBlockBrickSecretButton(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.NETHER).strength(2.0f, 6.0f).noOcclusion().sound(SoundType.NETHER_BRICKS).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> CHISELED_NETHER_BRICK_SECRET_BUTTON = registerBlock("chiseled_nether_brick_secret_button",
            () -> new ChiseledNetherBrickSecretButton(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.NETHER).strength(2.0f, 6.0f).noOcclusion().sound(SoundType.NETHER_BRICKS).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> RED_NETHER_BRICK_SECRET_BUTTON = registerBlock("red_nether_brick_secret_button",
            () -> new FullBlockBrickSecretButton(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.NETHER).strength(2.0f, 6.0f).noOcclusion().sound(SoundType.NETHER_BRICKS).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> DARK_PRISMARINE_SECRET_BUTTON = registerBlock("dark_prismarine_secret_button",
            () -> new FullBlockBrickSecretButton(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.DIAMOND).strength(1.5f, 6.0f).noOcclusion().sound(SoundType.STONE).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> OAK_PLANK_SECRET_BUTTON = registerBlock("oak_plank_secret_button",
            () -> new PlankSecretButton(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.WOOD).strength(2.0f, 3.0f).noOcclusion().sound(SoundType.WOOD)));

    public static final RegistryObject<Block> BIRCH_PLANK_SECRET_BUTTON = registerBlock("birch_plank_secret_button",
            () -> new PlankSecretButton(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.SAND).strength(2.0f, 3.0f).noOcclusion().sound(SoundType.WOOD)));

    public static final RegistryObject<Block> SPRUCE_PLANK_SECRET_BUTTON = registerBlock("spruce_plank_secret_button",
            () -> new PlankSecretButton(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.PODZOL).strength(2.0f, 3.0f).noOcclusion().sound(SoundType.WOOD)));

    public static final RegistryObject<Block> ACACIA_PLANK_SECRET_BUTTON = registerBlock("acacia_plank_secret_button",
            () -> new PlankSecretButton(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.COLOR_ORANGE).strength(2.0f, 3.0f).noOcclusion().sound(SoundType.WOOD)));

    public static final RegistryObject<Block> DARK_OAK_PLANK_SECRET_BUTTON = registerBlock("dark_oak_plank_secret_button",
            () -> new PlankSecretButton(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.COLOR_BROWN).strength(2.0f, 3.0f).noOcclusion().sound(SoundType.WOOD)));

    public static final RegistryObject<Block> JUNGLE_PLANK_SECRET_BUTTON = registerBlock("jungle_plank_secret_button",
            () -> new PlankSecretButton(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.DIRT).strength(2.0f, 3.0f).noOcclusion().sound(SoundType.WOOD)));

    public static final RegistryObject<Block> CRIMSON_PLANK_SECRET_BUTTON = registerBlock("crimson_plank_secret_button",
            () -> new PlankSecretButton(BlockBehaviour.Properties.of(Material.NETHER_WOOD, MaterialColor.CRIMSON_STEM).strength(2.0f, 3.0f).noOcclusion().sound(SoundType.WOOD)));

    public static final RegistryObject<Block> WARPED_PLANK_SECRET_BUTTON = registerBlock("warped_plank_secret_button",
            () -> new PlankSecretButton(BlockBehaviour.Properties.of(Material.NETHER_WOOD, MaterialColor.WARPED_STEM).strength(2.0f, 3.0f).noOcclusion().sound(SoundType.WOOD)));

    public static final RegistryObject<Block> PERMAFROST_BRICK_SECRET_BUTTON = registerCompatBlock("quark", "permafrost_brick_secret_button",
            () -> new BigBrickSecretButton(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_LIGHT_BLUE).strength(1.5f, 10.0f).noOcclusion().sound(SoundType.STONE).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> BLUE_NETHER_BRICK_SECRET_BUTTON = registerCompatBlock("quark", "blue_nether_brick_secret_button",
            () -> new FullBlockBrickSecretButton(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.NETHER).strength(2.0f, 6.0f).noOcclusion().sound(SoundType.NETHER_BRICKS).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> POLISHED_GRANITE_BRICK_SECRET_BUTTON = registerCompatBlock("quark", "polished_granite_brick_secret_button",
            () -> new BigBrickSecretButton(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.DIRT).strength(1.5f, 6.0f).noOcclusion().sound(SoundType.STONE).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> POLISHED_DIORITE_BRICK_SECRET_BUTTON = registerCompatBlock("quark", "polished_diorite_brick_secret_button",
            () -> new BigBrickSecretButton(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.QUARTZ).strength(1.5f, 6.0f).noOcclusion().sound(SoundType.STONE).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> POLISHED_ANDESITE_BRICK_SECRET_BUTTON = registerCompatBlock("quark", "polished_andesite_brick_secret_button",
            () -> new BigBrickSecretButton(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.STONE).strength(1.5f, 6.0f).noOcclusion().sound(SoundType.STONE).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> POLISHED_CALCITE_BRICK_SECRET_BUTTON = registerCompatBlock("quark", "polished_calcite_brick_secret_button",
            () -> new BigBrickSecretButton(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.TERRACOTTA_WHITE).strength(0.75f, 0.75f).noOcclusion().sound(SoundType.CALCITE).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> POLISHED_DRIPSTONE_BRICK_SECRET_BUTTON = registerCompatBlock("quark", "polished_dripstone_brick_secret_button",
            () -> new BigBrickSecretButton(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.TERRACOTTA_BROWN).strength(1.5f, 1.0f).noOcclusion().sound(SoundType.DRIPSTONE_BLOCK).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> POLISHED_TUFF_BRICK_SECRET_BUTTON = registerCompatBlock("quark", "polished_tuff_brick_secret_button",
            () -> new BigBrickSecretButton(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.TERRACOTTA_GRAY).strength(1.5f, 1.0f).noOcclusion().sound(SoundType.TUFF).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> POLISHED_LIMESTONE_BRICK_SECRET_BUTTON = registerCompatBlock("quark", "polished_limestone_brick_secret_button",
            () -> new BigBrickSecretButton(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.STONE).strength(1.5f, 6.0f).noOcclusion().sound(SoundType.STONE).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> POLISHED_JASPER_BRICK_SECRET_BUTTON = registerCompatBlock("quark", "polished_jasper_brick_secret_button",
            () -> new BigBrickSecretButton(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.TERRACOTTA_RED).strength(1.5f, 6.0f).noOcclusion().sound(SoundType.STONE).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> POLISHED_SHALE_BRICK_SECRET_BUTTON = registerCompatBlock("quark", "polished_shale_brick_secret_button",
            () -> new BigBrickSecretButton(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.ICE).strength(1.5f, 6.0f).noOcclusion().sound(SoundType.STONE).requiresCorrectToolForDrops()));

    /**
     * Misc
     */

    public static final RegistryObject<Block> DOORBELL = registerBlock("doorbell",
            () -> new Doorbell(BlockBehaviour.Properties.of(Material.DECORATION).strength(0.5f).noCollission().sound(SoundType.WOOD)));

    public static final RegistryObject<Block> DOORBELL_BUTTON = registerBlock("doorbell_button",
            () -> new DoorbellButton(BlockBehaviour.Properties.of(Material.DECORATION).strength(0.5f).noCollission().sound(SoundType.WOOD)));

    public static final RegistryObject<Block> HOGLIN_MOUNT_BUTTON = registerCompatBlock("nethersdelight", "hoglin_mount_button",
            () -> new HoglinMountButton(BlockBehaviour.Properties.copy(Blocks.BROWN_WOOL)
                    .lightLevel((p_50876_) -> {
                        return 1;
                    })));

    /**
     * Torches
     */

    public static final RegistryObject<Block> TORCH_BUTTON = registerTorchBlock("torch_button",
            () -> new TorchButton(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().instabreak().lightLevel((p_50876_) -> {
                return 14;
            }).sound(SoundType.WOOD), ParticleTypes.FLAME));

    public static final RegistryObject<Block> WALL_TORCH_BUTTON = registerTorchBlock("wall_torch_button",
            () -> new WallTorchButton(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().instabreak().lightLevel((p_50876_) -> {
                return 14;
            }).sound(SoundType.WOOD).lootFrom(TORCH_BUTTON), ParticleTypes.FLAME));

    public static final RegistryObject<Block> TORCH_LEVER = registerTorchBlock("torch_lever",
            () -> new TorchLever(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().instabreak().lightLevel((p_50876_) -> {
                return 14;
            }).sound(SoundType.WOOD), ParticleTypes.FLAME));

    public static final RegistryObject<Block> WALL_TORCH_LEVER = registerTorchBlock("wall_torch_lever",
            () -> new WallTorchLever(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().instabreak().lightLevel((p_50876_) -> {
                return 14;
            }).sound(SoundType.WOOD).lootFrom(TORCH_LEVER), ParticleTypes.FLAME));

    public static final RegistryObject<Block> SOUL_TORCH_BUTTON = registerTorchBlock("soul_torch_button",
            () -> new TorchButton(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().instabreak().lightLevel((p_50876_) -> {
                return 10;
            }).sound(SoundType.WOOD), ParticleTypes.SOUL_FIRE_FLAME));

    public static final RegistryObject<Block> SOUL_WALL_TORCH_BUTTON = registerTorchBlock("soul_wall_torch_button",
            () -> new WallTorchButton(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().instabreak().lightLevel((p_50876_) -> {
                return 10;
            }).sound(SoundType.WOOD).lootFrom(SOUL_TORCH_BUTTON), ParticleTypes.SOUL_FIRE_FLAME));

    public static final RegistryObject<Block> SOUL_TORCH_LEVER = registerTorchBlock("soul_torch_lever",
            () -> new TorchLever(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().instabreak().lightLevel((p_50876_) -> {
                return 10;
            }).sound(SoundType.WOOD), ParticleTypes.SOUL_FIRE_FLAME));

    public static final RegistryObject<Block> SOUL_WALL_TORCH_LEVER = registerTorchBlock("soul_wall_torch_lever",
            () -> new WallTorchLever(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().instabreak().lightLevel((p_50876_) -> {
                return 10;
            }).sound(SoundType.WOOD).lootFrom(SOUL_TORCH_LEVER), ParticleTypes.SOUL_FIRE_FLAME));

    public static final RegistryObject<Block> REDSTONE_TORCH_BUTTON = registerTorchBlock("redstone_torch_button",
            () -> new RedstoneTorchButton(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().instabreak().lightLevel((p_50876_) -> {
                return 7;
            }).sound(SoundType.WOOD)));

    public static final RegistryObject<Block> REDSTONE_WALL_TORCH_BUTTON = registerTorchBlock("redstone_wall_torch_button",
            () -> new RedstoneWallTorchButton(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().instabreak().lightLevel((p_50876_) -> {
                return 7;
            }).sound(SoundType.WOOD).lootFrom(REDSTONE_TORCH_BUTTON)));

    public static final RegistryObject<Block> REDSTONE_TORCH_LEVER = registerTorchBlock("redstone_torch_lever",
            () -> new RedstoneTorchLever(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().instabreak().lightLevel((p_50876_) -> {
                return 7;
            }).sound(SoundType.WOOD)));

    public static final RegistryObject<Block> REDSTONE_WALL_TORCH_LEVER = registerTorchBlock("redstone_wall_torch_lever",
            () -> new RedstoneWallTorchLever(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().instabreak().lightLevel((p_50876_) -> {
                return 7;
            }).sound(SoundType.WOOD).lootFrom(REDSTONE_TORCH_LEVER)));

    public static final RegistryObject<Block> PROPELPLANT_TORCH_BUTTON = registerCompatTorchBlock("nethersdelight", "propelplant_torch_button",
            () -> new PropelTorchButton(BlockBehaviour.Properties.of(Material.PLANT).noCollission().instabreak().lightLevel((p_50876_) -> {
                return 12;
            }).sound(SoundType.WOOD)));

    public static final RegistryObject<Block> PROPELPLANT_WALL_TORCH_BUTTON = registerCompatTorchBlock("nethersdelight", "propelplant_wall_torch_button",
            () -> new PropelWallTorchButton(BlockBehaviour.Properties.of(Material.PLANT).noCollission().instabreak().lightLevel((p_50876_) -> {
                return 12;
            }).sound(SoundType.WOOD).lootFrom(PROPELPLANT_TORCH_BUTTON)));

    public static final RegistryObject<Block> PROPELPLANT_TORCH_LEVER = registerCompatTorchBlock("nethersdelight", "propelplant_torch_lever",
            () -> new PropelTorchLever(BlockBehaviour.Properties.of(Material.PLANT).noCollission().instabreak().lightLevel((p_50876_) -> {
                return 12;
            }).sound(SoundType.WOOD)));

    public static final RegistryObject<Block> PROPELPLANT_WALL_TORCH_LEVER = registerCompatTorchBlock("nethersdelight", "propelplant_wall_torch_lever",
            () -> new PropelWallTorchLever(BlockBehaviour.Properties.of(Material.PLANT).noCollission().instabreak().lightLevel((p_50876_) -> {
                return 12;
            }).sound(SoundType.WOOD).lootFrom(PROPELPLANT_TORCH_LEVER)));

    /**
     * Methods
     */

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, InfinityButtonsItemGroup.INFINITYBUTTONS);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<T> registerCompatBlock(String modid, String name, Supplier<T> block) {
        RegistryObject<T> toReturn = null;
        if (ModList.get().isLoaded(modid)) {
            toReturn = BLOCKS.register(name, block);
            registerBlockItem(name, toReturn, InfinityButtonsItemGroup.INFINITYBUTTONSCOMPAT);
        }
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block, CreativeModeTab tab) {
        return InfinityButtonsItems.ITEMS.register(name, () -> new BlockItem(block.get(),
                new Item.Properties().tab(tab)));
    }

    private static <T extends Block>RegistryObject<T> registerTorchBlock(String name, Supplier<T> block) {
        return BLOCKS.register(name, block);
    }

    private static <T extends Block>RegistryObject<T> registerCompatTorchBlock(String modid, String name, Supplier<T> block) {
        RegistryObject<T> toReturn = null;
        if (ModList.get().isLoaded(modid)) {
            toReturn = BLOCKS.register(name, block);
        }
        return toReturn;
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }

}
