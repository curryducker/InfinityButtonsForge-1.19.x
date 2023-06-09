package net.larsmans.infinitybuttons.compat.jade;

import net.larsmans.infinitybuttons.InfinityButtons;
import net.larsmans.infinitybuttons.block.custom.HoglinMountButton;
import net.larsmans.infinitybuttons.block.custom.secretbutton.AbstractSecretButton;
import net.larsmans.infinitybuttons.block.custom.torch.RedstoneTorchButton;
import net.larsmans.infinitybuttons.block.custom.torch.TorchButton;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.ForgeRegistries;
import snownee.jade.api.BlockAccessor;
import snownee.jade.api.IWailaClientRegistration;
import snownee.jade.api.IWailaPlugin;
import snownee.jade.api.WailaPlugin;
import snownee.jade.api.config.IWailaConfig;

@WailaPlugin
public class InfinityButtonsPlugin implements IWailaPlugin {

    static final ResourceLocation CONFIG_HIDE_SECRET_BUTTONS = new ResourceLocation(InfinityButtons.MOD_ID, "hide_secret_buttons");
    static final ResourceLocation CONFIG_HIDE_TORCH_BUTTONS = new ResourceLocation(InfinityButtons.MOD_ID, "hide_torch_buttons");

    private static Block HOGLIN_MOUNT = ForgeRegistries.BLOCKS.getValue(new ResourceLocation("nethers_delight", "hoglin_mount"));

    @Override
    public void registerClient(IWailaClientRegistration registration) {
        registration.addConfig(CONFIG_HIDE_SECRET_BUTTONS, true);
        registration.addConfig(CONFIG_HIDE_TORCH_BUTTONS, true);
        registration.markAsClientFeature(CONFIG_HIDE_SECRET_BUTTONS);
        registration.markAsClientFeature(CONFIG_HIDE_TORCH_BUTTONS);
        registration.addRayTraceCallback((hitResult, accessor, originalAccessor) -> {
            if (accessor instanceof BlockAccessor blockAccessor) {
                if (IWailaConfig.get().getPlugin().get(CONFIG_HIDE_SECRET_BUTTONS) && blockAccessor.getBlock() instanceof AbstractSecretButton) {
                    return registration.blockAccessor().from(blockAccessor).blockState(((AbstractSecretButton) blockAccessor.getBlock()).jadeBlock.defaultBlockState()).build();
                }
                if (IWailaConfig.get().getPlugin().get(CONFIG_HIDE_SECRET_BUTTONS) && blockAccessor.getBlock() instanceof HoglinMountButton) {
                    return registration.blockAccessor().from(blockAccessor).blockState(HOGLIN_MOUNT.defaultBlockState()).build();
                }
                if (IWailaConfig.get().getPlugin().get(CONFIG_HIDE_TORCH_BUTTONS) && blockAccessor.getBlock() instanceof TorchButton) {
                    return registration.blockAccessor().from(blockAccessor).blockState(((TorchButton) blockAccessor.getBlock()).jadeBlock.defaultBlockState()).build();
                }
                if (IWailaConfig.get().getPlugin().get(CONFIG_HIDE_TORCH_BUTTONS) && blockAccessor.getBlock() instanceof RedstoneTorchButton) {
                    return registration.blockAccessor().from(blockAccessor).blockState(((RedstoneTorchButton) blockAccessor.getBlock()).jadeBlock.defaultBlockState()).build();
                }
            }
            return accessor;
        });
    }
}
