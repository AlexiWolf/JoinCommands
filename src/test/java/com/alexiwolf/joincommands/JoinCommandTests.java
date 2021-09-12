package com.alexiwolf.joincommands;

import org.bukkit.entity.Player;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static com.alexiwolf.joincommands.JoinCommandRunnerTests.mockPlayer;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class JoinCommandTests {
    @Test
    void shouldRunPlayerCommandsAsThePlayer() {
        Player player = mockPlayer(UUID.randomUUID());
        JoinCommand command = new JoinCommand("motd", RunAs.PLAYER);

        command.runFor(player);

        verify(player, times(1)).performCommand("motd");
    }
}