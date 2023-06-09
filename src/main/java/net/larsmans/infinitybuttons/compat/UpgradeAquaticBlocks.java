package net.larsmans.infinitybuttons.compat;

import net.larsmans.infinitybuttons.InfinityButtons;
import net.larsmans.infinitybuttons.block.InfinityButtonsBlocks;
import net.larsmans.infinitybuttons.block.custom.secretbutton.compat.BigCompatSecretButton;
import net.larsmans.infinitybuttons.block.custom.secretbutton.compat.ChiseledToothSecretButton;
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

public class UpgradeAquaticBlocks {

    public static final RegistryObject<Block> DRIFTWOOD_BOOKSHELF_SECRET_BUTTON = registerBookshelf("driftwood");
    public static final RegistryObject<Block> RIVER_BOOKSHELF_SECRET_BUTTON = registerBookshelf("river");

    public static final RegistryObject<Block> KELPY_STONE_BRICK_SECRET_BUTTON = registerBlock("kelpy_stone_brick_secret_button",
            () -> new BigCompatSecretButton(BlockBehaviour.Properties.of(Material.STONE).strength(1.5f, 6.0f).noOcclusion().sound(SoundType.STONE).requiresCorrectToolForDrops(), byName("kelpy_stone_bricks")));

    public static final RegistryObject<Block> TONGUE_KELPY_STONE_BRICK_SECRET_BUTTON = registerKelpyBlock("tongue");
    public static final RegistryObject<Block> THORNY_KELPY_STONE_BRICK_SECRET_BUTTON = registerKelpyBlock("thorny");
    public static final RegistryObject<Block> OCHRE_KELPY_STONE_BRICK_SECRET_BUTTON = registerKelpyBlock("ochre");
    public static final RegistryObject<Block> POLAR_KELPY_STONE_BRICK_SECRET_BUTTON = registerKelpyBlock("polar");


    public static final RegistryObject<Block> CHISELED_TOOTH_BRICK_SECRET_BUTTON = registerBlock("chiseled_tooth_brick_secret_button",
            () -> new ChiseledToothSecretButton(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.SAND).strength(3.0f, 9.0f).noOcclusion().sound(SoundType.STONE).requiresCorrectToolForDrops(), byName("chiseled_tooth_bricks")));


    /**
     * Methods
     */

    private static Block byName(String block) {
        return ForgeRegistries.BLOCKS.getValue(new ResourceLocation("upgrade_aquatic", block));
    }

    private static RegistryObject<Block> registerBookshelf(String wood) {
        return registerBlock(wood + "_bookshelf_secret_button",
                () -> new CompatBookshelfSecretButton(BlockBehaviour.Properties.of(Material.WOOD).strength(1.5f).noOcclusion().sound(SoundType.WOOD), byName(wood + "_bookshelf")));
    }

    private static RegistryObject<Block> registerKelpyBlock(String type) {
        return registerBlock(type + "_kelpy_stone_brick_secret_button",
                () -> new BigCompatSecretButton(BlockBehaviour.Properties.of(Material.STONE).strength(1.5f, 6.0f).noOcclusion().sound(SoundType.STONE).requiresCorrectToolForDrops(), byName(type + "_kelpy_stone_bricks")));
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
        InfinityButtons.LOGGER.debug("Registering Upgrade Aquatic Compat Blocks for Infinity Buttons");
    }
}
