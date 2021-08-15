package com.adnapstudios.adnapminigames.commands;

import com.adnapstudios.adnapminigames.AdnapMiniGames;
import com.adnapstudios.adnapminigames.models.game.Game;
import com.adnapstudios.adnapminigames.models.team.Team;
import com.adnapstudios.adnapminigames.models.team.TeamStatus;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.sql.SQLException;

public class TeamCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        String commandParameter = strings[0];

        // /team create GAME NAME COLOR
        if (commandParameter.equalsIgnoreCase("create")) {
            try {
                Game game = AdnapMiniGames.databaseManager.getGameByArenaName(strings[1]);
                Team team = new Team();
                team.setGameId(game.getId());
                team.setName(strings[2]);
                team.setColor(strings[3]);
                team.setStatus(TeamStatus.NOT_ALIVE);

                AdnapMiniGames.databaseManager.createTeam(team);
                commandSender.sendMessage("Team " + team.getName() +
                        String.format(" is created and saved! (Game: %s)", game.getArena().getName()));
                return true;

            } catch (SQLException e) {
                commandSender.sendMessage(e.getMessage());
                return false;
            }
        }

        return false;
    }
}
