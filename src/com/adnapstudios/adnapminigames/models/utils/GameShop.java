package com.adnapstudios.adnapminigames.models.utils;

import com.adnapstudios.adnapminigames.files.ShopConfig;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class GameShop {
    public static Inventory shop(Player player, ArrayList<ShopItem> shopItems) {
        Inventory inventory = Bukkit.createInventory(player, ShopConfig.getNumberOfSlots(), "Shop");

        for (ShopItem shopItem : shopItems) {
            shopItem.putItemInShop(inventory);
        }

        fillEmptySpots(inventory);
        return inventory;
    }

    public static Inventory shop(Player player, ArrayList<ShopItem> shopItems, String menuName) {
        Inventory inventory = Bukkit.createInventory(player, ShopConfig.getNumberOfSlots(), menuName);

        for (ShopItem shopItem : shopItems) {
            shopItem.putItemInShop(inventory);
        }

        fillEmptySpots(inventory);
        return inventory;
    }

    public static Inventory shopMenu(Player player, String menuName) {
        ArrayList<ShopItem> shopItems = ShopConfig.getAllShopItemsFromMenuItem(menuName);
        Inventory shop = shop(player, shopItems, menuName);
        setMenuButtons(shop);
        setMenuItems(shop, menuName);
        return shop;
    }

    public static void fillEmptySpots(Inventory inventory) {
        ItemStack fillItem = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        ItemMeta fillItemMeta = fillItem.getItemMeta();
        fillItemMeta.setDisplayName(" ");
        fillItem.setItemMeta(fillItemMeta);

        for (int i = 0; i < inventory.getSize(); i++) {
            ItemStack item = inventory.getItem(i);
            if (item == null) {
                inventory.setItem(i, fillItem);
            }
        }

    }

    public static void setMenuButtons(Inventory inventory) {
        ItemStack backItem = new ItemStack(Material.BLUE_STAINED_GLASS_PANE);
        ItemMeta backItemMeta = backItem.getItemMeta();
        backItemMeta.setDisplayName("Back to Main Menu");
        backItem.setItemMeta(backItemMeta);
        inventory.setItem(48, backItem);

        ItemStack closeItem = new ItemStack(Material.RED_STAINED_GLASS_PANE);
        ItemMeta closeItemMeta = closeItem.getItemMeta();
        closeItemMeta.setDisplayName("Close Shop");
        closeItem.setItemMeta(closeItemMeta);
        inventory.setItem(50, closeItem);
    }

    public static void setMenuItems(Inventory inventory, String currentMenu) {
        ArrayList<ShopItem> shopItems = ShopConfig.getAllMenuItems();

        int slot = 10;

        for (ShopItem shopItem : shopItems) {
            shopItem.setSlot(slot);

            if (shopItem.getDisplayName().equalsIgnoreCase(currentMenu)) {
                shopItem.setEnchantment(Enchantment.MENDING);
                shopItem.setItemFlag(ItemFlag.HIDE_ENCHANTS);
            }

            shopItem.putItemInShop(inventory);
            slot++;
        }

    }
}
