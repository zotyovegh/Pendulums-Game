package com.example.pendulumtestjava;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

public class PendulumView extends View {
    Paint paintCircle, paintThread;
    Path pathThread,pathHolder;


    float circlePositionX = 0;
    float threadPositionX = 0;
    float circlePositionY = 0;
    float threadPositionY = 0;

    int x_dir;
    int y_dir;

    double thread_x_dir;
    int circle_x;
    double thread_y_dir;
    int circle_y;
    double thread_x;
    double thread_y;

    //    float boundryRight;
    public PendulumView(Context context) {
        super(context);
        init();
    }

    public PendulumView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PendulumView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int xCenter = getWidth() / 2;
        float boundryRight = xCenter + 200;
        float boundryLeft = xCenter - 200;
        Log.i("width ", String.valueOf(getWidth()));

        paintCircle = new Paint();
        paintThread = new Paint();
        paintCircle.setStyle(Paint.Style.FILL);
        paintCircle.setColor(Color.BLACK);
        paintThread.setColor(Color.BLACK);
        paintThread.setStyle(Paint.Style.STROKE);
        paintThread.setStrokeWidth(3);
        pathThread = new Path();
        pathHolder = new Path();
        pathHolder.moveTo(xCenter + 100,100);
        pathHolder.lineTo(xCenter - 100,100);
        pathThread.moveTo(xCenter, 100);
        canvas.drawCircle(xCenter, 100, 10, paintCircle);

        float threadNewLine = xCenter + threadPositionX;


        pathThread.lineTo((float) thread_x, 600);
        canvas.drawCircle(circle_x, 600, 30, paintCircle);


        if (circle_x >= boundryRight) {

            x_dir -= 5;
        }

        if (circle_x <= boundryLeft) {

            x_dir += 5;

        }
        circle_x = circle_x + x_dir;
        thread_x = thread_x + x_dir;








        canvas.drawPath(pathThread, paintThread);
        canvas.drawPath(pathHolder,paintThread);
        Log.i("thread Line ", String.valueOf(threadNewLine));
        invalidate();
    }

    public void init() {

        x_dir = 5;
//        thread_x_dir = 9.5;


        circle_x = 360;
        thread_x = 360;

    }
}
