package com.adnapstudios.adnapminigames.commands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class ShopkeeperSpawnEggCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;

            ItemStack egg = new ItemStack(Material.AXOLOTL_SPAWN_EGG);
            ItemMeta itemMeta = egg.getItemMeta();
            itemMeta.setDisplayName("Shopkeeper");
            ArrayList<String> lores = new ArrayList<>();
            lores.add("Place a Shopkeeper");
            itemMeta.setLore(lores);
            itemMeta.addEnchant(Enchantment.MENDING,1, true);
            itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            egg.setItemMeta(itemMeta);

            player.getInventory().addItem(egg);
            return true;
        }

        return false;
    }
}
