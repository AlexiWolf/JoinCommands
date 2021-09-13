package com.alexiwolf.joincommands.commands;

import org.bukkit.entity.Player;

public class PlayerJoinCommand extends JoinCommand {

    public PlayerJoinCommand(String text) {
        super(text);
    }

    @Override
    public void runFor(Player player) {
        player.performCommand(text);
    }

    @Override
    public String toString() {
        return "Running '" + text + "' as the player.";
    }
}
