package com.example.administrator.fantasysoccerapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

/**
 * Created by Jordan Goldey
 * Last edited 9/29/2015
 * This activity shows the stats of one player at a time. The stats can be edited and saved. The
 * player that is being viewed can be moved to a new team. This activity returns to the first
 * activity.
 * */
public class second_activity extends AppCompatActivity implements View.OnClickListener{

    //Declare all widgets in the activity
    Spinner playerDropdown;
    Button team1;
    Button team2;
    Button team3;
    Button team4;
    Button team5;
    Button save;
    Button goToFirstActivity;
    EditText goals;
    EditText goalsSaved;
    EditText assists;
    EditText fouls;
    EditText yellowCards;
    EditText redCards;
    EditText posistion;
    ImageView image;
    ImageView teamLogo;
    TextView teamName;

    //Variables to know what player is currently selected and the list of players
    String playerSelected;
    List<String> playerList;

    @Override
    //Used to create the layout when it is called
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_activity);

        //Intilize all widgets
        playerDropdown = (Spinner) findViewById(R.id.playerDropDown);
        team1 = (Button) findViewById(R.id.team1ButtonSecond);
        team2 = (Button) findViewById(R.id.team2ButtonSecond);
        team3 = (Button) findViewById(R.id.team3ButtonSecond);
        team4 = (Button) findViewById(R.id.team4ButtonSecond);
        team5 = (Button) findViewById(R.id.team5ButtonSecond);
        save = (Button) findViewById(R.id.saveButton);
        goToFirstActivity = (Button) findViewById(R.id.leaveSecondActivity);
        goals = (EditText) findViewById(R.id.goalsValueSecond);
        goalsSaved = (EditText) findViewById(R.id.goalsSavedValueSecond);
        assists = (EditText) findViewById(R.id.assistsValueSecond);
        fouls = (EditText) findViewById(R.id.foulsValueSecond);
        yellowCards = (EditText) findViewById(R.id.yellowValueSecond);
        redCards = (EditText) findViewById(R.id.redValueSecond);
        posistion = (EditText) findViewById(R.id.posistionValueSecond);
        image = (ImageView) findViewById(R.id.playerImage);
        teamLogo = (ImageView) findViewById(R.id.secondActivityTeamLogo);
        teamName = (TextView) findViewById(R.id.teamNameSecond);

        //Set on click listeners for all buttons
        team1.setOnClickListener(this);
        team2.setOnClickListener(this);
        team3.setOnClickListener(this);
        team4.setOnClickListener(this);
        team5.setOnClickListener(this);
        save.setOnClickListener(this);
        goToFirstActivity.setOnClickListener(this);

        //Calls the methods to populate the dropdown and set the first player
        populatePlayerDropdown();
    }//onCreate

    //Sets all stats relating to the player selected as well as the images of the player and the
    //team logo and name
    private void showPlayerData(String fullName) {
        SoccerPlayer player = SoccerDB.getPlayer(fullName);
        //Set all stats with the corresponding player data
        goals.setText(Integer.toString(player.getGoalsScored()));
        goalsSaved.setText(Integer.toString(player.getGoalsSaved()));
        assists.setText(Integer.toString(player.getAssists()));
        fouls.setText(Integer.toString(player.getFouls()));
        yellowCards.setText(Integer.toString(player.getYellowCards()));
        redCards.setText(Integer.toString(player.getRedCards()));
        posistion.setText(player.getPosition());

        //Sets the player image and team name
        image.setBackgroundResource(player.getPicture());
        teamName.setText(player.getTeamName());

        //Sets team buttons for all teams that the player is not already on and the team logo
        if (player.getTeamName().compareTo("") == 1){
            ArrayList<String> teams = SoccerDB.getListOfTeamNames();
            setTeamButtons(teams, SoccerDB.getNumberOfTeams());
        }else {
            SoccerTeam team = SoccerDB.getTeam(player.getTeamName());
            teamLogo.setBackgroundResource(team.getTeamLogo());
            ArrayList<String> teams = SoccerDB.getListOfTeamNames();
            teams.remove(player.getTeamName());
            setTeamButtons(teams, SoccerDB.getNumberOfTeams() - 1);
        }
    }//showPlayerData

    //Function to set the team buttons to all teams that the current player is not on. If there are
    //less than five teams the buttons are hidden
    private void setTeamButtons(ArrayList<String> teams, int numTeams) {
        if (numTeams == 1) {
            team1.setText(teams.get(0));
            team2.setVisibility(View.GONE);
            team3.setVisibility(View.GONE);
            team4.setVisibility(View.GONE);
            team5.setVisibility(View.GONE);
        }else if (numTeams == 2){
            team1.setText(teams.get(0));
            team2.setText(teams.get(1));
            team3.setVisibility(View.GONE);
            team4.setVisibility(View.GONE);
            team5.setVisibility(View.GONE);
        }else if(numTeams == 3){
            team1.setText(teams.get(0));
            team2.setText(teams.get(1));
            team3.setText(teams.get(2));
            team4.setVisibility(View.GONE);
            team5.setVisibility(View.GONE);
        }else if(numTeams == 4){
            team1.setText(teams.get(0));
            team2.setText(teams.get(1));
            team3.setText(teams.get(2));
            team4.setText(teams.get(3));
            team5.setVisibility(View.GONE);
        }else{
            team1.setText(teams.get(0));
            team2.setText(teams.get(1));
            team3.setText(teams.get(2));
            team4.setText(teams.get(3));
            team5.setText(teams.get(4));
        }
    }//setTeamButtons

    //Populates the player dropdown with a list of all the players
    private void populatePlayerDropdown() {

        //Creates an array list of players
        Hashtable<String, SoccerPlayer> playersDatabase = SoccerDB.getPlayers();
        Enumeration<SoccerPlayer> players = playersDatabase.elements();
        playerList = new ArrayList<>();
        while (players.hasMoreElements()){
            SoccerPlayer player = players.nextElement();
            playerList.add(player.getFirstName() + " " + player.getLastName());
        }

        //Makes the array list into the proper format for dropdowna
        ArrayAdapter<String> adp = new ArrayAdapter<>
                (this,android.R.layout.simple_dropdown_item_1line, playerList);
        playerDropdown.setAdapter(adp);

        //When an item is selected in the dropdown the player selected variable changes and the
        //player data changes to match selection
        playerDropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                Toast.makeText(getBaseContext(), playerList.get(arg2),
                        Toast.LENGTH_SHORT).show();
                showPlayerData(playerList.get(arg2));
                playerSelected = playerList.get(arg2);
            }

            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });
    }//populatePlayerDropdown

    @Override
    //Function to handle all button clicks
    public void onClick(View view) {
        //If one of the team buttons is click change the player to that team if there are no
        //restrictions
        if (view == team1){
            SoccerDB.addPlayerToTeam(team1.getText().toString(), playerSelected);
            showPlayerData(playerSelected);
        }else if (view == team2){
            SoccerDB.addPlayerToTeam(team2.getText().toString(), playerSelected);
            showPlayerData(playerSelected);
        }else if (view == team3){
            SoccerDB.addPlayerToTeam(team3.getText().toString(), playerSelected);
            showPlayerData(playerSelected);
        }else if (view == team4){
            SoccerDB.addPlayerToTeam(team4.getText().toString(), playerSelected);
            showPlayerData(playerSelected);
        }else if (view == team5){
            SoccerDB.addPlayerToTeam(team5.getText().toString(), playerSelected);
            showPlayerData(playerSelected);
        }else if (view == goToFirstActivity){
            //If Go Back Button is pushed close activity
            finish();
        }else if (view == save){
            //if the save button is clicked save all the data, this involves updating the player
            //database and removing and re-adding the player to the corresponding team as well as
            //updating the team stats.
            SoccerPlayer player = SoccerDB.getPlayer(playerSelected);
            SoccerTeam team = SoccerDB.getTeam(player.getTeamName());
            team.removePlayer(player);
            player.increaseGoalsScored(Integer.parseInt(goals.getText().toString()));
            player.increaseGoalsSaved(Integer.parseInt(goalsSaved.getText().toString()));
            player.increaseAssists(Integer.parseInt(assists.getText().toString()));
            player.increaseFouls(Integer.parseInt(fouls.getText().toString()));
            player.increaseYellowCards(Integer.parseInt(yellowCards.getText().toString()));
            player.increaseRedCards(Integer.parseInt(redCards.getText().toString()));
            player.setPosition(posistion.getText().toString());
            SoccerDB.updatePlayer(playerSelected,player);
        }
    }//onClick
}//second_activity
