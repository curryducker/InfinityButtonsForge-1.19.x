package net.larsmans.infinitybuttons.compat.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IRecipeRegistration;
import net.larsmans.infinitybuttons.InfinityButtons;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fml.ModList;
import org.jetbrains.annotations.NotNull;

@JeiPlugin
public class InfinityButtonsJEIPlugin implements IModPlugin {

    @Override
    public void registerRecipes(@NotNull IRecipeRegistration registration) {
        if (ModList.get().isLoaded("copperized"))
            CopperizedJEI.registerRecipes(registration);
    }

    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(InfinityButtons.MOD_ID, "jei_plugin");
    }
}
