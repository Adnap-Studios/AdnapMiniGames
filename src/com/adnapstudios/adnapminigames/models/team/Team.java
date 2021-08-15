package com.adnapstudios.adnapminigames.models.team;

import org.bukkit.Color;
import org.bukkit.entity.Player;

import java.util.ArrayList;


public class Team {
    private int id;
    private int gameId;
    private String name;
    private String color;
    private TeamStatus status;
    private ArrayList<Player> players;
    private boolean canRespawn = false;

    public Team(String name, String color, ArrayList<Player> players) {
        this.name = name;
        this.color = color;
        this.players = players;
    }

    public Team() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
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
