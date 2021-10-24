package com.jordanschnur.deathmessagesplus.deathcontext;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.sql.Time;
import java.util.UUID;

public class DeathContext {
    private Player player;
    private Entity killer;
    private UUID playerUUID;
    private UUID killerUUID;
    private Time eventTime;

    /**
     * Attempt at keeping memory usage down.
     */
    public DeathContext(UUID playerUUID, UUID killerUUID) {
        this.playerUUID = playerUUID;
        this.killerUUID = killerUUID;

        this.eventTime = new Time(System.currentTimeMillis());
    }

    public DeathContext(Player player, Entity killer) {
        this.player = player;
        this.killer = killer;

        this.eventTime = new Time(System.currentTimeMillis());
    }

    public Player getPlayer() {
        if(player == null) {
            this.player = Bukkit.getPlayer(playerUUID);
        }
        return player;
    }

    public Entity getKiller() {
        if(player == null) {
            this.player = Bukkit.getPlayer(playerUUID);
        }
        return player;
    }

    public long getTimeSinceEvent() {
        return (System.currentTimeMillis() - eventTime.getTime());
    }
}
