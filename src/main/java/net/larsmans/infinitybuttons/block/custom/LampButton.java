package net.larsmans.infinitybuttons.block.custom;

import net.larsmans.infinitybuttons.block.custom.button.AbstractButton;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class LampButton extends AbstractButton {

    private static final VoxelShape FLOOR_SHAPE = Shapes.or(
            Block.box(3, 0, 3, 13, 1, 13),
            Block.box(4, 1, 4, 12, 8, 12));
    private static final VoxelShape CEILING_SHAPE = Shapes.or(
            Block.box(3, 15, 3, 13, 16, 13),
            Block.box(4, 8, 4, 12, 15, 12));
    private static final VoxelShape NORTH_SHAPE = Shapes.or(
            Block.box(3, 3, 15, 13, 13, 16),
            Block.box(4, 4, 8, 12, 12, 15));
    private static final VoxelShape EAST_SHAPE = Shapes.or(
            Block.box(0, 3, 3, 1, 13, 13),
            Block.box(1, 4, 4, 8, 12, 12));
    private static final VoxelShape SOUTH_SHAPE = Shapes.or(
            Block.box(3, 3, 0, 13, 13, 1),
            Block.box(4, 4, 1, 12, 12, 8));
    private static final VoxelShape WEST_SHAPE = Shapes.or(
            Block.box(15, 3, 3, 16, 13, 13),
            Block.box(8, 4, 4, 15, 12, 12));

    public LampButton(Properties properties) {
        super(false, false, properties);
    }

    @Override
    public int getPressDuration() {
        return 20;
    }

    @Override
    protected SoundEvent getSoundEvent(boolean pressed) {
        return pressed ? SoundEvents.STONE_BUTTON_CLICK_ON : SoundEvents.STONE_BUTTON_CLICK_OFF;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        Direction direction = state.getValue(FACING);
        switch(state.getValue(FACE)) {
            case FLOOR -> {
                return FLOOR_SHAPE;
            }
            case WALL -> {
                switch (direction) {
                    case NORTH -> {
                        return NORTH_SHAPE;
                    }
                    case EAST -> {
                        return EAST_SHAPE;
                    }
                    case SOUTH -> {
                        return SOUTH_SHAPE;
                    }
                }
                return WEST_SHAPE;
            }
        }
        return CEILING_SHAPE;
    }
}
