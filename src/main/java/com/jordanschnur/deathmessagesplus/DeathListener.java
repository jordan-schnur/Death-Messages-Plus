package com.jordanschnur.deathmessagesplus;

import com.jordanschnur.deathmessagesplus.handlers.ExplosionDeathHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerLoginEvent;

import java.util.logging.Logger;

public final class DeathListener implements Listener {

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        Logger logger = DeathMessagesPlusMain.getPluginLogger();

        if (!(e.getEntity() instanceof Player)) { // Don't know if this can occur, but best to check
            return;
        }

        EntityDamageEvent lastDamage = e.getEntity().getLastDamageCause();
        if (lastDamage != null) {
            switch (lastDamage.getCause()) {
                case BLOCK_EXPLOSION:
                    new ExplosionDeathHandler()
                    break;
            }
        } else {
            // Deal with cases where EntityDamageEvent is null
        }
        e.getEntity().getLastDamageCause();

        logger.info("Event fired....");
        //logger.info(e.getPlayer().getDisplayName() + " Logged qergqebggbeqin bro");
    }

    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent e) {
        Logger logger = DeathMessagesPlusMain.getPluginLogger();

        logger.info("Entity damaged");

        e.getEntity().getLastDamageCause();

        if(e.getEntity() instanceof Player) {
            logger.info("Is instance of player");
            Player p = (Player)e.getEntity();
            logger.info("health: " + p.getHealth());


        }
    }
}
