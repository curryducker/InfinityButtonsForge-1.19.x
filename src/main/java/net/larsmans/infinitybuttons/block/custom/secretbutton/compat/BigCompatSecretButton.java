package net.larsmans.infinitybuttons.block.custom.secretbutton.compat;

import net.larsmans.infinitybuttons.block.custom.secretbutton.BigBrickSecretButton;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootContext;

import java.util.List;

public class BigCompatSecretButton extends BigBrickSecretButton {
    public BigCompatSecretButton(Properties properties) {
        super(properties);
    }

    @Override
    public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
        return List.of(new ItemStack(this));
    }
}
