package com.example.pendulumtestjava;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import android.widget.TextView;
import java.util.Timer;
import java.util.TimerTask;
import static android.content.ContentValues.TAG;


public class SinglePendulum extends AppCompatActivity {
    private TextView stick, ball, middle;
    private Handler handler = new Handler();
    private Timer timer = new Timer();
    private DrawingPath path;
    private double widthMiddleBall, heightMiddleBall;
    private double widthMiddle, heightPoint;
    private double r = 400;
    private double x, y;
    private double a = Math.PI / 2;
    private float gravity = (float) 0.4;
    private double angularAcc, angularVel;
    private float damping = (float) 0.999;
    private int trace = 100;
    private boolean onHold = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_pendulum);

        stick = (TextView) findViewById(R.id.stickBox);
        ball = (TextView) findViewById(R.id.ballPaint);
        middle = (TextView) findViewById(R.id.middlePaint);
        path = (DrawingPath) findViewById(R.id.path);

        stick.setLayoutParams(new FrameLayout.LayoutParams(4, (int) r));

        widthMiddleBall = (getWindowManager().getDefaultDisplay().getWidth() / 2) - 30;
        heightMiddleBall = (getWindowManager().getDefaultDisplay().getHeight() / 2) - 30;
        widthMiddle = getWindowManager().getDefaultDisplay().getWidth() / 2;
        heightPoint = getWindowManager().getDefaultDisplay().getHeight() / 2;

        update();
    }

    public void update() {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(() -> {
                    if (onHold == false) {
                        calc();
                    }
                    draw();
                });
            }
        }, 0, 10);
    }

    public void draw() {
        stick.setRotation((float) Math.toDegrees(-a));
        stick.setX((float) (widthMiddle - 2));
        stick.setY((float) (heightPoint));

        ball.setX((float) x);
        ball.setY((float) y);

        middle.setX((float) (widthMiddle - 5));
        middle.setY((float) (heightPoint - 5));
        middle.setBackgroundResource(R.color.colorPrimaryDark);
    }

    public void calc() {
        angularAcc = (-1 * gravity / r) * Math.sin(a);
        angularVel += angularAcc;
        angularVel *= damping;
        a += angularVel;
        x = widthMiddleBall + (r * Math.sin(a));
        y = heightMiddleBall + (r * Math.cos(a));

        path.setVariables(x, y, trace);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int newx = (int) event.getX() - 28;
        int newy = (int) event.getY() - 90;

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:

                onHold = true;
                x = newx;
                y = newy;
                if (newy - heightMiddleBall > 0) {
                    a = Math.atan((newx - widthMiddleBall) / (newy - heightMiddleBall));

                }
                if(newy - heightMiddleBall < 0) {
                    a = Math.atan((newx - widthMiddleBall) / (newy - heightMiddleBall)) + Math.PI;

                }
                if(newy - heightMiddleBall == 0)
                {
                    if(newx-widthMiddleBall >= 0)
                    {
                        a = Math.PI / 2;
                    } else
                    {
                        a = -(Math.PI / 2);
                    }

                }
                r = Math.sqrt(((newx - widthMiddleBall) * (newx - widthMiddleBall)) + ((newy - heightMiddleBall) * (newy - heightMiddleBall)));
                stick.setLayoutParams(new FrameLayout.LayoutParams(5, (int) r));
                path.setVariables(x, y, trace);
                angularVel = 0;
                angularAcc = 0;

                break;
            case MotionEvent.ACTION_UP:
                onHold = false;
                break;
        }
        return false;
    }
}
