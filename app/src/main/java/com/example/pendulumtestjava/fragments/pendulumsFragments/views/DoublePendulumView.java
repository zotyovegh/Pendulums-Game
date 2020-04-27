package com.example.pendulumtestjava.fragments.pendulumsFragments.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.pendulumtestjava.R;
import com.example.pendulumtestjava.fragments.savingsFragment.shared.DbViewModel;
import com.example.pendulumtestjava.fragments.pendulumsFragments.settings.DoublePendulumSettings;
import com.example.pendulumtestjava.fragments.pendulumsFragments.viewModels.DoublePendulumViewModel;
import com.example.pendulumtestjava.fragments.pendulumsFragments.models.DoublePendulumModel;

import java.util.Timer;
import java.util.TimerTask;

public class DoublePendulumView extends AppCompatActivity implements View.OnClickListener {
    private TextView stick, stick2, ball, ball2, middle;

    private Handler handler = new Handler();
    private Timer timer = new Timer();
    private DrawingPathView path, path2;
    private DoublePendulumSettings doublePendulumSettings = new DoublePendulumSettings();
    private DoublePendulumModel model = DoublePendulumModel.getInstance();
    private DoublePendulumViewModel viewModel;
    private DbViewModel dbViewModel;

    private double widthMiddle, heightPoint;
    private boolean onHold = false;
    private boolean stop = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_double_pendulum);

        dbViewModel = new ViewModelProvider(this).get(DbViewModel.class);
        viewModel = new ViewModelProvider(this).get(DoublePendulumViewModel.class);

        stick = findViewById(R.id.stickBox);
        stick2 = findViewById(R.id.stickBox2);
        ball = findViewById(R.id.ballPaint);
        ball2 = findViewById(R.id.ballPaint2);
        middle = findViewById(R.id.middlePaint);
        path = findViewById(R.id.path);
        path2 = findViewById(R.id.path2);
        Button reset = findViewById(R.id.reset);
        Button pause = findViewById(R.id.pause);
        Button settings = findViewById(R.id.settings);
        Button save = findViewById(R.id.save);

        Intent intent = getIntent();
        if(intent.hasExtra("path"))
        {
            path.setPath(model.getPoints1());
            path2.setPath(model.getPoints2());
        }

        widthMiddle = getWindowManager().getDefaultDisplay().getWidth() / 2;
        heightPoint = getWindowManager().getDefaultDisplay().getHeight() / 8;
        viewModel.defineVariables(widthMiddle, heightPoint, path, path2, dbViewModel);

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
                        viewModel.calc();
                    }
                    drawObjects();
                });
            }
        },0, 15);
    }

    public void drawObjects() {
        stick.setRotation((float)Math.toDegrees(-viewModel.a1));
        stick.setX((float)(widthMiddle-2));
        stick.setY((float)(heightPoint));

        stick2.setRotation((float)Math.toDegrees(-viewModel.a2));
        stick2.setX((float)(viewModel.x1+28));
        stick2.setY((float)(viewModel.y1+30));

        ball.setX((float)viewModel.x1);
        ball.setY((float)viewModel.y1);
        ball2.setX((float)viewModel.x2);
        ball2.setY((float)viewModel.y2);

        stick.setLayoutParams(new FrameLayout.LayoutParams(4, (int) viewModel.r1));
        stick2.setLayoutParams(new FrameLayout.LayoutParams(4, (int) viewModel.r2));

        ball.getBackground().setColorFilter(viewModel.ball1Color, PorterDuff.Mode.SRC_ATOP);
        ball2.getBackground().setColorFilter(viewModel.ball2Color, PorterDuff.Mode.SRC_ATOP);

        if(model.isStop())
        {
            stop = true;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int newx = (int) event.getX() - 28;
        int newy = (int) event.getY() - 90;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                double difference1 = Math.sqrt(((newx - viewModel.x1) * (newx - viewModel.x1)) + ((newy - viewModel.y1) * (newy - viewModel.y1)));
                double difference2 = Math.sqrt(((newx - viewModel.x2) * (newx - viewModel.x2)) + ((newy - viewModel.y2) * (newy - viewModel.y2)));

                if(difference1 < difference2)
                {
                    viewModel.onHold(true, newx, newy);
                    stick.setLayoutParams(new FrameLayout.LayoutParams(5, (int) viewModel.r1));
                } else{
                    viewModel.onHold(false, newx, newy);
                    stick2.setLayoutParams(new FrameLayout.LayoutParams(5, (int) viewModel.r2));
                }
                viewModel.calcPositions();
                onHold = true;
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
                viewModel.resetVariables();
                viewModel.drawTraces();
                drawObjects();
                break;
            case R.id.pause:
                model.setStop(false);
                stop = !stop;
                break;
            case R.id.settings:
                doublePendulumSettings.show(getSupportFragmentManager(), "Settings");
                break;
            case R.id.save:
                viewModel.save();
                break;
        }
    }
}
