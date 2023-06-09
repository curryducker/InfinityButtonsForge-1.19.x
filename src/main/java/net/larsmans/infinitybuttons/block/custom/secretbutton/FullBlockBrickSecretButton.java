package net.larsmans.infinitybuttons.block.custom.secretbutton;

import net.larsmans.infinitybuttons.sounds.InfinityButtonsSounds;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.shapes.VoxelShape;

public class FullBlockBrickSecretButton extends AbstractSecretButton {
    public FullBlockBrickSecretButton(Properties properties, Block jadeBlock) {
        super(
                properties,
                FULL,
                FULL,
                FULL,
                FULL,
                FULL,
                jadeBlock
        );
    }

    // Full block because I am too lazy to do the whole voxelshape thing
    private static final VoxelShape FULL = Block.box(0, 0, 0, 16, 16, 16);

    @Override
    protected SoundEvent getSoundEvent(boolean isOn) {
        return InfinityButtonsSounds.STONE_SCRAPE.get();
    }
}
