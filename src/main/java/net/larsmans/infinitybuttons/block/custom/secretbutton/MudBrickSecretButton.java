package net.larsmans.infinitybuttons.block.custom.secretbutton;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class MudBrickSecretButton extends AbstractSecretButton{
    public MudBrickSecretButton(Properties properties, Block jadeBlock) {
        super (
                properties,
                Shapes.or(BASE,
                        Block.box(12, 0, 0, 16, 9, 16),
                        Block.box(3, 0, 3, 12, 9, 16),
                        Block.box(0, 0, 0, 3, 9, 16)
                ),
                Shapes.or(BASE,
                        Block.box(0, 0, 12, 16, 9, 16),
                        Block.box(0, 0, 3, 13, 9, 12),
                        Block.box(0, 0, 0, 16, 9, 3)
                ),
                Shapes.or(BASE,
                        Block.box(0, 0, 0, 4, 9, 16),
                        Block.box(3, 0, 0, 13, 9, 13),
                        Block.box(13, 0, 0, 16, 9, 16)
                ),
                Shapes.or(BASE,
                        Block.box(0, 0, 0, 16, 9, 4),
                        Block.box(3, 0, 3, 16, 9, 13),
                        Block.box(0, 0, 13, 16, 9, 16)
                ),
                Block.box(0, 0, 0, 16, 16, 16),
                jadeBlock
        );
    }

    // The top part that never moves
    private static final VoxelShape BASE = Block.box(0, 9, 0, 16, 16, 16);

    @Override
    protected SoundEvent getSoundEvent(boolean isOn) {
        return SoundEvents.MUD_BRICKS_PLACE;
    }
}
