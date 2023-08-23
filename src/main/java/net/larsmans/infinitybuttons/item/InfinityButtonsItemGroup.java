package net.larsmans.infinitybuttons.item;

import net.larsmans.infinitybuttons.block.InfinityButtonsBlocks;
import net.larsmans.infinitybuttons.block.custom.DoorbellButton;
import net.larsmans.infinitybuttons.block.custom.LampButton;
import net.larsmans.infinitybuttons.block.custom.LanternButton;
import net.larsmans.infinitybuttons.block.custom.button.*;
import net.larsmans.infinitybuttons.block.custom.consolebutton.ConsoleButton;
import net.larsmans.infinitybuttons.block.custom.emergencybutton.EmergencyButton;
import net.larsmans.infinitybuttons.block.custom.emergencybutton.SafeEmergencyButton;
import net.larsmans.infinitybuttons.block.custom.letterbutton.LetterButton;
import net.larsmans.infinitybuttons.block.custom.secretbutton.AbstractSecretButton;
import net.larsmans.infinitybuttons.block.custom.secretbutton.BookshelfSecretButton;
import net.larsmans.infinitybuttons.block.custom.secretbutton.PlankSecretButton;
import net.larsmans.infinitybuttons.block.custom.torch.RedstoneTorchButton;
import net.larsmans.infinitybuttons.block.custom.torch.TorchButton;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import static net.larsmans.infinitybuttons.InfinityButtonsUtil.REGISTRY_FOR_TAB;
import static net.larsmans.infinitybuttons.InfinityButtonsUtil.blockPathContains;

public class InfinityButtonsItemGroup extends CreativeModeTab {

    public static final CreativeModeTab INFINITYBUTTONS = new InfinityButtonsItemGroup("infinityButtonsTab");

    public InfinityButtonsItemGroup(String infinityButtonsTab) {
        super(infinityButtonsTab);
    }

    @Override
    public ItemStack makeIcon() {
        return new ItemStack(InfinityButtonsBlocks.OAK_LARGE_BUTTON.get());
    }

    @Override
    public void fillItemList(NonNullList<ItemStack> pItems) {
        List<String> BEFORE_PLANKS = List.of("brick_secret_button", "stone_brick_secret_button", "mossy_stone_brick_secret_button", "cracked_stone_brick_secret_button", "chiseled_stone_brick_secret_button", "deepslate_brick_secret_button", "cracked_deepslate_brick_secret_button", "deepslate_tile_secret_button", "cracked_deepslate_tile_secret_button");
        REGISTRY_FOR_TAB.sort(Comparator.comparing(o -> {
            Block block = Block.byItem(o.get());
            boolean b = block instanceof StoneButton || block instanceof CopperButton || block instanceof StickyCopperButton || block instanceof ArrowButton || block instanceof EmeraldButton || block instanceof DiamondButton || block instanceof PrismarineButton;
            if (block instanceof AbstractSmallButton && !((AbstractSmallButton) block).isLarge()) {
                if (block instanceof WoodenButton) {
                    return "AA";
                } else if (b) {
                    return "AB";
                } else if (block instanceof FallingButton) {
                    if (!((FallingButton)block).gravel && !blockPathContains(block, "concrete")) {
                        return "ACA";
                    } else if (((FallingButton)block).gravel) {
                        return "ACB";
                    } else {
                        return "ACZ";
                    }
                } else {
                    return "AZ";
                }
            } else if (block instanceof AbstractSmallButton && ((AbstractSmallButton) block).isLarge()) {
                if (block instanceof WoodenButton) {
                    return "BA";
                } else if (b) {
                    return "BB";
                } else if (block instanceof FallingButton) {
                    if (!((FallingButton)block).gravel && !blockPathContains(block, "concrete")) {
                        return "BCA";
                    } else if (((FallingButton)block).gravel) {
                        return "BCB";
                    } else {
                        return "BCZ";
                    }
                } else {
                    return "BZ";
                }
            } else if (block instanceof EmergencyButton || block instanceof SafeEmergencyButton) {
                return "C";
            } else if (block instanceof AbstractSecretButton) {
                if (block instanceof BookshelfSecretButton) {
                    return "DA";
                } else if (BEFORE_PLANKS.contains(Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(block)).getPath())) {
                    return "DB";
                } else if (block instanceof PlankSecretButton) {
                    return "DC";
                } else {
                    return "DZ";
                }
            } else if (block instanceof DoorbellButton || block instanceof LampButton || block instanceof LetterButton) {
                return "E";
            } else if (block instanceof LanternButton) {
                return "F";
            } else if (block instanceof ConsoleButton) {
                return "G";
            } else if (block instanceof TorchButton || block instanceof RedstoneTorchButton) {
                return "H";
            } else {
                return "ZZ";
            }
        }));
        for(RegistryObject<Item> entry : REGISTRY_FOR_TAB) {
            Item item = entry.get();
            item.fillItemCategory(this, pItems);
        }
    }

}
