package com.jordanschnur.deathmessagesplus.handlers;

import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

public class EntityAttackEntityHandler extends AbstractDeathHandler {

    @Override
    public EntityDamageEvent.DamageCause getType() {
        return EntityDamageEvent.DamageCause.ENTITY_ATTACK;
    }

    @Override
    public String constructDeathMessage(PlayerDeathEvent p) {
        return "Entity Death";
    }
}
