package com.adnapstudios.adnapminigames;

import com.adnapstudios.adnapminigames.commands.*;
import com.adnapstudios.adnapminigames.files.ShopConfig;
import com.adnapstudios.adnapminigames.listeners.ShopClickListener;
import com.adnapstudios.adnapminigames.listeners.ShopkeeperListener;
import com.adnapstudios.adnapminigames.listeners.TestEvents;
import com.adnapstudios.adnapminigames.models.game.GameManager;
import com.adnapstudios.adnapminigames.models.utils.DatabaseManager;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;

public class AdnapMiniGames extends JavaPlugin {
    public static GameManager gameManager;
    public static DatabaseManager databaseManager;

    @Override
    public void onEnable() {
        // Config files
        loadConfig();
        ShopConfig.setup();
        ShopConfig.get().options().copyDefaults(true);

        // GameManager, TeamManager and DatabaseManager
        gameManager = new GameManager();
        databaseManager = new DatabaseManager();

        try {
            databaseManager.readConfig();
            databaseManager.connect();
            databaseManager.createTables();
        } catch (SQLException e) {
            System.out.println("Database not connected!");
            System.out.println(e);
        }

        if (databaseManager.isConnected()) System.out.println("Database is connected!");

        // Listeners
        getServer().getPluginManager().registerEvents(new TestEvents(), this);
        getServer().getPluginManager().registerEvents(new ShopClickListener(), this);
        getServer().getPluginManager().registerEvents(new ShopkeeperListener(), this);

        // Commands
        getCommand("shop").setExecutor(new ShopCommand());
        getCommand("shopkeeper").setExecutor(new ShopkeeperSpawnEggCommand());
        getCommand("removeshopkeeper").setExecutor(new RemoveShopkeeperCommand());
        getCommand("pos1").setExecutor(new Pos1Command());
        getCommand("pos2").setExecutor(new Pos2Command());
        getCommand("arena").setExecutor(new CreateArenaCommand());


        // Plugin Enabled
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "[AdnapMiniGames] Plugin is enabled!");
    }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage(ChatColor.RED + "[AdnapMiniGames] Plugin is disabled!");
        databaseManager.close();
    }

    public void loadConfig() {
        getConfig().options().copyDefaults(true);
        saveConfig();
    }
}
