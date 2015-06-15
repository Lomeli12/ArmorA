package net.lomeli.armora.core.handler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;

import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.relauncher.Side;

import net.lomeli.lomlib.util.entity.EntityUtil;

import net.lomeli.armora.api.ArmorAAPI;
import net.lomeli.armora.api.charms.AbstractCharm;
import net.lomeli.armora.api.charms.CharmEventType;

public class CharmEventHandler {

    @SubscribeEvent
    public void playerTick(TickEvent.PlayerTickEvent event) {
        if (event.player.worldObj.isRemote || event.side == Side.CLIENT)
            return;
        EntityPlayer player = event.player;
        InventoryPlayer inv = player.inventory;

        if (!EntityUtil.isFakePlayer(player) && event.phase == TickEvent.Phase.START)
            activateCharms(inv, player, CharmEventType.PASSIVE, event);
    }

    @SubscribeEvent
    public void playerAttacked(LivingAttackEvent event) {
        if (event.entityLiving.worldObj.isRemote)
            return;
        if (event.entityLiving instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) event.entityLiving;
            InventoryPlayer inv = player.inventory;
            if (!EntityUtil.isFakePlayer(player))
                activateCharms(inv, player, CharmEventType.DAMAGED, event, event.source, event.ammount);
        }
    }

    @SubscribeEvent
    public void playerAttacks(AttackEntityEvent event) {
        if (event.entityPlayer.worldObj.isRemote)
            return;

        EntityPlayer player = event.entityPlayer;
        InventoryPlayer inv = player.inventory;
        if (!EntityUtil.isFakePlayer(player))
            activateCharms(inv, player, CharmEventType.ATTACKING, event, event.target);
    }

    private void activateCharms(InventoryPlayer inv, EntityPlayer player, CharmEventType type, Object... data) {

        for (int i = 0; i < 4; i++) {
            ItemStack stack = inv.armorItemInSlot(i);
            if (stack != null) {
                String[] charms = ArmorAAPI.charmRegistry.getItemCharms(stack);
                if (charms != null && charms.length > 0) {
                    for (String id : charms) {
                        AbstractCharm charm = ArmorAAPI.charmRegistry.getCharm(id);
                        if (charm != null && ArmorAAPI.charmRegistry.canApplyCharmToItem(stack, id))
                            charm.performCharm(player.worldObj, player, stack, type, data);
                    }
                }
            }
        }
    }
}
