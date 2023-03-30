package net.larsmans.infinitybuttons.block.custom.button;

import me.shedaniel.autoconfig.AutoConfig;
import net.larsmans.infinitybuttons.InfinityButtonsConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FaceAttachedHorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.AttachFace;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;

import javax.annotation.Nullable;
import java.util.List;

public abstract class AbstractButton extends FaceAttachedHorizontalDirectionalBlock {
    protected static InfinityButtonsConfig config = AutoConfig.getConfigHolder(InfinityButtonsConfig.class).getConfig();

    public static final BooleanProperty PRESSED = BooleanProperty.create("pressed");

    protected final boolean projectile;

    protected AbstractButton(boolean projectile, BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(PRESSED, false).setValue(FACE, AttachFace.FLOOR));
        this.projectile = projectile;
    }

    public abstract int getPressDuration();

    @Override
    public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
        if (state.getValue(PRESSED)) {
            return InteractionResult.CONSUME;
        } else {
            this.powerBlock(state, worldIn, pos);
            this.playSound(player, worldIn, pos, true);
            worldIn.gameEvent(player, GameEvent.BLOCK_ACTIVATE, pos);
            return InteractionResult.sidedSuccess(worldIn.isClientSide);
        }
    }

    public void powerBlock(BlockState state, Level world, BlockPos pos) {
        world.setBlock(pos, state.setValue(PRESSED, true), 3);
        this.updateNeighbors(state, world, pos);
        world.scheduleTick(pos, this, this.getPressDuration());
    }

    protected void playSound(@Nullable Player playerIn, LevelAccessor worldIn, BlockPos pos, boolean pressed) {
        worldIn.playSound(pressed ? playerIn : null, pos, this.getSoundEvent(pressed), SoundSource.BLOCKS, 0.3F, pressed ? 0.6F : 0.5F);
    }

    protected abstract SoundEvent getSoundEvent(boolean pressed);

    @Override
    public void onRemove(BlockState state, Level worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        if (!isMoving && !state.is(newState.getBlock())) {
            if (state.getValue(PRESSED)) {
                this.updateNeighbors(state, worldIn, pos);
            }

            super.onRemove(state, worldIn, pos, newState, isMoving);
        }
    }

    @Override
    public int getSignal(BlockState blockState, BlockGetter blockAccess, BlockPos pos, Direction side) {
        return blockState.getValue(PRESSED) ? 15 : 0;
    }

    @Override
    public int getDirectSignal(BlockState blockState, BlockGetter blockAccess, BlockPos pos, Direction side) {
        return blockState.getValue(PRESSED) && getConnectedDirection(blockState) == side ? 15 : 0;
    }

    @Override
    public boolean isSignalSource(BlockState state) {
        return true;
    }

    @Override
    public void tick(BlockState state, ServerLevel worldIn, BlockPos pos, RandomSource rand) {
        if (state.getValue(PRESSED)) {
            if (this.projectile) {
                this.checkPressed(state, worldIn, pos);
            } else {
                worldIn.setBlock(pos, state.setValue(PRESSED, false), 3);
                this.updateNeighbors(state, worldIn, pos);
                this.playSound(null, worldIn, pos, false);
                worldIn.gameEvent(null, GameEvent.BLOCK_DEACTIVATE, pos);
            }

        }
    }

    @Override
    public void entityInside(BlockState state, Level worldIn, BlockPos pos, Entity entityIn) {
        if (!worldIn.isClientSide && this.projectile && !state.getValue(PRESSED)) {
            this.checkPressed(state, worldIn, pos);
        }
    }

    private void checkPressed(BlockState state, Level worldIn, BlockPos pos) {
        List<? extends Entity> list = worldIn.getEntitiesOfClass(AbstractArrow.class, state.getShape(worldIn, pos).bounds().move(pos));
        boolean flag = !list.isEmpty();
        boolean flag1 = state.getValue(PRESSED);
        if (flag != flag1) {
            worldIn.setBlock(pos, state.setValue(PRESSED, flag), 3);
            this.updateNeighbors(state, worldIn, pos);
            this.playSound(null, worldIn, pos, flag);
        }

        if (flag) {
            worldIn.scheduleTick(new BlockPos(pos), this, this.getPressDuration());
        }

    }

    public void updateNeighbors(BlockState state, Level worldIn, BlockPos pos) {
        worldIn.updateNeighborsAt(pos, this);
        worldIn.updateNeighborsAt(pos.relative(getConnectedDirection(state).getOpposite()), this);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, PRESSED, FACE);
    }
}
