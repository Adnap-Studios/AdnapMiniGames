package com.adnapstudios.adnapminigames;

import com.adnapstudios.adnapminigames.listeners.TestEvents;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class AdnapMiniGames extends JavaPlugin {

    @Override
    public void onEnable() {
        loadConfig();

        // Listeners
        getServer().getPluginManager().registerEvents(new TestEvents(), this);

        // Commands


        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "[AdnapMiniGames] Plugin is enabled!");
    }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage(ChatColor.RED + "[AdnapMiniGames] Plugin is disabled!");
    }

    public void loadConfig() {
        getConfig().options().copyDefaults(true);
        saveConfig();
    }
}
