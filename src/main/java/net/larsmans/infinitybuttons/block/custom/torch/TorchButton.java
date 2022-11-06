package net.larsmans.infinitybuttons.block.custom.torch;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

public class TorchButton extends TorchBlock {
    public static final BooleanProperty PRESSED = BooleanProperty.create("pressed");
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

    public TorchButton(BlockBehaviour.Properties properties, ParticleOptions particleData) {
        super(properties, particleData);
        this.registerDefaultState((BlockState)((BlockState)this.stateDefinition.any()).setValue(FACING, Direction.NORTH).setValue(PRESSED, false));
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState stateIn, Level worldIn, BlockPos pos, RandomSource rand) {
        Direction direction = stateIn.getValue(FACING);
        if (stateIn.getValue(PRESSED)) {
            double d0 = (double) pos.getX() + 0.5D;
            double d1 = (double) pos.getY() + 0.63D;
            double d2 = (double) pos.getZ() + 0.5D;
            Direction direction2 = direction.getOpposite();
            worldIn.addParticle(ParticleTypes.SMOKE,
                    d0 + 0.23D * (double) direction2.getStepX(),
                    d1,
                    d2 + 0.23D * (double) direction2.getStepZ(),
                    0.0D, 0.0D, 0.0D);
            worldIn.addParticle(this.flameParticle,
                    d0 + 0.23D * (double) direction2.getStepX(),
                    d1,
                    d2 + 0.23D * (double) direction2.getStepZ(),
                    0.0D, 0.0D, 0.0D);
        } else {
            super.animateTick(stateIn, worldIn, pos, rand);
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, PRESSED);
    }

    @Override
    public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
        if (state.getValue(PRESSED)) {
            return InteractionResult.CONSUME;
        }
        this.powerBlock(state, worldIn, pos);
        this.playSound(player, worldIn, pos, true);
        return InteractionResult.sidedSuccess(worldIn.isClientSide);
    }

    public void powerBlock(BlockState state, Level worldIn, BlockPos pos) {
        worldIn.setBlock(pos, (BlockState)state.setValue(PRESSED, true), 3);
        this.updateNeighbors(state, worldIn, pos);
        worldIn.scheduleTick(pos, this, 60);
    }

    public void playSound(Player player, Level worldIn, BlockPos pos, boolean b) {
        worldIn.playSound(b ? player : null, pos, b ? SoundEvents.WOODEN_BUTTON_CLICK_OFF : SoundEvents.WOODEN_BUTTON_CLICK_ON, SoundSource.BLOCKS, 0.3f, b ? 0.6f : 0.5f);
    }

    @Override
    public void onRemove(BlockState state, Level worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        if (isMoving || state.is(newState.getBlock())) {
            return;
        }
        if (state.getValue(PRESSED)) {
            this.updateNeighbors(state, worldIn, pos);
        }
        super.onRemove(state, worldIn, pos, newState, isMoving);
    }

    @Override
    public int getSignal(BlockState blockState, BlockGetter blockAccess, BlockPos pos, Direction side) {
        return blockState.getValue(PRESSED) ? 15 : 0;
    }

    @Override
    public int  getDirectSignal(BlockState blockState, BlockGetter blockAccess, BlockPos pos, Direction side) {
        if (blockState.getValue(PRESSED) && Direction.DOWN.getOpposite() == side) {
            return 15;
        }
        return 0;
    }

    @Override
    public boolean isSignalSource(BlockState state) {
        return true;
    }

    @Override
    public void tick(BlockState state, ServerLevel worldIn, BlockPos pos, RandomSource rand) {
        if (state.getValue(PRESSED)) {
            worldIn.setBlock(pos, (BlockState)state.setValue(PRESSED, false), 3);
            this.updateNeighbors(state, worldIn, pos);
            this.playSound((Player)null, worldIn, pos, false);

        }
    }

    public void updateNeighbors(BlockState state, Level worldIn, BlockPos pos) {
        worldIn.updateNeighborsAt(pos, this);
        worldIn.updateNeighborsAt(pos.relative(Direction.DOWN), this);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        Direction[] directions;
        BlockState blockState = this.defaultBlockState();
        Level worldView = ctx.getLevel();
        BlockPos blockPos = ctx.getClickedPos();
        for (Direction direction : directions = ctx.getNearestLookingDirections()) {
            Direction direction2;
            if (!direction.getAxis().isHorizontal() || !(blockState = blockState.setValue(FACING, direction2 = direction.getOpposite())).canSurvive(worldView, blockPos)) continue;
            return blockState;
        }
        return null;
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rot) {
        return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirrorIn) {
        return state.rotate(mirrorIn.getRotation(state.getValue(FACING)));
    }
}
