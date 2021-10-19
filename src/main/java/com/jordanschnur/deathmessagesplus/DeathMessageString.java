package com.jordanschnur.deathmessagesplus;

public class DeathMessageString {
    private String deathMessage;
    protected final String playerNamePattern = "%player%";
    protected final String killerNamePattern = "%killer%";
    protected final String itemName = "%item%";


    //TODO: This class is meant to reduce the code repetition in handling the string patterns. However it's not possible to override string. I'll revisit this.
    public DeathMessageString(String deathMessage) {
        this.deathMessage = deathMessage;
    }

}
