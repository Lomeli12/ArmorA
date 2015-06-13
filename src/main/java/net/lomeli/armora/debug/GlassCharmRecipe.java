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

public class GlassCharmRecipe implements IRecipe {
    @Override
    public boolean matches(InventoryCrafting invCraft, World world) {
        boolean foundGlass = false;
        boolean foundItem = false;

        for (int i = 0; i < invCraft.getSizeInventory(); i++) {
            ItemStack stack = invCraft.getStackInSlot(i);
            if (stack != null) {
                if (stack.getItem() == Item.getItemFromBlock(Blocks.glass) && !foundGlass)
                    foundGlass = true;
                else if (!foundItem) {
                    if (!ArmorAAPI.charmRegistry.isItemBlackListed(stack) && !ArmorAAPI.charmRegistry.itemHasCharm(stack, CharmIDs.GLASS) && CharmRegistry.isValidArmor(stack))
                        foundItem = true;
                    else return false;
                } else return false;
            }
        }

        return foundGlass && foundItem;
    }

    @Override
    public ItemStack getCraftingResult(InventoryCrafting invCraft) {
        ItemStack item = null;

        for (int i = 0; i < invCraft.getSizeInventory(); i++) {
            ItemStack stack = invCraft.getStackInSlot(i);
            if (stack != null && item == null && CharmRegistry.isValidArmor(stack))
                item = stack;
        }

        if (ArmorAAPI.charmRegistry.isItemBlackListed(item) || ArmorAAPI.charmRegistry.itemHasCharm(item, CharmIDs.GLASS))
            return null;

        ItemStack copy = item.copy();
        ArmorAAPI.charmRegistry.applyCharmToItem(copy, CharmIDs.GLASS);
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
