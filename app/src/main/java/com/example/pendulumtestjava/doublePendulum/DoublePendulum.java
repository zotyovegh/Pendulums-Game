package com.example.pendulumtestjava.doublePendulum;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.pendulumtestjava.DrawingPath;
import com.example.pendulumtestjava.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class DoublePendulum extends AppCompatActivity implements View.OnClickListener {
    private TextView stick, stick2, ball, ball2, middle;

    private Handler handler = new Handler();
    private Timer timer = new Timer();
    private DrawingPath path, path2;
    private Button reset, pause, settings, save;
    private DoublePSettings doublePSettings = new DoublePSettings();
    private DoublePData data = DoublePData.getInstance();

    private double widthMiddleBall, heightMiddleBall;
    private double widthMiddle, heightPoint;
    private double x1, y1, x2, y2;
    private boolean onHold = false;
    private boolean stop = false;
    private double a1_v = 0;
    private double a2_v = 0;
    private double r1 = data.getR1();
    private double r2 = data.getR2();
    private double a1 = data.getA1();
    private double a2 = data.getA2();
    private double g = data.getG();
    private double m1 = data.getM1();
    private double m2 = data.getM2();
    private int trace1 = data.getTrace1();
    private int trace2 = data.getTrace2();
    private int trace1Color = data.getTrace1Color();
    private int trace2Color = data.getTrace2Color();
    private int ball1Color = data.getBall1Color();
    private int ball2Color = data.getBall2Color();
    private boolean endlessTrace1 = data.isEndlessTrace1();
    private boolean endlessTrace2 = data.isEndlessTrace2();
    private boolean isTrace1On = data.isTrace1On();
    private boolean isTrace2On = data.isTrace2On();


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
        path2 = (DrawingPath) findViewById(R.id.path2);
        reset = (Button) findViewById(R.id.reset);
        pause = (Button) findViewById(R.id.pause);
        settings = (Button) findViewById(R.id.settings);
        save = (Button) findViewById(R.id.save);

        widthMiddle = getWindowManager().getDefaultDisplay().getWidth() / 2;
        heightPoint = getWindowManager().getDefaultDisplay().getHeight() / 8;
        widthMiddleBall = widthMiddle - 30;
        heightMiddleBall = heightPoint - 30;

        reset.setOnClickListener(this);
        pause.setOnClickListener(this);
        settings.setOnClickListener(this);
        save.setOnClickListener(this);

        middle.setX((float)(widthMiddle -5));
        middle.setY((float)(heightPoint - 5));
        middle.setBackgroundResource(R.color.colorPrimaryDark);

        update();
    }

    public void update() {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(() -> {
                    if (!stop && !onHold) {
                        calc();
                    }
                    draw();
                });
            }
        },0, 15);
    }

    public void draw() {
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
    }

    public void calc() {
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

        calcPositions();
    }

    public void calcPositions() {
        x1 = widthMiddleBall + (r1 * Math.sin(a1));
        y1 = heightMiddleBall + (r1 * Math.cos(a1));
        x2 = x1 + (r2 * Math.sin(a2));
        y2 = y1 + (r2 * Math.cos(a2));
        stick.setLayoutParams(new FrameLayout.LayoutParams(4, (int) r1));
        stick2.setLayoutParams(new FrameLayout.LayoutParams(4, (int) r2));

        if(isTrace1On) {
            path.setVariables(x1, y1, trace1, trace1Color, endlessTrace1);
        }
        if(isTrace2On) {
            path2.setVariables(x2, y2, trace2, trace2Color, endlessTrace2);
        }
        ball.getBackground().setColorFilter(ball1Color, PorterDuff.Mode.SRC_ATOP);
        ball2.getBackground().setColorFilter(ball2Color, PorterDuff.Mode.SRC_ATOP);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int newx = (int) event.getX() - 28;
        int newy = (int) event.getY() - 90;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                onHold = true;
                double difference1 = Math.sqrt(((newx - x1) * (newx - x1)) + ((newy - y1) * (newy - y1)));
                double difference2 = Math.sqrt(((newx - x2) * (newx - x2)) + ((newy - y2) * (newy - y2)));

                if(difference1 < difference2)
                {
                    x1 = newx;
                    y1 = newy;
                    if (newy - heightMiddleBall > 0) {
                        a1 = Math.atan((newx - widthMiddleBall) / (newy - heightMiddleBall));
                    }
                    if(newy - heightMiddleBall < 0) {
                        a1 = Math.atan((newx - widthMiddleBall) / (newy - heightMiddleBall)) + Math.PI;
                    }
                    if(newy - heightMiddleBall == 0)
                    {
                        if(newx-widthMiddleBall >= 0)
                        {
                            a1 = Math.PI / 2;
                        } else
                        {
                            a1 = -(Math.PI / 2);
                        }
                    }
                    r1 = Math.sqrt(((newx - widthMiddleBall) * (newx - widthMiddleBall)) + ((newy - heightMiddleBall) * (newy - heightMiddleBall)));
                    stick.setLayoutParams(new FrameLayout.LayoutParams(5, (int) r1));
                } else{
                    x2 = newx;
                    y2 = newy;
                    if (newy - y1 > 0) {
                        a2 = Math.atan((newx - x1) / (newy - y1));
                    }
                    if(newy - y1 < 0) {
                        a2 = Math.atan((newx - x1) / (newy - y1)) + Math.PI;
                    }
                    if(newy - y1 == 0)
                    {
                        if(newx-x1 >= 0)
                        {
                            a2 = Math.PI / 2;
                        } else
                        {
                            a2 = -(Math.PI / 2);
                        }
                    }
                    r2 = Math.sqrt(((newx - x1) * (newx - x1)) + ((newy - y1) * (newy - y1)));
                    stick2.setLayoutParams(new FrameLayout.LayoutParams(5, (int) r2));
                }
                calcPositions();
                break;
            case MotionEvent.ACTION_UP:
                onHold = false;
                break;
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.reset:
                resetVariables();
                break;
            case R.id.pause:
                stopCheck();
                break;
            case R.id.settings:
                openSettings();
                break;
            case R.id.save:
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String millisInString  = dateFormat.format(new Date());


        }
    }

    public void stopCheck() {
        if (stop) {
            stop = false;
        } else {
            stop = true;
        }
    }

    public void resetVariables() {
        r1 = data.getR1();
        r2 = data.getR2();
        a1 = data.getA1();
        a2 = data.getA2();
        g = data.getG();
        m1 = data.getM1();
        m2 = data.getM2();
        trace1 = data.getTrace1();
        trace2 = data.getTrace2();
        trace1Color = data.getTrace1Color();
        trace2Color = data.getTrace2Color();
        ball1Color = data.getBall1Color();
        ball2Color = data.getBall2Color();

        a1_v = 0;
        a2_v = 0;
        calcPositions();
        draw();
        endlessTrace1 = data.isEndlessTrace1();
        endlessTrace2 = data.isEndlessTrace2();
        isTrace1On = data.isTrace1On();
        isTrace2On = data.isTrace2On();
        path.reset();
        path2.reset();
    }

    public void openSettings()
    {
        doublePSettings.show(getSupportFragmentManager(), "Settings");
    }
}
