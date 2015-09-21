package com.example.administrator.fantasysoccerapp;

/**
 * Created by Jordan Goldey
 * Last edited 9/20/2015
 * SoccerPlayer is a class that creates an object for a soccer player. It contains
 * instance variables relating to the information relating to a soccer payers stats during a game.
 * The class also contains get, increase and set methods for all the instance variables.
 */
public class SoccerPlayer {

    //Instance variable that describe the player
    private String firstName;
    private String lastName;
    private String teamName;
    private String position;
    private int goalsScored;
    private int goalsSaved;
    private int yellowCards;
    private int redCards;
    private int fouls;
    private int assists;

    //Constructor to create a Soccer Player
    public SoccerPlayer (String newFirstName, String newLastName, String newTeamName, String newPosition){
        firstName = newFirstName;
        lastName = newLastName;
        teamName = newTeamName;
        position = newPosition;
        goalsScored = 0;
        goalsSaved = 0;
        yellowCards = 0;
        redCards = 0;
        fouls = 0;
        assists = 0;
    }

    //Get methods to access the instance variables
    public String getFirstName(){
        return firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public String getTeamName(){
        return teamName;
    }

    public String getPosition(){
        return position;
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
    public void setFirstName(String newFirstName){
        firstName = newFirstName;
    }

    public void setLastName(String newLastName){
        lastName = newLastName;
    }

    public void setTeamName(String newTeamName){
        teamName = newTeamName;
    }

    public void setPosition(String newPosition){
        position = newPosition;
    }

    //Increase methods to increase the int variables
    public void increaseGoalsScored(){
        goalsScored++;
    }

    public void increaseGoalsSaved(){
        goalsSaved++;
    }

    public void increaseYellowCards(){
        yellowCards++;
    }

    public void increaseRedCards(){
        redCards++;
    }

    public void increaseFouls(){
        fouls++;
    }

    public void increaseAssists(){
        assists++;
    }

}
