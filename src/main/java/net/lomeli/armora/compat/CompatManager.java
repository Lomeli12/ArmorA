package net.lomeli.armora.compat;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;

import cpw.mods.fml.common.Loader;

public class CompatManager {
    private static List<ICompatModule> moduleList;

    public static void initCompatModules() {
        if (Loader.isModLoaded("Botania"))
            addModlue(new BotaniaModule());
    }

    public static void addModlue(ICompatModule module) {
        if (moduleList == null)
            moduleList = new ArrayList<ICompatModule>();
        if (module == null)
            return;
        moduleList.add(module);
    }

    public static void registerModules(){
        for (ICompatModule module : moduleList) {
            module.registerModule();
        }
    }
}
