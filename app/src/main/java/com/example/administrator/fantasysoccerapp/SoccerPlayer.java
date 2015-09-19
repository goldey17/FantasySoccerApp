package com.example.administrator.fantasysoccerapp;

/**
 * Created by jgoldey on 9/18/2015.
 */
public class SoccerPlayer {
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

}
