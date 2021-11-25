package com.jordanschnur.deathmessagesplus.handlers;

import org.bukkit.Material;
import org.bukkit.event.entity.EntityDamageByBlockEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

public class ContactHandler extends AbstractDeathHandler {
    @Override
    public String constructDeathMessage(PlayerDeathEvent p) {
        EntityDamageByBlockEvent lastBlockDamage = (EntityDamageByBlockEvent) lastDamage;

        String type = "";

        if (lastBlockDamage.getDamager().getType() == Material.SWEET_BERRY_BUSH) {
            type = "sweet-berry-bush";
        } else if(lastBlockDamage.getDamager().getType() == Material.CACTUS) {
            type = "cactus";
        } else {
            return null;
        }

        if (needsContext()) {
            return this.getDeathMessageFormat(type + ".escape")
                    .replaceAll(this.killerNamePattern, this.deathContext.getKiller().getName());
        }

        return this.getDeathMessageFormat(type + ".default");
    }

    @Override
    public EntityDamageEvent.DamageCause getType() { return EntityDamageEvent.DamageCause.CONTACT; }
}
