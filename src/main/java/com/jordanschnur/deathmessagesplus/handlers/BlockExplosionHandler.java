package com.jordanschnur.deathmessagesplus.handlers;

import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

public class BlockExplosionHandler extends AbstractDeathHandler {

    @Override
    public String constructDeathMessage(PlayerDeathEvent p) {
        return "Block explosion";
    }

    @Override
    public EntityDamageEvent.DamageCause getType() { return EntityDamageEvent.DamageCause.BLOCK_EXPLOSION; }
}
