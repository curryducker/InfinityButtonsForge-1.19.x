package net.larsmans.infinitybuttons.block;

import net.larsmans.infinitybuttons.InfinityButtons;
import net.larsmans.infinitybuttons.InfinityButtonsUtil;
import net.larsmans.infinitybuttons.block.custom.Doorbell;
import net.larsmans.infinitybuttons.block.custom.DoorbellButton;
import net.larsmans.infinitybuttons.block.custom.LampButton;
import net.larsmans.infinitybuttons.block.custom.LanternButton;
import net.larsmans.infinitybuttons.block.custom.button.*;
import net.larsmans.infinitybuttons.block.custom.consolebutton.ConsoleButton;
import net.larsmans.infinitybuttons.block.custom.consolebutton.LargeConsoleButton;
import net.larsmans.infinitybuttons.block.custom.consolebutton.SmallConsoleButton;
import net.larsmans.infinitybuttons.block.custom.emergencybutton.EmergencyButton;
import net.larsmans.infinitybuttons.block.custom.emergencybutton.SafeEmergencyButton;
import net.larsmans.infinitybuttons.block.custom.letterbutton.LetterButton;
import net.larsmans.infinitybuttons.block.custom.secretbutton.*;
import net.larsmans.infinitybuttons.block.custom.torch.*;
import net.larsmans.infinitybuttons.item.InfinityButtonsItemGroup;
import net.larsmans.infinitybuttons.item.InfinityButtonsItems;
import net.larsmans.infinitybuttons.item.custom.SafeEmergencyButtonItem;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.WeatheringCopper;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.eventbus.api.IEventBus;
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

    public static final RegistryObject<Block> DEEPSLATE_BUTTON = registerStoneButton("deepslate");
    public static final RegistryObject<Block> GRANITE_BUTTON = registerStoneButton("granite");
    public static final RegistryObject<Block> DIORITE_BUTTON = registerStoneButton("diorite");
    public static final RegistryObject<Block> ANDESITE_BUTTON = registerStoneButton("andesite");
    public static final RegistryObject<Block> CALCITE_BUTTON = registerStoneButton("calcite");
    public static final RegistryObject<Block> TUFF_BUTTON = registerStoneButton("tuff");
    public static final RegistryObject<Block> DRIPSTONE_BUTTON = registerStoneButton("dripstone");

    public static final RegistryObject<Block> COPPER_BUTTON = registerCopperButton("copper", WeatheringCopper.WeatherState.UNAFFECTED);
    public static final RegistryObject<Block> EXPOSED_COPPER_BUTTON = registerCopperButton("exposed_copper", WeatheringCopper.WeatherState.EXPOSED);
    public static final RegistryObject<Block> WEATHERED_COPPER_BUTTON = registerCopperButton("weathered_copper", WeatheringCopper.WeatherState.WEATHERED);
    public static final RegistryObject<Block> OXIDIZED_COPPER_BUTTON = registerCopperButton("oxidized_copper", WeatheringCopper.WeatherState.OXIDIZED);

    public static final RegistryObject<Block> WAXED_COPPER_BUTTON = registerWaxedCopperButton("copper");
    public static final RegistryObject<Block> WAXED_EXPOSED_COPPER_BUTTON = registerWaxedCopperButton("exposed_copper");
    public static final RegistryObject<Block> WAXED_WEATHERED_COPPER_BUTTON = registerWaxedCopperButton("weathered_copper");
    public static final RegistryObject<Block> WAXED_OXIDIZED_COPPER_BUTTON = registerWaxedCopperButton("oxidized_copper");

    public static final RegistryObject<Block> STICKY_COPPER_BUTTON = registerStickyCopperButton("copper");
    public static final RegistryObject<Block> STICKY_EXPOSED_COPPER_BUTTON = registerStickyCopperButton("exposed_copper");
    public static final RegistryObject<Block> STICKY_WEATHERED_COPPER_BUTTON = registerStickyCopperButton("weathered_copper");
    public static final RegistryObject<Block> STICKY_OXIDIZED_COPPER_BUTTON = registerStickyCopperButton("oxidized_copper");

    public static final RegistryObject<Block> IRON_BUTTON = registerArrowButton("iron");
    public static final RegistryObject<Block> GOLD_BUTTON = registerArrowButton("gold");

    public static final RegistryObject<Block> EMERALD_BUTTON = registerBlock("emerald_button",
            () -> new EmeraldButton(BlockBehaviour.Properties.of(Material.DECORATION).strength(0.5f).noCollission().sound(SoundType.METAL), false));

    public static final RegistryObject<Block> DIAMOND_BUTTON = registerBlock("diamond_button",
            () -> new DiamondButton(BlockBehaviour.Properties.of(Material.DECORATION).strength(0.5f).noCollission().sound(SoundType.METAL), false));

    public static final RegistryObject<Block> PRISMARINE_BUTTON = registerPrismarineButton("prismarine");
    public static final RegistryObject<Block> PRISMARINE_BRICK_BUTTON = registerPrismarineButton("prismarine_brick");
    public static final RegistryObject<Block> DARK_PRISMARINE_BUTTON = registerPrismarineButton("dark_prismarine");

    public static final RegistryObject<Block> SAND_BUTTON = registerSandButton("sand", false);
    public static final RegistryObject<Block> RED_SAND_BUTTON = registerSandButton("red_sand", false);
    public static final RegistryObject<Block> GRAVEL_BUTTON = registerSandButton("gravel", true);

    public static final RegistryObject<Block> WHITE_CONCRETE_POWDER_BUTTON = registerConcretePowderButton("white");
    public static final RegistryObject<Block> LIGHT_GRAY_CONCRETE_POWDER_BUTTON = registerConcretePowderButton("light_gray");
    public static final RegistryObject<Block> GRAY_CONCRETE_POWDER_BUTTON = registerConcretePowderButton("gray");
    public static final RegistryObject<Block> BLACK_CONCRETE_POWDER_BUTTON = registerConcretePowderButton("black");
    public static final RegistryObject<Block> BROWN_CONCRETE_POWDER_BUTTON = registerConcretePowderButton("brown");
    public static final RegistryObject<Block> RED_CONCRETE_POWDER_BUTTON = registerConcretePowderButton("red");
    public static final RegistryObject<Block> ORANGE_CONCRETE_POWDER_BUTTON = registerConcretePowderButton("orange");
    public static final RegistryObject<Block> YELLOW_CONCRETE_POWDER_BUTTON = registerConcretePowderButton("yellow");
    public static final RegistryObject<Block> LIME_CONCRETE_POWDER_BUTTON = registerConcretePowderButton("lime");
    public static final RegistryObject<Block> GREEN_CONCRETE_POWDER_BUTTON = registerConcretePowderButton("green");
    public static final RegistryObject<Block> CYAN_CONCRETE_POWDER_BUTTON = registerConcretePowderButton("cyan");
    public static final RegistryObject<Block> LIGHT_BLUE_CONCRETE_POWDER_BUTTON = registerConcretePowderButton("light_blue");
    public static final RegistryObject<Block> BLUE_CONCRETE_POWDER_BUTTON = registerConcretePowderButton("blue");
    public static final RegistryObject<Block> PURPLE_CONCRETE_POWDER_BUTTON = registerConcretePowderButton("purple");
    public static final RegistryObject<Block> MAGENTA_CONCRETE_POWDER_BUTTON = registerConcretePowderButton("magenta");
    public static final RegistryObject<Block> PINK_CONCRETE_POWDER_BUTTON = registerConcretePowderButton("pink");

    /**
     * Large Buttons
     */

    public static final RegistryObject<Block> OAK_LARGE_BUTTON = registerWoodenLargeButton("oak");
    public static final RegistryObject<Block> SPRUCE_LARGE_BUTTON = registerWoodenLargeButton("spruce");
    public static final RegistryObject<Block> BIRCH_LARGE_BUTTON = registerWoodenLargeButton("birch");
    public static final RegistryObject<Block> JUNGLE_LARGE_BUTTON = registerWoodenLargeButton("jungle");
    public static final RegistryObject<Block> ACACIA_LARGE_BUTTON = registerWoodenLargeButton("acacia");
    public static final RegistryObject<Block> DARK_OAK_LARGE_BUTTON = registerWoodenLargeButton("dark_oak");
    public static final RegistryObject<Block> MANGROVE_LARGE_BUTTON = registerWoodenLargeButton("mangrove");
    public static final RegistryObject<Block> CRIMSON_LARGE_BUTTON = registerWoodenLargeButton("crimson");
    public static final RegistryObject<Block> WARPED_LARGE_BUTTON = registerWoodenLargeButton("warped");

    public static final RegistryObject<Block> STONE_LARGE_BUTTON = registerStoneLargeButton("stone");
    public static final RegistryObject<Block> DEEPSLATE_LARGE_BUTTON = registerStoneLargeButton("deepslate");
    public static final RegistryObject<Block> GRANITE_LARGE_BUTTON = registerStoneLargeButton("granite");
    public static final RegistryObject<Block> DIORITE_LARGE_BUTTON = registerStoneLargeButton("diorite");
    public static final RegistryObject<Block> ANDESITE_LARGE_BUTTON = registerStoneLargeButton("andesite");
    public static final RegistryObject<Block> CALCITE_LARGE_BUTTON = registerStoneLargeButton("calcite");
    public static final RegistryObject<Block> TUFF_LARGE_BUTTON = registerStoneLargeButton("tuff");
    public static final RegistryObject<Block> DRIPSTONE_LARGE_BUTTON = registerStoneLargeButton("dripstone");
    public static final RegistryObject<Block> POLISHED_BLACKSTONE_LARGE_BUTTON = registerStoneLargeButton("polished_blackstone");

    public static final RegistryObject<Block> COPPER_LARGE_BUTTON = registerCopperLargeButton("copper", WeatheringCopper.WeatherState.UNAFFECTED);
    public static final RegistryObject<Block> EXPOSED_COPPER_LARGE_BUTTON = registerCopperLargeButton("exposed_copper", WeatheringCopper.WeatherState.EXPOSED);
    public static final RegistryObject<Block> WEATHERED_COPPER_LARGE_BUTTON = registerCopperLargeButton("weathered_copper", WeatheringCopper.WeatherState.WEATHERED);
    public static final RegistryObject<Block> OXIDIZED_COPPER_LARGE_BUTTON = registerCopperLargeButton("oxidized_copper", WeatheringCopper.WeatherState.OXIDIZED);

    public static final RegistryObject<Block> WAXED_COPPER_LARGE_BUTTON = registerWaxedCopperLargeButton("copper");
    public static final RegistryObject<Block> WAXED_EXPOSED_COPPER_LARGE_BUTTON = registerWaxedCopperLargeButton("exposed_copper");
    public static final RegistryObject<Block> WAXED_WEATHERED_COPPER_LARGE_BUTTON = registerWaxedCopperLargeButton("weathered_copper");
    public static final RegistryObject<Block> WAXED_OXIDIZED_COPPER_LARGE_BUTTON = registerWaxedCopperLargeButton("oxidized_copper");

    public static final RegistryObject<Block> STICKY_COPPER_LARGE_BUTTON = registerStickyCopperLargeButton("copper");
    public static final RegistryObject<Block> STICKY_EXPOSED_COPPER_LARGE_BUTTON = registerStickyCopperLargeButton("exposed_copper");
    public static final RegistryObject<Block> STICKY_WEATHERED_COPPER_LARGE_BUTTON = registerStickyCopperLargeButton("weathered_copper");
    public static final RegistryObject<Block> STICKY_OXIDIZED_COPPER_LARGE_BUTTON = registerStickyCopperLargeButton("oxidized_copper");

    public static final RegistryObject<Block> IRON_LARGE_BUTTON = registerArrowLargeButton("iron");
    public static final RegistryObject<Block> GOLD_LARGE_BUTTON = registerArrowLargeButton("gold");

    public static final RegistryObject<Block> EMERALD_LARGE_BUTTON = registerBlock("emerald_large_button",
            () -> new EmeraldButton(BlockBehaviour.Properties.of(Material.DECORATION).strength(0.5f).noCollission().sound(SoundType.METAL), true));

    public static final RegistryObject<Block> DIAMOND_LARGE_BUTTON = registerBlock("diamond_large_button",
            () -> new DiamondButton(BlockBehaviour.Properties.of(Material.DECORATION).strength(0.5f).noCollission().sound(SoundType.METAL), true));

    public static final RegistryObject<Block> PRISMARINE_LARGE_BUTTON = registerPrismarineLargeButton("prismarine");
    public static final RegistryObject<Block> PRISMARINE_BRICK_LARGE_BUTTON = registerPrismarineLargeButton("prismarine_brick");
    public static final RegistryObject<Block> DARK_PRISMARINE_LARGE_BUTTON = registerPrismarineLargeButton("dark_prismarine");

    public static final RegistryObject<Block> SAND_LARGE_BUTTON = registerSandLargeButton("sand", false);
    public static final RegistryObject<Block> RED_SAND_LARGE_BUTTON = registerSandLargeButton("red_sand", false);
    public static final RegistryObject<Block> GRAVEL_LARGE_BUTTON = registerSandLargeButton("gravel", true);

    public static final RegistryObject<Block> WHITE_CONCRETE_POWDER_LARGE_BUTTON = registerConcretePowderLargeButton("white");
    public static final RegistryObject<Block> LIGHT_GRAY_CONCRETE_POWDER_LARGE_BUTTON = registerConcretePowderLargeButton("light_gray");
    public static final RegistryObject<Block> GRAY_CONCRETE_POWDER_LARGE_BUTTON = registerConcretePowderLargeButton("gray");
    public static final RegistryObject<Block> BLACK_CONCRETE_POWDER_LARGE_BUTTON = registerConcretePowderLargeButton("black");
    public static final RegistryObject<Block> BROWN_CONCRETE_POWDER_LARGE_BUTTON = registerConcretePowderLargeButton("brown");
    public static final RegistryObject<Block> RED_CONCRETE_POWDER_LARGE_BUTTON = registerConcretePowderLargeButton("red");
    public static final RegistryObject<Block> ORANGE_CONCRETE_POWDER_LARGE_BUTTON = registerConcretePowderLargeButton("orange");
    public static final RegistryObject<Block> YELLOW_CONCRETE_POWDER_LARGE_BUTTON = registerConcretePowderLargeButton("yellow");
    public static final RegistryObject<Block> LIME_CONCRETE_POWDER_LARGE_BUTTON = registerConcretePowderLargeButton("lime");
    public static final RegistryObject<Block> GREEN_CONCRETE_POWDER_LARGE_BUTTON = registerConcretePowderLargeButton("green");
    public static final RegistryObject<Block> CYAN_CONCRETE_POWDER_LARGE_BUTTON = registerConcretePowderLargeButton("cyan");
    public static final RegistryObject<Block> LIGHT_BLUE_CONCRETE_POWDER_LARGE_BUTTON = registerConcretePowderLargeButton("light_blue");
    public static final RegistryObject<Block> BLUE_CONCRETE_POWDER_LARGE_BUTTON = registerConcretePowderLargeButton("blue");
    public static final RegistryObject<Block> PURPLE_CONCRETE_POWDER_LARGE_BUTTON = registerConcretePowderLargeButton("purple");
    public static final RegistryObject<Block> MAGENTA_CONCRETE_POWDER_LARGE_BUTTON = registerConcretePowderLargeButton("magenta");
    public static final RegistryObject<Block> PINK_CONCRETE_POWDER_LARGE_BUTTON = registerConcretePowderLargeButton("pink");

    /**
     * Emergency Buttons
     */

    public static final RegistryObject<Block> WHITE_EMERGENCY_BUTTON = registerEmergencyButton("white");
    public static final RegistryObject<Block> LIGHT_GRAY_EMERGENCY_BUTTON = registerEmergencyButton("light_gray");
    public static final RegistryObject<Block> GRAY_EMERGENCY_BUTTON = registerEmergencyButton("gray");
    public static final RegistryObject<Block> BLACK_EMERGENCY_BUTTON = registerEmergencyButton("black");
    public static final RegistryObject<Block> BROWN_EMERGENCY_BUTTON = registerEmergencyButton("brown");
    public static final RegistryObject<Block> RED_EMERGENCY_BUTTON = registerEmergencyButton("red");
    public static final RegistryObject<Block> ORANGE_EMERGENCY_BUTTON = registerEmergencyButton("orange");
    public static final RegistryObject<Block> YELLOW_EMERGENCY_BUTTON = registerEmergencyButton("yellow");
    public static final RegistryObject<Block> LIME_EMERGENCY_BUTTON = registerEmergencyButton("lime");
    public static final RegistryObject<Block> GREEN_EMERGENCY_BUTTON = registerEmergencyButton("green");
    public static final RegistryObject<Block> CYAN_EMERGENCY_BUTTON = registerEmergencyButton("cyan");
    public static final RegistryObject<Block> LIGHT_BLUE_EMERGENCY_BUTTON = registerEmergencyButton("light_blue");
    public static final RegistryObject<Block> BLUE_EMERGENCY_BUTTON = registerEmergencyButton("blue");
    public static final RegistryObject<Block> PURPLE_EMERGENCY_BUTTON = registerEmergencyButton("purple");
    public static final RegistryObject<Block> MAGENTA_EMERGENCY_BUTTON = registerEmergencyButton("magenta");
    public static final RegistryObject<Block> PINK_EMERGENCY_BUTTON = registerEmergencyButton("pink");
    public static final RegistryObject<Block> FANCY_EMERGENCY_BUTTON = registerEmergencyButton("fancy");

    public static final RegistryObject<Block> WHITE_SAFE_EMERGENCY_BUTTON = registerSafeEmergencyButton("white");
    public static final RegistryObject<Block> LIGHT_GRAY_SAFE_EMERGENCY_BUTTON = registerSafeEmergencyButton("light_gray");
    public static final RegistryObject<Block> GRAY_SAFE_EMERGENCY_BUTTON = registerSafeEmergencyButton("gray");
    public static final RegistryObject<Block> BLACK_SAFE_EMERGENCY_BUTTON = registerSafeEmergencyButton("black");
    public static final RegistryObject<Block> BROWN_SAFE_EMERGENCY_BUTTON = registerSafeEmergencyButton("brown");
    public static final RegistryObject<Block> RED_SAFE_EMERGENCY_BUTTON = registerSafeEmergencyButton("red");
    public static final RegistryObject<Block> ORANGE_SAFE_EMERGENCY_BUTTON = registerSafeEmergencyButton("orange");
    public static final RegistryObject<Block> YELLOW_SAFE_EMERGENCY_BUTTON = registerSafeEmergencyButton("yellow");
    public static final RegistryObject<Block> LIME_SAFE_EMERGENCY_BUTTON = registerSafeEmergencyButton("lime");
    public static final RegistryObject<Block> GREEN_SAFE_EMERGENCY_BUTTON = registerSafeEmergencyButton("green");
    public static final RegistryObject<Block> CYAN_SAFE_EMERGENCY_BUTTON = registerSafeEmergencyButton("cyan");
    public static final RegistryObject<Block> LIGHT_BLUE_SAFE_EMERGENCY_BUTTON = registerSafeEmergencyButton("light_blue");
    public static final RegistryObject<Block> BLUE_SAFE_EMERGENCY_BUTTON = registerSafeEmergencyButton("blue");
    public static final RegistryObject<Block> PURPLE_SAFE_EMERGENCY_BUTTON = registerSafeEmergencyButton("purple");
    public static final RegistryObject<Block> MAGENTA_SAFE_EMERGENCY_BUTTON = registerSafeEmergencyButton("magenta");
    public static final RegistryObject<Block> PINK_SAFE_EMERGENCY_BUTTON = registerSafeEmergencyButton("pink");
    public static final RegistryObject<Block> FANCY_SAFE_EMERGENCY_BUTTON = registerSafeEmergencyButton("fancy");

    /**
     * Secret Buttons
     */

    public static final RegistryObject<Block> BOOKSHELF_SECRET_BUTTON = registerBlock("bookshelf_secret_button",
            () -> new BookshelfSecretButton(BlockBehaviour.Properties.of(Material.WOOD).strength(1.5f).noOcclusion().sound(SoundType.WOOD), byName("bookshelf")));

    public static final RegistryObject<Block> BRICK_SECRET_BUTTON = registerBlock("brick_secret_button",
            () -> new FullBlockBrickSecretButton(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_RED).strength(2.0f, 6.0f).noOcclusion().sound(SoundType.STONE).requiresCorrectToolForDrops(), byName("bricks")));

    public static final RegistryObject<Block> STONE_BRICK_SECRET_BUTTON = registerBlock("stone_brick_secret_button",
            () -> new BigBrickSecretButton(BlockBehaviour.Properties.of(Material.STONE).strength(1.5f, 6.0f).noOcclusion().sound(SoundType.STONE).requiresCorrectToolForDrops(), byName("stone_bricks")));

    public static final RegistryObject<Block> MOSSY_STONE_BRICK_SECRET_BUTTON = registerBlock("mossy_stone_brick_secret_button",
            () -> new BigBrickSecretButton(BlockBehaviour.Properties.of(Material.STONE).strength(1.5f, 6.0f).noOcclusion().sound(SoundType.STONE).requiresCorrectToolForDrops(), byName("mossy_stone_bricks")));

    public static final RegistryObject<Block> CRACKED_STONE_BRICK_SECRET_BUTTON = registerBlock("cracked_stone_brick_secret_button",
            () -> new BigBrickSecretButton(BlockBehaviour.Properties.of(Material.STONE).strength(1.5f, 6.0f).noOcclusion().sound(SoundType.STONE).requiresCorrectToolForDrops(), byName("cracked_stone_bricks")));

    public static final RegistryObject<Block> CHISELED_STONE_BRICK_SECRET_BUTTON = registerBlock("chiseled_stone_brick_secret_button",
            () -> new ChiseledStoneBrickSecretButton(BlockBehaviour.Properties.of(Material.STONE).strength(1.5f, 6.0f).noOcclusion().sound(SoundType.STONE).requiresCorrectToolForDrops(), byName("chiseled_stone_bricks")));

    public static final RegistryObject<Block> DEEPSLATE_BRICK_SECRET_BUTTON = registerBlock("deepslate_brick_secret_button",
            () -> new BigBrickSecretButton(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY).strength(3.5f, 6.0f).noOcclusion().sound(SoundType.DEEPSLATE_BRICKS).requiresCorrectToolForDrops(), byName("deepslate_bricks")));

    public static final RegistryObject<Block> CRACKED_DEEPSLATE_BRICK_SECRET_BUTTON = registerBlock("cracked_deepslate_brick_secret_button",
            () -> new BigBrickSecretButton(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY).strength(3.5f, 6.0f).noOcclusion().sound(SoundType.DEEPSLATE_BRICKS).requiresCorrectToolForDrops(), byName("cracked_deepslate_bricks")));

    public static final RegistryObject<Block> DEEPSLATE_TILE_SECRET_BUTTON = registerBlock("deepslate_tile_secret_button",
            () -> new DeepslateTileSecretButton(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY).strength(3.5f, 6.0f).noOcclusion().sound(SoundType.DEEPSLATE_TILES).requiresCorrectToolForDrops(), byName("deepslate_tiles")));

    public static final RegistryObject<Block> CRACKED_DEEPSLATE_TILE_SECRET_BUTTON = registerBlock("cracked_deepslate_tile_secret_button",
            () -> new DeepslateTileSecretButton(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY).strength(3.5f, 6.0f).noOcclusion().sound(SoundType.DEEPSLATE_TILES).requiresCorrectToolForDrops(), byName("cracked_deepslate_tiles")));

    public static final RegistryObject<Block> OAK_PLANK_SECRET_BUTTON = registerPlankSecretButton("oak", MaterialColor.WOOD);
    public static final RegistryObject<Block> SPRUCE_PLANK_SECRET_BUTTON = registerPlankSecretButton("spruce", MaterialColor.PODZOL);
    public static final RegistryObject<Block> BIRCH_PLANK_SECRET_BUTTON = registerPlankSecretButton("birch", MaterialColor.SAND);
    public static final RegistryObject<Block> JUNGLE_PLANK_SECRET_BUTTON = registerPlankSecretButton("jungle", MaterialColor.DIRT);
    public static final RegistryObject<Block> ACACIA_PLANK_SECRET_BUTTON = registerPlankSecretButton("acacia", MaterialColor.COLOR_ORANGE);
    public static final RegistryObject<Block> DARK_OAK_PLANK_SECRET_BUTTON = registerPlankSecretButton("dark_oak", MaterialColor.COLOR_BROWN);
    public static final RegistryObject<Block> MANGROVE_PLANK_SECRET_BUTTON = registerPlankSecretButton("mangrove", MaterialColor.COLOR_RED);
    public static final RegistryObject<Block> CRIMSON_PLANK_SECRET_BUTTON = registerNetherPlankSecretButton("crimson", MaterialColor.CRIMSON_STEM);
    public static final RegistryObject<Block> WARPED_PLANK_SECRET_BUTTON = registerNetherPlankSecretButton("warped", MaterialColor.WARPED_STEM);

    public static final RegistryObject<Block> MUD_BRICK_SECRET_BUTTON = registerBlock("mud_brick_secret_button",
            () -> new MudBrickSecretButton(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.TERRACOTTA_LIGHT_GRAY).strength(1.5f, 3.0f).noOcclusion().sound(SoundType.MUD_BRICKS).requiresCorrectToolForDrops(), byName("mud_bricks")));

    public static final RegistryObject<Block> END_STONE_BRICK_SECRET_BUTTON = registerBlock("end_stone_brick_secret_button",
            () -> new BigBrickSecretButton(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.SAND).strength(3.0f, 9.0f).noOcclusion().sound(SoundType.STONE).requiresCorrectToolForDrops(), byName("end_stone_bricks")));

    public static final RegistryObject<Block> PURPUR_BLOCK_SECRET_BUTTON = registerBlock("purpur_block_secret_button",
            () -> new TileSecretButton(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_MAGENTA).strength(1.5f, 6.0f).noOcclusion().sound(SoundType.STONE).requiresCorrectToolForDrops(), byName("purpur_block")));

    public static final RegistryObject<Block> QUARTZ_BRICK_SECRET_BUTTON = registerBlock("quartz_brick_secret_button",
            () -> new BigBrickSecretButton(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.QUARTZ).strength(0.8f).noOcclusion().sound(SoundType.STONE).requiresCorrectToolForDrops(), byName("quartz_bricks")));

    public static final RegistryObject<Block> DARK_PRISMARINE_SECRET_BUTTON = registerBlock("dark_prismarine_secret_button",
            () -> new FullBlockBrickSecretButton(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.DIAMOND).strength(1.5f, 6.0f).noOcclusion().sound(SoundType.STONE).requiresCorrectToolForDrops(), byName("dark_prismarine")));

    public static final RegistryObject<Block> POLISHED_BLACKSTONE_BRICK_SECRET_BUTTON = registerBlock("polished_blackstone_brick_secret_button",
            () -> new BigBrickSecretButton(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_BLACK).strength(2.0f, 6.0f).noOcclusion().sound(SoundType.STONE).requiresCorrectToolForDrops(), byName("polished_blackstone_bricks")));

    public static final RegistryObject<Block> CRACKED_POLISHED_BLACKSTONE_BRICK_SECRET_BUTTON = registerBlock("cracked_polished_blackstone_brick_secret_button",
            () -> new BigBrickSecretButton(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_BLACK).strength(2.0f, 6.0f).noOcclusion().sound(SoundType.STONE).requiresCorrectToolForDrops(), byName("cracked_polished_blackstone_bricks")));

    public static final RegistryObject<Block> CHISELED_POLISHED_BLACKSTONE_SECRET_BUTTON = registerBlock("chiseled_polished_blackstone_secret_button",
            () -> new ChiseledStoneBrickSecretButton(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_BLACK).strength(2.0f, 6.0f).noOcclusion().sound(SoundType.STONE).requiresCorrectToolForDrops(), byName("chiseled_polished_blackstone")));

    public static final RegistryObject<Block> NETHER_BRICK_SECRET_BUTTON = registerBlock("nether_brick_secret_button",
            () -> new FullBlockBrickSecretButton(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.NETHER).strength(2.0f, 6.0f).noOcclusion().sound(SoundType.NETHER_BRICKS).requiresCorrectToolForDrops(), byName("nether_bricks")));

    public static final RegistryObject<Block> CRACKED_NETHER_BRICK_SECRET_BUTTON = registerBlock("cracked_nether_brick_secret_button",
            () -> new FullBlockBrickSecretButton(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.NETHER).strength(2.0f, 6.0f).noOcclusion().sound(SoundType.NETHER_BRICKS).requiresCorrectToolForDrops(), byName("cracked_nether_bricks")));

    public static final RegistryObject<Block> CHISELED_NETHER_BRICK_SECRET_BUTTON = registerBlock("chiseled_nether_brick_secret_button",
            () -> new ChiseledNetherBrickSecretButton(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.NETHER).strength(2.0f, 6.0f).noOcclusion().sound(SoundType.NETHER_BRICKS).requiresCorrectToolForDrops(), byName("chiseled_nether_bricks")));

    public static final RegistryObject<Block> RED_NETHER_BRICK_SECRET_BUTTON = registerBlock("red_nether_brick_secret_button",
            () -> new FullBlockBrickSecretButton(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.NETHER).strength(2.0f, 6.0f).noOcclusion().sound(SoundType.NETHER_BRICKS).requiresCorrectToolForDrops(), byName("red_nether_bricks")));

    /**
     * Misc
     */

    public static final RegistryObject<Block> DOORBELL = registerBlock("doorbell",
            () -> new Doorbell(doorbellProperties()));

    public static final RegistryObject<Block> DOORBELL_BUTTON = registerBlock("doorbell_button",
            () -> new DoorbellButton(doorbellProperties()));

    public static final RegistryObject<Block> LAMP_BUTTON = registerBlock("lamp_button",
            () -> new LampButton(lampProperties(), false));

    public static final RegistryObject<Block> LAMP_LEVER = registerBlock("lamp_lever",
            () -> new LampButton(lampProperties(), true));

    public static final RegistryObject<Block> LETTER_BUTTON = registerBlock("letter_button",
            () -> new LetterButton(BlockBehaviour.Properties.of(Material.WOOD).strength(0.5f).noOcclusion().noCollission().sound(SoundType.WOOD)));

    public static final RegistryObject<Block> LANTERN_BUTTON = registerBlock("lantern_button",
            () -> new LanternButton(lanternProperties(15), false));

    public static final RegistryObject<Block> LANTERN_LEVER = registerBlock("lantern_lever",
            () -> new LanternButton(lanternProperties(15), true));

    public static final RegistryObject<Block> SOUL_LANTERN_BUTTON = registerBlock("soul_lantern_button",
            () -> new LanternButton(lanternProperties(10), false));

    public static final RegistryObject<Block> SOUL_LANTERN_LEVER = registerBlock("soul_lantern_lever",
            () -> new LanternButton(lanternProperties(10), true));

    /**
     * Console Buttons
     */

    public static final RegistryObject<Block> SMALL_CONSOLE_BUTTON = registerBlock("small_console_button",
            () -> new SmallConsoleButton(consoleButtonProperties(), false));
    public static final RegistryObject<Block> SMALL_CONSOLE_LEVER = registerBlock("small_console_lever",
            () -> new SmallConsoleButton(consoleButtonProperties(), true));
    public static final RegistryObject<Block> CONSOLE_BUTTON = registerBlock("console_button",
            () -> new ConsoleButton(consoleButtonProperties(), false));
    public static final RegistryObject<Block> CONSOLE_LEVER = registerBlock("console_lever",
            () -> new ConsoleButton(consoleButtonProperties(), true));
    public static final RegistryObject<Block> LARGE_CONSOLE_BUTTON = registerBlock("large_console_button",
            () -> new LargeConsoleButton(consoleButtonProperties(), false));
    public static final RegistryObject<Block> LARGE_CONSOLE_LEVER = registerBlock("large_console_lever",
            () -> new LargeConsoleButton(consoleButtonProperties(), true));
    public static final RegistryObject<Block> BIG_CONSOLE_BUTTON = registerBlock("big_console_button",
            () -> new LargeConsoleButton(consoleButtonProperties(), false));
    public static final RegistryObject<Block> BIG_CONSOLE_LEVER = registerBlock("big_console_lever",
            () -> new LargeConsoleButton(consoleButtonProperties(), true));

    /**
     * Torches
     */

    public static final RegistryObject<Block> TORCH_BUTTON = registerTorchBlock("torch_button",
            () -> new TorchButton(torchProperties(14), ParticleTypes.FLAME, byName("torch")));

    public static final RegistryObject<Block> WALL_TORCH_BUTTON = registerTorchBlock("wall_torch_button",
            () -> new WallTorchButton(torchProperties(14).lootFrom(TORCH_BUTTON), ParticleTypes.FLAME, byName("torch")));

    public static final RegistryObject<Block> TORCH_LEVER = registerTorchBlock("torch_lever",
            () -> new TorchLever(torchProperties(14), ParticleTypes.FLAME, byName("torch")));

    public static final RegistryObject<Block> WALL_TORCH_LEVER = registerTorchBlock("wall_torch_lever",
            () -> new WallTorchLever(torchProperties(14).lootFrom(TORCH_LEVER), ParticleTypes.FLAME, byName("torch")));

    public static final RegistryObject<Block> SOUL_TORCH_BUTTON = registerTorchBlock("soul_torch_button",
            () -> new TorchButton(torchProperties(10), ParticleTypes.SOUL_FIRE_FLAME, byName("soul_torch")));

    public static final RegistryObject<Block> SOUL_WALL_TORCH_BUTTON = registerTorchBlock("soul_wall_torch_button",
            () -> new WallTorchButton(torchProperties(10).lootFrom(SOUL_TORCH_BUTTON), ParticleTypes.SOUL_FIRE_FLAME, byName("soul_torch")));

    public static final RegistryObject<Block> SOUL_TORCH_LEVER = registerTorchBlock("soul_torch_lever",
            () -> new TorchLever(torchProperties(10), ParticleTypes.SOUL_FIRE_FLAME, byName("soul_torch")));

    public static final RegistryObject<Block> SOUL_WALL_TORCH_LEVER = registerTorchBlock("soul_wall_torch_lever",
            () -> new WallTorchLever(torchProperties(10).lootFrom(SOUL_TORCH_LEVER), ParticleTypes.SOUL_FIRE_FLAME, byName("soul_torch")));

    public static final RegistryObject<Block> REDSTONE_TORCH_BUTTON = registerTorchBlock("redstone_torch_button",
            () -> new RedstoneTorchButton(torchProperties(7), byName("redstone_torch")));

    public static final RegistryObject<Block> REDSTONE_WALL_TORCH_BUTTON = registerTorchBlock("redstone_wall_torch_button",
            () -> new RedstoneWallTorchButton(torchProperties(7).lootFrom(REDSTONE_TORCH_BUTTON), byName("redstone_torch")));

    public static final RegistryObject<Block> REDSTONE_TORCH_LEVER = registerTorchBlock("redstone_torch_lever",
            () -> new RedstoneTorchLever(torchProperties(7), byName("redstone_torch")));

    public static final RegistryObject<Block> REDSTONE_WALL_TORCH_LEVER = registerTorchBlock("redstone_wall_torch_lever",
            () -> new RedstoneWallTorchLever(torchProperties(7).lootFrom(REDSTONE_TORCH_LEVER), byName("redstone_torch")));

    /**
     * Methods
     */

    private static Block byName(String block) {
        return ForgeRegistries.BLOCKS.getValue(new ResourceLocation("minecraft", block));
    }

    private static BlockBehaviour.Properties doorbellProperties() {
        return BlockBehaviour.Properties.of(Material.DECORATION).strength(0.5f).noCollission().sound(SoundType.WOOD);
    }

    private static BlockBehaviour.Properties lampProperties() {
        return BlockBehaviour.Properties.of(Material.DECORATION).strength(0.3f).sound(SoundType.GLASS).lightLevel(InfinityButtonsBlocks::getPressLight);
    }

    private static BlockBehaviour.Properties lanternProperties(int light) {
        return BlockBehaviour.Properties.of(Material.METAL).strength(3.5f).noOcclusion().sound(SoundType.LANTERN).requiresCorrectToolForDrops().lightLevel((p_50876_) -> light);
    }

    private static int getPressLight(BlockState state) {
        return state.getValue(LampButton.PRESSED) ? 15 : 0;
    }

    public static BlockBehaviour.Properties consoleButtonProperties() {
        return BlockBehaviour.Properties.of(Material.METAL).strength(0.5f).sound(SoundType.METAL);
    }

    private static BlockBehaviour.Properties torchProperties(int light) {
        return BlockBehaviour.Properties.of(Material.DECORATION).noCollission().instabreak().lightLevel((p_50876_) -> light).sound(SoundType.WOOD);
    }

    private static RegistryObject<Block> registerStoneButton(String type) {
        return registerBlock(type + "_button", () -> new StoneButton(BlockBehaviour.Properties.of(Material.DECORATION).strength(0.5f).noCollission().sound(SoundType.STONE), false));
    }

    private static RegistryObject<Block> registerCopperButton(String type, WeatheringCopper.WeatherState state) {
        return registerBlock(type + "_button", () -> new CopperButton(BlockBehaviour.Properties.of(Material.DECORATION).strength(0.5f).noCollission().sound(SoundType.COPPER), false, state));
    }

    private static RegistryObject<Block> registerWaxedCopperButton(String type) {
        return registerBlock("waxed_" + type + "_button", () -> new WaxedCopperButton(BlockBehaviour.Properties.of(Material.DECORATION).strength(0.5f).noCollission().sound(SoundType.COPPER), false));
    }

    private static RegistryObject<Block> registerStickyCopperButton(String type) {
        return registerBlock("sticky_" + type + "_button", () -> new StickyCopperButton(BlockBehaviour.Properties.of(Material.DECORATION).strength(0.5f).noCollission().sound(SoundType.COPPER), false));
    }

    private static RegistryObject<Block> registerArrowButton(String type) {
        return registerBlock(type + "_button", () -> new ArrowButton(BlockBehaviour.Properties.of(Material.DECORATION).strength(0.5f).noCollission().sound(SoundType.METAL), false));
    }

    private static RegistryObject<Block> registerPrismarineButton(String type) {
        return registerBlock(type + "_button", () -> new PrismarineButton(BlockBehaviour.Properties.of(Material.DECORATION).strength(0.5f).noCollission().sound(SoundType.STONE), false));
    }

    private static RegistryObject<Block> registerSandButton(String type, boolean gravel) {
        return registerBlock(type + "_button", () -> new FallingButton(gravel, BlockBehaviour.Properties.of(Material.DECORATION).strength(0.5f).noCollission().sound(gravel ? SoundType.GRAVEL : SoundType.SAND), false));
    }

    private static RegistryObject<Block> registerConcretePowderButton(String color) {
        return registerSandButton(color + "_concrete_powder", false);
    }

    private static RegistryObject<Block> registerWoodenLargeButton(String wood) {
        return registerBlock(wood + "_large_button", () -> new WoodenButton(BlockBehaviour.Properties.of(Material.DECORATION).strength(0.5f).noCollission().sound(SoundType.WOOD), true));
    }

    private static RegistryObject<Block> registerStoneLargeButton(String type) {
        return registerBlock(type + "_large_button", () -> new StoneButton(BlockBehaviour.Properties.of(Material.DECORATION).strength(0.5f).noCollission().sound(SoundType.STONE), true));
    }

    private static RegistryObject<Block> registerCopperLargeButton(String type, WeatheringCopper.WeatherState state) {
        return registerBlock(type + "_large_button", () -> new CopperButton(BlockBehaviour.Properties.of(Material.DECORATION).strength(0.5f).noCollission().sound(SoundType.COPPER), true, state));
    }

    private static RegistryObject<Block> registerWaxedCopperLargeButton(String type) {
        return registerBlock("waxed_" + type + "_large_button", () -> new WaxedCopperButton(BlockBehaviour.Properties.of(Material.DECORATION).strength(0.5f).noCollission().sound(SoundType.COPPER), true));
    }

    private static RegistryObject<Block> registerStickyCopperLargeButton(String type) {
        return registerBlock("sticky_" + type + "_large_button", () -> new StickyCopperButton(BlockBehaviour.Properties.of(Material.DECORATION).strength(0.5f).noCollission().sound(SoundType.COPPER), true));
    }

    private static RegistryObject<Block> registerArrowLargeButton(String type) {
        return registerBlock(type + "_large_button", () -> new ArrowButton(BlockBehaviour.Properties.of(Material.DECORATION).strength(0.5f).noCollission().sound(SoundType.METAL), true));
    }

    private static RegistryObject<Block> registerPrismarineLargeButton(String type) {
        return registerBlock(type + "_large_button", () -> new PrismarineButton(BlockBehaviour.Properties.of(Material.DECORATION).strength(0.5f).noCollission().sound(SoundType.STONE), true));
    }

    private static RegistryObject<Block> registerSandLargeButton(String type, boolean gravel) {
        return registerBlock(type + "_large_button", () -> new FallingButton(gravel, BlockBehaviour.Properties.of(Material.DECORATION).strength(0.5f).noCollission().sound(gravel ? SoundType.GRAVEL : SoundType.SAND), true));
    }

    private static RegistryObject<Block> registerConcretePowderLargeButton(String color) {
        return registerSandLargeButton(color + "_concrete_powder", false);
    }

    private static RegistryObject<Block> registerEmergencyButton(String color) {
        return registerBlock(color + "_emergency_button", () -> new EmergencyButton(BlockBehaviour.Properties.of(Material.DECORATION).strength(0.5f).noOcclusion().sound(SoundType.METAL)));
    }

    private static RegistryObject<Block> registerSafeEmergencyButton(String color) {
        return registerSafeEmergencyButtonBlock(color + "_safe_emergency_button", () -> new SafeEmergencyButton(BlockBehaviour.Properties.of(Material.DECORATION).strength(0.5f).noOcclusion().sound(SoundType.METAL)));
    }

    private static RegistryObject<Block> registerPlankSecretButton(String wood, MaterialColor materialColor) {
        return registerBlock(wood + "_plank_secret_button", () -> new PlankSecretButton(BlockBehaviour.Properties.of(Material.WOOD, materialColor).strength(2.0f, 3.0f).noOcclusion().sound(SoundType.WOOD), byName(wood + "_planks")));
    }

    private static RegistryObject<Block> registerNetherPlankSecretButton(String wood, MaterialColor materialColor) {
        return registerBlock(wood + "_plank_secret_button", () -> new PlankSecretButton(BlockBehaviour.Properties.of(Material.NETHER_WOOD, materialColor).strength(2.0f, 3.0f).noOcclusion().sound(SoundType.WOOD), byName(wood + "_planks")));
    }

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<T> registerSafeEmergencyButtonBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerSafeEmergencyButtonItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block) {
        RegistryObject<Item> register = InfinityButtonsItems.ITEMS.register(name, () -> new BlockItem(block.get(),
                new Item.Properties().tab(InfinityButtonsItemGroup.INFINITYBUTTONS)));
        InfinityButtonsUtil.REGISTRY_FOR_TAB.add(register);
    }

    private static <T extends Block> void registerSafeEmergencyButtonItem(String name, RegistryObject<T> block) {
        RegistryObject<Item> register = InfinityButtonsItems.ITEMS.register(name, () -> new SafeEmergencyButtonItem(block.get(),
                new Item.Properties().tab(InfinityButtonsItemGroup.INFINITYBUTTONS)));
        InfinityButtonsUtil.REGISTRY_FOR_TAB.add(register);
    }

    private static <T extends Block>RegistryObject<T> registerTorchBlock(String name, Supplier<T> block) {
        return BLOCKS.register(name, block);
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }

}
