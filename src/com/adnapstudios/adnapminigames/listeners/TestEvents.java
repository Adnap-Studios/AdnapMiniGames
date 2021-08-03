package com.adnapstudios.adnapminigames.listeners;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class TestEvents implements Listener {

    @EventHandler
    public static void onPlayerJoin(PlayerJoinEvent playerJoinEvent) {
        Player player = playerJoinEvent.getPlayer();
        player.sendMessage(ChatColor.GREEN + player.getDisplayName() + ", welcome to the server!");
    }

    @EventHandler
    public static void onPlayerWalk(PlayerMoveEvent playerMoveEvent) {
        Player player = playerMoveEvent.getPlayer();
        Location location = player.getLocation();
        location.setY(location.getY() - 1);

        Material block = player.getWorld().getBlockAt(location).getType();

        if (block == Material.STONE) {
            player.sendMessage("Block Type: " + ChatColor.YELLOW + "You are walking on stone.");
        }
    }
}
