package com.jordanschnur.deathmessagesplus;

import org.bukkit.GameMode;
import org.bukkit.attribute.Attribute;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

public class RespawnListener implements Listener {
    private boolean isDebugMode;

    public RespawnListener(DeathMessagesPlusMain deathMessagesPlusMain) {
        deathMessagesPlusMain.getLogger().info("IS DEBUG MODE: "+deathMessagesPlusMain.isDebugMode());
        this.isDebugMode = deathMessagesPlusMain.isDebugMode();
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent p) {
        if (isDebugMode) {
            p.getPlayer().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(5);
            p.getPlayer().setGameMode(GameMode.CREATIVE);
        }
    }
}
