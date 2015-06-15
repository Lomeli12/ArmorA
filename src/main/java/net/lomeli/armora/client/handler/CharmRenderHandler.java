package net.lomeli.armora.client.handler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;

import net.minecraftforge.client.event.RenderPlayerEvent;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;

import net.lomeli.armora.api.ArmorAAPI;
import net.lomeli.armora.api.charms.AbstractCharm;
import net.lomeli.armora.api.charms.CharmEventType;

public class CharmRenderHandler {

    @SubscribeEvent
    public void setPlayerArmor(RenderPlayerEvent.SetArmorModel event) {
        EntityPlayer player = event.entityPlayer;
        InventoryPlayer invPlayer = player.inventory;

        charmRenders(invPlayer, player, event, CharmEventType.RENDER_PRE);
    }

    @SubscribeEvent
    public void playerSpecialRenderPre(RenderPlayerEvent.Specials.Pre event) {
        EntityPlayer player = event.entityPlayer;
        InventoryPlayer invPlayer = player.inventory;

        charmRenders(invPlayer, player, event, CharmEventType.RENDER_PRE);
    }

    @SubscribeEvent
    public void playerSpecialRenderPost(RenderPlayerEvent.Specials.Post event) {
        EntityPlayer player = event.entityPlayer;
        InventoryPlayer invPlayer = player.inventory;

        charmRenders(invPlayer, player, event, CharmEventType.RENDER_POST);
    }

    private void charmRenders(InventoryPlayer inv, EntityPlayer player, RenderPlayerEvent event, CharmEventType type) {
        for (int i = 0; i < 4; i++) {
            ItemStack stack = inv.armorItemInSlot(i);
            if (stack != null) {
                String[] charms = ArmorAAPI.charmRegistry.getItemCharms(stack);
                if (charms != null && charms.length > 0) {
                    for (String id : charms) {
                        AbstractCharm charm = ArmorAAPI.charmRegistry.getCharm(id);
                        if (charm != null && ArmorAAPI.charmRegistry.canApplyCharmToItem(stack, id))
                            charm.performCharm(player.worldObj, player, stack, type, event);
                    }
                }
            }
        }
    }
}
