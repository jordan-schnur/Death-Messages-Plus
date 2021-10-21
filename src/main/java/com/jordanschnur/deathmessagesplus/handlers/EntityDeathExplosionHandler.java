package com.jordanschnur.deathmessagesplus.handlers;

import org.bukkit.entity.Creeper;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

public class EntityDeathExplosionHandler extends AbstractDeathHandler {

    public EntityDamageEvent.DamageCause getType() {
        return EntityDamageEvent.DamageCause.ENTITY_EXPLOSION;
    }

    @Override
    public String constructDeathMessage(PlayerDeathEvent p) {
        if (this.lastDamage.getEntity() instanceof Creeper) {
            return this.getDeathMessageFormat("explosion.default").replaceAll(this.playerNamePattern, p.getEntity().getDisplayName());
        }

        if(this.lastDamage.getEntity() instanceof LivingEntity) {
            // Caused by a living entity
        }

        return "Death by another entity";
    }
}
