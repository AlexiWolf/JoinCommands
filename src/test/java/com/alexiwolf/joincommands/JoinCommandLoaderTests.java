package com.alexiwolf.joincommands;

import com.alexiwolf.joincommands.commands.JoinCommand;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JoinCommandLoaderTests {

    @Test
    void shouldLoadNewPlayerCommands() throws IOException, InvalidConfigurationException {
        FileConfiguration config = open_test_config("test_config.yml");

        List<JoinCommand> commands = JoinCommandLoader.getNewPlayerCommands(config);

        assertEquals(1, commands.size());
    }

    private FileConfiguration open_test_config(String file_name) throws IOException, InvalidConfigurationException {
        FileConfiguration config = new YamlConfiguration();
        config.loadFromString(read_file_stream(file_name));
        return config;
    }

    private String read_file_stream(String file_name) throws IOException {
        InputStream inputStream = Objects.requireNonNull(
                getClass().getClassLoader().getResourceAsStream(file_name)
        );
        return read_contents(inputStream);
    }

    private String read_contents(InputStream inputStream) throws IOException {
        try (
                InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
                BufferedReader reader = new BufferedReader(streamReader)
        ) {
            StringBuilder config_string_builder = new StringBuilder();
            while (true) {
                String line = reader.readLine();
                if (line != null) {
                    config_string_builder.append(line);
                } else {
                    break;
                }
            }
            return config_string_builder.toString();
        }
    }
}