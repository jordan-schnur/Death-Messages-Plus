package com.jordanschnur.deathmessagesplus.handlers;


import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

public interface DynamicHandlerInterface {
    public DamageCause getType();
}
