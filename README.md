[![Build Status](https://app.travis-ci.com/AlexiWolf/JoinCommands.svg?branch=main)](https://app.travis-ci.com/AlexiWolf/JoinCommands)

# Join Commands

Join Commands is a simple utility for automatically running commands when a Player joins the server.  It's main use is
to run help commands for new players when they first join, and to run MOTD commands for returning players.

## Goals

- KISS
- Easy to use and configure.

### Features

- [x] Run a set of commands for new players.
- [x] Run a set of commands for returning players.
- [x] Optional PlaceholderAPI integration.
- [ ] A `reload` command to reload configured commands on the fly.

## Non-goals

- Providing custom commands (aside from plugin administration commands.)
- Triggers aside from players joining the server.

## Installation

Just place the jar file in your server's plugin folder.  Nothing more to it.

## Configuration

In the plugin config, the commands are provided in the `new_player_commands` and `returning_player_commands` lists.  
The commands should be provided as a yaml sting list, and they should not include the `/` character.

Here's an example of a config that will:

- Greet new players, and open the help menu for them.
- Run the MOTD for command for returning players.

```yaml
new_player_commands:
  'welcome':
    run_as: player
  'helpmenu':
    run_as: player

returning_player_commands:
  'motd':
    run_as: player
```

It's possible to run commands as either the `player`, or the server `console` by specifying which in the `run_as` 
value.

```yaml
new_player_commands:
  'player command':
    run_as: player
  'console command':
    run_as: console
```

When running the command as the server using the `console` option, this will bypass permission checks.  This allows 
you to run **any** command on the server, even if the player does not normally have permission to run it. 

## Placeholder API Support

If you have the [Placeholder API](https://placeholderapi.com) installed, you can use placeholder
values in the commands.

```yaml
returning_player_commands:
  'say Welcome back, %player_name%!':
    run_as: console
```

See the [Placeholder API Wiki](https://github.com/PlaceholderAPI/PlaceholderAPI/wiki) for more information.

## License

Join Commands is released under the [MIT License](LICENSE).
