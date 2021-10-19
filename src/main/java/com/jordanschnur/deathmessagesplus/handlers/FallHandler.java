package com.jordanschnur.deathmessagesplus.handlers;

import org.bukkit.entity.Creeper;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

public class FallHandler extends AbstractDeathHandler {

    public EntityDamageEvent.DamageCause getType() {
        return EntityDamageEvent.DamageCause.FALL;
    }

    @Override
    public String constructDeathMessage(PlayerDeathEvent p) {
        return "Fell";
    }
}
