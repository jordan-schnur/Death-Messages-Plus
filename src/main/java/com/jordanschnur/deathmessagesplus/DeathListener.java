package com.jordanschnur.deathmessagesplus;

import com.jordanschnur.deathmessagesplus.exception.HandlerNotFound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.logging.Logger;

public final class DeathListener implements Listener {

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        Logger logger = DeathMessagesPlusMain.getPluginLogger();

        logger.info("Player death: " + e.getEntity().toString() + " Cause: " + e.getEntity().getLastDamageCause() + e.getEntity().getLastDamageCause().getEntity().getName());

        if (!(e.getEntity() instanceof Player)) { // Don't know if this can occur, but best to check
            return;
        }

        EntityDamageEvent lastDamage = e.getEntity().getLastDamageCause();

        logger.info("Played death.");

        if (lastDamage != null) {
            try {
                e.setDeathMessage(DeathMessagesPlusMain.getHandler(lastDamage.getCause()).getDeathMessage(e, DeathMessagesPlusMain.getConfiguration()));
            } catch (Exception exception) {
                exception.printStackTrace();
            }

        } else {
            // Deal with cases where EntityDamageEvent is null
        }
    }

    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent e) {
        Logger logger = DeathMessagesPlusMain.getPluginLogger();

        //logger.info("Entity damaged");

        e.getEntity().getLastDamageCause();

        if(e.getEntity() instanceof Player) {
            //logger.info("Is instance of player");
            Player p = (Player)e.getEntity();
            //logger.info("health: " + p.getHealth());


        }
    }
}
