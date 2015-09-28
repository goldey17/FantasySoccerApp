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

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

public class second_activity extends AppCompatActivity {

    Spinner playerDropdown;
    List<String> playerList;
    Button team1;
    Button team2;
    Button team3;
    Button team4;
    Button team5;
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
        SoccerTeam team = SoccerDB.getTeam(player.getTeamName());
        teamLogo.setBackgroundResource(team.getTeamLogo());
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
            }

            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });
    }//populatePlayerDropdown

}//second_activity
