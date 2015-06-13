package net.lomeli.armora.core;

import cpw.mods.fml.common.registry.GameRegistry;

import net.lomeli.armora.api.ArmorAAPI;
import net.lomeli.armora.charms.CharmRegistry;
import net.lomeli.armora.charms.ModCharms;
import net.lomeli.armora.compat.CompatManager;
import net.lomeli.armora.debug.GlassCharmRecipe;

public class Proxy {
    public void preInit(){
        ArmorAAPI.charmRegistry = CharmRegistry.getInstance();
        ModCharms.registerCharms();
        GameRegistry.addRecipe(new GlassCharmRecipe());
        CompatManager.initCompatModules();
    }

    public void init() {

    }

    public void postInit() {
        CompatManager.registerModules();
    }
}
