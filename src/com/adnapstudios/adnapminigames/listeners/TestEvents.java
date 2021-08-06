package com.adnapstudios.adnapminigames.listeners;

import com.adnapstudios.adnapminigames.AdnapMiniGames;
import com.adnapstudios.adnapminigames.models.game.Arena;
import org.bukkit.ChatColor;
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

    /*@EventHandler
    public static void onPlayerWalk(PlayerMoveEvent playerMoveEvent) {
        Player player = playerMoveEvent.getPlayer();

        for (Arena arena : AdnapMiniGames.gameManager.getArenas()) {
            if (arena.isPlayerInArena(player)) {
                player.sendMessage("IN ARENA");
            } else {
                player.sendMessage("OUT ARENA");
            }
        }
    }*/
}
