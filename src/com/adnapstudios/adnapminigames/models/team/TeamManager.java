package com.adnapstudios.adnapminigames.models.team;

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

    public void addTeam(Team team) {
        this.teams.add(team);
    }

    public void addTeams(ArrayList<Team> teams) {
        for (Team newTeam : teams) {
            addTeam(newTeam);
        }
    }
}
