package com.jordanschnur.deathmessagesplus.handlers;

import com.jordanschnur.deathmessagesplus.DeathMessagesPlusMain;
import org.bukkit.Material;
import org.bukkit.entity.FallingBlock;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

public class FallingBlockHandler extends AbstractDeathHandler {
    @Override
    public String constructDeathMessage(PlayerDeathEvent p) {
        EntityDamageByEntityEvent entityDamageByEntityEvent = (EntityDamageByEntityEvent) lastDamage;
        FallingBlock fallingBlock = (FallingBlock) entityDamageByEntityEvent.getDamager();
        String type = "falling-block";

        if (fallingBlock.getBlockData().getMaterial() == org.bukkit.Material.ANVIL) {
            type = "falling-block.anvil";
        } else if (fallingBlock.getBlockData().getMaterial() == Material.POINTED_DRIPSTONE) {
            type = "falling-block.stalactite";
        }

        if (needsContext()) {
            return this.getDeathMessageFormat(type + ".escape")
                    .replaceAll(this.killerNamePattern, this.deathContext.getKiller().getName());
        }

        return this.getDeathMessageFormat(type + ".default");
    }

    @Override
    public EntityDamageEvent.DamageCause getType() { return EntityDamageEvent.DamageCause.FALLING_BLOCK; }
}
