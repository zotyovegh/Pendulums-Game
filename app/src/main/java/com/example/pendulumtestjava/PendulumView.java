package com.example.pendulumtestjava;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Display;
import android.view.View;

import androidx.annotation.Nullable;

public class PendulumView extends View {
    Paint paintCircle, paintThread;
    Path pathThread1, pathThread2, pathHolder;


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
    float r1 = 100;
    float r2 = 100;
    float m1 = 40;
    float m2 = 40;
    float a1 = 0;
    float a2 = 0;
    float x1;
    float y1;


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
//        float boundryRight = xCenter + 200;
//        float boundryLeft = xCenter - 200;

        Log.i("width ", String.valueOf(getWidth()));

        paintCircle = new Paint();
        paintThread = new Paint();
        paintCircle.setStyle(Paint.Style.FILL);
        paintCircle.setColor(Color.BLACK);
        paintThread.setColor(Color.BLACK);
        paintThread.setStyle(Paint.Style.STROKE);
        paintThread.setStrokeWidth(3);
        pathThread1 = new Path();
        pathThread2 = new Path();

        canvas.drawCircle(xCenter, 100, 10, paintCircle);
        pathThread1.moveTo(xCenter, 100);
//        float threadNewLine = xCenter + threadPositionX;
//        Log.i("thread Line ", String.valueOf(threadNewLine));




        x1 = r1 * (float)Math.toDegrees(Math.sin(a1));
        y1 = r1 * (float)Math.toDegrees(Math.cos(a1));

        pathThread1.lineTo(x1, y1);
        canvas.drawCircle(x1, y1, m1, paintCircle);










//        if (circle_x >= boundryRight) {
//
//            x_dir -= 5;
//        }
//
//        if (circle_x <= boundryLeft) {
//
//            x_dir += 5;
//
//        }
//        circle_x = circle_x + x_dir;
//        thread_x = thread_x + x_dir;


        canvas.drawPath(pathThread1, paintThread);

        invalidate();
    }

    public void init() {

//        x1 = 180;
//        y1 = 180;
//        x_dir = 5;
//        thread_x_dir = 9.5;
//        circle_x =  540;
//        thread_x =  540;

    }
}
