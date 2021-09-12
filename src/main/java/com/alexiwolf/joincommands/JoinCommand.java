package com.alexiwolf.joincommands;

public class JoinCommand {
    private String command;
    private RunAs runAs;

    public JoinCommand(String command, RunAs runAs) {
        this.command = command;
        this.runAs = runAs;
    }
}
