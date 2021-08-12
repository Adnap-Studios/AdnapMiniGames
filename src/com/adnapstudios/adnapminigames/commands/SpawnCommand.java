package com.adnapstudios.adnapminigames.commands;

import com.adnapstudios.adnapminigames.AdnapMiniGames;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.sql.SQLException;

public class SpawnCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage("You are not a player!");
            return false;
        }

        Player player = ((Player) commandSender);

        if (strings.length == 0) {
            try {
                if (AdnapMiniGames.databaseManager.getSpawn() == null) {
                    player.sendMessage("No spawn point is set!");
                    return false;
                } else {
                    player.sendMessage("Sending you to spawn...");
                    player.teleport(AdnapMiniGames.databaseManager.getSpawn());
                    return true;
                }
            } catch (SQLException e) {
                player.sendMessage(e.getMessage());
                return false;
            }
        }

        if (strings[0].equalsIgnoreCase("set")) {
            try {
                AdnapMiniGames.databaseManager.setSpawn(player.getLocation());
                player.sendMessage("Spawn point set!");
                return true;
            } catch (SQLException e) {
                player.sendMessage(e.getMessage());
                return false;
            }
        }

        return false;
    }
}
