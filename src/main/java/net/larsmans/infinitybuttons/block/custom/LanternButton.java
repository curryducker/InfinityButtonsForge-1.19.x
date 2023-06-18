package net.larsmans.infinitybuttons.block.custom;
import net.larsmans.infinitybuttons.InfinityButtonsUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ChainBlock;
import net.minecraft.world.level.block.LanternBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class LanternButton extends LanternBlock {

    public static final BooleanProperty PRESSED = BooleanProperty.create("pressed");
    public static final VoxelShape SHAPE_PRESSED = HANGING_AABB.move(0, (double) -1 / 16, 0);
    private final boolean isLever;

    public LanternButton(Properties properties, boolean isLever) {
        super(properties);
        this.isLever = isLever;
        this.registerDefaultState(this.stateDefinition.any().setValue(HANGING, true).setValue(WATERLOGGED, false).setValue(PRESSED, false));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        return state.getValue(PRESSED) ? SHAPE_PRESSED : HANGING_AABB;
    }

    @Override
    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        FluidState fluidState = ctx.getLevel().getFluidState(ctx.getClickedPos());
        for (Direction direction : ctx.getNearestLookingDirections()) {
            if (direction != Direction.UP) continue;
            return this.defaultBlockState().setValue(WATERLOGGED, fluidState.getType() == Fluids.WATER);
        }
        return null;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(HANGING, WATERLOGGED, PRESSED);
    }

    protected int getPressDuration() {
        return 30;
    }

    @Override
    public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, LevelAccessor worldIn, BlockPos currentPos, BlockPos facingPos) {
        return super.updateShape(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }

    @Override
    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (isLever) {
            if (state.getValue(PRESSED)) {
                this.powerOff(state, world, pos);
                this.playSound(player, world, pos, false);
                world.gameEvent(player, GameEvent.BLOCK_DEACTIVATE, pos);
            } else {
                this.powerOn(state, world, pos);
                this.playSound(player, world, pos, true);
                world.gameEvent(player, GameEvent.BLOCK_ACTIVATE, pos);
            }
        } else {
            if (state.getValue(PRESSED)) {
                return InteractionResult.CONSUME;
            }
            this.powerOn(state, world, pos);
            world.scheduleTick(pos, this, this.getPressDuration());
            this.playSound(player, world, pos, true);
            world.gameEvent(player, GameEvent.BLOCK_ACTIVATE, pos);
        }
        return InteractionResult.sidedSuccess(world.isClientSide);
    }

    public void powerOn(BlockState state, Level world, BlockPos pos) {
        world.setBlock(pos, state.setValue(PRESSED, true), 3);
        this.updateNeighbors(world, pos);
    }

    public void powerOff(BlockState state, Level world, BlockPos pos) {
        world.setBlock(pos, state.setValue(PRESSED, false), 3);
        this.updateNeighbors(world, pos);
    }

    protected void playSound(@javax.annotation.Nullable Player playerIn, LevelAccessor worldIn, BlockPos pos, boolean pressed) {
        worldIn.playSound(pressed ? playerIn : null, pos, this.getSoundEvent(), SoundSource.BLOCKS, 0.3f, pressed ? 0.6f : 0.5f);
    }

    protected SoundEvent getSoundEvent() {
        return SoundEvents.STONE_BUTTON_CLICK_OFF;
    }

    @Override
    public void onRemove(BlockState state, Level worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        if (isMoving || state.is(newState.getBlock())) {
            return;
        }
        if (state.getValue(PRESSED)) {
            this.updateNeighbors(worldIn, pos);
        }
        super.onRemove(state, worldIn, pos, newState, isMoving);
    }

    @Override
    public int getSignal(BlockState blockState, BlockGetter blockAccess, BlockPos pos, Direction side) {
        return blockState.getValue(PRESSED) ? 15 : 0;
    }

    @Override
    public int getDirectSignal(BlockState blockState, BlockGetter blockAccess, BlockPos pos, Direction side) {
        return (blockState.getValue(PRESSED) && side == Direction.DOWN) ? 15 : 0;
    }

    @Override
    public boolean isSignalSource(BlockState state) {
        return true;
    }

    @Override
    public void tick(BlockState state, ServerLevel worldIn, BlockPos pos, RandomSource rand) {
        if (!state.getValue(PRESSED)) {
            return;
        }
        worldIn.setBlock(pos, state.setValue(PRESSED, false), 3);
        this.updateNeighbors(worldIn, pos);
        this.playSound(null, worldIn, pos, false);
        worldIn.gameEvent(null, GameEvent.BLOCK_DEACTIVATE, pos);
    }

    public void updateNeighbors(Level world, BlockPos pos) {
        world.updateNeighborsAt(pos, this);
        world.updateNeighborsAt(pos.above(checkChains(world, pos)), this);
        world.updateNeighborsAt(pos.above(checkChains(world, pos) + 1), this);
    }

    public int checkChains(Level world, BlockPos pos) {
        int i = 1;
        while (world.getBlockState(pos.above(i)).getBlock() instanceof ChainBlock) {
            i++;
        }
        return i - 1;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack pStack, @Nullable BlockGetter pLevel, List<Component> pTooltip, TooltipFlag pFlag) {
        InfinityButtonsUtil.tooltip(pTooltip, "lantern_button");
    }
}