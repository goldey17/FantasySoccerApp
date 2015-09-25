package com.example.administrator.fantasysoccerapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceView;

/**
 * Author Jordan Goldey
 * Last Updated 9/24/2015
 * Purpose: To draw a soccer field on a surface view
 */
public class soccerField extends SurfaceView{

    public soccerField(Context context, AttributeSet attrs) {
        super(context, attrs);
        setWillNotDraw(false);
    }

    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);

        //Create the color for the field and a color for all the elements
        Paint field = new Paint();
        Paint lines = new Paint();
        field.setColor(Color.GREEN);
        lines.setColor(Color.WHITE);

        //Initializing all the variables needed to place elements on the screen
        int hieght = canvas.getHeight();
        int width = canvas.getWidth();
        int middleOfYAxis = hieght / 2;
        int middleOfXAxis = width / 2;
        int startOfGoalsYDirection = middleOfYAxis - 300;
        int endOfGoalsYDirection = middleOfYAxis + 300;

        //Draw middle circle
        canvas.drawCircle(middleOfXAxis, middleOfYAxis, 200, lines);

        //Draw mid-line
        canvas.drawRect(middleOfXAxis - 10, 0, middleOfXAxis + 10, hieght, lines);

        //Draw goals
        canvas.drawRect(0, startOfGoalsYDirection, 200, endOfGoalsYDirection, lines);
        canvas.drawRect(width - 200, startOfGoalsYDirection, width, endOfGoalsYDirection, lines);

    }
}
