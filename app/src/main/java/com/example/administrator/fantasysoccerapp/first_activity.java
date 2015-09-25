package com.example.administrator.fantasysoccerapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;


public class first_activity extends AppCompatActivity implements View.OnClickListener{

    //Initialize all Buttons
    Button selectTeam;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_activity);
        //Initialize buttons
        selectTeam = (Button)findViewById(R.id.selectTeamButton);
        addTeam = (Button)findViewById(R.id.addTeamButton);
        addPlayer = (Button)findViewById(R.id.addPlayerButton);
        removePlayer = (Button)findViewById(R.id.removePlayerButton);
        teamStats =(Button)findViewById(R.id.teamStatsButton);
        player1Stats = (Button)findViewById(R.id.player1StatsButton);
        player2Stats = (Button)findViewById(R.id.player2StatsButton);
        player3Stats = (Button)findViewById(R.id.player3StatsButton);
        player4Stats = (Button)findViewById(R.id.player4StatsButton);
        player5Stats = (Button)findViewById(R.id.player5StatsButton);
        playGame = (Button)findViewById(R.id.goToThirdActivity);

        //Set on click listeners for the popup generating buttons
        selectTeam.setOnClickListener(this);
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
    }//onCreate

    @Override
    public void onClick(View view) {
        if (view == playGame){
            startActivity(new Intent(first_activity.this,third_activity.class));
        }else {
            LayoutInflater layoutInflater = (LayoutInflater) getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);

            //Opens up the type of popup corresponding to the button clicked
            View popupView;
            if (view == addTeam) {
                popupView = layoutInflater.inflate(R.layout.text_popup, null);
            } else if (view == selectTeam || view == addPlayer || view == removePlayer) {
                popupView = layoutInflater.inflate(R.layout.dropdown_popup, null);
            } else {
                popupView = layoutInflater.inflate(R.layout.stats_popup, null);
            }

            //Opens up the popup at the center of the screen
            final PopupWindow popupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            popupWindow.showAtLocation(popupView, Gravity.CENTER, 0, 0);

            //Fills in the title of the popup depending on button clicked
            TextView title;
            if (view == selectTeam) {
                title = (TextView) popupView.findViewById(R.id.dropdownPopupTitle);
                title.setText("Pick Team");
            } else if (view == addTeam) {
                title = (TextView) popupView.findViewById(R.id.textPopupTitle);
                title.setText("Add Team");
            } else if (view == addPlayer) {
                title = (TextView) popupView.findViewById(R.id.dropdownPopupTitle);
                title.setText("Add Player");
            } else if (view == removePlayer) {
                title = (TextView) popupView.findViewById(R.id.dropdownPopupTitle);
                title.setText("Remove Player");
            }

            //Populates the dropdown based on button clicked
            if (view == selectTeam) {

            } else if (view == addPlayer) {

            } else if (view == removePlayer) {

            }

            //Dismisses the popup when the cancel button is clicked
            Button btnDismiss;
            if (view == addTeam) {
                btnDismiss = (Button) popupView.findViewById(R.id.textPopupCancel);
            } else if (view == selectTeam || view == addPlayer || view == removePlayer) {
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
            } else if (view == selectTeam || view == addPlayer || view == removePlayer) {
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
