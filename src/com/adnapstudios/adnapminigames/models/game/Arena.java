package com.adnapstudios.adnapminigames.models.game;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class Arena {
    private int id;
    private String name;
    private World world;
    private Location pos1;
    private Location pos2;
    private Location lobby;
    private Location spectator;

    public Arena(String name, World world, Location pos1, Location pos2) {
        this.name = name;
        this.world = world;
        this.pos1 = pos1;
        this.pos2 = pos2;
    }

    public Arena() {

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

    public Location getPos1() {
        return pos1;
    }

    public void setPos1(Location pos1) {
        this.pos1 = pos1;
    }

    public Location getPos2() {
        return pos2;
    }

    public void setPos2(Location pos2) {
        this.pos2 = pos2;
    }

    public Location getLobby() {
        return lobby;
    }

    public void setLobby(Location lobby) {
        this.lobby = lobby;
    }

    public Location getSpectator() {
        return spectator;
    }

    public void setSpectator(Location spectator) {
        this.spectator = spectator;
    }

    public boolean isPlayerInArena(Player player) {
        if (player.getLocation().getX() >= Math.min(pos1.getX(), pos2.getX()) &&
                player.getLocation().getY() >= Math.min(pos1.getY(), pos2.getY())) {
            if (player.getLocation().getX() <= Math.max(pos1.getX(), pos2.getX()) &&
                    player.getLocation().getY() <= Math.max(pos1.getY(), pos2.getY())) {
                return true;
            }
        }

        return false;
        /*

                if((player.getLocation().getBlockX() > pos1.getBlockX()) && (player.getLocation().getBlockX() < pos2.getBlockX())){
            if((player.getLocation().getBlockY() > pos1.getBlockY()) && (player.getLocation().getBlockY() < pos2.getBlockY())){
                if((player.getLocation().getBlockZ() > pos1.getBlockZ()) && (player.getLocation().getBlockZ() < pos2.getBlockZ())){
                    return true;
                }
            }
        }

        return false;
    }

         */
    }
}
