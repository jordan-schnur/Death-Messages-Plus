package com.jordanschnur.deathmessagesplus.handlers;

import com.jordanschnur.deathmessagesplus.DeathMessagesPlusMain;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

public class FallHandler extends AbstractDeathHandler {

    public EntityDamageEvent.DamageCause getType() {
        return EntityDamageEvent.DamageCause.FALL;
    }

    @Override
    public String constructDeathMessage(PlayerDeathEvent p) {
        if(this.lastDamage instanceof EntityDamageByEntityEvent || this.deadPlayer.getFallDistance() < 5) {
            return this.getDeathMessageFormat("falling.default.small").replaceAll(this.playerNamePattern, this.deadPlayer.getDisplayName());
        } else {
            return this.getDeathMessageFormat("falling.default.large").replaceAll(this.playerNamePattern, this.deadPlayer.getDisplayName());
        }
    }
}
