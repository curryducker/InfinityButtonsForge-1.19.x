package net.larsmans.infinitybuttons.item.custom;

import net.larsmans.infinitybuttons.advancement.InfinityButtonsTriggers;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

public class SafeEmergencyButtonItem extends BlockItem {
    public SafeEmergencyButtonItem(Block pBlock, Properties pProperties) {
        super(pBlock, pProperties);
    }

    @Override
    public boolean canEquip(ItemStack stack, EquipmentSlot armorType, Entity entity) {
        return armorType == EquipmentSlot.HEAD;
    }

    @Override
    public void onArmorTick(ItemStack stack, Level level, Player player) {
        super.onArmorTick(stack, level, player);
        if (player instanceof ServerPlayer) {
            InfinityButtonsTriggers.SAFETY_TRIGGER.trigger((ServerPlayer) player);
        }
    }
}
