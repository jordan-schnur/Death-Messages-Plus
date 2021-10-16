package com.jordanschnur.deathmessagesplus.handlers;

import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

public class ExplosionDeathHandler implements DynamicHandlerInterface {

    public ExplosionDeathHandler() {

    }

    public EntityDamageEvent.DamageCause getType() {
        return EntityDamageEvent.DamageCause.ENTITY_EXPLOSION;
    }
}
