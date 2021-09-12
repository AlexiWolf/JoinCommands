package com.alexiwolf.joincommands;

import com.alexiwolf.joincommands.commands.ConsoleJoinCommand;
import com.alexiwolf.joincommands.commands.JoinCommand;
import com.alexiwolf.joincommands.commands.PlayerJoinCommand;
import org.bukkit.Server;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class JoinCommandLoader {
    public static List<JoinCommand> getNewPlayerCommands(Configuration config, Server server) {
        return Objects.requireNonNull(
                config.getConfigurationSection("new_player_commands"),
                "The 'new_player_commands' section could not be found."
        )
                .getKeys(false)
                .stream()
                .map(command -> extract_command("new", config, server, command))
                .collect(Collectors.toList());
    }

    public static List<JoinCommand> getReturningPlayerCommands(YamlConfiguration config, Server server) {
        return Objects.requireNonNull(
                config.getConfigurationSection("returning_player_commands"),
                "The 'returning_player_commands' section could not be found."
        )
                .getKeys(false)
                .stream()
                .map(command -> extract_command("returning", config, server, command))
                .collect(Collectors.toList());
    }

    private static JoinCommand extract_command(String command_type, Configuration config, Server server, String command) {
        String command_path = command_type + "_player_commands." + command;
        String command_runner = config.getString(command_path + ".run_as");
        if (command_runner.equalsIgnoreCase("console")) {
            return new PlayerJoinCommand(command);
        } else {
            return new ConsoleJoinCommand(command, server);
        }
    }

}
