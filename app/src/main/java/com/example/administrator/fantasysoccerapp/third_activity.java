package com.example.administrator.fantasysoccerapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

/**
 * Created by Jordan Goldey
 * Last edited 9/29/2015
 * The third activity is a where the game is played. Each side gets assigned to a team via dropdown.
 * Once teams are picked the players can be placed on the board by clicking the image buttons. A
 * game wont start until both teams are different and all the players on the board are different.
 * Once a game is started a winner is determined.
 */
public class third_activity extends AppCompatActivity implements View.OnClickListener{

    //Declare all spinners
    Spinner teamOne;
    Spinner teamTwo;

    //Declare all image buttons
    ImageButton team1Forward1;
    ImageButton team1Forward2;
    ImageButton team1Defense1;
    ImageButton team1Defense2;
    ImageButton team1Goalie;
    ImageButton team2Forward1;
    ImageButton team2Forward2;
    ImageButton team2Defense1;
    ImageButton team2Defense2;
    ImageButton team2Goalie;

    //Declare all buttons
    Button goBack;
    Button playGame;

    //Declare and initalize the click count variables
    int team1GoalieClickCount = 0;
    int team1Forward1ClickCount = 0;
    int team1Forward2ClickCount = 0;
    int team1Defense1ClickCount = 0;
    int team1Defense2ClickCount = 0;
    int team2GoalieClickCount = 0;
    int team2Forward1ClickCount = 0;
    int team2Forward2ClickCount = 0;
    int team2Defense1ClickCount = 0;
    int team2Defense2ClickCount = 0;

    //Declare an Arraylist of the team names
    ArrayList <String> teams;

    //Delclare and initialize variables for keeping track of what information is set on the activity
    String teamOneSelected = "";
    String teamTwoSelected = "";
    String[] team1Positions = new String[5];
    String[] team2Positions = new String[5];

