package com.alexiwolf.joincommands;

import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.util.UUID;

import static com.alexiwolf.joincommands.JoinCommandRunnerTests.mockPlayer;
import static org.mockito.Mockito.*;

class JoinCommandTests {
    @Test
    void shouldRunPlayerCommandsAsThePlayer() {
        Player player = mockPlayer(UUID.randomUUID());
        JoinCommand command = new JoinCommand("motd", RunAs.PLAYER);

        command.runFor(player);

        verify(player, times(1)).performCommand("motd");
    }

    @Test
    void shouldRunConsoleCommandsAsTheServerConsole() {
        try (MockedStatic<Bukkit> bukkit = mockStatic(Bukkit.class)) {
            Server server = mock(Server.class);
            ConsoleCommandSender consoleCommandSender = mock(ConsoleCommandSender.class);
            when(server.getConsoleSender()).thenReturn(consoleCommandSender);
            bukkit.when(Bukkit::getServer).thenReturn(server);
            Player player = mockPlayer(UUID.randomUUID());
            JoinCommand command = new JoinCommand("motd", RunAs.SERVER_CONSOLE);

            command.runFor(player);

            verify(server, times(1)).dispatchCommand(consoleCommandSender, "motd");
        }
    }
}