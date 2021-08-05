package com.adnapstudios.adnapminigames.files;

import com.adnapstudios.adnapminigames.AdnapMiniGames;
import com.adnapstudios.adnapminigames.models.utils.ShopItem;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.Inventory;

import java.io.*;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class ShopConfig {

    private static File file;
    private static FileConfiguration shopConfig;

    public static void setup() {
        file = new File(Bukkit.getServer()
                .getPluginManager().
                getPlugin("AdnapMiniGames")
                .getDataFolder(), "shop.yml");

        if (!file.exists()) {
            try {
                file.createNewFile();

                // Download default shop
                String shopConfigString = AdnapMiniGames.getPlugin(AdnapMiniGames.class)
                        .getConfig().getString("shop-config-url");
                URL shopConfig = new URL(shopConfigString);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(shopConfig.openStream()));
                FileWriter out = new FileWriter(file);
                String inputLine;
                while ((inputLine = in.readLine()) != null)
                    out.write(inputLine + "\n");
                in.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        shopConfig = YamlConfiguration.loadConfiguration(file);
    }

    public static FileConfiguration get() {
        return shopConfig;
    }

    public static void save() {
        try {
            shopConfig.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void reload() {
        shopConfig = YamlConfiguration.loadConfiguration(file);
    }

    public static ArrayList<ShopItem> getAllMenuItems() {
        ArrayList<HashMap<String, Object>> list = (ArrayList<HashMap<String, Object>>) ShopConfig.get()
                .getList("menu-items");
        ArrayList<ShopItem> shopItems = new ArrayList<>();

        for (HashMap<String, Object> item : list) {
            Material material = Material.valueOf((String) item.get("material"));
            String displayName = (String) item.get("displayName");
            int slot = (int) item.get("slot");
            String lore = (String) item.get("lore");
            shopItems.add(new ShopItem(material, displayName, slot, lore));
        }

        return shopItems;
    }

    public static ArrayList<String> getAllMenuItemsDisplayNames() {
        ArrayList<ShopItem> menuItems = getAllMenuItems();
        ArrayList<String> allDisplayNames = new ArrayList<>();
        menuItems.forEach((item) -> allDisplayNames.add(item.getDisplayName()));
        return allDisplayNames;
    }

    public static ArrayList<ShopItem> getAllShopItemsFromMenuItem(String menuItem) {
        ArrayList<ShopItem> menuItems = getAllMenuItems();
        ArrayList<HashMap<String, Object>> list = (ArrayList<HashMap<String, Object>>) ShopConfig.get()
                .getList("shop-items");
        ArrayList<ShopItem> shopItems = new ArrayList<>();

        for (HashMap<String, Object> item : list) {
            if (item.get("menu-item").equals(menuItem)) {
                ShopItem shopItem = new ShopItem();
                shopItem.setMaterial(Material.valueOf((String) item.get("material")));
                shopItem.setDisplayName((String) item.get("displayName"));
                shopItem.setSlot((int) item.get("slot"));
                shopItem.setLore((String) item.get("lore"));
                shopItem.setAmount((int) item.get("amount"));
                shopItem.setPrice((int) item.get("cost"));
                shopItem.setCurrency(Material.valueOf((String) item.get("currency")));
                shopItems.add(shopItem);
            }
        }

        return shopItems;
    }

    public static ArrayList<ShopItem> getAllShopItems() {
        ArrayList<ShopItem> menuItems = getAllMenuItems();
        ArrayList<ShopItem> allShopItems = new ArrayList<>();

        for (ShopItem menu : menuItems) {
            ArrayList<ShopItem> items = getAllShopItemsFromMenuItem(menu.getDisplayName());
            allShopItems.addAll(items);
        }

        return allShopItems;
    }

    public static int getNumberOfRows() {
        return ShopConfig.get().getInt("number-of-rows");
    }

    public static int getNumberOfSlots() {
        return getNumberOfRows() * 9;
    }
}
