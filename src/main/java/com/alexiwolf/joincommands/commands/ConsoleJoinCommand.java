package com.alexiwolf.joincommands.commands;

import org.bukkit.Server;
import org.bukkit.entity.Player;

public class ConsoleJoinCommand extends JoinCommand {

    public ConsoleJoinCommand(String text, Server server) {
        super(text);
    }

    @Override
    public void runFor(Player player) {

    }
}
