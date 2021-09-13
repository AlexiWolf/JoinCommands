package com.alexiwolf.joincommands.commands;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.entity.Player;

public abstract class JoinCommand {
    public final String text;
    public final boolean usePlaceholders;

    protected JoinCommand(String text, boolean usePlaceholders) {
        this.text = text;
        this.usePlaceholders = usePlaceholders;
    }

    public abstract void runFor(Player player);

    protected String getTextWithFilledPlaceholders(Player player) {
        if (usePlaceholders) {
            return PlaceholderAPI.setPlaceholders(player, text);
        } else {
            return text;
        }
    }
}
