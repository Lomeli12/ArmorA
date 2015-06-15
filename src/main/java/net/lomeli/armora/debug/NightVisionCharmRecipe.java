package net.lomeli.armora.debug;

import net.minecraft.init.Blocks;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;

import net.lomeli.armora.api.ArmorAAPI;
import net.lomeli.armora.charms.CharmRegistry;
import net.lomeli.armora.libs.CharmIDs;

/**
 * This is only temporary while I test the charms. In the future
 * there will be a new mechanic for adding charms.
 */
public class NightVisionCharmRecipe implements IRecipe {
    @Override
    public boolean matches(InventoryCrafting invCraft, World world) {
        boolean foundGlowStone = false;
        boolean foundItem = false;

        for (int i = 0; i < invCraft.getSizeInventory(); i++) {
            ItemStack stack = invCraft.getStackInSlot(i);
            if (stack != null) {
                if (stack.getItem() == Item.getItemFromBlock(Blocks.glowstone) && !foundGlowStone)
                    foundGlowStone = true;
                else if (!foundItem) {
                    if (ArmorAAPI.charmRegistry.canApplyCharmToItem(stack, CharmIDs.SIGHT) && !ArmorAAPI.charmRegistry.itemHasCharm(stack, CharmIDs.SIGHT) && CharmRegistry.isValidArmor(stack))
                        foundItem = true;
                    else return false;
                } else return false;
            }
        }

        return foundGlowStone && foundItem;
    }

    @Override
    public ItemStack getCraftingResult(InventoryCrafting invCraft) {
        ItemStack item = null;

        for (int i = 0; i < invCraft.getSizeInventory(); i++) {
            ItemStack stack = invCraft.getStackInSlot(i);
            if (stack != null && item == null && CharmRegistry.isValidArmor(stack))
                item = stack;
        }

        if (!ArmorAAPI.charmRegistry.canApplyCharmToItem(item, CharmIDs.SIGHT) || ArmorAAPI.charmRegistry.itemHasCharm(item, CharmIDs.SIGHT))
            return null;

        ItemStack copy = item.copy();
        ArmorAAPI.charmRegistry.applyCharmToItem(copy, CharmIDs.SIGHT);
        return copy;
    }

    @Override
    public int getRecipeSize() {
        return 10;
    }

    @Override
    public ItemStack getRecipeOutput() {
        return null;
    }
}
