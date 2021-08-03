package com.adnapstudios.adnapminigames.models;

import org.bukkit.Color;
import org.bukkit.entity.Player;

import java.util.ArrayList;


public class Team {
    private String name;
    private Color color;
    private TeamStatus status;
    private ArrayList<Player> players;
    private boolean canRespawn = false;

    public Team(String name, Color color, ArrayList<Player> players) {
        this.name = name;
        this.color = color;
        this.players = players;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public TeamStatus getStatus() {
        return status;
    }

    public void setStatus(TeamStatus status) {
        this.status = status;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public boolean canRespawn() {
        return canRespawn;
    }

    public void canRespawn(boolean canRespawn) {
        this.canRespawn = canRespawn;
    }
}
