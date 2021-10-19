package com.jordanschnur.deathmessagesplus.handlers;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.List;
import java.util.Random;

public abstract class AbstractDeathHandler implements DynamicHandlerInterface {
    private PlayerDeathEvent playerDeathEvent;
    protected Player deadPlayer;
    protected EntityDamageEvent lastDamage;
    private FileConfiguration configuration;
    protected final String playerNamePattern = "%player%";
    protected final String killerNamePattern = "%killer%";
    protected final String itemName = "%item%";

    public abstract String constructDeathMessage(PlayerDeathEvent p);

    public AbstractDeathHandler() {}

    public String getDeathMessage(PlayerDeathEvent p, FileConfiguration config) {
        this.playerDeathEvent = p;
        this.deadPlayer = p.getEntity();
        this.lastDamage = p.getEntity().getLastDamageCause();
        this.configuration = config;

        String deathMessage = this.constructDeathMessage(p);

        return deathMessage;
    }

    public String getDeathMessageFormat(String key) {
        // Get random or single values
        List<String> keyList = configuration.getStringList(key);
        return keyList.size() == 0 ? configuration.getString(key) : keyList.get(getRandomIntRange(0, keyList.size() - 1));
    }

    public abstract EntityDamageEvent.DamageCause getType();

    private int getRandomIntRange(int min, int max) {
        // Inclusive min/max.
        return (new Random()).nextInt(max + 1 - min) + min;
    }
}
