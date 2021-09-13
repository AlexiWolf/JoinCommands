package com.alexiwolf.joincommands.commands;

import org.bukkit.Server;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class ConsoleJoinCommand extends JoinCommand {

    private final Server server;

    public ConsoleJoinCommand(String text, Server server, boolean usePlaceholders) {
        super(text, usePlaceholders);
        this.server = server;
    }

    @Override
    public void runFor(Player player) {
        ConsoleCommandSender console = server.getConsoleSender();
        server.dispatchCommand(console, getTextWithFilledPlaceholders(player));
    }

    @Override
    public String toString() {
        return "Running '" + text + "' as the server console " + getPlaceholderStatusMessage() + ".";
    }
}
