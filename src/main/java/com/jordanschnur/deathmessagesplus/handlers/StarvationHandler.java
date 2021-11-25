package com.jordanschnur.deathmessagesplus.handlers;

import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

public class StarvationHandler extends AbstractDeathHandler {
    @Override
    public String constructDeathMessage(PlayerDeathEvent p) {

        if (needsContext()) {
            return this.getDeathMessageFormat("starving.escape")
                    .replaceAll(this.killerNamePattern, this.deathContext.getKiller().getName());
        }


        return this.getDeathMessageFormat("starving.default");
    }

    @Override
    public EntityDamageEvent.DamageCause getType() { return EntityDamageEvent.DamageCause.STARVATION; }
}
