package com.jordanschnur.deathmessagesplus.handlers;

import org.bukkit.event.entity.EntityDamageEvent;

public class EntityAttackEntityHandler implements DynamicHandlerInterface {
    @Override
    public EntityDamageEvent.DamageCause getType() {
        return EntityDamageEvent.DamageCause.CONTACT;
    }
}
