package com.example.administrator.fantasysoccerapp;

import java.util.ArrayList;

/**
 * Created by Jordan Goldey
 * Last edited 9/25/2015
 * SoccerTeam is a class that creates an object for a soccer team. It contains
 * instance variables relating to the information relating to a soccer teams stats during a game.
 * The class also contains get, increase, add, remove, and set methods for all the instance
 * variables.
 */
public class SoccerTeam {
    //Instance variable that describe the team
    private String teamName;
    private ArrayList<SoccerPlayer> players;
    private int teamLogo;
    private int goalsScored;
    private int goalsSaved;
    private int yellowCards;
    private int redCards;
    private int fouls;
    private int assists;

    //Constructor to create a Soccer Player
    public SoccerTeam (String newTeamName, int newTeamLogo){
        teamName = newTeamName;
        players = new ArrayList<>();
        teamLogo = newTeamLogo;
        goalsScored = 0;
        goalsSaved = 0;
        yellowCards = 0;
        redCards = 0;
        fouls = 0;
        assists = 0;
    }

    //Get methods to access the instance variables
    public String getTeamName(){
        return teamName;
    }

    public ArrayList<SoccerPlayer> getPlayers(){
        return players;
    }

    public int getTeamLogo(){
        return teamLogo;
    }

    public int getGoalsScored(){
        return goalsScored;
    }

    public int getGoalsSaved(){
        return goalsSaved;
    }

    public int getYellowCards(){
        return yellowCards;
    }

    public int getRedCards(){
        return redCards;
    }

    public int getFouls(){
        return fouls;
    }

    public int getAssists(){
        return assists;
    }

    //Set methods to edit the string variables
    public void setTeamName(String newTeamName){
        teamName = newTeamName;
    }

    //Add method so add players to the players array if 5 players are not already on the team
    public boolean addPlayer(SoccerPlayer newPlayer){
        if (players.size() < 5){
            players.add(newPlayer);
            return true;
        }else{
            return false;
        }
    }

    //Increase methods to increase the stat variables
    public void increaseGoalsScored(int newValue){
        goalsScored = newValue;
    }

    public void increaseGoalsSaved(int newValue){
        goalsSaved = newValue;
    }

    public void increaseYellowCards(int newValue){
        yellowCards = newValue;
    }

    public void increaseRedCards(int newValue){
        redCards = newValue;
    }

    public void increaseFouls(int newValue){
        fouls = newValue;
    }

    public void increaseAssists(int newValue){
        assists = newValue;
    }

    //Remove method for removing players from team
    public boolean removePlayer(SoccerPlayer player){
        if (players.size() != 0){
            players.remove(player);
            return true;
        }else{
            return false;
        }
    }

}
