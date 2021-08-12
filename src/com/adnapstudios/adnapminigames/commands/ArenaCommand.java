package com.adnapstudios.adnapminigames.commands;

import com.adnapstudios.adnapminigames.AdnapMiniGames;
import com.adnapstudios.adnapminigames.models.game.Arena;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.sql.SQLException;
import java.util.ArrayList;

public class ArenaCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        String commandParameter = strings[0];

        if (commandParameter.equalsIgnoreCase("list")) {
            try {
                ArrayList<Arena> arenas = AdnapMiniGames.databaseManager.getAllArenas();
                commandSender.sendMessage("All saved arenas:");
                arenas.forEach(arena -> commandSender.sendMessage(arena.getId() + ": " + arena.getName()));
                return true;

            } catch (SQLException e) {
                commandSender.sendMessage(e.getMessage());
                return false;
            }
        }

        if (commandParameter.equalsIgnoreCase("create")) {
            if (AdnapMiniGames.gameManager.getPos1() == null || AdnapMiniGames.gameManager.getPos2() == null) {
                commandSender.sendMessage("You need to set the positions first!");
                return false;
            }

            Arena arena = new Arena();
            arena.setName(strings[1]);
            arena.setWorld(AdnapMiniGames.gameManager.getPos1().getWorld());
            arena.setPos1(AdnapMiniGames.gameManager.getPos1());
            arena.setPos2(AdnapMiniGames.gameManager.getPos2());

            try {
                AdnapMiniGames.databaseManager.createArena(arena);
                commandSender.sendMessage("Arena " + arena.getName() + " is created and saved!");

            } catch (SQLException e) {
                commandSender.sendMessage(e.getMessage());
                return false;
            }

            return true;
        }

        if (commandParameter.equalsIgnoreCase("set")) {
            if (strings[1].equalsIgnoreCase("lobby")) {
                String arenaName = strings[2];
                Player player = (Player) commandSender;

                try {
                    AdnapMiniGames.databaseManager.setLobby(arenaName, player.getLocation());
                    player.sendMessage("Lobby set for arena " + arenaName);
                    return true;
                } catch (SQLException e) {
                    player.sendMessage(e.getMessage());
                    return false;
                }
            }

            if (strings[1].equalsIgnoreCase("spectator")) {
                String arenaName = strings[2];
                Player player = (Player) commandSender;

                try {
                    AdnapMiniGames.databaseManager.setSpectator(arenaName, player.getLocation());
                    player.sendMessage("Spectator spawn set for arena " + arenaName);
                    return true;
                } catch (SQLException e) {
                    player.sendMessage(e.getMessage());
                    return false;
                }
            }
        }

        return false;
    }
}
