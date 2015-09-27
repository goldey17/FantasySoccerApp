package com.example.administrator.fantasysoccerapp;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Hashtable;


public class first_activity extends AppCompatActivity implements View.OnClickListener{

    //Initialize all Buttons
    Button addPlayer;
    Button removePlayer;
    Button teamStats;
    Button player1Stats;
    Button player2Stats;
    Button player3Stats;
    Button player4Stats;
    Button player5Stats;
    Button playGame;
    Button playerMenu;
    Button team1Name;
    Button team2Name;
    Button team3Name;
    Button team4Name;
    Button team5Name;
    Button saveTeam;
    Button savePlayer;

    //Initialize all textviews
    TextView teamName;
    TextView player1Name;
    TextView player2Name;
    TextView player3Name;
    TextView player4Name;
    TextView player5Name;

    //Initialize all pictures
    ImageView player1Image;
    ImageView player2Image;
    ImageView player3Image;
    ImageView player4Image;
    ImageView player5Image;
    ImageView teamLogo;

    //Initialize edit text
    EditText editableTeamName;
    EditText editablePlayerName;



    @Override
    //Used for all button clicks associated with a popup
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_activity);

        //Initialize buttons
        teamStats =(Button)findViewById(R.id.teamStatsButton);
        player1Stats = (Button)findViewById(R.id.player1StatsButton);
        player2Stats = (Button)findViewById(R.id.player2StatsButton);
        player3Stats = (Button)findViewById(R.id.player3StatsButton);
        player4Stats = (Button)findViewById(R.id.player4StatsButton);
        player5Stats = (Button)findViewById(R.id.player5StatsButton);
        team1Name = (Button)findViewById(R.id.team1name);
        team2Name = (Button)findViewById(R.id.team2name);
        team3Name = (Button)findViewById(R.id.team3name);
        team4Name = (Button)findViewById(R.id.team4name);
        team5Name = (Button)findViewById(R.id.team5name);
        playGame = (Button)findViewById(R.id.goToThirdActivity);
        playerMenu = (Button)findViewById(R.id.goToSecondActivity);
        saveTeam = (Button)findViewById(R.id.saveTeam);
        savePlayer = (Button)findViewById(R.id.savePlayer);

        //Initialize text
        teamName = (TextView)findViewById(R.id.teamName);
        player1Name = (TextView)findViewById(R.id.player1Name);
        player2Name = (TextView)findViewById(R.id.player2Name);
        player3Name = (TextView)findViewById(R.id.player3Name);
        player4Name = (TextView)findViewById(R.id.player4Name);
        player5Name = (TextView)findViewById(R.id.player5Name);

        //Initialize all picture
        player1Image = (ImageView)findViewById(R.id.player1Image);
        player2Image = (ImageView)findViewById(R.id.player2Image);
        player3Image = (ImageView)findViewById(R.id.player3Image);
        player4Image = (ImageView)findViewById(R.id.player4Image);
        player5Image = (ImageView)findViewById(R.id.player5Image);
        teamLogo = (ImageView)findViewById(R.id.teamLogo);

        //Initialize edit text
        editableTeamName = (EditText)findViewById(R.id.enterTeamName);
        editablePlayerName = (EditText)findViewById(R.id.enterPlayerName);

        //Set on click listeners for the popup generating buttons
        teamStats.setOnClickListener(this);
        player1Stats.setOnClickListener(this);
        player2Stats.setOnClickListener(this);
        player3Stats.setOnClickListener(this);
        player4Stats.setOnClickListener(this);
        player5Stats.setOnClickListener(this);
        playGame.setOnClickListener(this);
        playerMenu.setOnClickListener(this);
        saveTeam.setOnClickListener(this);
        savePlayer.setOnClickListener(this);

        //Set on click listeners for team buttons
        team1Name.setOnClickListener(this);
        team2Name.setOnClickListener(this);
        team3Name.setOnClickListener(this);
        team4Name.setOnClickListener(this);
        team5Name.setOnClickListener(this);

        //Create default database
        SoccerDB.createDefaultDatabase();

        //Set all the data on the page
        setTeamButtons();
        setPlayerInfo("Kittenz");

    }//onCreate

    //Sets all the information relating to the  player on the screen, including hiding the ones that
    //aren't needed
    private void setPlayerInfo(String tempTeamName) {
        teamLogo.setBackgroundResource(SoccerDB.returnTeamLogo(tempTeamName));
        teamName.setText(tempTeamName);
        ArrayList<SoccerPlayer> players = SoccerDB.getPlayers(tempTeamName);
        if (SoccerDB.getNumberOfPlayers(tempTeamName) == 0) {
            player1Name.setVisibility(View.GONE);
            player2Name.setVisibility(View.GONE);
            player3Name.setVisibility(View.GONE);
            player4Name.setVisibility(View.GONE);
            player5Name.setVisibility(View.GONE);
            player1Image.setVisibility(View.GONE);
            player2Image.setVisibility(View.GONE);
            player3Image.setVisibility(View.GONE);
            player4Image.setVisibility(View.GONE);
            player5Image.setVisibility(View.GONE);
            player1Stats.setVisibility(View.GONE);
            player2Stats.setVisibility(View.GONE);
            player3Stats.setVisibility(View.GONE);
            player4Stats.setVisibility(View.GONE);
            player5Stats.setVisibility(View.GONE);
        }else if (SoccerDB.getNumberOfPlayers(tempTeamName) == 1){
            String player1 = (players.get(0).getFirstName() + " " + players.get(0).getLastName());
            player1Name.setText(player1);
            player2Name.setVisibility(View.GONE);
            player3Name.setVisibility(View.GONE);
            player4Name.setVisibility(View.GONE);
            player5Name.setVisibility(View.GONE);
            int player1Im = (players.get(0).getPicture());
            player1Image.setBackgroundResource(player1Im);
            player2Image.setVisibility(View.GONE);
            player3Image.setVisibility(View.GONE);
            player4Image.setVisibility(View.GONE);
            player5Image.setVisibility(View.GONE);
            player2Stats.setVisibility(View.GONE);
            player3Stats.setVisibility(View.GONE);
            player4Stats.setVisibility(View.GONE);
            player5Stats.setVisibility(View.GONE);
        }else if (SoccerDB.getNumberOfPlayers(tempTeamName) == 2) {
            String player1 = (players.get(0).getFirstName() + " " + players.get(0).getLastName());
            String player2 = (players.get(1).getFirstName() + " " + players.get(1).getLastName());
            player1Name.setText(player1);
            player2Name.setText(player2);
            player3Name.setVisibility(View.GONE);
            player4Name.setVisibility(View.GONE);
            player5Name.setVisibility(View.GONE);
            int player1Im = (players.get(0).getPicture());
            int player2Im = (players.get(1).getPicture());
            player1Image.setBackgroundResource(player1Im);
            player2Image.setBackgroundResource(player2Im);
            player3Image.setVisibility(View.GONE);
            player4Image.setVisibility(View.GONE);
            player5Image.setVisibility(View.GONE);
            player3Stats.setVisibility(View.GONE);
            player4Stats.setVisibility(View.GONE);
            player5Stats.setVisibility(View.GONE);
        }else if(SoccerDB.getNumberOfPlayers(tempTeamName) == 3){
            String player1 = (players.get(0).getFirstName() + " " + players.get(0).getLastName());
            String player2 = (players.get(1).getFirstName() + " " + players.get(1).getLastName());
            String player3 = (players.get(2).getFirstName() + " " + players.get(2).getLastName());
            player1Name.setText(player1);
            player2Name.setText(player2);
            player3Name.setText(player3);
            player4Name.setVisibility(View.GONE);
            player5Name.setVisibility(View.GONE);
            int player1Im = (players.get(0).getPicture());
            int player2Im = (players.get(1).getPicture());
            int player3Im = (players.get(2).getPicture());
            player1Image.setBackgroundResource(player1Im);
            player2Image.setBackgroundResource(player2Im);
            player3Image.setBackgroundResource(player3Im);
            player4Image.setVisibility(View.GONE);
            player5Image.setVisibility(View.GONE);
            player4Stats.setVisibility(View.GONE);
            player5Stats.setVisibility(View.GONE);
        }else if(SoccerDB.getNumberOfPlayers(tempTeamName) == 4){
            String player1 = (players.get(0).getFirstName() + " " + players.get(0).getLastName());
            String player2 = (players.get(1).getFirstName() + " " + players.get(1).getLastName());
            String player3 = (players.get(2).getFirstName() + " " + players.get(2).getLastName());
            String player4 = (players.get(3).getFirstName() + " " + players.get(3).getLastName());
            player1Name.setText(player1);
            player2Name.setText(player2);
            player3Name.setText(player3);
            player4Name.setText(player4);
            player5Name.setVisibility(View.GONE);
            int player1Im = (players.get(0).getPicture());
            int player2Im = (players.get(1).getPicture());
            int player3Im = (players.get(2).getPicture());
            int player4Im = (players.get(3).getPicture());
            player1Image.setBackgroundResource(player1Im);
            player2Image.setBackgroundResource(player2Im);
            player3Image.setBackgroundResource(player3Im);
            player4Image.setBackgroundResource(player4Im);
            player5Image.setVisibility(View.GONE);
            player5Stats.setVisibility(View.GONE);
        }else{
            String player1 = (players.get(0).getFirstName() + " " + players.get(0).getLastName());
            String player2 = (players.get(1).getFirstName() + " " + players.get(1).getLastName());
            String player3 = (players.get(2).getFirstName() + " " + players.get(2).getLastName());
            String player4 = (players.get(3).getFirstName() + " " + players.get(3).getLastName());
            String player5 = (players.get(4).getFirstName() + " " + players.get(4).getLastName());
            player1Name.setText(player1);
            player2Name.setText(player2);
            player3Name.setText(player3);
            player4Name.setText(player4);
            player5Name.setText(player5);
            int player1Im = (players.get(0).getPicture());
            int player2Im = (players.get(1).getPicture());
            int player3Im = (players.get(2).getPicture());
            int player4Im = (players.get(3).getPicture());
            int player5Im = (players.get(4).getPicture());
            player1Image.setBackgroundResource(player1Im);
            player2Image.setBackgroundResource(player2Im);
            player3Image.setBackgroundResource(player3Im);
            player4Image.setBackgroundResource(player4Im);
            player5Image.setBackgroundResource(player5Im);
        }
    }

    //Sets all the team buttons on the screen if there is less than 5 teams the team buttons
    //are hidden
    private void setTeamButtons() {
        ArrayList<String> teams = SoccerDB.getListOfTeamNames();
        if (SoccerDB.getNumberOfTeams() == 2){
            team1Name.setText(teams.get(0));
            team2Name.setText(teams.get(1));
            team3Name.setVisibility(View.GONE);
            team4Name.setVisibility(View.GONE);
            team5Name.setVisibility(View.GONE);
        }else if(SoccerDB.getNumberOfTeams() == 3){
            team1Name.setText(teams.get(0));
            team2Name.setText(teams.get(1));
            team3Name.setText(teams.get(2));
            team4Name.setVisibility(View.GONE);
            team5Name.setVisibility(View.GONE);
        }else if(SoccerDB.getNumberOfTeams() == 4){
            team1Name.setText(teams.get(0));
            team2Name.setText(teams.get(1));
            team3Name.setText(teams.get(2));
            team4Name.setText(teams.get(3));
            team5Name.setVisibility(View.GONE);
        }else{
            team1Name.setText(teams.get(0));
            team2Name.setText(teams.get(1));
            team3Name.setText(teams.get(2));
            team4Name.setText(teams.get(3));
            team5Name.setText(teams.get(4));
        }
    }

    @Override
    public void onClick(View view) {
        if (view == playGame) {
            startActivity(new Intent(first_activity.this, third_activity.class));
        }else if (view == playerMenu) {
            startActivity(new Intent(first_activity.this, second_activity.class));
        }else if (view == team1Name){
            setPlayerInfo((String) team1Name.getText());
        }else if (view == team2Name){
            setPlayerInfo((String)team2Name.getText());
        }else if (view == team3Name){
            setPlayerInfo((String) team3Name.getText());
        }else if (view == team4Name){
            setPlayerInfo((String) team4Name.getText());
        }else if (view == team5Name){
            setPlayerInfo((String) team5Name.getText());
        }else if (view == saveTeam){
            String str = editableTeamName.getText().toString();
            SoccerDB.addTeam(str);
            setTeamButtons();
            setPlayerInfo(str);
        }else if (view == savePlayer) {
            String str = editablePlayerName.getText().toString();
            SoccerDB.addPlayer(str, teamName.getText().toString());
            setPlayerInfo(teamName.getText().toString());
        }else {
            LayoutInflater layoutInflater = (LayoutInflater) getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);

            //Opens up the type of popup corresponding to the button clicked
            final View popupView = layoutInflater.inflate(R.layout.stats_popup, null);

            //Opens up the popup at the center of the screen
            final PopupWindow popupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            popupWindow.showAtLocation(popupView, Gravity.CENTER, 0, 0);

            //Populates the player and team stats popup
            if (view == player1Stats || view == player2Stats || view == player3Stats ||
                    view == player4Stats || view == player5Stats || view == teamStats) {
                String playerName = "";
                String tempTeamName = "";
                if (view == player1Stats){
                    playerName = (String) player1Name.getText();
                }else if (view == player2Stats){
                    playerName = (String) player2Name.getText();
                }else if (view == player3Stats){
                    playerName = (String) player3Name.getText();
                }else if (view == player4Stats){
                    playerName = (String) player4Name.getText();
                }else if (view == player5Stats){
                    playerName = (String) player5Name.getText();
                }else{
                    tempTeamName = (String) teamName.getText();
                }

                EditText goals = (EditText)popupView.findViewById(R.id.goalsValue);
                EditText goalssaved = (EditText)popupView.findViewById(R.id.goalsSavedValue);
                EditText assists = (EditText)popupView.findViewById(R.id.assistsValue);
                EditText fouls = (EditText)popupView.findViewById(R.id.foulsValue);
                EditText yellowcards = (EditText)popupView.findViewById(R.id.yellowCardsValue);
                EditText redcards = (EditText)popupView.findViewById(R.id.redCardsValue);
                EditText position = (EditText)popupView.findViewById(R.id.posistionValue);
                TextView title = (TextView)popupView.findViewById(R.id.statsPopupTitle);
                if (tempTeamName.equals("")){
                    SoccerPlayer player = SoccerDB.getPlayer(playerName);
                    title.setText(playerName);
                    goals.setText(player.getGoalsScored());
                    goalssaved.setText(player.getGoalsSaved());
                    assists.setText(player.getAssists());
                    fouls.setText(player.getFouls());
                    yellowcards.setText(player.getYellowCards());
                    redcards.setText(player.getRedCards());
                    position.setText(player.getPosition());
                }else{
                    SoccerTeam team = SoccerDB.getTeam(tempTeamName);
                    title.setText(tempTeamName);
                    goals.setText(team.getGoalsScored());
                    goalssaved.setText(team.getGoalsSaved());
                    assists.setText(team.getAssists());
                    fouls.setText(team.getFouls());
                    yellowcards.setText(team.getYellowCards());
                    redcards.setText(team.getRedCards());
                    position.setVisibility(view.GONE);
                    TextView posistionLabel = (TextView)popupView.findViewById(R.id.posistionText);
                    posistionLabel.setVisibility(view.GONE);
                }
            }

            //Dismisses the popup when the cancel button is clicked
            Button btnDismiss = (Button) popupView.findViewById(R.id.statsPopupCancel);
            btnDismiss.setOnClickListener(new Button.OnClickListener() {

                @Override
                public void onClick(View v) {
                    popupWindow.dismiss();
                }
            });
        }
    }//onClick



}//first_activity
