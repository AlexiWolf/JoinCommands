package com.alexiwolf.joincommands;

import com.alexiwolf.joincommands.commands.ConsoleJoinCommand;
import com.alexiwolf.joincommands.commands.JoinCommand;
import com.alexiwolf.joincommands.commands.PlayerJoinCommand;
import org.bukkit.Server;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.ConfigurationSection;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class JoinCommandLoader {

    public static List<JoinCommand> getNewPlayerCommands(
            Configuration config, Server server, boolean usePlaceholders
    ) {
        return getCommandList("new", config, server, usePlaceholders);
    }

    public static List<JoinCommand> getReturningPlayerCommands(
            Configuration config, Server server, boolean usePlaceholders
    ) {
        return getCommandList("returning", config, server, usePlaceholders);
    }

    private static List<JoinCommand> getCommandList(
            String commandType, Configuration config, Server server, boolean usePlaceholders
    ) {
        String commandSection = commandType + "_player_commands";
        ConfigurationSection commandConfigSection = config.getConfigurationSection(commandSection);
        if (commandConfigSection == null) {
            return new ArrayList<>();
        } else {
            return commandConfigSection
                .getKeys(false)
                .stream()
                .map(command -> extractCommand(commandSection, config, server, command, usePlaceholders))
                .collect(Collectors.toList());
        }
    }

    private static JoinCommand extractCommand(
            String commandSection, Configuration config, Server server, String command, boolean usePlaceholders
    ) {
        String command_path = commandSection + "." + command;
        String command_runner = config.getString(command_path + ".run_as");
        if (command_runner.equalsIgnoreCase("console")) {
            return new ConsoleJoinCommand(command, server, usePlaceholders);
        } else {
            return new PlayerJoinCommand(command, usePlaceholders);
        }
    }

}
