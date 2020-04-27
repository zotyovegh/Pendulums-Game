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
import com.example.pendulumtestjava.fragments.pendulumsFragments.models.SinglePendulumModel;
import com.example.pendulumtestjava.fragments.pendulumsFragments.settings.SinglePendulumSettings;
import com.example.pendulumtestjava.fragments.pendulumsFragments.viewModels.SinglePendulumViewModel;

import java.util.Timer;
import java.util.TimerTask;


public class SinglePendulumView extends AppCompatActivity implements View.OnClickListener {
    private TextView stick, ball, middle;
    private Handler handler = new Handler();
    private Timer timer = new Timer();
    private SinglePendulumSettings singlePendulumSettings = new SinglePendulumSettings();
    private SinglePendulumModel model = SinglePendulumModel.getInstance();

    private SinglePendulumViewModel viewModel;
    private DbViewModel dbViewModel;
    private DrawingPathView path;
    private double widthMiddle, heightPoint;
    private boolean onHold, stop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_pendulum);

        dbViewModel = new ViewModelProvider(this).get(DbViewModel.class);
        viewModel = new ViewModelProvider(this).get(SinglePendulumViewModel.class);

        stick = findViewById(R.id.stickBox);
        ball = findViewById(R.id.ballPaint);
        middle = findViewById(R.id.middlePaint);
        path = findViewById(R.id.path);
        Button reset = findViewById(R.id.reset);
        Button pause = findViewById(R.id.pause);
        Button settings = findViewById(R.id.settings);
        Button save = findViewById(R.id.save);

        Intent intent = getIntent();
        if(intent.hasExtra("path"))
        {
            path.setPath(model.getPoints());
        }

        widthMiddle = getWindowManager().getDefaultDisplay().getWidth() / 2;
        heightPoint = getWindowManager().getDefaultDisplay().getHeight() / 2;
        viewModel.defineVariables(widthMiddle, heightPoint, path, dbViewModel);

        reset.setOnClickListener(this);
        pause.setOnClickListener(this);
        settings.setOnClickListener(this);
        save.setOnClickListener(this);

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
        }, 0, 10);
    }

    public void drawObjects() {
        stick.setRotation((float) Math.toDegrees(-viewModel.a));
        stick.setX((float) (widthMiddle - 2));
        stick.setY((float) (heightPoint));

        ball.setX((float) viewModel.x);
        ball.setY((float) viewModel.y);

        middle.setX((float) (widthMiddle - 5));
        middle.setY((float) (heightPoint - 5));

        middle.setBackgroundResource(R.color.colorPrimaryDark);
        stick.setLayoutParams(new FrameLayout.LayoutParams(4, (int) viewModel.r));
        ball.getBackground().setColorFilter(viewModel.ballDrawColor, PorterDuff.Mode.SRC_ATOP);

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
                viewModel.onHold(newx, newy);
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
                viewModel.drawTrace();
                drawObjects();
                break;
            case R.id.pause:
                model.setStop(false);
                stop = !stop;
                break;
            case R.id.settings:
                singlePendulumSettings.show(getSupportFragmentManager(), "Settings");
                break;
            case R.id.save:
                viewModel.save();
                break;
        }
    }

}
