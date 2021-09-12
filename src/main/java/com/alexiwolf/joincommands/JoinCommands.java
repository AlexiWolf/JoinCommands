package com.alexiwolf.joincommands;

import com.alexiwolf.joincommands.commands.JoinCommand;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

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
        List<JoinCommand> returningPlayerCommands = JoinCommandLoader
                .getReturningPlayerCommands(config, Bukkit.getServer());
        return new JoinCommandRunner(newPlayerCommands, returningPlayerCommands);
    }
}
