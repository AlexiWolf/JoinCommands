package com.alexiwolf.joincommands;

import org.bukkit.entity.Player;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

class JoinCommandRunnerTests {

    JoinCommandRunner joinCommandRunner;

    @BeforeEach
    public void setup() {
         joinCommandRunner = new TestJoinCommandRunner();
    }

    // No-op Implementation
    private static class TestJoinCommandRunner extends JoinCommandRunner {
        @Override
        public void runCommandsForNewPlayer(Player player) { }

        @Override
        public void runCommandsForReturningPlayer(Player player) { }
    }
}