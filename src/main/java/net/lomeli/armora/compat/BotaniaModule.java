package net.lomeli.armora.compat;

import java.util.Iterator;

import net.minecraft.item.Item;

import cpw.mods.fml.common.registry.FMLControlledNamespacedRegistry;
import cpw.mods.fml.common.registry.GameData;

import net.lomeli.armora.api.ArmorAAPI;

import vazkii.botania.api.item.IPhantomInkable;

public class BotaniaModule implements ICompatModule {
    @Override
    public void registerModule() {
        final FMLControlledNamespacedRegistry<Item> itemRegistry = GameData.getItemRegistry();
        Iterator<Item> itemIterator = itemRegistry.iterator();
        while (itemIterator.hasNext()) {
            Item item = itemIterator.next();
            if (item != null && item instanceof IPhantomInkable)
                ArmorAAPI.charmRegistry.addItemToBlackList(itemRegistry.getNameForObject(item));
        }
    }
}
