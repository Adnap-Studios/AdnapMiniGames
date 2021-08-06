package com.adnapstudios.adnapminigames.models.game;

import org.bukkit.Location;

import java.util.ArrayList;

public class GameManager {
    private Location pos1;
    private Location pos2;
    private ArrayList<Arena> arenas;

    public GameManager() {
        arenas = new ArrayList<>();
    }

    public void setPos1(Location pos1) {
        this.pos1 = pos1;
    }

    public void setPos2(Location pos2) {
        this.pos2 = pos2;
    }

    public Location getPos1() {
        return pos1;
    }

    public Location getPos2() {
        return pos2;
    }

    public ArrayList<Arena> getArenas() {
        return arenas;
    }

    public void createArena(Arena arena) {
        arenas.add(arena);
    }

    public void createGame(Game game) {
        // Create Game
    }
}
