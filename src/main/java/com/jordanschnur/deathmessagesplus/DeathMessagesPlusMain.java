package com.jordanschnur.deathmessagesplus;

import com.jordanschnur.deathmessagesplus.exception.HandlerNotFound;
import com.jordanschnur.deathmessagesplus.handlers.AbstractDeathHandler;
import com.jordanschnur.deathmessagesplus.handlers.DynamicHandlerInterface;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.ServiceLoader;
import java.util.logging.Logger;

public final class DeathMessagesPlusMain extends JavaPlugin {

    private static Logger logger;
    private static HashMap<EntityDamageEvent.DamageCause, AbstractDeathHandler> handlers = new HashMap<EntityDamageEvent.DamageCause, AbstractDeathHandler>();
    private static FileConfiguration configuration;

    @Override
    public void onEnable() {
        // Plugin startup logic

        DeathMessagesPlusMain.logger = this.getLogger();
        getServer().getPluginManager().registerEvents(new DeathListener(), this);

        logger.info("Starting search");
        logger.info(AbstractDeathHandler.class.toString());
        ServiceLoader<AbstractDeathHandler> handlers = ServiceLoader.load(AbstractDeathHandler.class, getClassLoader());

        for (AbstractDeathHandler handler : handlers) {
            DeathMessagesPlusMain.handlers.put(handler.getType(), handler);
            //TODO: Remove this log
            logger.info("Registering " + handler.getType().name() + " handler.");
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

    public static AbstractDeathHandler getHandler(EntityDamageEvent.DamageCause cause) throws Exception {
        AbstractDeathHandler handler = DeathMessagesPlusMain.handlers.get(cause);
        if (handler == null) {
            DeathMessagesPlusMain.getPluginLogger().info("Failed to find " + cause.name());
            throw new HandlerNotFound();
        }

        return handler;
    }

    public static FileConfiguration getConfiguration() {
        return DeathMessagesPlusMain.configuration;
    }
}
