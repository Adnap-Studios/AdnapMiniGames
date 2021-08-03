package com.adnapstudios.adnapminigames;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class AdnapMiniGames extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "[AdnapMiniGames] Plugin is enabled!");
    }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage(ChatColor.RED + "[AdnapMiniGames] Plugin is disabled!");
    }
}
