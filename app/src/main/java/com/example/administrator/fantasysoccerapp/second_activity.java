package com.example.administrator.fantasysoccerapp;

import android.content.DialogInterface;
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

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

public class second_activity extends AppCompatActivity implements View.OnClickListener{

    Spinner playerDropdown;
    List<String> playerList;
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

    String playerSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_activity);
        
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

        team1.setOnClickListener(this);
        team2.setOnClickListener(this);
        team3.setOnClickListener(this);
        team4.setOnClickListener(this);
        team5.setOnClickListener(this);
        save.setOnClickListener(this);


        populatePlayerDropdown();
        showPlayerData("small fry");
    }//onCreate

    private void showPlayerData(String fullName) {
        SoccerPlayer player = SoccerDB.getPlayer(fullName);
        goals.setText(player.getGoalsScored());
        goalsSaved.setText(player.getGoalsSaved());
        assists.setText(player.getAssists());
        fouls.setText(player.getFouls());
        yellowCards.setText(player.getYellowCards());
        redCards.setText(player.getRedCards());
        posistion.setText(player.getPosition());
        image.setBackgroundResource(player.getPicture());
        teamName.setText(player.getTeamName());
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
    }

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
    }

    private void populatePlayerDropdown() {
        Hashtable<String, SoccerPlayer> playersDatabase = SoccerDB.getPlayers();
        Enumeration<SoccerPlayer> players = playersDatabase.elements();

        playerList = new ArrayList<>();

        while (players.hasMoreElements()){
            SoccerPlayer player = players.nextElement();
            playerList.add(player.getFirstName() + " " + player.getLastName());
        }

        ArrayAdapter<String> adp = new ArrayAdapter<>
                (this,android.R.layout.simple_dropdown_item_1line, playerList);
        playerDropdown.setAdapter(adp);

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
    public void onClick(View view) {
        if (view == team1){
            SoccerDB.addPlayerToTeam(team1.getText().toString(), playerSelected);
            showPlayerData(playerSelected);
        }else if (view == team2){
            SoccerDB.addPlayerToTeam(team1.getText().toString(), playerSelected);
            showPlayerData(playerSelected);
        }else if (view == team3){
            SoccerDB.addPlayerToTeam(team1.getText().toString(), playerSelected);
            showPlayerData(playerSelected);
        }else if (view == team4){
            SoccerDB.addPlayerToTeam(team1.getText().toString(), playerSelected);
            showPlayerData(playerSelected);
        }else if (view == team5){
            SoccerDB.addPlayerToTeam(team1.getText().toString(), playerSelected);
            showPlayerData(playerSelected);
        }else if (view == save){
            return;
        }
    }//onClick
}//second_activity
