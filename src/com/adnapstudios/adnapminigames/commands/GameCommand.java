package com.adnapstudios.adnapminigames.commands;

import com.adnapstudios.adnapminigames.AdnapMiniGames;
import com.adnapstudios.adnapminigames.models.game.Arena;
import com.adnapstudios.adnapminigames.models.game.Game;
import com.adnapstudios.adnapminigames.models.game.GameStatus;
import com.adnapstudios.adnapminigames.models.game.GameType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.sql.SQLException;
import java.util.ArrayList;

public class GameCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        String commandParameter = strings[0];

        if (commandParameter.equalsIgnoreCase("list")) {
            try {
                ArrayList<Game> games = AdnapMiniGames.databaseManager.getAllGames();
                commandSender.sendMessage("All saved games:");

                if (games != null) {
                    games.forEach(game -> commandSender.sendMessage(game.getId() + ": " + game.getArena().getName()));
                    return true;

                } else {
                    commandSender.sendMessage("No saved games...");
                    return false;
                }

            } catch (SQLException e) {
                commandSender.sendMessage(e.getMessage());
                return false;
            }
        }

        // /game create ARENA GAMETYPE MAXTEAMS TEAMSIZE
        if (commandParameter.equalsIgnoreCase("create")) {
            try {
                Arena arena = AdnapMiniGames.databaseManager.getArenaByName(strings[1]);
                GameType gameType = GameType.valueOf(strings[2].toUpperCase());
                int maxTeams = Integer.parseInt(strings[3]);
                int teamSize = Integer.parseInt(strings[4]);

                Game game = new Game();
                game.setArena(arena);
                game.setGameType(gameType);
                game.setMaxTeams(maxTeams);
                game.setTeamSize(teamSize);
                game.setGameStatus(GameStatus.DISABLED);

                AdnapMiniGames.databaseManager.createGame(game);
                commandSender.sendMessage("Game " + arena.getName() + " is created and saved!");
                return true;

            } catch (SQLException e) {
                commandSender.sendMessage(e.getMessage());
                return false;
            }
        }

        return false;
    }
}
