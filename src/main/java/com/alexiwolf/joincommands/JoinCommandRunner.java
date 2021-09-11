package com.alexiwolf.joincommands;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.UUID;

public abstract class JoinCommandRunner implements Listener {

    public void OnPlayerJoin(PlayerJoinEvent playerJoinEvent) { }

    public boolean isNewPlayer(PlayerJoinEvent playerJoinEvent) {
        UUID playerUuid = playerJoinEvent
                .getPlayer()
                .getUniqueId();
        OfflinePlayer player = Bukkit.getOfflinePlayer(playerUuid);
        return !player.hasPlayedBefore();
    }

    public abstract void runCommandsForNewPlayer(Player player);

    public abstract void runCommandsForReturningPlayer(Player player);
}
