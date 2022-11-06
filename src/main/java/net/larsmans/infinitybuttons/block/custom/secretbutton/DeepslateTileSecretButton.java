package net.larsmans.infinitybuttons.block.custom.secretbutton;

import net.larsmans.infinitybuttons.sounds.InfinityButtonsSounds;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class DeepslateTileSecretButton extends AbstractSecretButton {
    public DeepslateTileSecretButton(Properties properties) {
        super(
                properties,
                Shapes.or(BASE,
                        Block.box(13, 10, 0, 16, 16, 16),
                        Block.box(0, 10, 3, 13, 16, 19)
                ),
                Shapes.or(BASE,
                        Block.box(0, 10, 13, 16, 16, 16),
                        Block.box(-3, 10, 0, 13, 16, 13)
                ),
                Shapes.or(BASE,
                        Block.box(0, 10, 0, 3, 16, 16),
                        Block.box(3, 10, -3, 16, 16, 13)
                ),
                Shapes.or(BASE,
                        Block.box(0, 10, 0, 16, 16, 3),
                        Block.box(3, 10, 3, 19, 16, 16)
                ),
                Block.box(0, 0, 0, 16, 16, 16)
        );
    }

    // The bottom part that never moves
    private static final VoxelShape BASE = Block.box(0, 0, 0, 16, 10, 16);

    @Override
    protected SoundEvent getSoundEvent(boolean isOn) {
        return InfinityButtonsSounds.STONE_SCRAPE.get();
    }
}
