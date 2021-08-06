package com.adnapstudios.adnapminigames.commands;

import com.adnapstudios.adnapminigames.AdnapMiniGames;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Pos2Command implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        AdnapMiniGames.gameManager.setPos2(((Player) commandSender).getLocation());
        commandSender.sendMessage("Pos2 set! -  " + AdnapMiniGames.gameManager.getPos2());
        return true;
    }
}
