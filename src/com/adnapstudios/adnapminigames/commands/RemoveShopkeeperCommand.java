package com.adnapstudios.adnapminigames.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;

import java.util.List;

public class RemoveShopkeeperCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            List<Entity> entities = player.getNearbyEntities(2, 2, 2);
            if (entities.size() > 0) {
                Entity entity = entities.get(0);
                if (entity instanceof Villager) {
                    if (entity.getCustomName() != null) {
                        if (entity.getCustomName().equalsIgnoreCase("Player Shop")) {
                            entity.remove();
                            player.sendMessage("Shopkeeper removed.");
                            return true;
                        }
                    }
                }
            } else {
                player.sendMessage("No nearby shopkeepers found.");
            }
        }

        return false;
    }
}
