package com.adnapstudios.adnapminigames.listeners;

import com.adnapstudios.adnapminigames.files.ShopConfig;
import com.adnapstudios.adnapminigames.models.utils.GameShop;
import com.adnapstudios.adnapminigames.models.utils.ShopItem;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class ShopClickListener implements Listener {

    @EventHandler
    public void onClicked(InventoryClickEvent inventoryClickEvent) {
        Player player = (Player) inventoryClickEvent.getWhoClicked();
        String title = inventoryClickEvent.getView().getTitle();

        Inventory next = null;
        ItemStack item = null;

        String itemName = inventoryClickEvent.getCurrentItem()
                .getItemMeta().getDisplayName();

        // TODO Check if Player is in game
        if (itemName.equalsIgnoreCase("Back to Main Menu")) {
            next = GameShop.shop(player, ShopConfig.getAllMenuItems());
        } else if (itemName.equalsIgnoreCase("Close Shop")) {
            player.closeInventory();
            return;
        } else {
            for (String displayName : ShopConfig.getAllMenuItemsDisplayNames()) {
                if (itemName.equalsIgnoreCase(displayName)) {
                    next = GameShop.shopMenu(player, itemName);
                    break;
                }
            }
        }

        if (next != null) {
            player.closeInventory();
            player.openInventory(next);
            inventoryClickEvent.setCancelled(true);
            return;
        }

        for (ShopItem shopItem : ShopConfig.getAllShopItems()) {
            if (itemName.equalsIgnoreCase(shopItem.getDisplayName())) {
                item = shopItem.getItemStack();
            }
        }

        if (item != null) {
            player.getInventory().addItem(item);
        }

        inventoryClickEvent.setCancelled(true);
    }
}
