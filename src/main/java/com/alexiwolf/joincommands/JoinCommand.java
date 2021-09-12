package com.alexiwolf.joincommands;

public class JoinCommand {
    public final String text;
    public final RunAs runAs;

    public JoinCommand(String text, RunAs runAs) {
        this.text = text;
        this.runAs = runAs;
    }
}
