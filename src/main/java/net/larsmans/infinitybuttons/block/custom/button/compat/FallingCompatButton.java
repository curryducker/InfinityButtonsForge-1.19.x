package net.larsmans.infinitybuttons.block.custom.button.compat;

import net.larsmans.infinitybuttons.block.custom.button.FallingButton;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootContext;

import java.util.List;

public class FallingCompatButton extends FallingButton {
    public FallingCompatButton(boolean gravel, Properties properties, boolean large) {
        super(gravel, properties, large);
    }

    @Override
    public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
        return List.of(new ItemStack(this));
    }
}
