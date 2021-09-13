package com.alexiwolf.joincommands.commands;

import org.bukkit.entity.Player;

public class PlayerJoinCommand extends JoinCommand {

    public PlayerJoinCommand(String text, boolean usePlaceholders) {
        super(text, usePlaceholders);
    }

    @Override
    public void runFor(Player player) {
        player.performCommand(getTextWithFilledPlaceholders(player));
    }

    @Override
    public String toString() {
        return "Running '" + text + "' as the player " + getPlaceholderStatusMessage() + ".";
    }
}
