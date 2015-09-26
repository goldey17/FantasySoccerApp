package com.example.administrator.fantasysoccerapp;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
    Button addTeam;
    Button addPlayer;
    Button removePlayer;
    Button teamStats;
    Button player1Stats;
    Button player2Stats;
    Button player3Stats;
    Button player4Stats;
    Button player5Stats;
    Button playGame;
    Button team1Name;
    Button team2Name;
    Button team3Name;
    Button team4Name;
    Button team5Name;

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


    @Override
    //Used for all button clicks associated with a popup
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_activity);

        //Initialize buttons
        addTeam = (Button)findViewById(R.id.addTeamButton);
        addPlayer = (Button)findViewById(R.id.addPlayerButton);
        removePlayer = (Button)findViewById(R.id.removePlayerButton);
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

        //Set on click listeners for the popup generating buttons
        addTeam.setOnClickListener(this);
        addPlayer.setOnClickListener(this);
        removePlayer.setOnClickListener(this);
        teamStats.setOnClickListener(this);
        player1Stats.setOnClickListener(this);
        player2Stats.setOnClickListener(this);
        player3Stats.setOnClickListener(this);
        player4Stats.setOnClickListener(this);
        player5Stats.setOnClickListener(this);
        playGame.setOnClickListener(this);

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
        setPlayerNames("Kittenz");
        setPlayerImages("Kittenz");
        setTeamLogo("Kittenz");

    }//onCreate

    private void setTeamLogo(String teamName) {
        teamLogo.setBackgroundResource(SoccerDB.returnTeamLogo(teamName));
    }

    private void setPlayerNames(String teamName) {
        ArrayList<SoccerPlayer> players = SoccerDB.getPlayers(teamName);
        if (SoccerDB.getNumberOfPlayers(teamName) == 0) {
            player1Name.setVisibility(View.GONE);
            player2Name.setVisibility(View.GONE);
            player3Name.setVisibility(View.GONE);
            player4Name.setVisibility(View.GONE);
            player5Name.setVisibility(View.GONE);
        }else if (SoccerDB.getNumberOfPlayers(teamName) == 1){
            String player1 = (players.get(0).getFirstName() + " " + players.get(0).getLastName());
            player1Name.setText(player1);
            player2Name.setVisibility(View.GONE);
            player3Name.setVisibility(View.GONE);
            player4Name.setVisibility(View.GONE);
            player5Name.setVisibility(View.GONE);
        }else if (SoccerDB.getNumberOfPlayers(teamName) == 2) {
            String player1 = (players.get(0).getFirstName() + " " + players.get(0).getLastName());
            String player2 = (players.get(1).getFirstName() + " " + players.get(1).getLastName());
            player1Name.setText(player1);
            player2Name.setText(player2);
            player3Name.setVisibility(View.GONE);
            player4Name.setVisibility(View.GONE);
            player5Name.setVisibility(View.GONE);
        }else if(SoccerDB.getNumberOfPlayers(teamName) == 3){
            String player1 = (players.get(0).getFirstName() + " " + players.get(0).getLastName());
            String player2 = (players.get(1).getFirstName() + " " + players.get(1).getLastName());
            String player3 = (players.get(2).getFirstName() + " " + players.get(2).getLastName());
            player1Name.setText(player1);
            player2Name.setText(player2);
            player3Name.setText(player3);
            player4Name.setVisibility(View.GONE);
            player5Name.setVisibility(View.GONE);
        }else if(SoccerDB.getNumberOfPlayers(teamName) == 4){
            String player1 = (players.get(0).getFirstName() + " " + players.get(0).getLastName());
            String player2 = (players.get(1).getFirstName() + " " + players.get(1).getLastName());
            String player3 = (players.get(2).getFirstName() + " " + players.get(2).getLastName());
            String player4 = (players.get(3).getFirstName() + " " + players.get(3).getLastName());
            player1Name.setText(player1);
            player2Name.setText(player2);
            player3Name.setText(player3);
            player4Name.setText(player4);
            player5Name.setVisibility(View.GONE);
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
        }
    }

    private void setPlayerImages(String teamName) {
        ArrayList<SoccerPlayer> players = SoccerDB.getPlayers(teamName);
        if (SoccerDB.getNumberOfPlayers(teamName) == 0) {
            player1Image.setVisibility(View.GONE);
            player2Image.setVisibility(View.GONE);
            player3Image.setVisibility(View.GONE);
            player4Image.setVisibility(View.GONE);
            player5Image.setVisibility(View.GONE);
        }else if (SoccerDB.getNumberOfPlayers(teamName) == 1){
            int player1 = (players.get(0).getPicture());
            player1Image.setBackgroundResource(player1);
            player2Image.setVisibility(View.GONE);
            player3Image.setVisibility(View.GONE);
            player4Image.setVisibility(View.GONE);
            player5Image.setVisibility(View.GONE);
        }else if (SoccerDB.getNumberOfPlayers(teamName) == 2) {
            int player1 = (players.get(0).getPicture());
            int player2 = (players.get(1).getPicture());
            player1Image.setBackgroundResource(player1);
            player2Image.setBackgroundResource(player2);
            player3Image.setVisibility(View.GONE);
            player4Image.setVisibility(View.GONE);
            player5Image.setVisibility(View.GONE);
        }else if(SoccerDB.getNumberOfPlayers(teamName) == 3){
            int player1 = (players.get(0).getPicture());
            int player2 = (players.get(1).getPicture());
            int player3 = (players.get(2).getPicture());
            player1Image.setBackgroundResource(player1);
            player2Image.setBackgroundResource(player2);
            player3Image.setBackgroundResource(player3);
            player4Image.setVisibility(View.GONE);
            player5Image.setVisibility(View.GONE);
        }else if(SoccerDB.getNumberOfPlayers(teamName) == 4){
            int player1 = (players.get(0).getPicture());
            int player2 = (players.get(1).getPicture());
            int player3 = (players.get(2).getPicture());
            int player4 = (players.get(3).getPicture());
            player1Image.setBackgroundResource(player1);
            player2Image.setBackgroundResource(player2);
            player3Image.setBackgroundResource(player3);
            player4Image.setBackgroundResource(player4);
            player5Image.setVisibility(View.GONE);
        }else{
            int player1 = (players.get(0).getPicture());
            int player2 = (players.get(1).getPicture());
            int player3 = (players.get(2).getPicture());
            int player4 = (players.get(3).getPicture());
            int player5 = (players.get(4).getPicture());
            player1Image.setBackgroundResource(player1);
            player2Image.setBackgroundResource(player2);
            player3Image.setBackgroundResource(player3);
            player4Image.setBackgroundResource(player4);
            player5Image.setBackgroundResource(player5);
        }
    }

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
        }else if (view == team1Name){
            setPlayerNames((String)team1Name.getText());
            setPlayerImages((String) team1Name.getText());
            setTeamLogo((String) team1Name.getText());
        }else if (view == team2Name){
            setPlayerNames((String)team2Name.getText());
            setPlayerImages((String) team2Name.getText());
            setTeamLogo((String) team2Name.getText());
        }else if (view == team3Name){
            setPlayerNames((String)team3Name.getText());
            setPlayerImages((String) team3Name.getText());
            setTeamLogo((String) team3Name.getText());
        }else if (view == team4Name){
            setPlayerNames((String)team4Name.getText());
            setPlayerImages((String) team4Name.getText());
            setTeamLogo((String) team4Name.getText());
        }else if (view == team5Name){
            setPlayerNames((String)team5Name.getText());
            setPlayerImages((String) team5Name.getText());
            setTeamLogo((String) team5Name.getText());
        }else {
            LayoutInflater layoutInflater = (LayoutInflater) getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);

            //Opens up the type of popup corresponding to the button clicked
            View popupView;
            if (view == addTeam) {
                popupView = layoutInflater.inflate(R.layout.text_popup, null);
            } else if (view == addPlayer || view == removePlayer) {
                popupView = layoutInflater.inflate(R.layout.dropdown_popup, null);
            } else {
                popupView = layoutInflater.inflate(R.layout.stats_popup, null);
            }

            //Opens up the popup at the center of the screen
            final PopupWindow popupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            popupWindow.showAtLocation(popupView, Gravity.CENTER, 0, 0);

            //Fills in the title of the popup depending on button clicked
            TextView title;
            if (view == addTeam) {
                title = (TextView) popupView.findViewById(R.id.textPopupTitle);
                title.setText("Add Team");
            }else if (view == addPlayer) {
                title = (TextView) popupView.findViewById(R.id.dropdownPopupTitle);
                title.setText("Add Player");
            }else if (view == removePlayer) {
                title = (TextView) popupView.findViewById(R.id.dropdownPopupTitle);
                title.setText("Remove Player");
            }

            //Populates the player stats popup
            if (view == player1Stats || view == player2Stats || view == player3Stats ||
                    view == player4Stats || view == player5Stats) {
                String playerName;
                if (view == player1Stats){
                    playerName = (String) player1Name.getText();
                }else if (view == player2Stats){
                    playerName = (String) player1Name.getText();
                }else if (view == player3Stats){
                    playerName = (String) player1Name.getText();
                }else if (view == player4Stats){
                    playerName = (String) player1Name.getText();
                }else{
                    playerName = (String) player1Name.getText();
                }

                EditText goals = (EditText)popupView.findViewById(R.id.goalsValue);
                EditText goalssaved = (EditText)popupView.findViewById(R.id.goalsSavedValue);
                EditText assists = (EditText)popupView.findViewById(R.id.assistsValue);
                EditText fouls = (EditText)popupView.findViewById(R.id.foulsValue);
                EditText yellowcards = (EditText)popupView.findViewById(R.id.yellowCardsValue);
                EditText redcards = (EditText)popupView.findViewById(R.id.redCardsValue);
                EditText position = (EditText)popupView.findViewById(R.id.posistionValue);
                title = (TextView)popupView.findViewById(R.id.statsPopupTitle);

                SoccerPlayer player = SoccerDB.getPlayer(playerName);
                title.setText(playerName);
                goals.setText(player.getGoalsScored());
                goalssaved.setText(player.getGoalsSaved());
                assists.setText(player.getAssists());
                fouls.setText(player.getFouls());
                yellowcards.setText(player.getYellowCards());
                redcards.setText(player.getRedCards());
                position.setText(player.getPosition());
            }


            //Populates the dropdown based on button clicked
            Spinner dropdown;
            if (view == addPlayer) {

            } else if (view == removePlayer) {

            }

            //Dismisses the popup when the cancel button is clicked
            Button btnDismiss;
            if (view == addTeam) {
                btnDismiss = (Button) popupView.findViewById(R.id.textPopupCancel);
            } else if (view == addPlayer || view == removePlayer) {
                btnDismiss = (Button) popupView.findViewById(R.id.dropdownPopupCancel);
            } else {
                btnDismiss = (Button) popupView.findViewById(R.id.statsPopupCancel);
            }
            btnDismiss.setOnClickListener(new Button.OnClickListener() {

                @Override
                public void onClick(View v) {
                    popupWindow.dismiss();
                }
            });

            //Saves data and leaves popup when enter button is pressed
            Button btnSave;
            if (view == addTeam) {
                btnSave = (Button) popupView.findViewById(R.id.textPopupCancel);
            } else if (view == addPlayer || view == removePlayer) {
                btnSave = (Button) popupView.findViewById(R.id.dropdownPopupCancel);
            } else {
                btnSave = (Button) popupView.findViewById(R.id.statsPopupCancel);
            }
            btnSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    popupWindow.dismiss();
                }
            });
        }
    }//onClick

}//first_activity
