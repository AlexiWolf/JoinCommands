package com.alexiwolf.joincommands;

import com.alexiwolf.joincommands.commands.JoinCommand;
import org.bukkit.Server;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.MemoryConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

class JoinCommandLoaderTests {

    @Test
    void shouldLoadNewPlayerCommands() throws IOException, InvalidConfigurationException, URISyntaxException {
        Server server = mock(Server.class);
        File configFile = new File(getClass().getClassLoader().getResource("test_config.yml").toURI());
        YamlConfiguration config = YamlConfiguration.loadConfiguration(configFile);

        List<JoinCommand> commands = JoinCommandLoader.getNewPlayerCommands(config, server);

        assertEquals(1, commands.size());
    }
}