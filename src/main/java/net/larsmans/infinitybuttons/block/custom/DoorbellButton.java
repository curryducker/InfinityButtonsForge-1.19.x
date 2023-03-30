package net.larsmans.infinitybuttons.block.custom;

import net.larsmans.infinitybuttons.InfinityButtonsUtil;
import net.larsmans.infinitybuttons.block.custom.button.AbstractWallButton;
import net.larsmans.infinitybuttons.sounds.InfinityButtonsSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class DoorbellButton extends AbstractWallButton {

    protected static final VoxelShape NORTH_PRESSED_SHAPE =
            Block.box(6, 4, 14, 10, 12, 16);
    protected static final VoxelShape EAST_PRESSED_SHAPE =
            Block.box(0, 4, 6, 2, 12, 10);
    protected static final VoxelShape SOUTH_PRESSED_SHAPE =
            Block.box(6, 4, 0, 10, 12, 2);
    protected static final VoxelShape WEST_PRESSED_SHAPE =
            Block.box(14, 4, 6, 16, 12, 10);
    protected static final VoxelShape NORTH_SHAPE = Shapes.or(NORTH_PRESSED_SHAPE,
            Block.box(7, 6, 13, 9, 10, 14));
    protected static final VoxelShape EAST_SHAPE = Shapes.or(EAST_PRESSED_SHAPE,
            Block.box(2, 6, 7, 3, 10, 9));
    protected static final VoxelShape SOUTH_SHAPE = Shapes.or(SOUTH_PRESSED_SHAPE,
            Block.box(7, 6, 2, 9, 10, 3));
    protected static final VoxelShape WEST_SHAPE = Shapes.or(WEST_PRESSED_SHAPE,
            Block.box(13, 6, 7, 14, 10, 9));

    public DoorbellButton(Properties properties) {
        super(properties, NORTH_SHAPE, EAST_SHAPE, SOUTH_SHAPE, WEST_SHAPE, NORTH_PRESSED_SHAPE, EAST_PRESSED_SHAPE, SOUTH_PRESSED_SHAPE, WEST_PRESSED_SHAPE);
    }

    @Override
    public int getPressDuration() {
        return 15;
    }

    @Override
    protected void playSound(@Nullable Player playerIn, LevelAccessor worldIn, BlockPos pos, boolean pressed) {
        worldIn.playSound(pressed ? playerIn : null, pos, this.getSoundEvent(pressed), SoundSource.BLOCKS, 0.3f, 1f);
    }

    @Override
    protected SoundEvent getSoundEvent(boolean pressed) {
        return InfinityButtonsSounds.DOORBELL.get();
    }

    @Override
    public void tick(BlockState state, ServerLevel worldIn, BlockPos pos, RandomSource rand) {
        if (state.getValue(PRESSED)) {
            worldIn.setBlock(pos, state.setValue(PRESSED, false), 3);
            this.updateNeighbors(state, worldIn, pos);
            worldIn.gameEvent(null, GameEvent.BLOCK_DEACTIVATE, pos);
        }
    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
        if (direction.getOpposite() == state.getValue(FACING) && !state.canSurvive(level, pos)) {
            return Blocks.AIR.defaultBlockState();
        }
        return state;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack pStack, @org.jetbrains.annotations.Nullable BlockGetter pLevel, List<Component> pTooltip, TooltipFlag pFlag) {
        InfinityButtonsUtil.tooltip(pTooltip, "doorbell_button");
        super.appendHoverText(pStack, pLevel, pTooltip, pFlag);
    }
}
