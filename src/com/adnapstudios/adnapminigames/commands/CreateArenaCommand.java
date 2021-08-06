package com.adnapstudios.adnapminigames.commands;

import com.adnapstudios.adnapminigames.AdnapMiniGames;
import com.adnapstudios.adnapminigames.models.game.Arena;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CreateArenaCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings[0].equalsIgnoreCase("create")) {
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
