package com.jordanschnur.deathmessagesplus;

import com.jordanschnur.deathmessagesplus.logging.DMPLogger;
import com.jordanschnur.deathmessagesplus.logging.LoggingContext;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByBlockEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.logging.Logger;

public final class DeathListener implements Listener {

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        Logger logger = DeathMessagesPlusMain.getPluginLogger();

        if (!(e.getEntity() instanceof Player)) { // Don't know if this can occur, but best to check
            return;
        }

        EntityDamageEvent lastDamage = e.getEntity().getLastDamageCause();

        LoggingContext loggingContext = new LoggingContext();
        loggingContext.addContext("Player", e.getEntity().getDisplayName());
        loggingContext.addContext("Last Damage", lastDamage.getCause());
        loggingContext.addContext("Last Damage entity_type", lastDamage.getEntityType());
        loggingContext.addContext("Default Death message", e.getDeathMessage());

        if (lastDamage instanceof EntityDamageByBlockEvent) {
            loggingContext.addContext("Damager Block Type", ((EntityDamageByBlockEvent) lastDamage).getDamager().getType());
            loggingContext.addContext("ClassType", EntityDamageByBlockEvent.class);
        } else if(lastDamage instanceof EntityDamageByEntityEvent) {
            loggingContext.addContext("Damager Entity Type", ((EntityDamageByEntityEvent) lastDamage).getDamager().getType());
            loggingContext.addContext("Damager Entity Name", ((EntityDamageByEntityEvent) lastDamage).getDamager().getName());
            loggingContext.addContext("ClassType", EntityDamageByEntityEvent.class);
        } else {
            loggingContext.addContext("ClassType", lastDamage.getClass());
        }

        if (e.getEntity().getKiller() != null) {
            loggingContext.addContext("Killer Player: ", e.getEntity().getKiller().getDisplayName());
        }

        if (lastDamage.getEntity() != null) {

            if (lastDamage.getEntity() instanceof LivingEntity) {
                LivingEntity l = (LivingEntity) lastDamage.getEntity();
                loggingContext.addContext("Living Entity: ", l.getName());

                if (l.getEquipment() != null && (l.getEquipment().getItemInMainHand() != null || l.getEquipment().getItemInOffHand() != null)) {
                    if (l.getEquipment().getItemInOffHand() != null) {
                        if(l.getEquipment().getItemInOffHand().hasItemMeta()) {
                            loggingContext.addContext("Killer Item Off Hand Meta: ", l.getEquipment().getItemInOffHand().getItemMeta().getDisplayName());
                        } else {
                            loggingContext.addContext("Killer Item Off Hand: ", l.getEquipment().getItemInOffHand().getType().toString());
                        }
                    }

                    if (l.getEquipment().getItemInMainHand() != null) {
                        if(l.getEquipment().getItemInMainHand().hasItemMeta()) {
                            loggingContext.addContext("Killer Item Main Hand Meta: ", l.getEquipment().getItemInMainHand().getItemMeta().getDisplayName());
                        } else {
                            loggingContext.addContext("Killer Item Main Hand: ", l.getEquipment().getItemInMainHand().getType().toString());
                        }
                    }
                }
            } else {
                loggingContext.addContext("Entity: ", lastDamage.getEntity().getName());
            }

        }

        if (lastDamage != null) {
            try {
                String newDeathMessage = DeathMessagesPlusMain.getHandler(lastDamage.getCause()).getDeathMessage(e, DeathMessagesPlusMain.getConfiguration());
                e.setDeathMessage(newDeathMessage);
                DeathMessagesPlusMain.getDmpLogger().logToFile(newDeathMessage, loggingContext);
            } catch (Exception exception) {
                DeathMessagesPlusMain.getDmpLogger().logToFile("Handler Not Found", loggingContext);
                exception.printStackTrace();
            }

        } else {
            DeathMessagesPlusMain.getDmpLogger().logToFile("Last Damage was null", loggingContext);
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
