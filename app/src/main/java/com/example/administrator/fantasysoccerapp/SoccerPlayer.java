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
    private int picture;
    private String goalsScored;
    private String goalsSaved;
    private String yellowCards;
    private String redCards;
    private String fouls;
    private String assists;

    //Constructor to create a Soccer Player
    public SoccerPlayer (String newFirstName, String newLastName, String newTeamName,
                         String newPosition, int newPicture){
        firstName = newFirstName;
        lastName = newLastName;
        teamName = newTeamName;
        position = newPosition;
        picture = newPicture;
        goalsScored = "0";
        goalsSaved = "0";
        yellowCards = "0";
        redCards = "0";
        fouls = "0";
        assists = "0";
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

    public int getPicture(){return picture;}

    public String getGoalsScored(){
        return goalsScored;
    }

    public String getGoalsSaved(){
        return goalsSaved;
    }

    public String getYellowCards(){
        return yellowCards;
    }

    public String getRedCards(){
        return redCards;
    }

    public String getFouls(){
        return fouls;
    }

    public String getAssists(){
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
    public void increaseGoalsScored(String newValue){
        goalsScored = newValue;
    }

    public void increaseGoalsSaved(String newValue){
        goalsSaved = newValue;
    }

    public void increaseYellowCards(String newValue){
        yellowCards = newValue;
    }

    public void increaseRedCards(String newValue){
        redCards = newValue;
    }

    public void increaseFouls(String newValue){
        fouls = newValue;
    }

    public void increaseAssists(String newValue){
        assists = newValue;
    }

}
