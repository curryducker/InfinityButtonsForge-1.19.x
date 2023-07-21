package net.larsmans.infinitybuttons.compat;

import net.larsmans.infinitybuttons.InfinityButtons;
import net.larsmans.infinitybuttons.InfinityButtonsUtil;
import net.larsmans.infinitybuttons.block.InfinityButtonsBlocks;
import net.larsmans.infinitybuttons.block.custom.secretbutton.AbstractSecretButton;
import net.larsmans.infinitybuttons.block.custom.secretbutton.compat.ChiseledStonepatBrickSecretButton;
import net.larsmans.infinitybuttons.item.InfinityButtonsItemGroup;
import net.larsmans.infinitybuttons.item.InfinityButtonsItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class SavageAndRavageBlocks {

    public static final RegistryObject<Block> CHISELED_GLOOMY_TILE_SECRET_BUTTON = registerBlock(
            () -> new ChiseledStonepatBrickSecretButton(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.TERRACOTTA_LIGHT_BLUE).strength(1.5f, 6.0f).noOcclusion().sound(SoundType.STONE).requiresCorrectToolForDrops().lightLevel(SavageAndRavageBlocks::getPressLight), ForgeRegistries.BLOCKS.getValue(new ResourceLocation("savage_and_ravage", "chiseled_gloomy_tiles"))));

    /**
     * Methods
     */

    private static <T extends Block> RegistryObject<T> registerBlock(Supplier<T> block) {
        RegistryObject<T> toReturn = InfinityButtonsBlocks.BLOCKS.register("chiseled_gloomy_tile_secret_button", block);
        registerBlockItem(toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(RegistryObject<T> block) {
        RegistryObject<Item> register = InfinityButtonsItems.ITEMS.register("chiseled_gloomy_tile_secret_button", () -> new BlockItem(block.get(),
                new Item.Properties().tab(InfinityButtonsItemGroup.INFINITYBUTTONS)));
        InfinityButtonsUtil.REGISTRY_FOR_TAB.add(register);
    }

    public static int getPressLight(BlockState state) {
        return state.getValue(AbstractSecretButton.PRESSED) ? 7 : 0;
    }

    public static void registerCompatBlocks() {
        InfinityButtons.LOGGER.debug("Registering Savage and Ravage Compat Blocks for Infinity Buttons");
    }
}
