package net.lomeli.armora.client;

import net.minecraftforge.common.MinecraftForge;

import net.lomeli.armora.client.handler.CharmRenderHandler;
import net.lomeli.armora.client.handler.ItemTooltipHandler;
import net.lomeli.armora.core.Proxy;

public class ClientProxy extends Proxy {
    @Override
    public void preInit() {
        super.preInit();
    }

    @Override
    public void init() {
        super.init();
        MinecraftForge.EVENT_BUS.register(new CharmRenderHandler());
        MinecraftForge.EVENT_BUS.register(new ItemTooltipHandler());
    }

    @Override
    public void postInit() {
        super.postInit();
    }
}
