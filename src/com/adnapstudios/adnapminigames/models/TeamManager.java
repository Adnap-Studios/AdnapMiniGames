package com.adnapstudios.adnapminigames.models;

import java.util.ArrayList;

public class TeamManager {
    private ArrayList<Team> teams;

    public TeamManager(ArrayList<Team> teams) {
        this.teams = teams;
    }

    public ArrayList<Team> getTeams() {
        return teams;
    }

    public void setTeams(ArrayList<Team> teams) {
        this.teams = teams;
    }
}
