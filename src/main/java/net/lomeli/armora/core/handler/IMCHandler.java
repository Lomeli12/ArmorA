package net.lomeli.armora.core.handler;

import com.google.common.base.Strings;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

import java.util.Iterator;
import java.util.List;

import net.minecraft.nbt.NBTTagCompound;

import cpw.mods.fml.common.event.FMLInterModComms;

import net.lomeli.armora.api.ArmorAAPI;
import net.lomeli.armora.libs.ModLibs;

/**
 * Totally copying Botania's IMCHandler, which I wrote anyway so it doesn't matter
 */
public class IMCHandler {
    public static void processMessages(ImmutableList<FMLInterModComms.IMCMessage> messageList) {
        Iterator<FMLInterModComms.IMCMessage> iterator = messageList.iterator();
        while (iterator.hasNext()) {
            FMLInterModComms.IMCMessage message = iterator.next();
            if (message != null && message.key != null && message.key.equals(ModLibs.BLACKLIST_ITEM) && message.isNBTMessage()) {
                NBTTagCompound tag = message.getNBTValue();
                String itemName = tag.getString(ModLibs.IMC_ITEM_NAME);
                String blackList = tag.getString(ModLibs.IMC_CHARMS);
                List<String> charms = Lists.newArrayList();
                if (!Strings.isNullOrEmpty(itemName) && !Strings.isNullOrEmpty(blackList)) {
                    if (blackList.contains(";")) {
                        String[] values = blackList.split(";");
                        for (String id : values) {
                            if (!Strings.isNullOrEmpty(id))
                                charms.add(id);
                        }
                    } else
                        charms.add(blackList);

                    for (String id : charms)
                        ArmorAAPI.charmRegistry.blackListCharmFromItem(itemName, id);
                }
            }
        }
    }
}
