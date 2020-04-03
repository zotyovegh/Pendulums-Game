package com.example.pendulumtestjava;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.Nullable;

public class PendulumView extends View {


    Paint paintCircle, paintThread;
    Path pathThread1, pathThread2;


    float r1 = 400;
    float r2 = 400;
    float m1 = 30;
    float m2 = 30;
    float a1 = (float)Math.toDegrees(Math.PI / 2);
    float a2 = (float)Math.toDegrees(Math.PI / 2);
    float x1;
    float y1;
    float x2;
    float y2;
    float a1_v = 0;
    float a2_v = 0;
    float a1_a = (float)0.01;
    float a2_a = -(float)0.01;
    float g = 1;


     public PendulumView(Context context) {
        super(context);
    }

    public PendulumView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PendulumView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int xCenter = getWidth() / 2;
        //Some initialization and constants
        paintCircle = new Paint();
        paintThread = new Paint();
        paintCircle.setStyle(Paint.Style.FILL);
        paintCircle.setColor(Color.BLACK);
        paintThread.setColor(Color.BLACK);
        paintThread.setStyle(Paint.Style.STROKE);
        paintThread.setStrokeWidth(3);
        pathThread1 = new Path();
        pathThread2 = new Path();
        canvas.translate(xCenter, 100);
        canvas.drawCircle(0, 0, 10, paintCircle);
        pathThread1.moveTo(0, 0);
        pathThread2.moveTo(x1, y1);


        //coordinate settings
        x1 = r1 * (float)Math.sin(Math.toRadians(a1));
        y1 = r1 * (float)Math.cos(Math.toRadians(a1));
        x2 = x1 + r2 * (float)Math.sin(Math.toRadians(a2));
        y2 = y1 + r2 * (float)Math.cos(Math.toRadians(a2));

        //actual calculations ***turned off, as long as figure out the speed issue
//        float num1 = -g * (2 * m1 + m2) * (float)Math.sin(Math.toRadians(a1));
//        float num2 = -m2 * g * (float)Math.sin(Math.toRadians(a1 - 2 * a2));
//        float num3 = -2 * (float)Math.sin(Math.toRadians(a1 - a2)) * m2;
//        float num4 = a2_v * a2_v * r2 + a1_v * a1_v * r1 * (float)Math.cos(Math.toRadians(a1 - a2));
//        float den = r1 * (2 * m1 + m2 - m2 * (float)Math.cos(Math.toRadians(2 * a1 - 2 * a2)));
//        float a1_a = (num1 + num2 + num3 * num4) / den;
//
//        num1 = 2 * (float)Math.sin(Math.toRadians(a1 - a2));
//        num2 = a1_v * a1_v * r1 * (m1 + m2);
//        num3 = g * (m1 + m2) * (float)Math.cos(Math.toRadians(a1));
//        num4 = a2_v * a2_v * r2 * m2 * (float)Math.cos(Math.toRadians(a1 - a2));
//        den = r2 * (2 * m1 + m2 - m2 * (float)Math.cos(Math.toRadians(2 * a1 - 2 * a2)));
//        float a2_a = (num1 * (num2 + num3 + num4)) / den;
//
//
//        a1_v += a1_a;
//        a2_v += a2_a;
//        a1 += a1_v;
//        a2 += a2_v;
        //For testing the speed **the app should be very fast, when we are incrementing the angles by 0.1
        a1 += 1.0; //even with 1 it is very slow
        a2 -= 1.5;

        //displaying the string and the ball in the end
        pathThread1.lineTo(x1, y1);
        canvas.drawCircle(x1, y1, 20, paintCircle);
        pathThread2.lineTo(x2, y2);
        canvas.drawCircle(x2, y2, 20, paintCircle);
        canvas.drawPath(pathThread1, paintThread);
        canvas.drawPath(pathThread2, paintThread);

        invalidate();
    }

}
