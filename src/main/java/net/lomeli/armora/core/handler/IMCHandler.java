package net.lomeli.armora.core.handler;

import com.google.common.base.Strings;
import com.google.common.collect.ImmutableList;

import java.util.Iterator;

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
            if (message != null && message.key != null && message.key.equals(ModLibs.BLACKLIST_ITEM) && message.isStringMessage()) {
                String value = message.getStringValue();
                if (!Strings.isNullOrEmpty(value))
                    ArmorAAPI.charmRegistry.addItemToBlackList(value);
            }
        }
    }
}
