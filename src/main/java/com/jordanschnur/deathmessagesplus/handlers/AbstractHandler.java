package com.jordanschnur.deathmessagesplus.handlers;

import org.bukkit.event.entity.PlayerDeathEvent;

public class AbstractHandler {
    private PlayerDeathEvent playerDeathEvent;

    public AbstractHandler(PlayerDeathEvent p) {
        this.playerDeathEvent = pl;
    }

    public PlayerDeathEvent getPlayerDeathEvent() {
        return this.playerDeathEvent;
    }

    public void setDeathMessage(String message) {
        playerDeathEvent.setDeathMessage(message);
    }
}
