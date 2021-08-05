package com.adnapstudios.adnapminigames.models.game;

import org.bukkit.World;

public class Arena {
    private int id;
    private String name;
    private World world;
    private int pos1;
    private int pos2;

    public Arena(int id, String name, World world, int pos1, int pos2) {
        this.id = id;
        this.name = name;
        this.world = world;
        this.pos1 = pos1;
        this.pos2 = pos2;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public int getPos1() {
        return pos1;
    }

    public void setPos1(int pos1) {
        this.pos1 = pos1;
    }

    public int getPos2() {
        return pos2;
    }

    public void setPos2(int pos2) {
        this.pos2 = pos2;
    }
}
