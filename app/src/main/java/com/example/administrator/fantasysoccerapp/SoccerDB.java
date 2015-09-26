package com.example.administrator.fantasysoccerapp;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.jar.Attributes;

/**
 * Created by Jordan Goldey
 * Last edited 9/25/2015
 * SoccerDB
 */
public class SoccerDB {
    private static Hashtable<String, SoccerPlayer> playerDatabase;
    private static Hashtable<String, SoccerTeam> teamDatabase;

    public static void createDefaultDatabase() {
        //create the tables
        playerDatabase = new Hashtable<>();
        teamDatabase = new Hashtable<>();

        //create soccer players for the Catz team
        SoccerPlayer catPlayer1 = new SoccerPlayer("fluffy", "whiskers", "Catz", "forward", R.drawable.player1_cats);
        SoccerPlayer catPlayer2 = new SoccerPlayer("angry", "tail", "Catz", "forward", R.drawable.player2_cats);
        SoccerPlayer catPlayer3 = new SoccerPlayer("fire", "star", "Catz", "defense", R.drawable.player3_cats);
        SoccerPlayer catPlayer4 = new SoccerPlayer("tabby", "blue", "Catz", "defense", R.drawable.player4_cats);
        SoccerPlayer catPlayer5 = new SoccerPlayer("felix", "garfield", "Catz", "goalie", R.drawable.player5_cats);

        //put them in the player database
        playerDatabase.put("fluffy whiskers", catPlayer1);
        playerDatabase.put("angry tail", catPlayer2);
        playerDatabase.put("fire star", catPlayer3);
        playerDatabase.put("tabby blue", catPlayer4);
        playerDatabase.put("felix garfield", catPlayer5);

        //create the Catz soccerTeam and add players
        SoccerTeam Catz = new SoccerTeam("Catz", R.drawable.cats_logo);
        Catz.addPlayer(catPlayer1);
        Catz.addPlayer(catPlayer2);
        Catz.addPlayer(catPlayer3);
        Catz.addPlayer(catPlayer4);
        Catz.addPlayer(catPlayer5);

        //create soccer players for the Kittenz team
        SoccerPlayer kittenzPlayer1 = new SoccerPlayer("little", "paw", "Kittenz", "forward", R.drawable.player6_cats);
        SoccerPlayer kittenzPlayer2 = new SoccerPlayer("small", "fry", "Kittenz", "forward", R.drawable.player7_cats);
        SoccerPlayer kittenzPlayer3 = new SoccerPlayer("tiny", "mouse", "Kittenz", "defense", R.drawable.player8_cats);
        SoccerPlayer kittenzPlayer4 = new SoccerPlayer("petite", "fur", "Kittenz", "defense", R.drawable.player9_cats);
        SoccerPlayer kittenzPlayer5 = new SoccerPlayer("baby", "eyes", "Kittenz", "goalie", R.drawable.player10_cats);

        //put them in the player database
        playerDatabase.put("little paw", kittenzPlayer1);
        playerDatabase.put("small fry", kittenzPlayer2);
        playerDatabase.put("tiny mouse", kittenzPlayer3);
        playerDatabase.put("petite fur", kittenzPlayer4);
        playerDatabase.put("baby eyes", kittenzPlayer5);

        //create the Kittenz soccerTeam and add players
        SoccerTeam Kittenz = new SoccerTeam("Kittenz", R.drawable.kittenz_logo);
        Kittenz.addPlayer(kittenzPlayer1);
        Kittenz.addPlayer(kittenzPlayer2);
        Kittenz.addPlayer(kittenzPlayer3);
        Kittenz.addPlayer(kittenzPlayer4);
        Kittenz.addPlayer(kittenzPlayer5);

        //put the teams in the team database
        teamDatabase.put("Catz", Catz);
        teamDatabase.put("Kittenz", Kittenz);
    }

    public static ArrayList<String> getListOfTeamNames() {
        ArrayList<String> teamNames = new ArrayList<>();
        Enumeration<String> teams = teamDatabase.keys();
        while (teams.hasMoreElements()) {
            teamNames.add(teams.nextElement());
        }
        return teamNames;
    }

    public static int getNumberOfTeams() {
        return (teamDatabase.size());
    }

    public static int getNumberOfPlayers(String teamName) {
        SoccerTeam team = teamDatabase.get(teamName);
        ArrayList<SoccerPlayer> players = team.getPlayers();
        return players.size();
    }

    public static ArrayList<SoccerPlayer> getPlayers(String teamName) {
        SoccerTeam team = teamDatabase.get(teamName);
        ArrayList<SoccerPlayer> players = team.getPlayers();
        return players;
    }

    public static SoccerPlayer getPlayer(String playerName) {
        SoccerPlayer player = playerDatabase.get(playerName);
        return player;
    }

    public static int returnTeamLogo(String teamName){
        SoccerTeam team = teamDatabase.get(teamName);
        return team.getTeamLogo();
    }
}
