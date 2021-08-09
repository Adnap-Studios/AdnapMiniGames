package com.adnapstudios.adnapminigames.models.utils;

public class Player {
    private String uuid;
    private String username;
    private int gameId;
    private int iron;
    private int gold;
    private int diamond;
    private int emerald;

    public Player(String uuid, String username) {
        this.uuid = uuid;
        this.username = username;
    }
}
