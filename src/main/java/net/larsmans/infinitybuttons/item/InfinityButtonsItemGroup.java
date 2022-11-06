package net.larsmans.infinitybuttons.item;

import net.larsmans.infinitybuttons.block.InfinityButtonsBlocks;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class InfinityButtonsItemGroup {

    public static final CreativeModeTab INFINITYBUTTONS = new CreativeModeTab("infinityButtonsTab")
    {
        @Override
        public ItemStack makeIcon()
        {
            return new ItemStack(InfinityButtonsBlocks.OAK_LARGE_BUTTON.get());
        }
    };

    public static final CreativeModeTab INFINITYBUTTONSCOMPAT = new CreativeModeTab("infinityButtonsCompatTab")
    {
        @Override
        public ItemStack makeIcon()
        {
            return new ItemStack(InfinityButtonsBlocks.RED_EMERGENCY_BUTTON.get());
        }
    };

}
