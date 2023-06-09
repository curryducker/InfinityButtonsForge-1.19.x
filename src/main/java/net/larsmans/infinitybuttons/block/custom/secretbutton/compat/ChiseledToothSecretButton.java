package net.larsmans.infinitybuttons.block.custom.secretbutton.compat;

import net.larsmans.infinitybuttons.block.custom.secretbutton.AbstractSecretButton;
import net.larsmans.infinitybuttons.sounds.InfinityButtonsSounds;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ChiseledToothSecretButton extends AbstractSecretButton {
    public ChiseledToothSecretButton(Properties properties, Block jadeBlock) {
        super(
                properties,
                Shapes.or(BOTTOM, TOP,
                        Block.box(0, 1, 0, 1, 15, 16),
                        Block.box(1, 1, 3, 15, 15, 16),
                        Block.box(15, 1, 0, 16, 15, 16)
                ),
                Shapes.or(BOTTOM, TOP,
                        Block.box(0, 1, 0, 16, 15, 1),
                        Block.box(0, 1, 1, 13, 15, 15),
                        Block.box(0, 1, 15, 16, 15, 16)
                ),
                Shapes.or(BOTTOM, TOP,
                        Block.box(0, 1, 0, 1, 15, 16),
                        Block.box(1, 1, 0, 15, 15, 13),
                        Block.box(15, 1, 0, 16, 15, 16)
                ),
                Shapes.or(BOTTOM, TOP,
                        Block.box(0, 1, 0, 16, 15, 1),
                        Block.box(3, 1, 1, 16, 15, 15),
                        Block.box(0, 1, 15, 16, 15, 16)
                ),
                Block.box(0, 0, 0, 16, 16, 16),
                jadeBlock
        );
    }

    // The bottom part that never moves
    private static final VoxelShape BOTTOM = Block.box(0, 0, 0, 16, 1, 16);
    // The top part that never moves
    private static final VoxelShape TOP = Block.box(0, 15, 0, 16, 16, 16);

    @Override
    protected SoundEvent getSoundEvent(boolean isOn) {
        return InfinityButtonsSounds.STONE_SCRAPE.get();
    }

    @Override
    public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
        return new ArrayList<>(Collections.singleton(new ItemStack(this)));
    }
}
