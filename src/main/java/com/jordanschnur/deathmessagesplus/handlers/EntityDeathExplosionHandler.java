package com.jordanschnur.deathmessagesplus.handlers;

import org.bukkit.entity.Creeper;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

public class EntityDeathExplosionHandler extends AbstractDeathHandler {

    public EntityDamageEvent.DamageCause getType() {
        return EntityDamageEvent.DamageCause.ENTITY_EXPLOSION;
    }

    @Override
    public String constructDeathMessage(PlayerDeathEvent p) {
        if (this.lastDamage.getEntity() instanceof Creeper) {
            return this.getDeathMessageFormat("explosions.blew-up").replaceAll(this.playerNamePattern, p.getEntity().getDisplayName());
        }
        return "Death by another entity";
    }
}
