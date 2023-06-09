package net.larsmans.infinitybuttons.block.custom.secretbutton;

import net.larsmans.infinitybuttons.sounds.InfinityButtonsSounds;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class BigBrickSecretButton extends AbstractSecretButton {
    public BigBrickSecretButton(Properties properties, Block jadeBlock) {
        super(
                properties,
                Shapes.or(BASE,
                        Block.box(0, 8, 3, 16, 16, 19)
                ),
                Shapes.or(BASE,
                        Block.box(-3, 8, 0, 13, 16, 16)
                ),
                Shapes.or(BASE,
                        Block.box(0, 8, -3, 16, 16, 13)
                ),
                Shapes.or(BASE,
                        Block.box(3, 8, 0, 19, 16, 16)
                ),
                Block.box(0, 0, 0, 16, 16, 16),
                jadeBlock
        );
    }

    // The bottom part that doesn't move
    private static final VoxelShape BASE = Block.box(0, 0, 0, 16, 8, 16);

    @Override
    protected SoundEvent getSoundEvent(boolean isOn) {
        return InfinityButtonsSounds.STONE_SCRAPE.get();
    }
}
