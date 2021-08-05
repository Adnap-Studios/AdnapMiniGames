package com.adnapstudios.adnapminigames.commands;

import com.adnapstudios.adnapminigames.files.ShopConfig;
import com.adnapstudios.adnapminigames.models.utils.GameShop;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ShopCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player player = (Player) commandSender;
        player.openInventory(GameShop.shop(player, ShopConfig.getAllMenuItems()));
        return true;
    }
}
