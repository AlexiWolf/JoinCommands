package com.alexiwolf.joincommands;

import com.alexiwolf.joincommands.commands.JoinCommand;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;
import java.util.logging.Logger;

public final class JoinCommands extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();
        registerEventListeners();
    }

    private void registerEventListeners() {
        JoinCommandRunner joinCommandRunner = getJoinCommandRunner(getConfig());
        Bukkit.getPluginManager()
                .registerEvents(joinCommandRunner, this);
    }

    private JoinCommandRunner getJoinCommandRunner(FileConfiguration config) {
        boolean usePlaceholders = isPlaceholderApiInstalled();
        List<JoinCommand> newPlayerCommands = JoinCommandLoader
                .getNewPlayerCommands(config, Bukkit.getServer(), usePlaceholders);
        logCommandList(newPlayerCommands, "new player");
        List<JoinCommand> returningPlayerCommands = JoinCommandLoader
                .getReturningPlayerCommands(config, Bukkit.getServer(), usePlaceholders);
        logCommandList(returningPlayerCommands, "returning player");
        return new JoinCommandRunner(newPlayerCommands, returningPlayerCommands);
    }

    private boolean isPlaceholderApiInstalled() {
        Logger logger = getLogger();
        boolean usePlaceholders = Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null;
        if (usePlaceholders) {
            logger.info("Placeholder API found.");
        } else {
            logger.warning("Placeholder API not found. Commands with placeholder texts will not work correctly!");
        }
        return usePlaceholders;
    }

    private void logCommandList(List<JoinCommand> commands, String commandGroup) {
        Logger logger = getLogger();
        logger.info("Loaded " + commands.size() + " " + commandGroup + " commands.");
        commands.forEach(command -> logger.info(command.toString()));
    }
}
