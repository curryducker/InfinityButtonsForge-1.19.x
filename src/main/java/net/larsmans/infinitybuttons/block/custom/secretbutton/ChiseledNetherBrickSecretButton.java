package net.larsmans.infinitybuttons.block.custom.secretbutton;

import net.larsmans.infinitybuttons.sounds.InfinityButtonsSounds;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class ChiseledNetherBrickSecretButton extends AbstractSecretButton {
    public ChiseledNetherBrickSecretButton(Properties properties) {
        super(
                properties,
                Shapes.or(BOTTOM, TOP,
                        Block.box(13, 3, 0, 16, 13, 16),
                        Block.box(3, 3, 3, 13, 13, 16),
                        Block.box(0, 3, 0, 3, 13, 16)
                ),
                Shapes.or(BOTTOM, TOP,
                        Block.box(0, 3, 13, 16, 13, 16),
                        Block.box(0, 3, 3, 13, 13, 13),
                        Block.box(0, 3, 0, 16, 13, 3)
                ),
                Shapes.or(BOTTOM, TOP,
                        Block.box(0, 3, 0, 3, 13, 16),
                        Block.box(3, 3, 0, 13, 13, 13),
                        Block.box(13, 3, 0, 16, 13, 16)
                ),
                Shapes.or(BOTTOM, TOP,
                        Block.box(0, 3, 0, 16, 13, 3),
                        Block.box(3, 3, 3, 16, 13, 13),
                        Block.box(0, 3, 13, 16, 13, 16)
                ),
                Block.box(0, 0, 0, 16, 16, 16)
        );
    }

    // The bottom part that never moves
    private static final VoxelShape BOTTOM = Block.box(0, 0, 0, 16, 3, 16);
    // The top part that never moves
    private static final VoxelShape TOP = Block.box(0, 13, 0, 16, 16, 16);

    @Override
    protected SoundEvent getSoundEvent(boolean isOn) {
        return InfinityButtonsSounds.STONE_SCRAPE.get();
    }
}
