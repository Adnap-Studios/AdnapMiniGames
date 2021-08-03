package com.adnapstudios.adnapminigames.models;

public class Game {
    private int id;
    private Arena arena;
    private GameType gameType;
    private GameStatus gameStatus;
    private TeamManager teamManager;

    public Game(int id, Arena arena, GameType gameType) {
        this.id = id;
        this.arena = arena;
        this.gameType = gameType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Arena getArena() {
        return arena;
    }

    public GameType getGameType() {
        return gameType;
    }

    public void setGameType(GameType gameType) {
        this.gameType = gameType;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public void setTeamManager(TeamManager teamManager) {
        this.teamManager = teamManager;
    }

    public void setArena(Arena arena) {
        this.arena = arena;
    }

    public TeamManager getTeamManager() {
        return teamManager;
    }
}
