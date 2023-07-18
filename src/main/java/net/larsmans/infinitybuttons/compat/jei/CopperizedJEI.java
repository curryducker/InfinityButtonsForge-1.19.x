package net.larsmans.infinitybuttons.compat.jei;

import mezz.jei.api.registration.IRecipeRegistration;
import net.larsmans.infinitybuttons.InfinityButtonsUtil;
import net.minecraft.world.item.ItemStack;
import net.onvoid.copperized.common.compat.jei.AxeScrapingRecipe;
import net.onvoid.copperized.common.compat.jei.OxidationRecipe;
import net.onvoid.copperized.common.compat.jei.WaxingRecipe;

import java.util.ArrayList;

import static net.onvoid.copperized.common.compat.jei.CopperizedJEIPlugin.*;

public class CopperizedJEI {

    private static final ArrayList<OxidationRecipe> oxidationRecipes = new ArrayList<>();
    private static final ArrayList<WaxingRecipe> waxingRecipes = new ArrayList<>();
    private static final ArrayList<AxeScrapingRecipe> axeScrapingRecipes = new ArrayList<>();

    public static void registerRecipes(IRecipeRegistration registration) {
        InfinityButtonsUtil.buildNext();
        InfinityButtonsUtil.NEXT_BY_BLOCK.get().forEach((blockFrom, blockTo) -> oxidationRecipes.add(new OxidationRecipe(new ItemStack(blockFrom), new ItemStack(blockTo))));
        registration.addRecipes(OXIDATION, oxidationRecipes);

        InfinityButtonsUtil.buildWax();
        InfinityButtonsUtil.WAX_ON_BY_BLOCK.get().forEach((blockFrom, blockTo) -> waxingRecipes.add(new WaxingRecipe(new ItemStack(blockFrom), new ItemStack(blockTo))));
        registration.addRecipes(WAXING, waxingRecipes);

        InfinityButtonsUtil.PREVIOUS_BY_BLOCK.get().forEach((blockFrom, blockTo) -> axeScrapingRecipes.add(new AxeScrapingRecipe(new ItemStack(blockFrom), new ItemStack(blockTo))));
        InfinityButtonsUtil.WAX_OFF_BY_BLOCK.get().forEach((blockFrom, blockTo) -> axeScrapingRecipes.add(new AxeScrapingRecipe(new ItemStack(blockFrom), new ItemStack(blockTo))));
        registration.addRecipes(AXE_SCRAPING, axeScrapingRecipes);
    }
}
