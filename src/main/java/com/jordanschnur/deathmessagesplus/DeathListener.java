package com.jordanschnur.deathmessagesplus;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerLoginEvent;

import java.util.logging.Logger;

public final class DeathListener implements Listener {

    @EventHandler
    public void onLogin(PlayerDeathEvent e) {
        Logger logger = DeathMessagesPlusMain.getPluginLogger();

        logger.info(e.getDeathMessage());

        logger.info("Event fired....");
        //logger.info(e.getPlayer().getDisplayName() + " Logged qergqebggbeqin bro");
    }

    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent e) {
        Logger logger = DeathMessagesPlusMain.getPluginLogger();

        logger.info("Entity damaged");

        if(e.getEntity() instanceof Player) {
            logger.info("Is instance of player");
            Player p = (Player)e.getEntity();
            logger.info("health: " + p.getHealth());


        }
    }
}
