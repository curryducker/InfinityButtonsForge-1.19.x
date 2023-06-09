package net.larsmans.infinitybuttons.block.custom.torch.compat;

import net.larsmans.infinitybuttons.block.custom.torch.WallTorchLever;
import net.larsmans.infinitybuttons.compat.NethersDelightItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.List;

public class PropelWallTorchLever extends WallTorchLever {
    public PropelWallTorchLever(Properties properties, Block jadeBlock) {
        super(properties, ParticleTypes.FLAME, jadeBlock);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState stateIn, Level worldIn, BlockPos pos, RandomSource rand) {
    }

    @Override
    public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
        return List.of(new ItemStack(NethersDelightItems.PROPELPLANT_TORCH_LEVER.get()));
    }
}
