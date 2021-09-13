package com.alexiwolf.joincommands;

import com.alexiwolf.joincommands.commands.ConsoleJoinCommand;
import com.alexiwolf.joincommands.commands.JoinCommand;
import com.alexiwolf.joincommands.commands.PlayerJoinCommand;
import org.bukkit.Server;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static com.alexiwolf.joincommands.JoinCommandRunnerTests.mockPlayer;
import static org.mockito.Mockito.*;

class JoinCommandTests {
    @Test
    void shouldRunPlayerCommandsAsThePlayer() {
        Player player = mockPlayer(UUID.randomUUID());
        JoinCommand command = new PlayerJoinCommand("motd", false);

        command.runFor(player);

        verify(player, times(1)).performCommand("motd");
    }

    @Test
    void shouldRunConsoleCommandsAsTheServerConsole() {
        Server server = mock(Server.class);
        ConsoleCommandSender consoleCommandSender = mock(ConsoleCommandSender.class);
        when(server.getConsoleSender()).thenReturn(consoleCommandSender);
        Player player = mockPlayer(UUID.randomUUID());
        JoinCommand command = new ConsoleJoinCommand("motd", server, false);

        command.runFor(player);

        verify(server, times(1)).dispatchCommand(consoleCommandSender, "motd");
    }
}