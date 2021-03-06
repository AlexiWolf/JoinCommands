package com.alexiwolf.joincommands;

import com.alexiwolf.joincommands.commands.JoinCommand;
import com.alexiwolf.joincommands.commands.PlayerJoinCommand;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class JoinCommandRunnerTests {

    List<JoinCommand> newPlayerCommands;
    List<JoinCommand> returningPlayerCommands;

    JoinCommandRunner joinCommandRunner;

    @BeforeEach
    void setUp() {
        newPlayerCommands = new ArrayList<>();
        newPlayerCommands.add(new PlayerJoinCommand("welcome", false));
        newPlayerCommands.add(new PlayerJoinCommand("helpmenu", false));
        returningPlayerCommands = new ArrayList<>();
        returningPlayerCommands.add(new PlayerJoinCommand("motd", false));
        joinCommandRunner = new JoinCommandRunner(newPlayerCommands, returningPlayerCommands);
    }

    @Test
    void shouldBeANewPlayer() {
        try (MockedStatic<Bukkit> bukkit = mockStatic(Bukkit.class)) {
            UUID playerUuid = UUID.randomUUID();
            Player player = mockPlayer(playerUuid);
            OfflinePlayer offlinePlayer = mockNewPlayer();
            PlayerJoinEvent playerJoinEvent = new PlayerJoinEvent(player, "Player has joined.");
            bukkit.when(() -> Bukkit.getOfflinePlayer(playerUuid))
                    .thenReturn(offlinePlayer);

            assertTrue(
                    joinCommandRunner.isNewPlayer(playerJoinEvent),
                    "The player is returning, but they should be a new player."
            );
        }
    }

    @Test
    void shouldBeAReturningPlayer() {
        try (MockedStatic<Bukkit> bukkit = mockStatic(Bukkit.class)) {
            UUID playerUuid = UUID.randomUUID();
            Player player = mockPlayer(playerUuid);
            OfflinePlayer offlinePlayer = mockReturningPlayer();
            PlayerJoinEvent playerJoinEvent = new PlayerJoinEvent(player, "Player has joined.");
            bukkit.when(() -> Bukkit.getOfflinePlayer(playerUuid))
                    .thenReturn(offlinePlayer);

            assertFalse(
                    joinCommandRunner.isNewPlayer(playerJoinEvent),
                    "The player is new, but they should be a returning player."
            );
        }
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

    @Test
    void shouldRunReturningPlayerCommandsForReturningPlayers() {
        try (MockedStatic<Bukkit> bukkit = mockStatic(Bukkit.class)) {
            UUID playerUuid = UUID.randomUUID();
            Player player = mockPlayer(playerUuid);
            OfflinePlayer offlinePlayer = mockReturningPlayer();
            PlayerJoinEvent playerJoinEvent = new PlayerJoinEvent(player, "Player has joined.");
            bukkit.when(() -> Bukkit.getOfflinePlayer(playerUuid))
                    .thenReturn(offlinePlayer);

            joinCommandRunner.onPlayerJoin(playerJoinEvent);

            verify(player, times(1)).performCommand("motd");
        }
    }

    public static OfflinePlayer mockNewPlayer() {
        OfflinePlayer offlinePlayer = mock(OfflinePlayer.class);
        when(offlinePlayer.hasPlayedBefore()).thenReturn(false);
        return offlinePlayer;
    }

    public static OfflinePlayer mockReturningPlayer() {
        OfflinePlayer offlinePlayer = mock(OfflinePlayer.class);
        when(offlinePlayer.hasPlayedBefore()).thenReturn(true);
        return offlinePlayer;
    }

    public static Player mockPlayer(UUID playerUuid) {
        Player player = mock(Player.class);
        when(player.getUniqueId()).thenReturn(playerUuid);
        return player;
    }
}