package net.larsmans.infinitybuttons.block.custom;

import net.larsmans.infinitybuttons.block.custom.button.AbstractWallButton;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.shapes.VoxelShape;

public class HoglinMountButton extends AbstractWallButton {

    protected static final VoxelShape NORTH_SHAPE = Block.box(0, 0, 13, 16, 16, 16);
    protected static final VoxelShape EAST_SHAPE =  Block.box(0, 0, 0, 3, 16, 16);
    protected static final VoxelShape SOUTH_SHAPE =  Block.box(0, 0, 0, 16, 16, 3);
    protected static final VoxelShape WEST_SHAPE =  Block.box(13, 0, 0, 16, 16, 16);

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
}
