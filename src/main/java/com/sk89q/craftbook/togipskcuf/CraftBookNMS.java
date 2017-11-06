package com.sk89q.craftbook.togipskcuf;

import com.sk89q.craftbook.bukkit.CraftBookPlugin;
import com.sk89q.craftbook.togipskcuf.adapter.RealNMSAdapter;
import org.bukkit.plugin.java.JavaPlugin;

public final class CraftBookNMS extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        try {
            CraftBookPlugin.inst().setNmsAdapter(new RealNMSAdapter());
            System.out.println("Successfully injected CraftBookNMS!");
        } catch (Throwable t) {
            System.out.println("Failed to inject CraftBookNMS. Are you using the version corresponding to your MC version?");
            t.printStackTrace();
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
