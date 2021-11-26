package com.jordanschnur.deathmessagesplus.handlers;

import org.bukkit.entity.EntityType;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

public class EntityExplosionHandler extends AbstractDeathHandler {
    @Override
    public String constructDeathMessage(PlayerDeathEvent p) {
        EntityDamageByEntityEvent e = (EntityDamageByEntityEvent) p.getEntity().getLastDamageCause();

        //TODO: Come back to <player> was blown up by <player/mob> using <item>

        if (e.getDamager() != null && e.getDamager().getType() == EntityType.FIREWORK) {
            return this.getDeathMessageFormat("fireworks.default");
        }

        if (needsContext()) {
            return this.getDeathMessageFormat("explosion.escape")
                    .replaceAll(this.killerNamePattern, this.deathContext.getKiller().getName());
        }

        return this.getDeathMessageFormat("explosion.default");
    }

    @Override
    public EntityDamageEvent.DamageCause getType() { return EntityDamageEvent.DamageCause.ENTITY_EXPLOSION; }
}
