package com.alexiwolf.joincommands.commands;

import org.bukkit.entity.Player;

public abstract class JoinCommand {
    public final String text;

    protected JoinCommand(String text) {
        this.text = text;
    }

    public abstract void runFor(Player player);
}
