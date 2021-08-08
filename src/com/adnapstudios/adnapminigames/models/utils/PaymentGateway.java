package com.adnapstudios.adnapminigames.models.utils;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class PaymentGateway {
    public static void payment(Player player, ShopItem shopItem) {
        boolean allowed = checkIfPlayerCanBuyItem(player, shopItem);

        if (allowed) {
            ItemStack paymentItem = new ItemStack(shopItem.getCurrency());
            int price = shopItem.getPrice();
            paymentItem.setAmount(price);

            player.getInventory().removeItem(paymentItem);

            if (shopItem.isArmor()) {
                for (ItemStack armor : shopItem.getArmor()) {
                    player.getInventory().addItem(armor);
                }
            } else {
                player.getInventory().addItem(shopItem.getItem());
            }

            String itemName = shopItem.getDisplayName();
            if (itemName.equalsIgnoreCase("")) itemName = shopItem.getMaterial().name();
            player.sendMessage("You bought " + ChatColor.LIGHT_PURPLE
                    + itemName + ChatColor.RESET + " for "
                    + ChatColor.LIGHT_PURPLE + shopItem.getPrice() + " " + shopItem.getCurrency().name());
        }
    }

    private static boolean checkIfPlayerCanBuyItem(Player player, ShopItem shopItem) {
        if (player.getInventory().firstEmpty() == -1) {
            player.sendMessage("You have no empty spots!");
            return false;
        }

        if (!player.getInventory().contains(shopItem.getCurrency(), shopItem.getPrice())) {
            player.sendMessage("You do not have enough " + shopItem.getCurrency().name());
            return false;
        }

        return true;
    }
}
