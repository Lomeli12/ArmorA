package net.lomeli.armora.api.charms;

import java.util.Map;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public interface ICharmRegistry {
    /**
     * Registers a new charm.
     *
     * @param charm
     */
    public void registerCharm(AbstractCharm charm);

    /**
     * Checks if charmID has been registered
     *
     * @param charmID
     * @return
     */
    public boolean charmWithIDExists(String charmID);

    /**
     * Gets charms from ID. Returns null if no charm with the specific id is found.
     *
     * @param id
     * @return
     */
    public AbstractCharm getCharm(String id);

    /**
     * Blacklist charm from item
     *
     * @param item
     * @param charm
     */
    public void blackListCharmFromItem(String item, String charm);

    /**
     * Check if item can have charm
     *
     * @param item
     * @param charm
     * @return
     */
    public boolean canApplyCharmToItem(String item, String charm);

    public boolean canApplyCharmToItem(Item item, String charm);

    public boolean canApplyCharmToItem(ItemStack stack, String charm);

    /**
     * Get charms applied to item. If the item has no charms, it return a 0 lenght array.
     *
     * @param stack
     * @return
     */
    public String[] getItemCharms(ItemStack stack);

    /**
     * Checks if stack has specific charm.
     *
     * @param stack
     * @param charmID
     * @return
     */
    public boolean itemHasCharm(ItemStack stack, String charmID);

    /**
     * Apply charm to item. If item is blacklisted or already has charm, will return false.
     *
     * @param stack
     * @param charmID
     */
    public boolean applyCharmToItem(ItemStack stack, String charmID);

    /**
     * Gets a copy of charm registry.
     *
     * @return
     */
    public Map<String, AbstractCharm> getCharmMap();

    /**
     * Gets a copy of the item charm blacklist
     *
     * @return
     */
    public Map<String, String> getItemCharmBlackList();
}
