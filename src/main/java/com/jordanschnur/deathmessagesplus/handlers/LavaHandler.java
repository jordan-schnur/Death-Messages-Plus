package com.jordanschnur.deathmessagesplus.handlers;

import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

public class LavaHandler extends AbstractDeathHandler {
    @Override
    public String constructDeathMessage(PlayerDeathEvent p) {

        if (needsContext()) {
            return this.getDeathMessageFormat("lava.escape")
                    .replaceAll(this.killerNamePattern, this.deathContext.getKiller().getName());
        }


        return this.getDeathMessageFormat("lava.default");
    }

    @Override
    public EntityDamageEvent.DamageCause getType() { return EntityDamageEvent.DamageCause.LAVA; }
}
