package com.example.pendulumtestjava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

import static android.content.ContentValues.TAG;

public class DoublePendulum extends AppCompatActivity {
    private TextView stick, stick2, ball, ball2, middle;

    private Handler handler = new Handler();
    private Timer timer = new Timer();
    private DrawingPath path;

    private double r1 = 250;
    private double r2 = 250;
    private double a1 = Math.PI / 2;
    private double a2 = Math.PI / 2;
    private double x1, y1, x2, y2;
    private double widthMiddleBall, heightMiddleBall;
    private double widthMiddle, heightPoint;
    private double a1_v = 0;
    private double a2_v = 0;
    private double g = 1;
    private double m1 = 20;
    private double m2 = 20;
    private int trace = 50000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_double_pendulum);

        stick = (TextView) findViewById(R.id.stickBox);
        stick2 = (TextView) findViewById(R.id.stickBox2);
        ball = (TextView) findViewById(R.id.ballPaint);
        ball2 = (TextView) findViewById(R.id.ballPaint2);
        middle = (TextView) findViewById(R.id.middlePaint);
        path = (DrawingPath) findViewById(R.id.path);

        ViewGroup.LayoutParams param = stick.getLayoutParams();
        param.height = (int)Math.round(r1);
        ViewGroup.LayoutParams param2 = stick2.getLayoutParams();
        param2.height = (int)Math.round(r2);

        widthMiddleBall = (getWindowManager().getDefaultDisplay().getWidth() / 2) - 30;
        heightMiddleBall = (getWindowManager().getDefaultDisplay().getHeight() / 8) - 30;
        widthMiddle = getWindowManager().getDefaultDisplay().getWidth() / 2;
        heightPoint = getWindowManager().getDefaultDisplay().getHeight() / 8;


        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(() -> {
                    calc();
                    draw();
                });
            }
        },0, 15);
    }

    public void draw(){
        stick.setRotation((float)Math.toDegrees(-a1));
        stick.setX((float)(widthMiddle-2));
        stick.setY((float)(heightPoint));

        stick2.setRotation((float)Math.toDegrees(-a2));
        stick2.setX((float)(x1+28));
        stick2.setY((float)(y1+30));

        ball.setX((float)x1);
        ball.setY((float)y1);
        ball2.setX((float)x2);
        ball2.setY((float)y2);

        middle.setX((float)(widthMiddle -5));
        middle.setY((float)(heightPoint - 5));
        middle.setBackgroundResource(R.color.colorPrimaryDark);
    }

    public void calc()
    {
        double num1 = -g * (2 * m1 + m2) * (Math.sin((a1)));
        double num2 = -m2 * g * (Math.sin(a1 - (2 * a2)));
        double num3 = -2 * (Math.sin(a1 - a2)) * m2;
        double num4 = (a2_v * a2_v * r2) + (a1_v * a1_v * r1 * (Math.cos((a1 - a2))));
        double den = r1 * (2 * m1) + m2 - (m2 * (Math.cos((2 * a1) - (2 * a2))));
        double a1_a = (num1 + num2 + (num3 * num4)) / den;

        num1 = 2 * (Math.sin((a1 - a2)));
        num2 = a1_v * a1_v * r1 * (m1 + m2);
        num3 = g * (m1 + m2) * (Math.cos((a1)));
        num4 = a2_v * a2_v * r2 * m2 * (Math.cos((a1 - a2)));
        den = r2 * ((2 * m1) + m2 - (m2 * (Math.cos((2 * a1) - (2 * a2)))));
        double a2_a = (num1 * (num2 + num3 + num4)) / den;

        a1_v += a1_a;
        a2_v += a2_a;
        a1 += a1_v;
        a2 += a2_v;

        x1 = widthMiddleBall + (r1 * Math.sin(a1));
        y1 = heightMiddleBall + (r1 * Math.cos(a1));
        x2 = x1 + (r2 * Math.sin(a2));
        y2 = y1 + (r2 * Math.cos(a2));

       path.setVariables(x2, y2, trace);
    }
}
