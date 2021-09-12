package com.alexiwolf.joincommands;

public class JoinCommand {
    public final String command;
    public final RunAs runAs;

    public JoinCommand(String command, RunAs runAs) {
        this.command = command;
        this.runAs = runAs;
    }
}
