package com.adnapstudios.adnapminigames.commands;

import com.adnapstudios.adnapminigames.AdnapMiniGames;
import com.adnapstudios.adnapminigames.models.game.Arena;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ArenaCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length < 2) {
            commandSender.sendMessage("No parameters found!");
            return false;
        }

        String commandParameter = strings[0];

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
            AdnapMiniGames.gameManager.createArena(arena);

            return true;
        }

        return false;
    }
}
