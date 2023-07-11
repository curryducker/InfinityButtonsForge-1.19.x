package net.larsmans.infinitybuttons.item;

import net.larsmans.infinitybuttons.InfinityButtonsUtil;
import net.larsmans.infinitybuttons.block.InfinityButtonsBlocks;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.RegistryObject;

public class InfinityButtonsItemGroup extends CreativeModeTab {

    public static final CreativeModeTab INFINITYBUTTONS = new InfinityButtonsItemGroup("infinityButtonsTab");

    public InfinityButtonsItemGroup(String infinityButtonsTab) {
        super(infinityButtonsTab);
    }

    @Override
    public ItemStack makeIcon() {
        return new ItemStack(InfinityButtonsBlocks.OAK_LARGE_BUTTON.get());
    }

    @Override
    public void fillItemList(NonNullList<ItemStack> pItems) {
        for(RegistryObject<Item> entry : InfinityButtonsUtil.REGISTRY_FOR_TAB) {
            Item item = entry.get();
            item.fillItemCategory(this, pItems);
        }
    }

}
