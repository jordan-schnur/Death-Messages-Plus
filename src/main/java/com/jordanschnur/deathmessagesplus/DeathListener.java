package com.jordanschnur.deathmessagesplus;

import com.jordanschnur.deathmessagesplus.deathcontext.DeathContext;
import com.jordanschnur.deathmessagesplus.logging.LoggingContext;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByBlockEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Logger;

public final class DeathListener implements Listener {

    private Map<UUID, DeathContext> damages = new HashMap<UUID, DeathContext>();

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
            EntityDamageByBlockEvent blockDamage = (EntityDamageByBlockEvent) lastDamage;

            if (((EntityDamageByBlockEvent) lastDamage).getDamager() == null) {
                loggingContext.addContext("Damager Null", "");
            } else {
                loggingContext.addContext("Damager Block Type", ((EntityDamageByBlockEvent) lastDamage).getDamager().getType());
            }
            loggingContext.addContext("ClassType", EntityDamageByBlockEvent.class);
        } else if(lastDamage instanceof EntityDamageByEntityEvent) {
            EntityDamageByEntityEvent entityDamageByEntityEvent = (EntityDamageByEntityEvent) lastDamage;
            loggingContext.addContext("Damager Entity Type", ((EntityDamageByEntityEvent) lastDamage).getDamager().getType());
            loggingContext.addContext("Damager Entity Name", ((EntityDamageByEntityEvent) lastDamage).getDamager().getName());
            loggingContext.addContext("Damager Entity ID", ((EntityDamageByEntityEvent) lastDamage).getDamager().getEntityId());
            loggingContext.addContext("Damager Entity Class", entityDamageByEntityEvent.getDamager().getClass());
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
                String newDeathMessage = DeathMessagesPlusMain.getHandler(lastDamage.getCause()).getDeathMessage(
                        e,
                        DeathMessagesPlusMain.getConfiguration(),
                        this.damages.get(e.getEntity().getUniqueId()));
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

    //TODO: Map needs to be cleared when player leaves the server

    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent e) {
        if (e.getEntity() instanceof Player && e.getDamager() instanceof LivingEntity) {
            DeathMessagesPlusMain.getPluginLogger().info("Player Damaged");
            DeathMessagesPlusMain.getPluginLogger().info("Player Damage Cause: " + e.getCause());
            if (e.getDamager() != null) {
                DeathMessagesPlusMain.getPluginLogger().info("Damager Type: " + e.getDamager().getType());
            } else {
                DeathMessagesPlusMain.getPluginLogger().info("Damager is null");
            }

            Entity damager = e.getDamager();
            Player player = (Player) e.getEntity();
            Projectile projectile = null;

            // If getShooter() is a living entity we set damger to that entity and set projectile to the projectile
            if (damager instanceof Projectile && ((Projectile) damager).getShooter() instanceof LivingEntity) {
                projectile = (Projectile) damager;
                damager = (Entity) ((Projectile) damager).getShooter();
            }

            if (this.damages.get(e.getEntity().getUniqueId()) == null) {
                this.damages.put(e.getEntity().getUniqueId(), new DeathContext(player, damager, projectile));
            } else {
                this.damages.replace(e.getEntity().getUniqueId(), new DeathContext(player, damager, projectile));
            }
        }
    }
}