    @Override
    //Function that is used when the activity is drawn
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_activity);

        //Initialize the spinners
        teamOne = (Spinner) findViewById(R.id.teamOneDropdown);
        teamTwo = (Spinner) findViewById(R.id.teamTwoDropdown);

        //Initialize all Image Buttons
        team1Forward1 = (ImageButton) findViewById(R.id.team1Forward1);
        team1Forward2 = (ImageButton) findViewById(R.id.team1Forward2);
        team1Defense1 = (ImageButton) findViewById(R.id.team1Defense1);
        team1Defense2 = (ImageButton) findViewById(R.id.team1Defense2);
        team1Goalie = (ImageButton) findViewById(R.id.team1Goalie);
        team2Forward1 = (ImageButton) findViewById(R.id.team2Forward1);
        team2Forward2 = (ImageButton) findViewById(R.id.team2Forward2);
        team2Defense1 = (ImageButton) findViewById(R.id.team2Defense1);
        team2Defense2 = (ImageButton) findViewById(R.id.team2Defense2);
        team2Goalie = (ImageButton) findViewById(R.id.team2Goalie);

        //Initialize the Buttons
        goBack = (Button) findViewById(R.id.leaveThirdActivity);
        playGame = (Button) findViewById(R.id.playGame);

        //Set on click listeners for all Buttons and Image Buttons
        team1Forward1.setOnClickListener(this);
        team1Forward2.setOnClickListener(this);
        team1Defense1.setOnClickListener(this);
        team1Defense2.setOnClickListener(this);
        team1Goalie.setOnClickListener(this);
        team2Forward1.setOnClickListener(this);
        team2Forward2.setOnClickListener(this);
        team2Defense1.setOnClickListener(this);
        team2Defense2.setOnClickListener(this);
        team2Goalie.setOnClickListener(this);
        goBack.setOnClickListener(this);
        playGame.setOnClickListener(this);

        //Set all team positions to "" to show they haven't been set yet
        for (int i = 0; i < 5; i ++){
            team1Positions[i] = "";
            team2Positions[i] = "";
        }

        //Call methods to populate dropdowns
        populateTeamOneDropdown();
        populateTeamTwoDropdown();
    }//onCreate

    //Populates the team one dropdown with a list of all the teams
    private void populateTeamOneDropdown() {

        teams = SoccerDB.getListOfTeamNames();

        //Makes the array list into the proper format for dropdown
        ArrayAdapter<String> adp = new ArrayAdapter<>
                (this,android.R.layout.simple_dropdown_item_1line, teams);
        teamOne.setAdapter(adp);

        //When an item is selected in the dropdown the team selected variable changes
        teamOne.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                Toast.makeText(getBaseContext(), teams.get(arg2),
                        Toast.LENGTH_SHORT).show();
                teamOneSelected = teams.get(arg2);
            }

            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
    }//populateTeamOneDropdown

    //Populates the team one dropdown with a list of all the teams
    private void populateTeamTwoDropdown() {

        teams = SoccerDB.getListOfTeamNames();

        //Makes the array list into the proper format for dropdown
        ArrayAdapter<String> adp = new ArrayAdapter<>
                (this,android.R.layout.simple_dropdown_item_1line, teams);
        teamTwo.setAdapter(adp);

        //When an item is selected in the dropdown the team selected variable changes
        teamTwo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                Toast.makeText(getBaseContext(), teams.get(arg2),
                        Toast.LENGTH_SHORT).show();
                teamTwoSelected = teams.get(arg2);
            }

            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
    }//populateTeamTwoDropdown

    @Override
    //Method to handle any button clicks
    public void onClick(View view) {
        if(view == team1Defense1 || view == team1Defense2 || view == team1Forward1 ||
                view == team1Forward2 || view == team1Goalie){
            //If any of the team one image buttons are clicked, increase the count according to what
            //position was clicked, and load an image of one of the players. So each time the button
            //is clicked a new player image appears. When the counter reaches the size of the team
            //it rolls back to zero. This happens for all the positions on team one.
            if (!teamOneSelected.equals("")){
                SoccerTeam team = SoccerDB.getTeam(teamOneSelected);
                ArrayList<SoccerPlayer> players = team.getPlayers();
                if(view == team1Goalie){
                    SoccerPlayer player = players.get(team1GoalieClickCount);
                    team1Goalie.setBackgroundResource(player.getPicture());
                    team1Positions[0] = player.getFirstName() + " " + player.getLastName();
                    team1GoalieClickCount++;
                    if(team1GoalieClickCount == players.size()){
                        team1GoalieClickCount = 0;
                    }
                }else if(view == team1Defense1){
                    SoccerPlayer player = players.get(team1Defense1ClickCount);
                    team1Defense1.setBackgroundResource(player.getPicture());
                    team1Positions[1] = player.getFirstName() + " " + player.getLastName();
                    team1Defense1ClickCount++;
                    if(team1Defense1ClickCount == players.size()){
                        team1Defense1ClickCount = 0;
                    }
                }else if(view == team1Defense2){
                    SoccerPlayer player = players.get(team1Defense2ClickCount);
                    team1Defense2.setBackgroundResource(player.getPicture());
                    team1Positions[2] = player.getFirstName() + " " + player.getLastName();
                    team1Defense2ClickCount++;
                    if(team1Defense2ClickCount == players.size()){
                        team1Defense2ClickCount = 0;
                    }
                }else if(view == team1Forward1){
                    SoccerPlayer player = players.get(team1Forward1ClickCount);
                    team1Forward1.setBackgroundResource(player.getPicture());
                    team1Positions[3] = player.getFirstName() + " " + player.getLastName();
                    team1Forward1ClickCount++;
                    if(team1Forward1ClickCount == players.size()){
                        team1Forward1ClickCount = 0;
                    }
                }else if(view == team1Forward2){
                    SoccerPlayer player = players.get(team1Forward2ClickCount);
                    team1Forward2.setBackgroundResource(player.getPicture());
                    team1Positions[4] = player.getFirstName() + " " + player.getLastName();
                    team1Forward2ClickCount++;
                    if(team1Forward2ClickCount == players.size()){
                        team1Forward2ClickCount = 0;
                    }
                }

            }
        }else if(view == team2Defense1 || view == team2Defense2 || view == team2Forward1 ||
                view == team2Forward2 || view == team2Goalie){
            //If any of the team two image buttons are clicked, increase the count according to what
            //position was clicked, and load an image of one of the players. So each time the button
            //is clicked a new player image appears. When the counter reaches the size of the team
            //it rolls back to zero. This happens for all the positions on team team.
            if (!teamTwoSelected.equals("")){
                SoccerTeam team = SoccerDB.getTeam(teamTwoSelected);
                ArrayList<SoccerPlayer> players = team.getPlayers();
                if(view == team2Goalie){
                    SoccerPlayer player = players.get(team2GoalieClickCount);
                    team2Goalie.setBackgroundResource(player.getPicture());
                    team2Positions[0] = player.getFirstName() + " " + player.getLastName();
                    team2GoalieClickCount++;
                    if(team2GoalieClickCount == players.size()){
                        team2GoalieClickCount = 0;
                    }
                }else if(view == team2Defense1){
                    SoccerPlayer player = players.get(team2Defense1ClickCount);
                    team2Defense1.setBackgroundResource(player.getPicture());
                    team2Positions[1] = player.getFirstName() + " " + player.getLastName();
                    team2Defense1ClickCount++;
                    if(team2Defense1ClickCount == players.size()){
                        team2Defense1ClickCount = 0;
                    }
                }else if(view == team2Defense2){
                    SoccerPlayer player = players.get(team2Defense2ClickCount);
                    team2Defense2.setBackgroundResource(player.getPicture());
                    team2Positions[2] = player.getFirstName() + " " + player.getLastName();
                    team2Defense2ClickCount++;
                    if(team2Defense2ClickCount == players.size()){
                        team2Defense2ClickCount = 0;
                    }
                }else if(view == team2Forward1){
                    SoccerPlayer player = players.get(team2Forward1ClickCount);
                    team2Forward1.setBackgroundResource(player.getPicture());
                    team2Positions[3] = player.getFirstName() + " " + player.getLastName();
                    team2Forward1ClickCount++;
                    if(team2Forward1ClickCount == players.size()){
                        team2Forward1ClickCount = 0;
                    }
                }else if(view == team2Forward2){
                    SoccerPlayer player = players.get(team2Forward2ClickCount);
                    team2Forward2.setBackgroundResource(player.getPicture());
                    team2Positions[4] = player.getFirstName() + " " + player.getLastName();
                    team2Forward2ClickCount++;
                    if(team2Forward2ClickCount == players.size()){
                        team2Forward2ClickCount = 0;
                    }
                }
            }
        }else if (view == goBack){
            //finish the activity if the goBack button is clicked
            finish();
        }else if (view == playGame){
            //If the play game button is pushed make sure that team one and team two are not the
            //same. Next check if all the positions on both teams are filled. Next check if all the
            //positions on both teams have unique players and that one player isn't playing two
            //positions. If any of these checks fail nothing happens.
            if (!teamOneSelected.equals(teamTwoSelected)) {
                boolean playersNotSet = false;
                for (int i = 0; i < 5; i++){
                    if (team1Positions[i].equals("")){
                        playersNotSet = true;
                    }
                }
                for (int i = 0; i < 5; i++){
                    if (team2Positions[i].equals("")){
                        playersNotSet = true;
                    }
                }
                if (!playersNotSet){
                    boolean playersDifferent = true;
                    for (int i = 0; i < 5; i++){
                        for (int j = 0; j < 5; j++){
                            if (team1Positions[i].equals(team1Positions[j]) && i != j){
                                playersDifferent = false;
                            }
                        }
                    }
                    for (int i = 0; i < 5; i++){
                        for (int j = 0; j < 5; j++){
                            if (team2Positions[i].equals(team2Positions[j]) && i != j){
                                playersDifferent = false;
                            }
                        }
                    }
                    if (playersDifferent){
                        //If all checks passed a game is started and popup appears saying who won.
                        LayoutInflater layoutInflater = (LayoutInflater) getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);

                        //Opens up the type of popup corresponding to the button clicked
                        final View popupView = layoutInflater.inflate(R.layout.won_popup, null);

                        //Opens up the popup at the center of the screen
                        final PopupWindow popupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        popupWindow.showAtLocation(popupView, Gravity.CENTER, 0, 0);

                        //Fills in the title with who won 
                        TextView title = (TextView) popupView.findViewById(R.id.wonPopupText);
                        String winner = SoccerDB.chooseWinner(teamOneSelected,teamTwoSelected);
                        title.setText(winner + " Won!!!!!");

                        //Dismisses the popup when the cancel button is clicked
                        Button btnDismiss = (Button) popupView.findViewById(R.id.wonPopupCancel);
                        btnDismiss.setOnClickListener(new Button.OnClickListener() {

                            @Override
                            public void onClick(View v) {
                                popupWindow.dismiss();
                            }
                        });
                    }
                }
            }
        }
    }//onClick
}//third_activity
