package net.larsmans.infinitybuttons.block.custom.secretbutton;

import net.larsmans.infinitybuttons.sounds.InfinityButtonsSounds;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class PlankSecretButton extends AbstractSecretButton {
    public PlankSecretButton(Properties properties, Block jadeBlock) {
        super(
                properties,
                Shapes.or(BOTTOM, TOP,
                        Block.box(0, 4, 3, 16, 9, 16)),
                Shapes.or(BOTTOM, TOP,
                        Block.box(0, 4, 0, 13, 9, 16)),
                Shapes.or(BOTTOM, TOP,
                        Block.box(0, 4, 0, 16, 9, 13)),
                Shapes.or(BOTTOM, TOP,
                        Block.box(3, 4, 0, 16, 9, 16)),

                Block.box(0, 0, 0, 16, 16, 16),
                jadeBlock
        );
    }

    // The bottom part that never moves
    private static final VoxelShape BOTTOM = Block.box(0, 0, 0, 16, 4, 16);
    // The top part that never moves
    private static final VoxelShape TOP = Block.box(0, 9, 0, 16, 16, 16);

    @Override
    protected SoundEvent getSoundEvent(boolean isOn) {
        return InfinityButtonsSounds.WOOD_SCRAPE.get();
    }
}
