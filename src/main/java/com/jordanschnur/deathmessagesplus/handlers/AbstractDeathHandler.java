package com.jordanschnur.deathmessagesplus.handlers;

import com.jordanschnur.deathmessagesplus.DeathMessagesPlusMain;
import com.jordanschnur.deathmessagesplus.deathcontext.DeathContext;
import com.jordanschnur.deathmessagesplus.logging.LoggingContext;
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
    protected DeathContext deathContext;
    protected final String playerNamePattern = "%player%";
    protected final String worldNamePattern = "%world%";
    protected final String killerNamePattern = "%killer%";
    protected final String itemName = "%item%";

    public abstract String constructDeathMessage(PlayerDeathEvent p);

    public AbstractDeathHandler() {}

    public String getDeathMessage(PlayerDeathEvent p, FileConfiguration config, DeathContext deathContext) {
        this.playerDeathEvent = p;
        this.deadPlayer = p.getEntity();
        this.lastDamage = p.getEntity().getLastDamageCause();
        this.configuration = config;
        this.deathContext = deathContext;

        String deathMessage = this.constructDeathMessage(p);
        deathMessage.replaceAll(worldNamePattern, p.getEntity().getWorld().getName());
        deathMessage.replaceAll(playerNamePattern, p.getEntity().getName());

        return deathMessage;
    }

    public String getDeathMessageFormat(String key) {
        // Get random or single values
        List<String> keyList = configuration.getStringList(key);

        // TODO: Check the case when the key does not exist.

        return keyList.size() == 0 ? configuration.getString(key) : keyList.get(getRandomIntRange(0, keyList.size() - 1));
    }

    public abstract EntityDamageEvent.DamageCause getType();

    private int getRandomIntRange(int min, int max) {
        // Inclusive min/max.
        return (new Random()).nextInt(max + 1 - min) + min;
    }
}
