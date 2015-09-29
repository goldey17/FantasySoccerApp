package com.example.administrator.fantasysoccerapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import java.util.ArrayList;

/**
 * Created by Jordan Goldey
 * Last edited 9/28/2015
 * The first activity contains all the team information. There are up to five team buttons. When a
 * team button is clicked the players are loaded on the screen showing there picture, name, and a
 * stats button. When clicked the stats button loads a popup containing the players stats. Likewise
 * when clicked the team stats button loads a popup with the team stats. A team or player can be
 * added by typing a name and pushing the corresponding save button. Only five teams can be created
 * and only 5 players can be on a team. Lastly, the page has buttons to go to the second and third
 * activities.
 */
public class first_activity extends AppCompatActivity implements View.OnClickListener{

    //Declare all Buttons
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

    //Declare all Text Views
    TextView teamName;
    TextView player1Name;
    TextView player2Name;
    TextView player3Name;
    TextView player4Name;
    TextView player5Name;

    //Declare all Images
    ImageView player1Image;
    ImageView player2Image;
    ImageView player3Image;
    ImageView player4Image;
    ImageView player5Image;
    ImageView teamLogo;

    //Declare all Edit Text
    EditText editableTeamName;
    EditText editablePlayerName;

    @Override
    //Used to create the layout when it is called
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_activity);

        //Initialize all Buttons
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

        //Initialize all Text View
        teamName = (TextView)findViewById(R.id.teamName);
        player1Name = (TextView)findViewById(R.id.player1Name);
        player2Name = (TextView)findViewById(R.id.player2Name);
        player3Name = (TextView)findViewById(R.id.player3Name);
        player4Name = (TextView)findViewById(R.id.player4Name);
        player5Name = (TextView)findViewById(R.id.player5Name);

        //Initialize all Pictures
        player1Image = (ImageView)findViewById(R.id.player1Image);
        player2Image = (ImageView)findViewById(R.id.player2Image);
        player3Image = (ImageView)findViewById(R.id.player3Image);
        player4Image = (ImageView)findViewById(R.id.player4Image);
        player5Image = (ImageView)findViewById(R.id.player5Image);
        teamLogo = (ImageView)findViewById(R.id.teamLogo);

        //Initialize all Edit Text
        editableTeamName = (EditText)findViewById(R.id.enterTeamName);
        editablePlayerName = (EditText)findViewById(R.id.enterPlayerName);

        //Set on click listeners for the popup generating buttons
        teamStats.setOnClickListener(this);
        player1Stats.setOnClickListener(this);
        player2Stats.setOnClickListener(this);
        player3Stats.setOnClickListener(this);
        player4Stats.setOnClickListener(this);
        player5Stats.setOnClickListener(this);

        //Set on click listeners for all buttons
        team1Name.setOnClickListener(this);
        team2Name.setOnClickListener(this);
        team3Name.setOnClickListener(this);
        team4Name.setOnClickListener(this);
        team5Name.setOnClickListener(this);
        playGame.setOnClickListener(this);
        playerMenu.setOnClickListener(this);
        saveTeam.setOnClickListener(this);
        savePlayer.setOnClickListener(this);

        //Create default database
        SoccerDB.createDefaultDatabase();

        //Set all the data on the page initially to the Kittenz team
        setTeamButtons();
        setPlayerInfo("Kittenz");

    }//onCreate

    //Sets all the information relating to the team name passed in. This includes showing/hiding the
    //players on the given team.
    private void setPlayerInfo(String tempTeamName) {
        //Sets team logo and text
        teamLogo.setBackgroundResource(SoccerDB.returnTeamLogo(tempTeamName));
        teamName.setText(tempTeamName);

        //Determines how many players are on the team and sets the needed ones to visible and the
        //not needed ones to hidden.
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
            player1Name.setVisibility(View.VISIBLE);
            player2Name.setVisibility(View.GONE);
            player3Name.setVisibility(View.GONE);
            player4Name.setVisibility(View.GONE);
            player5Name.setVisibility(View.GONE);

            int player1Im = (players.get(0).getPicture());
            player1Image.setBackgroundResource(player1Im);
            player1Image.setVisibility(View.VISIBLE);
            player2Image.setVisibility(View.GONE);
            player3Image.setVisibility(View.GONE);
            player4Image.setVisibility(View.GONE);
            player5Image.setVisibility(View.GONE);

            player1Stats.setVisibility(View.VISIBLE);
            player2Stats.setVisibility(View.GONE);
            player3Stats.setVisibility(View.GONE);
            player4Stats.setVisibility(View.GONE);
            player5Stats.setVisibility(View.GONE);
        }else if (SoccerDB.getNumberOfPlayers(tempTeamName) == 2) {
            String player1 = (players.get(0).getFirstName() + " " + players.get(0).getLastName());
            String player2 = (players.get(1).getFirstName() + " " + players.get(1).getLastName());
            player1Name.setText(player1);
            player2Name.setText(player2);
            player1Name.setVisibility(View.VISIBLE);
            player2Name.setVisibility(View.VISIBLE);
            player3Name.setVisibility(View.GONE);
            player4Name.setVisibility(View.GONE);
            player5Name.setVisibility(View.GONE);

            int player1Im = (players.get(0).getPicture());
            int player2Im = (players.get(1).getPicture());
            player1Image.setBackgroundResource(player1Im);
            player2Image.setBackgroundResource(player2Im);
            player1Image.setVisibility(View.VISIBLE);
            player2Image.setVisibility(View.VISIBLE);
            player3Image.setVisibility(View.GONE);
            player4Image.setVisibility(View.GONE);
            player5Image.setVisibility(View.GONE);

            player1Stats.setVisibility(View.VISIBLE);
            player2Stats.setVisibility(View.VISIBLE);
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
            player1Name.setVisibility(View.VISIBLE);
            player2Name.setVisibility(View.VISIBLE);
            player3Name.setVisibility(View.VISIBLE);
            player4Name.setVisibility(View.GONE);
            player5Name.setVisibility(View.GONE);

            int player1Im = (players.get(0).getPicture());
            int player2Im = (players.get(1).getPicture());
            int player3Im = (players.get(2).getPicture());
            player1Image.setBackgroundResource(player1Im);
            player2Image.setBackgroundResource(player2Im);
            player3Image.setBackgroundResource(player3Im);
            player1Image.setVisibility(View.VISIBLE);
            player2Image.setVisibility(View.VISIBLE);
            player3Image.setVisibility(View.VISIBLE);
            player4Image.setVisibility(View.GONE);
            player5Image.setVisibility(View.GONE);

            player1Stats.setVisibility(View.VISIBLE);
            player2Stats.setVisibility(View.VISIBLE);
            player3Stats.setVisibility(View.VISIBLE);
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
            player1Name.setVisibility(View.VISIBLE);
            player2Name.setVisibility(View.VISIBLE);
            player3Name.setVisibility(View.VISIBLE);
            player4Name.setVisibility(View.VISIBLE);
            player5Name.setVisibility(View.GONE);

            int player1Im = (players.get(0).getPicture());
            int player2Im = (players.get(1).getPicture());
            int player3Im = (players.get(2).getPicture());
            int player4Im = (players.get(3).getPicture());
            player1Image.setBackgroundResource(player1Im);
            player2Image.setBackgroundResource(player2Im);
            player3Image.setBackgroundResource(player3Im);
            player4Image.setBackgroundResource(player4Im);
            player1Image.setVisibility(View.VISIBLE);
            player2Image.setVisibility(View.VISIBLE);
            player3Image.setVisibility(View.VISIBLE);
            player4Image.setVisibility(View.VISIBLE);
            player5Image.setVisibility(View.GONE);

            player1Stats.setVisibility(View.VISIBLE);
            player2Stats.setVisibility(View.VISIBLE);
            player3Stats.setVisibility(View.VISIBLE);
            player4Stats.setVisibility(View.VISIBLE);
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
            player1Name.setVisibility(View.VISIBLE);
            player2Name.setVisibility(View.VISIBLE);
            player3Name.setVisibility(View.VISIBLE);
            player4Name.setVisibility(View.VISIBLE);
            player5Name.setVisibility(View.VISIBLE);

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
            player1Image.setVisibility(View.VISIBLE);
            player2Image.setVisibility(View.VISIBLE);
            player3Image.setVisibility(View.VISIBLE);
            player4Image.setVisibility(View.VISIBLE);
            player5Image.setVisibility(View.VISIBLE);

            player1Stats.setVisibility(View.VISIBLE);
            player2Stats.setVisibility(View.VISIBLE);
            player3Stats.setVisibility(View.VISIBLE);
            player4Stats.setVisibility(View.VISIBLE);
            player5Stats.setVisibility(View.VISIBLE);
        }
    }//setPlayerInfo

    //Determines how many teams there are and sets the needed ones to visible and the
    //not needed ones to hidden.
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
            team3Name.setVisibility(View.VISIBLE);
            team4Name.setVisibility(View.GONE);
            team5Name.setVisibility(View.GONE);
        }else if(SoccerDB.getNumberOfTeams() == 4){
            team1Name.setText(teams.get(0));
            team2Name.setText(teams.get(1));
            team3Name.setText(teams.get(2));
            team4Name.setText(teams.get(3));
            team3Name.setVisibility(View.VISIBLE);
            team4Name.setVisibility(View.VISIBLE);
            team5Name.setVisibility(View.GONE);
        }else{
            team1Name.setText(teams.get(0));
            team2Name.setText(teams.get(1));
            team3Name.setText(teams.get(2));
            team4Name.setText(teams.get(3));
            team5Name.setText(teams.get(4));
            team3Name.setVisibility(View.VISIBLE);
            team4Name.setVisibility(View.VISIBLE);
            team5Name.setVisibility(View.VISIBLE);
        }
    }//setTeamButtons

    @Override
    //Function to handle all the button clicks associated with this activity
    public void onClick(View view) {
        if (view == playGame) {
            //If the Play Game button is picked the third activity is loaded
            startActivity(new Intent(first_activity.this, third_activity.class));
        }else if (view == playerMenu) {
            //If the Player Menu button is picked the second activity is loaded
            startActivity(new Intent(first_activity.this, second_activity.class));
        }else if (view == team1Name){
            //If the first team button is clicked, changes the player info to that team
            setPlayerInfo((String) team1Name.getText());
        }else if (view == team2Name){
            //If the second team button is clicked, changes the player info to that team
            setPlayerInfo((String)team2Name.getText());
        }else if (view == team3Name){
            //If the third team button is clicked, changes the player info to that team
            setPlayerInfo((String) team3Name.getText());
        }else if (view == team4Name){
            //If the fourth team button is clicked, changes the player info to that team
            setPlayerInfo((String) team4Name.getText());
        }else if (view == team5Name){
            //If the fifth team button is clicked, changes the player info to that team
            setPlayerInfo((String) team5Name.getText());
        }else if (view == saveTeam){
            //If the Save Team button is clicked a new team is added, the team buttons are updated
            //and the new team info is loaded. If 5 teams exists or team with the same name exists
            //no new team is added.
            if (SoccerDB.getNumberOfTeams() < 5) {
                String str = editableTeamName.getText().toString();
                if (!SoccerDB.isTeam(str)){
                    SoccerDB.addTeam(str);
                    setTeamButtons();
                    setPlayerInfo(str);
                }
            }
        }else if (view == savePlayer) {
            //If the Save Player Button is clicked a new player is added, to the team that is currently
            // loaded, if there isn't a player with that name already in the database
            String str = editablePlayerName.getText().toString();
            if (str.indexOf(" ") == -1){
                str = str + " ";
            }
            if (!SoccerDB.isPlayer(str)){
                SoccerDB.addPlayer(str, teamName.getText().toString());
                setPlayerInfo(teamName.getText().toString());
            }
        }else {
            //If any other button is clicked then a popup will be generated with stats corresponding
            //to the button clicked
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

                //Set Player name or Team name depending on button clicked
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

                //Initilize all Edit Text and title on popup
                EditText goals = (EditText)popupView.findViewById(R.id.goalsValue);
                EditText goalssaved = (EditText)popupView.findViewById(R.id.goalsSavedValue);
                EditText assists = (EditText)popupView.findViewById(R.id.assistsValue);
                EditText fouls = (EditText)popupView.findViewById(R.id.foulsValue);
                EditText yellowcards = (EditText)popupView.findViewById(R.id.yellowCardsValue);
                EditText redcards = (EditText)popupView.findViewById(R.id.redCardsValue);
                EditText position = (EditText)popupView.findViewById(R.id.posistionValue);
                TextView title = (TextView)popupView.findViewById(R.id.statsPopupTitle);

                //Set information based on if player or team
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
