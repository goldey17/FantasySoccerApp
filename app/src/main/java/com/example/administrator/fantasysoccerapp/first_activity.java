package com.example.administrator.fantasysoccerapp;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;


public class first_activity extends ActionBarActivity implements View.OnClickListener{

    //Initialize all Buttons
    Button selectTeam;
    Button addTeam;
    Button addPlayer;
    Button removePlayer;
    Button player1Stats;
    Button player2Stats;
    Button player3Stats;
    Button player4Stats;
    Button player5Stats;
    Button player6Stats;
    Button player7Stats;
    Button player8Stats;
    Button player9Stats;
    Button player10Stats;
    Button player11Stats;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_activity);
        //Initialize buttons
        selectTeam = (Button)findViewById(R.id.selectTeamButton);
        addTeam = (Button)findViewById(R.id.addTeamButton);
        addPlayer = (Button)findViewById(R.id.addPlayerButton);
        removePlayer = (Button)findViewById(R.id.removePlayerButton);
        player1Stats = (Button)findViewById(R.id.player1StatsButton);
        player2Stats = (Button)findViewById(R.id.player2StatsButton);
        player3Stats = (Button)findViewById(R.id.player3StatsButton);
        player4Stats = (Button)findViewById(R.id.player4StatsButton);
        player5Stats = (Button)findViewById(R.id.player5StatsButton);
        player6Stats = (Button)findViewById(R.id.player6StatsButton);
        player7Stats = (Button)findViewById(R.id.player7StatsButton);
        player8Stats = (Button)findViewById(R.id.player8StatsButton);
        player9Stats = (Button)findViewById(R.id.player9StatsButton);
        player10Stats = (Button)findViewById(R.id.player10StatsButton);
        player11Stats = (Button)findViewById(R.id.player11StatsButton);

        //Set on click listeners for the popup generating buttons
        selectTeam.setOnClickListener(this);
        addTeam.setOnClickListener(this);
        addPlayer.setOnClickListener(this);
        removePlayer.setOnClickListener(this);
        player1Stats.setOnClickListener(this);
        player2Stats.setOnClickListener(this);
        player3Stats.setOnClickListener(this);
        player4Stats.setOnClickListener(this);
        player5Stats.setOnClickListener(this);
        player6Stats.setOnClickListener(this);
        player7Stats.setOnClickListener(this);
        player8Stats.setOnClickListener(this);
        player9Stats.setOnClickListener(this);
        player10Stats.setOnClickListener(this);
        player11Stats.setOnClickListener(this);


    }//onCreate


    @Override
    public void onClick(View view) {
        LayoutInflater layoutInflater = (LayoutInflater) getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);

        //Opens up the type of popup corresponding to the button clicked
        View popupView;
        if (view == addTeam){
            popupView = layoutInflater.inflate(R.layout.text_popup, null);
        } else if popupView = layoutInflater.inflate(R.layout.dropdown_popup, null);{
            popupView = layoutInflater.inflate(R.layout.dropdown_popup, null);
        }else{
            popupView = layoutInflater.inflate(R.layout.stats_popup, null);
        }

        //Opens up the popup at the center of the screen
        final PopupWindow popupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.showAtLocation(popupView, Gravity.CENTER, 0, 0);

        //Fills in the title of the popup depending on button clicked
        TextView title = (TextView) popupView.findViewById(R.id.dropdownPopupTitle);
        if (view == selectTeam){
            title.setText("Pick Team");
        }else if (view == addTeam){
            title.setText("Add Team");
        }else if (view == addPlayer){
            title.setText("Add Player");
        }else if (view == removePlayer) {
            title.setText("Remove Player");
        }else{
        }

        //Populates the dropdown based on button clicked

        //Dismisses the popup when the cancel button is clicked
        Button btnDismiss = (Button) popupView.findViewById(R.id.dropdownPopupCancel);
        btnDismiss.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

        //Saves data and leaves popup when enter button is pressed
        Button tradePlayerButton = (Button) popupView.findViewById(R.id.dropdownPopupEnter);
        tradePlayerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
            }
        });

    }
}//first_activity
