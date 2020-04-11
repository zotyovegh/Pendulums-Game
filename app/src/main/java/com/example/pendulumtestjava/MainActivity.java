package com.example.pendulumtestjava;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.os.Handler;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity{
    private ImageView ball, ball2, middleBall;
    private TextView stickB, stickB2;

    private Handler handler = new Handler();
    private Timer timer = new Timer();

    double r1 = 250;
    double r2 = 250;
    double a1 = Math.PI / 2;
    double a2 = Math.PI / 2;
    double x1, y1, x2, y2;
    double widthMiddleBall;
    double heightMiddleBall;
    double widthMiddle;
    double heightPoint;
    double a1_v = 0;
    double a2_v = 0;
    double g = 1;
    double m1 = 20;
    double m2 = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ball = (ImageView) findViewById(R.id.ball);
        ball2 = (ImageView) findViewById(R.id.ball2) ;
        middleBall = (ImageView) findViewById(R.id.middleBall) ;
        stickB = (TextView) findViewById(R.id.stickBox);
        stickB2 = (TextView) findViewById(R.id.stickBox2);

        ViewGroup.LayoutParams param = stickB.getLayoutParams();
        param.height = (int)Math.round(r1);
        ViewGroup.LayoutParams param2 = stickB2.getLayoutParams();
        param2.height = (int)Math.round(r2);
        stickB2.setLayoutParams(param2);

        widthMiddleBall = (getWindowManager().getDefaultDisplay().getWidth() / 2) - 34;
        heightMiddleBall = (getWindowManager().getDefaultDisplay().getHeight() / 8) - 34;
        widthMiddle = getWindowManager().getDefaultDisplay().getWidth() / 2;
        heightPoint = getWindowManager().getDefaultDisplay().getHeight() / 8;
        middleBall.setX((float)(widthMiddle - 5));
        middleBall.setY((float)(heightPoint - 5));

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(() -> {
                    calc();
                    draw();
                });
            }
        },0, 10);
    }

    public void draw(){
        stickB.setRotation((float)Math.toDegrees(-a1));
        stickB.setX((float)(widthMiddle-2));
        stickB.setY((float)(heightPoint));

        stickB2.setRotation((float)Math.toDegrees(-a2));
        stickB2.setX((float)(x1+32));
        stickB2.setY((float)(y1+34));

        ball.setX((float)x1);
        ball.setY((float)y1);
        ball2.setX((float)x2);
        ball2.setY((float)y2);
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

        x1 = widthMiddleBall + (r1 * Math.sin(a1));
        y1 = heightMiddleBall + (r1 * Math.cos(a1));
        x2 = x1 + (r2 * Math.sin(a2));
        y2 = y1 + (r2 * Math.cos(a2));

        a1_v += a1_a;
        a2_v += a2_a;
        a1 += a1_v;
        a2 += a2_v;
    }
}
