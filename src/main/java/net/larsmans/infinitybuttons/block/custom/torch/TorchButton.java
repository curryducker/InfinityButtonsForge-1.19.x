package net.larsmans.infinitybuttons.block.custom.torch;

import net.larsmans.infinitybuttons.block.custom.button.AbstractHorizontalButton;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class TorchButton extends AbstractHorizontalButton {

    protected static final VoxelShape BOUNDING_SHAPE = Block.box(6.0, 0.0, 6.0, 10.0, 10.0, 10.0);
    protected final ParticleOptions particle;
    public final Block jadeBlock;

    public TorchButton(Properties properties, ParticleOptions particle, Block jadeBlock) {
        super(properties, BOUNDING_SHAPE, BOUNDING_SHAPE, BOUNDING_SHAPE, BOUNDING_SHAPE, BOUNDING_SHAPE, BOUNDING_SHAPE, BOUNDING_SHAPE, BOUNDING_SHAPE);
        this.particle = particle;
        this.jadeBlock = jadeBlock;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        return BOUNDING_SHAPE;
    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
        return (direction == Direction.DOWN && !this.canSurvive(state, level, pos)) ? Blocks.AIR.defaultBlockState() : state;
    }

    @Override
    public boolean canSurvive(BlockState pState, LevelReader pLevel, BlockPos pos) {
        return canSupportCenter(pLevel, pos.below(), Direction.UP);
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
            worldIn.addParticle(this.particle,
                    d0 + 0.23D * (double) direction2.getStepX(),
                    d1,
                    d2 + 0.23D * (double) direction2.getStepZ(),
                    0.0D, 0.0D, 0.0D);
        } else {
            super.animateTick(stateIn, worldIn, pos, rand);
        }
    }

    @Override
    public int getPressDuration() {
        return 50;
    }

    @Override
    protected SoundEvent getSoundEvent(boolean pressed) {
        return SoundEvents.WOODEN_BUTTON_CLICK_ON;
    }

    @Override
    public int  getDirectSignal(BlockState blockState, BlockGetter blockAccess, BlockPos pos, Direction side) {
        return blockState.getValue(PRESSED) && Direction.DOWN.getOpposite() == side ? 15 : 0;
    }

    @Override
    public void updateNeighbors(BlockState state, Level worldIn, BlockPos pos) {
        worldIn.updateNeighborsAt(pos, this);
        worldIn.updateNeighborsAt(pos.relative(Direction.DOWN), this);
    }
}
