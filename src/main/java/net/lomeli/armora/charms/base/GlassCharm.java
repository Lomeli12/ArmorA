package net.lomeli.armora.charms.base;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import net.minecraftforge.client.event.RenderPlayerEvent;

import net.lomeli.armora.api.ArmorAAPI;
import net.lomeli.armora.api.charms.AbstractCharm;
import net.lomeli.armora.api.charms.CharmEventType;
import net.lomeli.armora.libs.CharmIDs;

public class GlassCharm extends AbstractCharm {
    private static final int[] values = { 3, 2, 1, 0 };
    @Override
    public void performCharm(World world, EntityLivingBase entity, ItemStack stack, CharmEventType type, Object... data) {
        if (type == CharmEventType.RENDER_PRE && data != null && data.length > 0 && data[0] instanceof RenderPlayerEvent.SetArmorModel) {
            RenderPlayerEvent.SetArmorModel event = (RenderPlayerEvent.SetArmorModel) data[0];
            ItemArmor armor = (ItemArmor) stack.getItem();
            EntityPlayer player = (EntityPlayer) entity;
            if (event.slot < 4) {
                if (values[event.slot] == armor.armorType && ArmorAAPI.charmRegistry.itemHasCharm(player.inventory.armorItemInSlot(event.slot), getCharmID()))
                    event.result = 0;
            }
        }
    }

    @Override
    public String getCharmID() {
        return CharmIDs.GLASS;
    }

    @Override
    public String unlocalizedName() {
        return "Clear Glass";
    }
}
