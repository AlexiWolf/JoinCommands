package com.alexiwolf.joincommands;

import org.bukkit.entity.Player;

public class JoinCommand {
    public final String text;
    public final RunAs runAs;

    public JoinCommand(String text, RunAs runAs) {
        this.text = text;
        this.runAs = runAs;
    }

    public void runFor(Player player) { }
}
