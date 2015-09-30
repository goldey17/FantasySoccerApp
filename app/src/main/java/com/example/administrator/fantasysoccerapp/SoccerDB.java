package com.example.administrator.fantasysoccerapp;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Random;

/**
 * Created by Jordan Goldey
 * Last edited 9/29/2015
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
        int oldGoals = team.getGoalsScored();
        int newGoals = player.getGoalsScored();
        int goals = (oldGoals + newGoals);
        team.increaseGoalsScored(goals);

        //Change goals saved
        int oldGoalsSaved = team.getGoalsSaved();
        int newGoalsSaved = player.getGoalsSaved();
        int goalsSaved = (oldGoalsSaved + newGoalsSaved);
        team.increaseGoalsSaved(goalsSaved);

        //Change assists
        int oldAssists = team.getAssists();
        int newAssists = player.getAssists();
        int Assists = (oldAssists + newAssists);
        team.increaseAssists(Assists);

        //Change fouls
        int oldFouls = team.getFouls();
        int newFouls = player.getFouls();
        int fouls = (oldFouls + newFouls);
        team.increaseFouls(fouls);

        //Change yellow cards
        int oldYellow = team.getYellowCards();
        int newYellow = player.getYellowCards();
        int yellow = (oldYellow + newYellow);
        team.increaseYellowCards(yellow);

        //Change red cards
        int oldRed = team.getRedCards();
        int newRed = player.getRedCards();
        int red = (oldRed + newRed);
        team.increaseRedCards(red);

        teamDatabase.put(player.getTeamName(), team);
    }

    //Function to decide the winner of a game when played and update stats for the team and players
    public static String chooseWinner(String teamOne, String teamTwo) {

        //Declare the teams of the winning team and losing team
        SoccerTeam winningTeam = null;
        SoccerTeam losingTeam  = null;

        //Declare and initialize variables to track which team is better
        int teamOneBetter = 0;
        int teamTwoBetter = 0;

        //Create a temporary team for both to get the stats
        SoccerTeam tempTeamOne = getTeam(teamOne);
        SoccerTeam tempTeamTwo = getTeam(teamTwo);

        //Figure out who has more goals scored, if equal do nothing
        if (tempTeamOne.getGoalsScored() > tempTeamTwo.getGoalsScored()){
            teamOneBetter++;
        }else if (tempTeamTwo.getGoalsScored() > tempTeamOne.getGoalsScored()){
            teamTwoBetter++;
        }

        //Figure out who has more goals saved, if equal do nothing
        if (tempTeamOne.getGoalsSaved() > tempTeamTwo.getGoalsSaved()){
            teamOneBetter++;
        }else if (tempTeamTwo.getGoalsSaved() > tempTeamOne.getGoalsSaved()){
            teamTwoBetter++;
        }

        //Figure out who has more fouls, if equal do nothing
        if (tempTeamOne.getFouls() > tempTeamTwo.getFouls()){
            teamOneBetter++;
        }else if (tempTeamTwo.getFouls() > tempTeamOne.getFouls()){
            teamTwoBetter++;
        }

        //Figure out who has more yellow cards, if equal do nothing
        if (tempTeamOne.getYellowCards() > tempTeamTwo.getYellowCards()){
            teamOneBetter++;
        }else if (tempTeamTwo.getYellowCards() > tempTeamOne.getYellowCards()){
            teamTwoBetter++;
        }

        //Figure out who has more red cards, if equal do nothing
        if (tempTeamOne.getRedCards() > tempTeamTwo.getRedCards()){
            teamOneBetter++;
        }else if (tempTeamTwo.getRedCards() > tempTeamOne.getRedCards()){
            teamTwoBetter++;
        }

        //Determine which team is better, if equal do nothing
        if (teamOneBetter > teamTwoBetter){
            winningTeam = getTeam(teamOne);
            losingTeam = getTeam(teamTwo);
        }else if (teamTwoBetter > teamOneBetter){
            winningTeam = getTeam(teamTwo);
            losingTeam = getTeam(teamOne);
        }

        //Decide a winner randomly if no team has stats
        if (winningTeam == null || losingTeam == null){
            double randomNumber = Math.random();
            if (randomNumber < 0.5) {
                winningTeam = getTeam(teamOne);
                losingTeam = getTeam(teamTwo);
            } else {
                winningTeam = getTeam(teamTwo);
                losingTeam = getTeam(teamOne);
            }
        }

        //Get list of both teams players
        ArrayList<SoccerPlayer> winningTeamPlayers = winningTeam.getPlayers();
        ArrayList<SoccerPlayer> losingTeamPlayers = losingTeam.getPlayers();

        //Create a random number generator
        Random randomGenerator = new Random();

        //Find the goalie of each of the teams if no goalie just use random player
        int randomPlayer = randomGenerator.nextInt(4);
        SoccerPlayer goalie = winningTeamPlayers.get(randomPlayer);
        SoccerPlayer goalie2 = losingTeamPlayers.get(randomPlayer);
        for (int i = 0; i < 5; i++){
            SoccerPlayer tempPlayer = winningTeamPlayers.get(i);
            SoccerPlayer tempPlayer2 = losingTeamPlayers.get(i);
            if (tempPlayer.getPosition().equals("goalie")){
                goalie = tempPlayer;
            }
            if (tempPlayer2.getPosition().equals("goalie")){
                goalie2 = tempPlayer2;
            }
        }

        //Give the goalies of each team a random amount of saves
        int randomGoalsSaved = randomGenerator.nextInt(10);
        int randomGoalsSaved2 = randomGenerator.nextInt(10);
        winningTeam.removePlayer(goalie);
        goalie.increaseGoalsSaved(randomGoalsSaved + goalie.getGoalsSaved());
        playerDatabase.put(goalie.getFirstName() + " " + goalie.getLastName(), goalie);
        winningTeam.addPlayer(goalie);
        winningTeam.increaseGoalsSaved(randomGoalsSaved + winningTeam.getGoalsSaved());
        teamDatabase.put(winningTeam.getTeamName(), winningTeam);

        losingTeam.removePlayer(goalie2);
        goalie2.increaseGoalsSaved(randomGoalsSaved2 + goalie2.getGoalsSaved());
        playerDatabase.put(goalie2.getFirstName() + " " + goalie2.getLastName(), goalie2);
        losingTeam.addPlayer(goalie2);
        losingTeam.increaseGoalsSaved(randomGoalsSaved2 + losingTeam.getGoalsSaved());
        teamDatabase.put(losingTeam.getTeamName(), losingTeam);


        //Randomly add goals to players stats and team stats of the winning team, the goalie is not
        //allowed to score goals
        int goalsScored = 1;
        for (int i = 0; i < 5; i++){
            int randomGoalsScored = randomGenerator.nextInt(1);
            if (randomGoalsScored == 1){
                goalsScored ++;
                SoccerPlayer tempPlayer = winningTeamPlayers.get(i);
                if (!tempPlayer.equals(goalie)){
                    winningTeam.removePlayer(tempPlayer);
                    tempPlayer.increaseGoalsScored(1 + tempPlayer.getGoalsScored());
                    playerDatabase.put(tempPlayer.getFirstName() + " " + tempPlayer.getLastName(), tempPlayer);
                    winningTeam.addPlayer(tempPlayer);
                    winningTeam.increaseGoalsScored(1 + winningTeam.getGoalsScored());
                    teamDatabase.put(winningTeam.getTeamName(), winningTeam);
                }
            }
        }

        //In case no goals were added add one for sure again making sure the player isnt the goalie
        randomPlayer = randomGenerator.nextInt(4);
        SoccerPlayer player = winningTeamPlayers.get(randomPlayer);
        while (player.equals(goalie)){
            randomPlayer = randomGenerator.nextInt(4);
            player = winningTeamPlayers.get(randomPlayer);
        }
        winningTeam.removePlayer(player);
        player.increaseGoalsScored(1 + player.getGoalsScored());
        playerDatabase.put(player.getFirstName() + " " + player.getLastName(), player);
        winningTeam.addPlayer(player);
        winningTeam.increaseGoalsScored(1 + winningTeam.getGoalsScored());
        teamDatabase.put(winningTeam.getTeamName(), winningTeam);

        //If the winning team scored more than two goals have the losing team have two less goals
        // than the winning team, again the goalie cannot score
        if (goalsScored > 2){
            for (int i = 0; i == (goalsScored - 2); i++){
                int rand = randomGenerator.nextInt(4);
                SoccerPlayer tempPlayer = losingTeamPlayers.get(rand);
                while(player.equals(goalie2)){
                    rand = randomGenerator.nextInt(4);
                    tempPlayer = losingTeamPlayers.get(rand);
                }
                losingTeam.removePlayer(tempPlayer);
                tempPlayer.increaseGoalsScored(1 + tempPlayer.getGoalsScored());
                playerDatabase.put(tempPlayer.getFirstName() + " " + tempPlayer.getLastName(), tempPlayer);
                winningTeam.addPlayer(tempPlayer);
                losingTeam.increaseGoalsScored(1 + losingTeam.getGoalsScored());
                teamDatabase.put(losingTeam.getTeamName(), winningTeam);
            }
        }

        //Assign assists to a random player of each team, the number of assists is half of the goals
        for (int i = 0; i == (goalsScored/2); i++){
            int rand = randomGenerator.nextInt(4);
            SoccerPlayer tempPlayer = winningTeamPlayers.get(rand);
            winningTeam.removePlayer(tempPlayer);
            tempPlayer.increaseAssists(1 + tempPlayer.getAssists());
            playerDatabase.put(tempPlayer.getFirstName() + " " + tempPlayer.getLastName(), tempPlayer);
            winningTeam.addPlayer(tempPlayer);
            winningTeam.increaseAssists(1 + winningTeam.getAssists());
            teamDatabase.put(winningTeam.getTeamName(), winningTeam);
        }
        if (goalsScored > 2){
            for (int i = 0; i == ((goalsScored - 2)/2); i++){
                int rand = randomGenerator.nextInt(4);
                SoccerPlayer tempPlayer = losingTeamPlayers.get(rand);
                losingTeam.removePlayer(tempPlayer);
                tempPlayer.increaseAssists(1 + tempPlayer.getAssists());
                playerDatabase.put(tempPlayer.getFirstName() + " " + tempPlayer.getLastName(), tempPlayer);
                losingTeam.addPlayer(tempPlayer);
                losingTeam.increaseAssists(1 + losingTeam.getAssists());
                teamDatabase.put(losingTeam.getTeamName(), losingTeam);
            }
        }

        //Assign fouls to a random player each team gets a random number of fouls
        int randomFouls = randomGenerator.nextInt(3);
        for (int i = 0; i == randomFouls; i++){
            int rand = randomGenerator.nextInt(4);
            SoccerPlayer tempPlayer = winningTeamPlayers.get(rand);
            winningTeam.removePlayer(tempPlayer);
            tempPlayer.increaseFouls(1 + tempPlayer.getFouls());
            playerDatabase.put(tempPlayer.getFirstName() + " " + tempPlayer.getLastName(), tempPlayer);
            winningTeam.addPlayer(tempPlayer);
            winningTeam.increaseFouls(1 + winningTeam.getFouls());
            teamDatabase.put(winningTeam.getTeamName(), winningTeam);
        }
        randomFouls = randomGenerator.nextInt(3);
        for (int i = 0; i == randomFouls; i++){
            int rand = randomGenerator.nextInt(4);
            SoccerPlayer tempPlayer = losingTeamPlayers.get(rand);
            losingTeam.removePlayer(tempPlayer);
            tempPlayer.increaseFouls(1 + tempPlayer.getFouls());
            playerDatabase.put(tempPlayer.getFirstName() + " " + tempPlayer.getLastName(), tempPlayer);
            losingTeam.addPlayer(tempPlayer);
            losingTeam.increaseFouls(1 + losingTeam.getFouls());
            teamDatabase.put(losingTeam.getTeamName(), losingTeam);
        }

        //Assign red cards to a random player on each team
        double chanceToGetRedCard = Math.random();
        if (chanceToGetRedCard > 0.8){
            int rand = randomGenerator.nextInt(4);
            SoccerPlayer tempPlayer = winningTeamPlayers.get(rand);
            winningTeam.removePlayer(tempPlayer);
            tempPlayer.increaseRedCards(1 + tempPlayer.getRedCards());
            playerDatabase.put(tempPlayer.getFirstName() + " " + tempPlayer.getLastName(), tempPlayer);
            winningTeam.addPlayer(tempPlayer);
            winningTeam.increaseRedCards(1 + winningTeam.getRedCards());
            teamDatabase.put(winningTeam.getTeamName(), winningTeam);
        }
        chanceToGetRedCard = Math.random();
        if (chanceToGetRedCard > 0.8){
            int rand = randomGenerator.nextInt(4);
            SoccerPlayer tempPlayer = losingTeamPlayers.get(rand);
            losingTeam.removePlayer(tempPlayer);
            tempPlayer.increaseRedCards(1 + tempPlayer.getRedCards());
            playerDatabase.put(tempPlayer.getFirstName() + " " + tempPlayer.getLastName(), tempPlayer);
            losingTeam.addPlayer(tempPlayer);
            losingTeam.increaseRedCards(1 + losingTeam.getRedCards());
            teamDatabase.put(losingTeam.getTeamName(), losingTeam);
        }
        //Assign yellow cards to a random player on each team
        double chanceToGetYellowCard = Math.random();
        if (chanceToGetYellowCard > 0.6){
            int rand = randomGenerator.nextInt(4);
            SoccerPlayer tempPlayer = winningTeamPlayers.get(rand);
            winningTeam.removePlayer(tempPlayer);
            tempPlayer.increaseYellowCards(1 + tempPlayer.getYellowCards());
            playerDatabase.put(tempPlayer.getFirstName() + " " + tempPlayer.getLastName(), tempPlayer);
            winningTeam.addPlayer(tempPlayer);
            winningTeam.increaseYellowCards(1 + winningTeam.getYellowCards());
            teamDatabase.put(winningTeam.getTeamName(), winningTeam);
        }
        chanceToGetYellowCard = Math.random();
        if (chanceToGetYellowCard > 0.6){
            int rand = randomGenerator.nextInt(4);
            SoccerPlayer tempPlayer = losingTeamPlayers.get(rand);
            losingTeam.removePlayer(tempPlayer);
            tempPlayer.increaseYellowCards(1 + tempPlayer.getYellowCards());
            playerDatabase.put(tempPlayer.getFirstName() + " " + tempPlayer.getLastName(), tempPlayer);
            losingTeam.addPlayer(tempPlayer);
            losingTeam.increaseYellowCards(1 + losingTeam.getYellowCards());
            teamDatabase.put(losingTeam.getTeamName(), losingTeam);
        }

        //Return the winning team
        return winningTeam.getTeamName();
    }
}
