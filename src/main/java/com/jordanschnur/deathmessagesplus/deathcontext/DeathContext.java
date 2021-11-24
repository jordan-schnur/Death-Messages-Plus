package com.jordanschnur.deathmessagesplus.deathcontext;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;

import java.sql.Time;
import java.util.UUID;

public class DeathContext {
    private Player player;
    private Entity killer;
    private Entity projectile;
    private Time eventTime;

    public DeathContext(Player player, Entity killer, Projectile projectile) {
        this.player = player;
        this.killer = killer;
        this.projectile = projectile;

        this.eventTime = new Time(System.currentTimeMillis());
    }

    public DeathContext(Player player, Entity killer) {
        this.player = player;
        this.killer = killer;

        this.eventTime = new Time(System.currentTimeMillis());
    }

    public Player getPlayer() {
        return player;
    }

    public Entity getKiller() {
        return this.killer;
    }

    public long getTimeSinceEvent() {
        return (System.currentTimeMillis() - eventTime.getTime());
    }
}
