package com.adnapstudios.adnapminigames.models.game;

public class Game {
    private int id;
    private Arena arena;
    private GameType gameType;
    private GameStatus gameStatus;
    private int maxTeams;
    private int teamSize;

    public Game(int id, Arena arena, GameType gameType) {
        this.id = id;
        this.arena = arena;
        this.gameType = gameType;
    }

    public Game() {}

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

    public int getMaxTeams() {
        return maxTeams;
    }

    public void setMaxTeams(int maxTeams) {
        this.maxTeams = maxTeams;
    }

    public int getTeamSize() {
        return teamSize;
    }

    public void setTeamSize(int teamSize) {
        this.teamSize = teamSize;
    }

    public void setArena(Arena arena) {
        this.arena = arena;
    }
}
