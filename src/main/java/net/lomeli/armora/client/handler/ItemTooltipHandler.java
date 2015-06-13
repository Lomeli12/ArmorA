package net.lomeli.armora.client.handler;

import org.lwjgl.input.Keyboard;

import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;

import net.minecraftforge.event.entity.player.ItemTooltipEvent;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;

import net.lomeli.armora.api.ArmorAAPI;
import net.lomeli.armora.api.charms.AbstractCharm;
import net.lomeli.armora.charms.CharmRegistry;

public class ItemTooltipHandler {
    @SubscribeEvent
    public void handleItemTooltipEvent(ItemTooltipEvent event) {
        ItemStack stack = event.itemStack;
        if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) && CharmRegistry.isValidArmor(stack) && !ArmorAAPI.charmRegistry.isItemBlackListed(stack)) {
            String[] charms = ArmorAAPI.charmRegistry.getItemCharms(stack);
            if (charms != null && charms.length > 0) {
                event.toolTip.add(EnumChatFormatting.DARK_PURPLE + "" + EnumChatFormatting.ITALIC + "Charms:" + EnumChatFormatting.RESET);
                for (String id : charms) {
                    AbstractCharm charm = ArmorAAPI.charmRegistry.getCharm(id);
                    if (charm != null)
                        event.toolTip.add(EnumChatFormatting.GOLD + StatCollector.translateToLocal(charm.unlocalizedName()) + EnumChatFormatting.RESET);
                }
            }
        }
    }
}
