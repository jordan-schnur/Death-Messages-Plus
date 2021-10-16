package com.jordanschnur.deathmessagesplus.handlers;

import org.bukkit.entity.Entity;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

public abstract class AbstractHandler {
    private PlayerDeathEvent playerDeathEvent;
    protected final EntityDamageEvent lastDamage;
    protected final Entity lastDamageEntity;

    public AbstractHandler(PlayerDeathEvent[] p) {
        this.playerDeathEvent = p[0]; //Hack to pass by reference instead of value
        this.lastDamage = this.playerDeathEvent.getEntity().getLastDamageCause();
        this.lastDamageEntity = this.lastDamage.getEntity();
    }

    public PlayerDeathEvent getPlayerDeathEvent() {
        return this.playerDeathEvent;
    }

    public abstract String getDeathMessage();

    public void setDeathMessage(String message) {
        playerDeathEvent.setDeathMessage(message);
    }

    public EntityDamageEvent getLastDamage() {
        return lastDamage;
    }
}
