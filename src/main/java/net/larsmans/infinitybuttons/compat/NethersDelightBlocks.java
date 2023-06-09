package net.larsmans.infinitybuttons.compat;

import net.larsmans.infinitybuttons.InfinityButtons;
import net.larsmans.infinitybuttons.block.InfinityButtonsBlocks;
import net.larsmans.infinitybuttons.block.custom.HoglinMountButton;
import net.larsmans.infinitybuttons.block.custom.torch.compat.PropelTorchButton;
import net.larsmans.infinitybuttons.block.custom.torch.compat.PropelTorchLever;
import net.larsmans.infinitybuttons.block.custom.torch.compat.PropelWallTorchButton;
import net.larsmans.infinitybuttons.block.custom.torch.compat.PropelWallTorchLever;
import net.larsmans.infinitybuttons.item.InfinityButtonsItemGroup;
import net.larsmans.infinitybuttons.item.InfinityButtonsItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class NethersDelightBlocks {

    public static BlockBehaviour.Properties PROP = BlockBehaviour.Properties.of(Material.PLANT).noCollission().instabreak().lightLevel((p_50876_) -> 12).sound(SoundType.WOOD);

    public static final RegistryObject<Block> HOGLIN_MOUNT_BUTTON = registerBlock(
            () -> new HoglinMountButton(BlockBehaviour.Properties.copy(Blocks.BROWN_WOOL).lightLevel((p_50876_) -> 1)));

    public static final RegistryObject<Block> PROPELPLANT_TORCH_BUTTON = registerTorchBlock("propelplant_torch_button",
            () -> new PropelTorchButton(PROP, byName("propelplant_torch")));

    public static final RegistryObject<Block> PROPELPLANT_WALL_TORCH_BUTTON = registerTorchBlock("propelplant_wall_torch_button",
            () -> new PropelWallTorchButton(PROP, byName("propelplant_torch")));

    public static final RegistryObject<Block> PROPELPLANT_TORCH_LEVER = registerTorchBlock("propelplant_torch_lever",
            () -> new PropelTorchLever(PROP, byName("propelplant_torch")));

    public static final RegistryObject<Block> PROPELPLANT_WALL_TORCH_LEVER = registerTorchBlock("propelplant_wall_torch_lever",
            () -> new PropelWallTorchLever(PROP, byName("propelplant_torch")));

    /**
     * Methods
     */

    private static Block byName(String block) {
        return ForgeRegistries.BLOCKS.getValue(new ResourceLocation("nethers_delight", block));
    }

    private static <T extends Block> RegistryObject<T> registerBlock(Supplier<T> block) {
        RegistryObject<T> toReturn = InfinityButtonsBlocks.BLOCKS.register("hoglin_mount_button", block);
        registerBlockItem(toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(RegistryObject<T> block) {
        InfinityButtonsItems.ITEMS.register("hoglin_mount_button", () -> new BlockItem(block.get(),
                new Item.Properties().tab(InfinityButtonsItemGroup.INFINITYBUTTONS)));
    }

    private static <T extends Block> RegistryObject<T> registerTorchBlock(String name, Supplier<T> block) {
        return InfinityButtonsBlocks.BLOCKS.register(name, block);
    }

    public static void registerCompatBlocks() {
        InfinityButtons.LOGGER.debug("Registering Nether's Delight Compat Blocks for Infinity Buttons");
    }
}
