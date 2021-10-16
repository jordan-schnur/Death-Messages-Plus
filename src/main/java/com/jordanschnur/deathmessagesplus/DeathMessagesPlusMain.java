package com.jordanschnur.deathmessagesplus;

import com.google.common.collect.Iterators;
import com.jordanschnur.deathmessagesplus.handlers.DynamicHandlerInterface;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.ServiceLoader;
import java.util.logging.Logger;

public final class DeathMessagesPlusMain extends JavaPlugin {

    private static Logger logger;

    @Override
    public void onEnable() {
        // Plugin startup logic

        DeathMessagesPlusMain.logger = this.getLogger();
        getServer().getPluginManager().registerEvents(new DeathListener(), this);

        logger.info("Starting search");
        logger.info(DynamicHandlerInterface.class.toString());
        ServiceLoader<DynamicHandlerInterface> handlers = ServiceLoader.load(DynamicHandlerInterface.class, getClassLoader());
        logger.info(String.valueOf(Iterators.size(handlers.iterator())));
        for(DynamicHandlerInterface handler : handlers) {
            logger.info("FOund class");
            logger.info(handler.getType().name());
        }
        logger.info("Finished Search");

        this.getLogger().info("Starting Death Messages Plus");

        if(!getDataFolder().exists()) {
            this.getLogger().info("No configuration found for Death Messages Plus. Creating it.");
            try {
                this.getDataFolder().mkdirs();
            } catch (Exception e) {
                e.printStackTrace();
            }

            File dataFolder = this.getDataFolder();

            getConfig().options().copyDefaults(true);
            saveConfig();
        }

        try {
            getConfig().load(new File(getDataFolder(), "config.yml"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
        }

        logger.info(getConfig().getStringList("arrows.shotby.using").toString());

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static Logger getPluginLogger() {
        return DeathMessagesPlusMain.logger;
    }
}
