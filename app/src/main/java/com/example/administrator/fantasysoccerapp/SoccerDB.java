package com.example.administrator.fantasysoccerapp;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

/**
 * Created by Jordan Goldey
 * Last edited 9/28/2015
 * SoccerDB is the database for the app. It has a default database and all the methods required to
 * handle the database.
 */
public class SoccerDB {
    //Hashtables to store data about the players and teams
    private static Hashtable<String, SoccerPlayer> playerDatabase;
    private static Hashtable<String, SoccerTeam> teamDatabase;

    //Create a default database for the game with two teams of five
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


    //Return a list of team names in the form of an array list
    public static ArrayList<String> getListOfTeamNames() {
        ArrayList<String> teamNames = new ArrayList<>();
        Enumeration<String> teams = teamDatabase.keys();
        while (teams.hasMoreElements()) {
            teamNames.add(teams.nextElement());
        }
        return teamNames;
    }

    //Return number of teams
    public static int getNumberOfTeams() {
        return (teamDatabase.size());
    }

    //Return number of players on a team
    public static int getNumberOfPlayers(String teamName) {
        SoccerTeam team = teamDatabase.get(teamName);
        ArrayList<SoccerPlayer> players = team.getPlayers();
        return players.size();
    }

    //Return an array list of players on a team
    public static ArrayList<SoccerPlayer> getPlayers(String teamName) {
        SoccerTeam team = teamDatabase.get(teamName);
        ArrayList<SoccerPlayer> players = team.getPlayers();
        return players;
    }

    //Return the playerDatabase
    public static Hashtable<String, SoccerPlayer> getPlayers() {
        return playerDatabase;
    }

    //Return a Soccer Player from the database
    public static SoccerPlayer getPlayer(String playerName) {
        SoccerPlayer player = playerDatabase.get(playerName);
        return player;
    }

    //Return a Soccer Team from the database
    public static SoccerTeam getTeam(String teamName) {
        SoccerTeam team = teamDatabase.get(teamName);
        return team;
    }

    //Return the team logo of the given team
    public static int returnTeamLogo(String teamName){
        SoccerTeam team = teamDatabase.get(teamName);
        return team.getTeamLogo();
    }

    //Add the given team to the database
    public static void addTeam(String teamName){
        SoccerTeam newTeam = new SoccerTeam(teamName, R.drawable.error_page_logo);
        teamDatabase.put(teamName, newTeam);
    }

    //Add a player to the given team and create the player in the database
    public static void addPlayer(String playerName, String teamName){
        SoccerPlayer newPlayer;
        playerName = playerName.trim();
        if (playerName.indexOf(" ") == -1){
            newPlayer = new SoccerPlayer(playerName, "", teamName,"forward",R.drawable.error_page_logo);
            playerName = playerName + " ";
        }else{
            newPlayer = new SoccerPlayer(playerName.substring(0,playerName.indexOf(" ")), playerName.substring(playerName.indexOf(" ") + 1), teamName,"forward",R.drawable.error_page_logo);
        }
        playerDatabase.put(playerName, newPlayer);
        SoccerTeam team = teamDatabase.get(teamName);
        team.addPlayer(newPlayer);
    }

    //Return true if the team is already in the database
    public static boolean isTeam(String teamName){
        return teamDatabase.containsKey(teamName);
    }

    //Return true if the player is already in the database
    public static boolean isPlayer(String playerName){
        return playerDatabase.containsKey(playerName);
    }

    //Add the existing player to the given team and remove them from the team they were on
    public static void addPlayerToTeam(String teamName, String playerName){
        SoccerTeam team = teamDatabase.get(teamName);
        SoccerPlayer player = playerDatabase.get(playerName);
        String oldTeamName = player.getTeamName();
        SoccerTeam oldTeam = teamDatabase.get(oldTeamName);
        if (team.addPlayer(player)){
            player.setTeamName(teamName);
            oldTeam.removePlayer(player);
        }
    }

    //Update the player stats and corresponding team stats when they are changed
    public static void updatePlayer(String name, SoccerPlayer player){
        playerDatabase.put(name, player);
        SoccerTeam team = teamDatabase.get(player.getTeamName());
        team.addPlayer(player);

        //Change goals scored
        int oldGoals = Integer.parseInt(team.getGoalsScored());
        int newGoals = Integer.parseInt(player.getGoalsScored());
        String goals = "" + (oldGoals + newGoals);
        team.increaseGoalsScored(goals);

        //Change goals saved
        int oldGoalsSaved = Integer.parseInt(team.getGoalsSaved());
        int newGoalsSaved = Integer.parseInt(player.getGoalsSaved());
        String goalsSaved = "" + (oldGoalsSaved + newGoalsSaved);
        team.increaseGoalsSaved(goalsSaved);

        //Change assists
        int oldAssists = Integer.parseInt(team.getAssists());
        int newAssists = Integer.parseInt(player.getAssists());
        String Assists = "" + (oldAssists + newAssists);
        team.increaseAssists(Assists);

        //Change fouls
        int oldFouls = Integer.parseInt(team.getFouls());
        int newFouls = Integer.parseInt(player.getFouls());
        String fouls = "" + (oldFouls + newFouls);
        team.increaseFouls(fouls);

        //Change yellow cards
        int oldYellow = Integer.parseInt(team.getYellowCards());
        int newYellow = Integer.parseInt(player.getYellowCards());
        String yellow = "" + (oldYellow + newYellow);
        team.increaseYellowCards(yellow);

        //Change red cards
        int oldRed = Integer.parseInt(team.getRedCards());
        int newRed = Integer.parseInt(player.getRedCards());
        String red = "" + (oldRed + newRed);
        team.increaseRedCards(red);

        teamDatabase.put(player.getTeamName(), team);
    }
}
