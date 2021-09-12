package com.alexiwolf.joincommands;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public final class JoinCommands extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();
        FileConfiguration config = getConfig();

        List<String> newPlayerCommands = config.getStringList("new_player_commands");
        List<String> returningPlayerCommands = config.getStringList("returning_player_commands");
        JoinCommandRunner joinCommandRunner = new JoinCommandRunner(newPlayerCommands, returningPlayerCommands);

        Bukkit.getPluginManager().registerEvents(joinCommandRunner, this);
    }
}
