package net.lomeli.armora;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLInterModComms;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

import net.lomeli.armora.core.Proxy;
import net.lomeli.armora.core.handler.IMCHandler;
import net.lomeli.armora.libs.ModLibs;

@Mod(modid = ModLibs.MOD_ID, name = ModLibs.NAME, version = ModLibs.VERSION, dependencies = ModLibs.DEPENDENCIES, acceptedMinecraftVersions = ModLibs.MINECRAFT_VERSION)
public class Armora {
    @Mod.Instance
    public static Armora INSTANCE;

    @SidedProxy(serverSide = ModLibs.PROXY_SERVER, clientSide = ModLibs.PROXY_CLIENT)
    public static Proxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit();
    }

    @Mod.EventHandler
    public void handleIMC(FMLInterModComms.IMCEvent event) {
        IMCHandler.processMessages(event.getMessages());
    }
}
