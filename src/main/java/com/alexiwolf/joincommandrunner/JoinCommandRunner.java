package com.alexiwolf.joincommandrunner;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public abstract class JoinCommandRunner implements Listener {

    public void OnPlayerJoin(PlayerJoinEvent playerJoinEvent) { }

    public boolean isNewPlayer(PlayerJoinEvent playerJoinEvent) {
        return false;
    }

    public abstract void runCommandsForNewPlayer(Player player);

    public abstract void runCommandsForReturningPlayer(Player player);
}
