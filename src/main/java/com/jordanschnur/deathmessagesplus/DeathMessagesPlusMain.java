package com.jordanschnur.deathmessagesplus;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class DeathMessagesPlusMain extends JavaPlugin {

    private static Logger logger;

    @Override
    public void onEnable() {
        // Plugin startup logic

        DeathMessagesPlusMain.logger = this.getLogger();
        getServer().getPluginManager().registerEvents(new DeathListener(), this);
        this.getLogger().info("Starting Death Messages Plus");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static Logger getPluginLogger() {
        return DeathMessagesPlusMain.logger;
    }
}
