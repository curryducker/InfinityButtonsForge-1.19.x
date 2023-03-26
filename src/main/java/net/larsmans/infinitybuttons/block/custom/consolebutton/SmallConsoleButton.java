package net.larsmans.infinitybuttons.block.custom.consolebutton;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class SmallConsoleButton extends ConsoleButton{
    protected static final VoxelShape CEILING_X_SHAPE = box(5, 14, 3, 11, 16, 13);
    protected static final VoxelShape CEILING_Z_SHAPE = box(3, 14, 5, 13, 16, 11);
    protected static final VoxelShape FLOOR_X_SHAPE = box(5, 0, 3, 11, 2, 13);
    protected static final VoxelShape FLOOR_Z_SHAPE = box(3, 0, 5, 13, 2, 11);
    protected static final VoxelShape NORTH_SHAPE = box(3, 5, 14, 13, 11, 16);
    protected static final VoxelShape SOUTH_SHAPE = box(3, 5, 0, 13, 11, 2);
    protected static final VoxelShape WEST_SHAPE = box(14, 5, 3, 16, 11, 13);
    protected static final VoxelShape EAST_SHAPE = box(0, 5, 3, 2, 11, 13);

    public SmallConsoleButton(Properties settings, boolean lever) {
        super(settings, lever);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        Direction direction = state.getValue(FACING);
        switch (state.getValue(FACE)) {
            case FLOOR -> {
                if (direction.getAxis() == Direction.Axis.X) {
                    return FLOOR_X_SHAPE;
                }
                return FLOOR_Z_SHAPE;
            }
            case WALL -> {
                switch (direction) {
                    case EAST -> {
                        return EAST_SHAPE;
                    }
                    case WEST -> {
                        return WEST_SHAPE;
                    }
                    case SOUTH -> {
                        return SOUTH_SHAPE;
                    }
                }
                return NORTH_SHAPE;
            }
        }
        if (direction.getAxis() == Direction.Axis.X) {
            return CEILING_X_SHAPE;
        }
        return CEILING_Z_SHAPE;
    }
}
