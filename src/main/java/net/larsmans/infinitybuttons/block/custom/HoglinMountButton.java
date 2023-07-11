package net.larsmans.infinitybuttons.block.custom;

import net.larsmans.infinitybuttons.block.custom.button.AbstractWallButton;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.List;

public class HoglinMountButton extends AbstractWallButton {

    protected static final VoxelShape NORTH_SHAPE = box(0, 0, 13, 16, 16, 16);
    protected static final VoxelShape EAST_SHAPE =  box(0, 0, 0, 3, 16, 16);
    protected static final VoxelShape SOUTH_SHAPE =  box(0, 0, 0, 16, 16, 3);
    protected static final VoxelShape WEST_SHAPE =  box(13, 0, 0, 16, 16, 16);

    public HoglinMountButton (Properties settings) {
        super(settings, NORTH_SHAPE, EAST_SHAPE, SOUTH_SHAPE, WEST_SHAPE, NORTH_SHAPE, EAST_SHAPE, SOUTH_SHAPE, WEST_SHAPE);
    }

    @Override
    public int getPressDuration() {
        return 15;
    }

    @Override
    protected SoundEvent getSoundEvent(boolean pressed) {
        return pressed ? SoundEvents.STONE_BUTTON_CLICK_ON : SoundEvents.STONE_BUTTON_CLICK_OFF;
    }

    @Override
    public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
        return List.of(new ItemStack(this));
    }
}
