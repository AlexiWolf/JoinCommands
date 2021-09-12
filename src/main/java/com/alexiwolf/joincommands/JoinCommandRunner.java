package com.alexiwolf.joincommands;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.List;
import java.util.UUID;

public class JoinCommandRunner implements Listener {

    private final List<String> newPlayerCommands;
    private final List<String> returningPlayerCommands;

    public JoinCommandRunner(List<String> newPlayerCommands, List<String> returningPlayerCommands) {
        this.newPlayerCommands = newPlayerCommands;
        this.returningPlayerCommands = returningPlayerCommands;
    }


    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent playerJoinEvent) {
        Player player = playerJoinEvent.getPlayer();
        if (isNewPlayer(playerJoinEvent)) {
            runCommandsForNewPlayer(player);
        } else {
            runCommandsForReturningPlayer(player);
        }
    }

    public boolean isNewPlayer(PlayerJoinEvent playerJoinEvent) {
        UUID playerUuid = playerJoinEvent
                .getPlayer()
                .getUniqueId();
        OfflinePlayer player = Bukkit.getOfflinePlayer(playerUuid);
        return !player.hasPlayedBefore();
    }

    public void runCommandsForNewPlayer(Player player) {
        newPlayerCommands.forEach(player::performCommand);
    }

    public void runCommandsForReturningPlayer(Player player) {
        returningPlayerCommands.forEach(player::performCommand);
    }
}
