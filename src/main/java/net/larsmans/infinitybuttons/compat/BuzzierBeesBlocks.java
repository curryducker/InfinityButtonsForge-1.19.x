package net.larsmans.infinitybuttons.compat;

import net.larsmans.infinitybuttons.InfinityButtons;
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

public class BuzzierBeesBlocks {

    public static final RegistryObject<Block> HONEYCOMB_BRICK_SECRET_BUTTON = registerBlock("honeycomb_brick_secret_button",
            () -> new FullCompatBrickSecretButton(BlockBehaviour.Properties.of(Material.CLAY, MaterialColor.COLOR_ORANGE).strength(2.0f, 6.0f).noOcclusion().sound(SoundType.CORAL_BLOCK).requiresCorrectToolForDrops(), byName("honeycomb_bricks")));

    public static final RegistryObject<Block> CHISELED_HONEYCOMB_BRICK_SECRET_BUTTON = registerBlock("chiseled_honeycomb_brick_secret_button",
            () -> new ChiseledCompatSecretButton(BlockBehaviour.Properties.of(Material.CLAY, MaterialColor.COLOR_ORANGE).strength(2.0f, 6.0f).noOcclusion().sound(SoundType.CORAL_BLOCK).requiresCorrectToolForDrops(), byName("chiseled_honeycomb_bricks")));

    /**
     * Methods
     */

    private static Block byName(String block) {
        return ForgeRegistries.BLOCKS.getValue(new ResourceLocation("buzzier_bees", block));
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
