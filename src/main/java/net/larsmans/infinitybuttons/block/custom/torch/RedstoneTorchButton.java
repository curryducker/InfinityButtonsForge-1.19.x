package net.larsmans.infinitybuttons.block.custom.torch;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.TorchBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class RedstoneTorchButton extends TorchBlock {
    public static final BooleanProperty LIT = BlockStateProperties.LIT;

    public RedstoneTorchButton(BlockBehaviour.Properties properties) {
        super(properties, DustParticleOptions.REDSTONE);
        this.registerDefaultState((BlockState)((BlockState)this.stateDefinition.any()).setValue(LIT, false));
    }

    public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
        if (state.getValue(LIT)) {
            return InteractionResult.CONSUME;
        }
        this.powerBlock(state, worldIn, pos);
        this.playSound(player, worldIn, pos, true);
        return InteractionResult.sidedSuccess(worldIn.isClientSide);
    }

    public void tick(BlockState state, ServerLevel worldIn, BlockPos pos, RandomSource rand) {
        if (state.getValue(LIT)) {
            worldIn.setBlock(pos, (BlockState)state.setValue(LIT, false), 3);
            this.updateNeighbors(state, worldIn, pos);
            this.playSound((Player)null, worldIn, pos, false);

        }
    }

    public void powerBlock(BlockState state, Level worldIn, BlockPos pos) {
        worldIn.setBlock(pos, (BlockState)state.setValue(LIT, true), 3);
        this.updateNeighbors(state, worldIn, pos);
        worldIn.scheduleTick(pos, this, 60);
    }

    protected void playSound(Player player, Level worldIn, BlockPos pos, boolean b) {
        worldIn.playSound(b ? player : null, pos, b ? SoundEvents.WOODEN_BUTTON_CLICK_OFF : SoundEvents.WOODEN_BUTTON_CLICK_ON, SoundSource.BLOCKS, 0.3f, b ? 0.6f : 0.5f);
    }

    public void updateNeighbors(BlockState state, Level worldIn, BlockPos pos) {
        worldIn.updateNeighborsAt(pos, this);
    }

    public void onPlace(BlockState state, Level worldIn, BlockPos pos, BlockState oldState, boolean isMoving) {
        for(Direction direction : Direction.values()) {
            worldIn.updateNeighborsAt(pos.relative(direction), this);
        }
    }

    public int getDirectSignal(BlockState blockState, BlockGetter blockAccess, BlockPos pos, Direction side) {
        if (side == Direction.DOWN) {
            return blockState.getSignal(blockAccess, pos, side);
        }
        return 0;
    }

    public int getSignal(BlockState blockState, BlockGetter blockAccess, BlockPos pos, Direction side) {
        if (blockState.getValue(LIT) && Direction.UP != side) {
            return 15;
        }
        return 0;
    }

    public boolean isSignalSource(BlockState state) {
        return true;
    }

    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState stateIn, Level worldIn, BlockPos pos, RandomSource rand) {
        if (stateIn.getValue(LIT)) {
            double d0 = (double)pos.getX() + 0.5D + (rand.nextDouble() - 0.5D) * 0.2D;
            double d1 = (double)pos.getY() + 0.7D + (rand.nextDouble() - 0.5D) * 0.2D;
            double d2 = (double)pos.getZ() + 0.5D + (rand.nextDouble() - 0.5D) * 0.2D;
            worldIn.addParticle(this.flameParticle, d0, d1, d2, 0.0D, 0.0D, 0.0D);
        }
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(LIT);
    }
}