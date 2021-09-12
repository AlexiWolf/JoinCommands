package com.alexiwolf.joincommands;

import com.alexiwolf.joincommands.commands.ConsoleJoinCommand;
import com.alexiwolf.joincommands.commands.JoinCommand;
import com.alexiwolf.joincommands.commands.PlayerJoinCommand;
import org.bukkit.Server;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class JoinCommandLoader {

    public static List<JoinCommand> getNewPlayerCommands(Configuration config, Server server) {
        return getCommandList("new", config, server);
    }

    public static List<JoinCommand> getReturningPlayerCommands(YamlConfiguration config, Server server) {
        return getCommandList("returning", config, server);
    }

    private static List<JoinCommand> getCommandList(String commandType, Configuration config, Server server) {
        String commandSection = commandType + "_player_commands";
        ConfigurationSection commandConfigSection = config.getConfigurationSection(commandSection);
        if (commandConfigSection == null) {
            return new ArrayList<>();
        } else {
            return commandConfigSection
                .getKeys(false)
                .stream()
                .map(command -> extractCommand(commandSection, config, server, command))
                .collect(Collectors.toList());
        }
    }

    private static JoinCommand extractCommand(String commandSection, Configuration config, Server server, String command) {
        String command_path = commandSection + "." + command;
        String command_runner = config.getString(command_path + ".run_as");
        if (command_runner.equalsIgnoreCase("console")) {
            return new ConsoleJoinCommand(command, server);
        } else {
            return new PlayerJoinCommand(command);
        }
    }

}
