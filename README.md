[![Build Status](https://app.travis-ci.com/AlexiWolf/JoinCommands.svg?branch=main)](https://app.travis-ci.com/AlexiWolf/JoinCommands)

# Join Commands

Join Commands is a simple utility for automatically running commands when a Player joins the server.  It's main use is
to run help commands for new players when they first join, and to run MOTD commands for returning players.

## Goals

- KISS
- Run a set of commands for new players.
- Run a set of commands for returning players.
- Easy to use and configure.
- Optional PlaceholderAPI integration.

## Non-goals

- Providing commands.
- Triggers aside from players joining the server.

## Installation

Just place the jar file in your server's plugin folder.  Nothing more to it.

## Configuration

In the plugin config, the commands are provided in the `new_player_commands` and `returning_player_commands` lists.  
The commands should be provided as a yaml sting list, and they should not include the `/` character.

Here's an example of a config that will:

- Greet new players, and open the help menu for them.
- Show the MOTD for returning players.

```yaml
new_player_commands:
  - welcome
  - helpmenu

returning_player_commands:
  - motd
```

## License

Join Commands is released under the [MIT License](LICENSE).
