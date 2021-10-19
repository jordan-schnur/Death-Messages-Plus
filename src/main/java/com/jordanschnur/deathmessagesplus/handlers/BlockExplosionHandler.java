package com.jordanschnur.deathmessagesplus.handlers;

import org.bukkit.entity.Creeper;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

public class BlockExplosionHandler extends AbstractDeathHandler {

    public EntityDamageEvent.DamageCause getType() {
        return EntityDamageEvent.DamageCause.BLOCK_EXPLOSION;
    }

    @Override
    public String constructDeathMessage(PlayerDeathEvent p) {
        return "Block explosion";
    }
}
