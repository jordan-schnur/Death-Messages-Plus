package com.jordanschnur.deathmessagesplus.handlers;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Trident;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

public class ProjectileHandler extends AbstractDeathHandler {
    @Override
    public String constructDeathMessage(PlayerDeathEvent p) {
        EntityDamageByEntityEvent e = (EntityDamageByEntityEvent) p.getEntity().getLastDamageCause();

        if (e.getDamager() instanceof Arrow) {
            Arrow a = (Arrow) e.getDamager();

            if (a.getShooter() instanceof LivingEntity) {

                LivingEntity shooter = (LivingEntity) a.getShooter();

                return this.getDeathMessageFormat("arrows.default").replaceAll(killerNamePattern, ((LivingEntity) a.getShooter()).getName());
            }
        } if (e.getDamager() instanceof Trident) {
            Trident a = (Trident) e.getDamager();

            if (a.getShooter() instanceof LivingEntity) {

                LivingEntity shooter = (LivingEntity) a.getShooter();

                return this.getDeathMessageFormat("trident.default").replaceAll(killerNamePattern, ((LivingEntity) a.getShooter()).getName());
            }
        }

        return null;
    }

    @Override
    public EntityDamageEvent.DamageCause getType() { return EntityDamageEvent.DamageCause.PROJECTILE; }
}
