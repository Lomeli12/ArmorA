package net.lomeli.armora.core;

import net.minecraftforge.common.MinecraftForge;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.registry.GameRegistry;

import net.lomeli.armora.api.ArmorAAPI;
import net.lomeli.armora.charms.CharmRegistry;
import net.lomeli.armora.charms.ModCharms;
import net.lomeli.armora.compat.CompatManager;
import net.lomeli.armora.core.handler.CharmEventHandler;
import net.lomeli.armora.debug.GlassCharmRecipe;
import net.lomeli.armora.debug.NightVisionCharmRecipe;

public class Proxy {
    public void preInit(){
        ArmorAAPI.charmRegistry = CharmRegistry.getInstance();
        ModCharms.registerCharms();
        GameRegistry.addRecipe(new GlassCharmRecipe());
        GameRegistry.addRecipe(new NightVisionCharmRecipe());
        CompatManager.initCompatModules();
    }

    public void init() {
        CharmEventHandler charmEvents = new CharmEventHandler();
        FMLCommonHandler.instance().bus().register(charmEvents);
        MinecraftForge.EVENT_BUS.register(charmEvents);
    }

    public void postInit() {
        CompatManager.registerModules();
    }
}
