package com.alexiwolf.joincommands.commands;

import me.clip.placeholderapi.PlaceholderAPI;

import java.util.Optional;

import org.bukkit.entity.Player;

public abstract class JoinCommand {
    public final String text;
    public final boolean usePlaceholders;
    public final Optional<String> permission;

    protected JoinCommand(
            String text, 
            boolean usePlaceholders,
            Optional<String> permission
        ) {
        this.text = text;
        this.usePlaceholders = usePlaceholders;
        this.permission = permission;
    }

    public abstract void runFor(Player player);

    protected String getTextWithFilledPlaceholders(Player player) {
        if (usePlaceholders) {
            return PlaceholderAPI.setPlaceholders(player, text);
        } else {
            return text;
        }
    }

    protected String getPlaceholderStatusMessage() {
        if (usePlaceholders) {
            return "with placeholder support";
        } else {
            return "without placeholder support";
        }
    }
}
