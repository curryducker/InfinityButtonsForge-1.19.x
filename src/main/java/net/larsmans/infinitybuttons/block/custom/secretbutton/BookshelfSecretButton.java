package net.larsmans.infinitybuttons.block.custom.secretbutton;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;

public class BookshelfSecretButton extends AbstractSecretButton {
    public BookshelfSecretButton(Properties properties) {
        super(
                properties,
                FULL,
                FULL,
                FULL,
                FULL,
                FULL
        );
    }

    // Full block because I am too lazy to do the whole voxelshape thing
    private static final VoxelShape FULL = Block.box(0, 0, 0, 16, 16, 16);

    @Override
    protected SoundEvent getSoundEvent(boolean isOn) {
        // Credits to anne for suggesting this immensely satisfying sound
        return SoundEvents.BAMBOO_PLACE;
    }

    @Override
    public float getEnchantPowerBonus(BlockState state, LevelReader level, BlockPos pos) {
        return 1;
    }
}
