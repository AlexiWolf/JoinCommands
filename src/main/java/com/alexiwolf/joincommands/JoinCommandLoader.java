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
                .map(command -> extract_command(config, server, command))
                .collect(Collectors.toList());
    }

    public static List<JoinCommand> getReturningPlayerCommands(YamlConfiguration config, Server server) {
        return null;
    }

    private static JoinCommand extract_command(Configuration config, Server server, String command) {
        String command_path = "new_player_commands." + command;
        String command_type = config.getString(command_path + ".run_as");
        if (command_type.equalsIgnoreCase("console")) {
            return new PlayerJoinCommand(command);
        } else {
            return new ConsoleJoinCommand(command, server);
        }
    }

}
