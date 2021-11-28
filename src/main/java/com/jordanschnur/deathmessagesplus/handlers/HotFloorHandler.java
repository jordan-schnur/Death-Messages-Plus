package com.jordanschnur.deathmessagesplus.handlers;

import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

public class HotFloorHandler extends AbstractDeathHandler {
    @Override
    public String constructDeathMessage(PlayerDeathEvent p) {

        if (needsContext()) {
            return this.getDeathMessageFormat("magma-block.escape")
                    .replaceAll(this.killerNamePattern, this.deathContext.getKiller().getName());
        }

        return this.getDeathMessageFormat("magma-block.default");
    }

    @Override
    public EntityDamageEvent.DamageCause getType() { return EntityDamageEvent.DamageCause.HOT_FLOOR; }
}
