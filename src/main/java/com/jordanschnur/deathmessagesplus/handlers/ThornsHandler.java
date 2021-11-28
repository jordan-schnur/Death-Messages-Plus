package com.jordanschnur.deathmessagesplus.handlers;

import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

public class ThornsHandler extends AbstractDeathHandler {
    @Override
    public String constructDeathMessage(PlayerDeathEvent p) {
        EntityDamageByEntityEvent e = (EntityDamageByEntityEvent) p.getEntity().getLastDamageCause();

//        if (needsContext()) {
//            return this.getDeathMessageFormat("thorns.default")
//                    .replaceAll(this.killerNamePattern, this.deathContext.getKiller().getName());
//        }


        return this.getDeathMessageFormat("thorns.default").replaceAll(this.killerNamePattern, e.getDamager().getName());
    }

    @Override
    public EntityDamageEvent.DamageCause getType() { return EntityDamageEvent.DamageCause.THORNS; }
}
