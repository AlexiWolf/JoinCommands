package com.alexiwolf.joincommands;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.alexiwolf.joincommands.JoinCommandRunnerTests.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SimpleJoinCommandRunnerTests {

    List<String> newPlayerCommands;
    List<String> returningPlayerCommands;

    JoinCommandRunner joinCommandRunner;

    @BeforeEach
    void setUp() {
        newPlayerCommands = new ArrayList<>();
        newPlayerCommands.add("welcome");
        newPlayerCommands.add("helpmenu");
        returningPlayerCommands = new ArrayList<>();
        returningPlayerCommands.add("motd");
        joinCommandRunner = new SimpleJoinCommandRunner(newPlayerCommands, returningPlayerCommands);
    }

    @Test
    void shouldRunNewPlayerCommandsForNewPlayers() {
        try (MockedStatic<Bukkit> bukkit = mockStatic(Bukkit.class)) {
            UUID playerUuid = UUID.randomUUID();
            Player player = mockPlayer(playerUuid);
            OfflinePlayer offlinePlayer = mockNewPlayer();
            PlayerJoinEvent playerJoinEvent = new PlayerJoinEvent(player, "Player has joined.");
            bukkit.when(() -> Bukkit.getOfflinePlayer(playerUuid))
                    .thenReturn(offlinePlayer);

            joinCommandRunner.onPlayerJoin(playerJoinEvent);

            verify(player, times(1)).performCommand("welcome");
            verify(player, times(1)).performCommand("helpmenu");
        }
    }

}