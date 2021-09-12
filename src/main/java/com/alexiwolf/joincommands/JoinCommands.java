package com.alexiwolf.joincommands;

import com.alexiwolf.joincommands.commands.ConsoleJoinCommand;
import com.alexiwolf.joincommands.commands.JoinCommand;
import com.alexiwolf.joincommands.commands.PlayerJoinCommand;
import jdk.internal.joptsimple.internal.Strings;
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
        List<JoinCommand> newPlayerCommands = JoinCommandLoader
                .getNewPlayerCommands(config, Bukkit.getServer());
        logCommandList(newPlayerCommands, "new player");
        List<JoinCommand> returningPlayerCommands = JoinCommandLoader
                .getReturningPlayerCommands(config, Bukkit.getServer());
        logCommandList(returningPlayerCommands, "returning player");
        return new JoinCommandRunner(newPlayerCommands, returningPlayerCommands);
    }

    private void logCommandList(List<JoinCommand> commands, String commandGroup) {
        Logger logger = getLogger();
        logger.info("Loaded " + commands.size() + " " + commandGroup + " commands.");
        commands.forEach(command -> logger.info(command.toString()));
    }
}
