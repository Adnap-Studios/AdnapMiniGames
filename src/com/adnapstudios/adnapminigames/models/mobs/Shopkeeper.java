package com.adnapstudios.adnapminigames.models.mobs;

import com.adnapstudios.adnapminigames.AdnapMiniGames;
import org.bukkit.Location;
import org.bukkit.entity.Villager;

public class Shopkeeper {
    public static void spawnPlayerShop(Location location) {
        location.add(0.5, 1, 0.5);
        Villager shopKeeper = location.getWorld().spawn(location, Villager.class);
        shopKeeper.setCustomName("Player Shop");
        shopKeeper.setAI(false);
        shopKeeper.setAware(true);
    }
}
