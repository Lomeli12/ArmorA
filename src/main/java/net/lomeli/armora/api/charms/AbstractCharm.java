package net.lomeli.armora.api.charms;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public abstract class AbstractCharm {
    public abstract void performCharm(World world, EntityLivingBase entity, ItemStack stack, CharmEventType type, Object...data);

    public abstract String getCharmID();

    public abstract String unlocalizedName();
}
