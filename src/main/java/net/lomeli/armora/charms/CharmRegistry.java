package net.lomeli.armora.charms;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.RegistryNamespaced;

import cpw.mods.fml.common.registry.GameData;

import net.lomeli.lomlib.util.NBTUtil;

import net.lomeli.armora.api.charms.AbstractCharm;
import net.lomeli.armora.api.charms.ICharmRegistry;
import net.lomeli.armora.libs.ModLibs;

public class CharmRegistry implements ICharmRegistry {
    private Map<String, AbstractCharm> charmMap;
    private List<String> blackList;

    public CharmRegistry() {
        charmMap = Maps.newHashMap();
        blackList = Lists.newArrayList();
    }

    @Override
    public void registerCharm(AbstractCharm charm) {
        if (charm == null)
            throw new RuntimeException("Registering null value to Charm Registry!");
        if (Strings.isNullOrEmpty(charm.getCharmID()))
            throw new RuntimeException("Charm ID cannot be null!");
        if (charm.getCharmID().contains(";"))
            throw new RuntimeException("Charm ID cannot contain \";\"");
        if (charmWithIDExists(charm.getCharmID()))
            throw new RuntimeException("Charm ID already registered!");
        charmMap.put(charm.getCharmID(), charm);
    }

    @Override
    public boolean charmWithIDExists(String charmID) {
        return charmMap.containsKey(charmID);
    }

    @Override
    public AbstractCharm getCharm(String id) {
        return charmWithIDExists(id) ? charmMap.get(id) : null;
    }

    @Override
    public void addItemToBlackList(String item) {
        blackList.add(item);
    }

    @Override
    public boolean isItemBlackListed(Item item) {
        if (item == null)
            return false;
        String name = itemRegistry.getNameForObject(item);
        return blackList.contains(name);
    }

    @Override
    public boolean isItemBlackListed(ItemStack stack) {
        return stack == null ? false : isItemBlackListed(stack.getItem());
    }

    @Override
    public String[] getItemCharms(ItemStack stack) {
        if (stack == null || stack.getItem() == null)
            return new String[0];
        String values = NBTUtil.getString(stack, ModLibs.CHARM_TAG);
        String[] charms = values.split(";");
        return charms != null ? charms : new String[0];
    }

    @Override
    public boolean itemHasCharm(ItemStack stack, String charmID) {
        if (stack == null || stack.getItem() == null)
            return false;
        if (isItemBlackListed(stack))
            return false;
        if (!charmWithIDExists(charmID))
            return false;
        String[] charms = getItemCharms(stack);
        if (charms != null && charms.length > 0) {
            for (String id : charms) {
                if (id.equals(charmID))
                    return true;
            }
        }
        return false;
    }

    @Override
    public boolean applyCharmToItem(ItemStack stack, String charmID) {
        if (itemHasCharm(stack, charmID))
            return false;
        String values = NBTUtil.getString(stack, ModLibs.CHARM_TAG) + charmID + ";";
        NBTUtil.setString(stack, ModLibs.CHARM_TAG, values);
        return true;
    }

    @Override
    public Map<String, AbstractCharm> getCharmMap() {
        Map<String, AbstractCharm> copy = Maps.newHashMap();
        copy.putAll(charmMap);
        return copy;
    }

    @Override
    public List<String> getBlackList() {
        List<String> copy = Lists.newArrayList();
        copy.addAll(blackList);
        return copy;
    }

    private static final RegistryNamespaced itemRegistry = GameData.getItemRegistry();
    private static final CharmRegistry INSTANCE = new CharmRegistry();

    public static CharmRegistry getInstance() {
        return INSTANCE;
    }

    public static boolean isValidArmor(ItemStack stack) {
        return (stack == null || stack.getItem() == null) ? false : (stack.getItem() instanceof ItemArmor || stack.getItem() == Item.getItemFromBlock(Blocks.pumpkin) || stack.getItem() == Items.skull);
    }
}
