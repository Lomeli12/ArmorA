package net.lomeli.armora.charms;

import net.lomeli.armora.api.ArmorAAPI;
import net.lomeli.armora.charms.base.GlassCharm;
import net.lomeli.armora.charms.base.NightVisionCharm;

public class ModCharms {

    public static void registerCharms() {
        ArmorAAPI.charmRegistry.registerCharm(new GlassCharm());
        ArmorAAPI.charmRegistry.registerCharm(new NightVisionCharm());
    }
}
