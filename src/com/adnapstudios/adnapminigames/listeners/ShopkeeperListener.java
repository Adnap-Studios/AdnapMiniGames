package com.adnapstudios.adnapminigames.listeners;

import com.adnapstudios.adnapminigames.files.ShopConfig;
import com.adnapstudios.adnapminigames.models.mobs.Shopkeeper;
import com.adnapstudios.adnapminigames.models.utils.GameShop;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class ShopkeeperListener implements Listener {
    @EventHandler
    public void placePlayerShop(PlayerInteractEvent playerInteractEvent) {
        Material material = playerInteractEvent.getPlayer().getInventory().getItemInMainHand().getType();
        if (material == Material.AXOLOTL_SPAWN_EGG) {
            String name = playerInteractEvent.getPlayer().getInventory().getItemInMainHand().getItemMeta().getDisplayName();
            if (name.equalsIgnoreCase("Shopkeeper")) {
                if (playerInteractEvent.getAction() == Action.RIGHT_CLICK_BLOCK) {
                    Location location = playerInteractEvent.getClickedBlock().getLocation();
                    Shopkeeper.spawnPlayerShop(location);
                    playerInteractEvent.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void openPlayerShop(PlayerInteractAtEntityEvent playerInteractAtEntityEvent) {
        String name = playerInteractAtEntityEvent.getRightClicked().getCustomName();
        if (name != null) {
            if (name.equalsIgnoreCase("Player Shop")) {
                Player player = playerInteractAtEntityEvent.getPlayer();
                player.openInventory(GameShop.shop(player, ShopConfig.getAllMenuItems()));
                playerInteractAtEntityEvent.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void noDamageToShopkeeper(EntityDamageByEntityEvent entityDamageByEntityEvent) {
        if (entityDamageByEntityEvent.getEntity() instanceof Villager) {
            if (entityDamageByEntityEvent.getEntity().getCustomName().equalsIgnoreCase("Player Shop")) {
                entityDamageByEntityEvent.setCancelled(true);
            }
        }
    }
}
