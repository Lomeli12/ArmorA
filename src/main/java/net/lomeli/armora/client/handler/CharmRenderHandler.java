package net.lomeli.armora.client.handler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;

import net.minecraftforge.client.event.RenderPlayerEvent;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;

import net.lomeli.armora.api.ArmorAAPI;
import net.lomeli.armora.api.charms.AbstractCharm;
import net.lomeli.armora.api.charms.CharmType;

public class CharmRenderHandler {

    @SubscribeEvent
    public void setPlayerArmor(RenderPlayerEvent.SetArmorModel event) {
        EntityPlayer player = event.entityPlayer;
        InventoryPlayer invPlayer = player.inventory;

        dispatchRenders(invPlayer, player, event, CharmType.RENDER_PRE);
    }

    @SubscribeEvent
    public void playerSpecialRenderPre(RenderPlayerEvent.Specials.Pre event) {
        EntityPlayer player = event.entityPlayer;
        InventoryPlayer invPlayer = player.inventory;

        dispatchRenders(invPlayer, player, event, CharmType.RENDER_PRE);
    }

    @SubscribeEvent
    public void playerSpecialRenderPost(RenderPlayerEvent.Specials.Post event) {
        EntityPlayer player = event.entityPlayer;
        InventoryPlayer invPlayer = player.inventory;

        dispatchRenders(invPlayer, player, event, CharmType.RENDER_POST);
    }

    private void dispatchRenders(InventoryPlayer inv, EntityPlayer player, RenderPlayerEvent event, CharmType type) {
        for (int i = 0; i < inv.getSizeInventory(); i++) {
            ItemStack stack = inv.getStackInSlot(i);
            if (stack != null && !ArmorAAPI.charmRegistry.isItemBlackListed(stack)) {
                Item item = stack.getItem();
                String[] charms = ArmorAAPI.charmRegistry.getItemCharms(stack);
                if (charms != null && charms.length > 0) {
                    for (String id : charms) {
                        AbstractCharm charm = ArmorAAPI.charmRegistry.getCharm(id);
                        if (charm != null)
                            charm.performCharm(player.worldObj, player, stack, type, event);
                    }
                }
            }
        }
    }
}
