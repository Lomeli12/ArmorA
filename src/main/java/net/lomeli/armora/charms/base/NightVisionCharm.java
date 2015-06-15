package net.lomeli.armora.charms.base;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

import net.lomeli.armora.api.charms.AbstractCharm;
import net.lomeli.armora.api.charms.CharmEventType;
import net.lomeli.armora.libs.CharmIDs;

public class NightVisionCharm extends AbstractCharm {
    @Override
    public void performCharm(World world, EntityLivingBase entity, ItemStack stack, CharmEventType type, Object... data) {
        if (type == CharmEventType.PASSIVE) {
            PotionEffect nightVision = new PotionEffect(Potion.nightVision.getId(), 5, 0, false);
            entity.addPotionEffect(nightVision);
        }
    }

    @Override
    public String getCharmID() {
        return CharmIDs.SIGHT;
    }

    @Override
    public String unlocalizedName() {
        return "Sight";
    }
}
