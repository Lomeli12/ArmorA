package net.lomeli.armora.api.charms;

import java.util.List;
import java.util.Map;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public interface ICharmRegistry {
    /**
     * Registers a new charm.
     * @param charm
     */
    public void registerCharm(AbstractCharm charm);

    /**
     * Checks if charmID has been registered
     * @param charmID
     * @return
     */
    public boolean charmWithIDExists(String charmID);

    /**
     * Gets charms from ID. Returns null if no charm with the specific id is found.
     * @param id
     * @return
     */
    public AbstractCharm getCharm(String id);

    /**
     * Adds item to blacklist so they cannot be charmed.
     * @param item
     */
    public void addItemToBlackList(String item);

    /**
     * Check if item is blacklisted
     * @param item
     * @return
     */
    public boolean isItemBlackListed(Item item);

    /**
     * Check if itemstack is blacklisted.
     * @param stack
     * @return
     */
    public boolean isItemBlackListed(ItemStack stack);

    /**
     * Get charms applied to item. If the item has no charms, it return a 0 lenght array.
     * @param stack
     * @return
     */
    public String[] getItemCharms(ItemStack stack);

    /**
     * Checks if stack has specific charm.
     * @param stack
     * @param charmID
     * @return
     */
    public boolean itemHasCharm(ItemStack stack, String charmID);

    /**
     * Apply charm to item. If item is blacklisted or already has charm, will return false.
     * @param stack
     * @param charmID
     */
    public boolean applyCharmToItem(ItemStack stack, String charmID);

    /**
     * Gets copy of charm registry.
     * @return
     */
    public Map<String, AbstractCharm> getCharmMap();

    /**
     * Gets copy of blacklist.
     * @return
     */
    public List<String> getBlackList();
}
