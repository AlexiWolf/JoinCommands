package com.alexiwolf.joincommands;

import com.alexiwolf.joincommands.commands.ConsoleJoinCommand;
import com.alexiwolf.joincommands.commands.JoinCommand;
import com.alexiwolf.joincommands.commands.PlayerJoinCommand;
import org.bukkit.Server;
import org.bukkit.configuration.file.YamlConfiguration;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

class JoinCommandLoaderTests {

    @Test
    void shouldLoadNewPlayerCommands() throws URISyntaxException {
        Server server = mock(Server.class);
        YamlConfiguration config = openTestConfigFile("test_config.yml");

        List<JoinCommand> commands = JoinCommandLoader.getNewPlayerCommands(config, server);

        assertEquals(2, commands.size());
        assertTrue(commands.get(0) instanceof PlayerJoinCommand);
        assertTrue(commands.get(1) instanceof ConsoleJoinCommand);
    }

    @Test
    void shouldLoadReturningPlayerCommands() throws URISyntaxException {
        Server server = mock(Server.class);
        YamlConfiguration config = openTestConfigFile("test_config.yml");

        List<JoinCommand> commands = JoinCommandLoader.getReturningPlayerCommands(config, server);

        assertEquals(2, commands.size());
        assertTrue(commands.get(0) instanceof PlayerJoinCommand);
        assertTrue(commands.get(1) instanceof ConsoleJoinCommand);
    }

    private YamlConfiguration openTestConfigFile(String file_name) throws URISyntaxException {
        File configFile = new File(
                Objects.requireNonNull(
                        getClass().getClassLoader().getResource(file_name),
                        "The file '" + file_name + "' could not be found in the test resources."
                ).toURI()
        );
        return YamlConfiguration.loadConfiguration(configFile);
    }
}