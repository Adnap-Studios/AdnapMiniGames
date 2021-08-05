package com.adnapstudios.adnapminigames;

import com.adnapstudios.adnapminigames.commands.ShopCommand;
import com.adnapstudios.adnapminigames.files.ShopConfig;
import com.adnapstudios.adnapminigames.listeners.ShopClickListener;
import com.adnapstudios.adnapminigames.listeners.TestEvents;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class AdnapMiniGames extends JavaPlugin {

    @Override
    public void onEnable() {
        // Config files
        loadConfig();
        ShopConfig.setup();
        //ShopConfig.get().options().copyDefaults(true);

        // Listeners
        getServer().getPluginManager().registerEvents(new TestEvents(), this);
        getServer().getPluginManager().registerEvents(new ShopClickListener(), this);

        // Commands
        getCommand("shop").setExecutor(new ShopCommand());


        // Plugin Enabled
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
