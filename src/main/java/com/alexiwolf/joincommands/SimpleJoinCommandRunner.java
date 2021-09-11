package com.alexiwolf.joincommands;

import org.bukkit.entity.Player;

import java.util.List;

public class SimpleJoinCommandRunner extends JoinCommandRunner {
    private final List<String> newPlayerCommands;
    private final List<String> returningPlayerCommands;

    public SimpleJoinCommandRunner(List<String> newPlayerCommands, List<String> returningPlayerCommands) {
        this.newPlayerCommands = newPlayerCommands;
        this.returningPlayerCommands = returningPlayerCommands;
    }

    @Override
    public void runCommandsForNewPlayer(Player player) {

    }

    @Override
    public void runCommandsForReturningPlayer(Player player) {

    }
}
