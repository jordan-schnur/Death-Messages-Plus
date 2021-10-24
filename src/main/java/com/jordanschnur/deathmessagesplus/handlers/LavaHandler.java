package com.jordanschnur.deathmessagesplus.handlers;

import com.jordanschnur.deathmessagesplus.DeathMessagesPlusMain;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

public class LavaHandler extends AbstractDeathHandler {
    @Override
    public String constructDeathMessage(PlayerDeathEvent p) {

        if (this.deathContext != null && this.deathContext.getTimeSinceEvent() < 1500 && this.deathContext.getKiller() != null) {
            DeathMessagesPlusMain.getPluginLogger().info("Def killed dude");
        }

        if (this.deathContext != null) {
            DeathMessagesPlusMain.getPluginLogger().info(String.valueOf(this.deathContext.getTimeSinceEvent()));
            DeathMessagesPlusMain.getPluginLogger().info(this.deathContext.getKiller().getType().toString());
        } else {
            DeathMessagesPlusMain.getPluginLogger().info("For some fucking reason, killer is null");
        }
        if (this.lastDamage instanceof EntityDamageByEntityEvent) {
            return this.getDeathMessageFormat("lava.escape")
                    .replaceAll(this.playerNamePattern, p.getEntity().getDisplayName())
                    .replaceAll(this.killerNamePattern, ((EntityDamageByEntityEvent) this.lastDamage).getDamager().getName());
        } else {
            return this.getDeathMessageFormat("lava.default").replaceAll(this.playerNamePattern, p.getEntity().getDisplayName());
        }
    }

    @Override
    public EntityDamageEvent.DamageCause getType() { return EntityDamageEvent.DamageCause.LAVA; }
}
