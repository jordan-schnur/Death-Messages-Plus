package com.jordanschnur.deathmessagesplus.handlers;

import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

public class LavaHandler extends AbstractDeathHandler {
    @Override
    public String constructDeathMessage(PlayerDeathEvent p) {
        if (this.lastDamage instanceof EntityDamageByEntityEvent) {
            return this.getDeathMessageFormat("lava.escape")
                    .replaceAll(this.playerNamePattern, p.getEntity().getDisplayName())
                    .replaceAll(this.killerNamePattern, ((EntityDamageByEntityEvent) this.lastDamage).getDamager().getName());
        } else {
            return this.getDeathMessageFormat("lava.default").replaceAll(this.playerNamePattern, p.getEntity().getDisplayName());
        }
    }

    @Override
    public EntityDamageEvent.DamageCause getType() {
        return EntityDamageEvent.DamageCause.LAVA;
    }
}
