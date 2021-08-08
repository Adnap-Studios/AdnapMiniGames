package com.adnapstudios.adnapminigames.listeners;

import com.adnapstudios.adnapminigames.files.ShopConfig;
import com.adnapstudios.adnapminigames.models.utils.GameShop;
import com.adnapstudios.adnapminigames.models.utils.PaymentGateway;
import com.adnapstudios.adnapminigames.models.utils.ShopItem;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class ShopClickListener implements Listener {

    @EventHandler
    public void onClicked(InventoryClickEvent inventoryClickEvent) {
        Player player = (Player) inventoryClickEvent.getWhoClicked();
        String title = inventoryClickEvent.getView().getTitle();

        boolean isShopWindow = false;
        Inventory next = null;
        ShopItem item = null;

        if (inventoryClickEvent.getCurrentItem() == null) {
            return;
        }

        ItemStack clickedItem = inventoryClickEvent.getCurrentItem();
        String itemName = clickedItem.getItemMeta().getDisplayName();

        // TODO Check if Player is in game
        if (itemName.equalsIgnoreCase("Back to Main Menu")) {
            next = GameShop.shop(player, ShopConfig.getAllMenuItems());
        } else if (itemName.equalsIgnoreCase("Close Shop")) {
            player.closeInventory();
            return;
        } else {
            for (String displayName : ShopConfig.getAllMenuItemsDisplayNames()) {
                if (title.equalsIgnoreCase(displayName)) isShopWindow = true;
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
            List<String> lores = clickedItem.getItemMeta().getLore();
            assert lores != null;
            if (lores.get(0) != null) {
                if (lores.get(0).equalsIgnoreCase("Shop Item")) {
                    if (shopItem.getDisplayName().equalsIgnoreCase(itemName)) {
                        item = shopItem;
                        break;
                    }
                }
            }
        }

        if (item != null) {
            PaymentGateway.payment(player, item);
            inventoryClickEvent.setCancelled(true);
        }

        if (isShopWindow) inventoryClickEvent.setCancelled(true);

    }
}
