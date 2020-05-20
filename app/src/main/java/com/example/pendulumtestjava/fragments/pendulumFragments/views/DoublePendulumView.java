package com.example.pendulumtestjava.fragments.pendulumFragments.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.pendulumtestjava.R;
import com.example.pendulumtestjava.fragments.savingsFragment.shared.DbViewModel;
import com.example.pendulumtestjava.fragments.pendulumFragments.settings.DoublePendulumSettings;
import com.example.pendulumtestjava.fragments.pendulumFragments.viewModels.DoublePendulumViewModel;
import com.example.pendulumtestjava.fragments.pendulumFragments.models.DoublePendulumModel;

import java.util.Timer;
import java.util.TimerTask;

public class DoublePendulumView extends AppCompatActivity implements View.OnClickListener {
    private TextView stick;
    private TextView stick2;
    private TextView ball;
    private TextView ball2;

    private Handler handler = new Handler();
    private Timer timer = new Timer();
    private DoublePendulumSettings doublePendulumSettings = new DoublePendulumSettings();
    private DoublePendulumModel model = DoublePendulumModel.getInstance();
    private DoublePendulumViewModel viewModel;

    private double widthMiddle, heightPoint;
    private boolean onHold = false;
    private boolean stop = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_double_pendulum);

        DbViewModel dbViewModel = new ViewModelProvider(this).get(DbViewModel.class);
        viewModel = new ViewModelProvider(this).get(DoublePendulumViewModel.class);

        stick = findViewById(R.id.stickBox);
        stick2 = findViewById(R.id.stickBox2);
        ball = findViewById(R.id.ballPaint);
        ball2 = findViewById(R.id.ballPaint2);
        setBallSize();

        TextView middle = findViewById(R.id.middlePaint);
        DrawingPathView path = findViewById(R.id.path);
        DrawingPathView path2 = findViewById(R.id.path2);
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
        stick.setRotation((float)Math.toDegrees(-viewModel.getA1()));
        stick.setX((float)(widthMiddle-2));
        stick.setY((float)(heightPoint));

        stick2.setRotation((float)Math.toDegrees(-viewModel.getA2()));
        stick2.setX((float)(viewModel.getX1()+28));
        stick2.setY((float)(viewModel.getY1()+30));


        ball.setX((float)viewModel.getX1()-(float)viewModel.getSizeCorrecter(true));
        ball.setY((float)viewModel.getY1()-(float)viewModel.getSizeCorrecter(true));
        ball2.setX((float)viewModel.getX2()-(float)viewModel.getSizeCorrecter(false));
        ball2.setY((float)viewModel.getY2()-(float)viewModel.getSizeCorrecter(false));

        stick.setLayoutParams(new FrameLayout.LayoutParams(4, (int) viewModel.getR1()));
        stick2.setLayoutParams(new FrameLayout.LayoutParams(4, (int) viewModel.getR2()));

        ball.getBackground().setColorFilter(viewModel.getBall1Color(), PorterDuff.Mode.SRC_ATOP);
        ball2.getBackground().setColorFilter(viewModel.getBall2Color(), PorterDuff.Mode.SRC_ATOP);

        if(model.isStop())
        {
            stop = true;
        }
    }

    private void setBallSize()
    {
        ball.setLayoutParams(new FrameLayout.LayoutParams(viewModel.getBallSize(true), viewModel.getBallSize(true), FrameLayout.LayoutParams.WRAP_CONTENT));
        ball2.setLayoutParams(new FrameLayout.LayoutParams(viewModel.getBallSize(false), viewModel.getBallSize(false), FrameLayout.LayoutParams.WRAP_CONTENT));
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int newx = (int) event.getX() - 28;
        int newy = (int) event.getY() - 90;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                double difference1 = Math.sqrt(((newx - viewModel.getX1()) * (newx - viewModel.getX1())) + ((newy - viewModel.getY1()) * (newy - viewModel.getY1())));
                double difference2 = Math.sqrt(((newx - viewModel.getX2()) * (newx - viewModel.getX2())) + ((newy - viewModel.getY2()) * (newy - viewModel.getY2())));

                if(difference1 < difference2)
                {
                    viewModel.onHold(true, newx, newy);
                    stick.setLayoutParams(new FrameLayout.LayoutParams(5, (int) viewModel.getR1()));
                } else{
                    viewModel.onHold(false, newx, newy);
                    stick2.setLayoutParams(new FrameLayout.LayoutParams(5, (int) viewModel.getR2()));
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
                setBallSize();
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

    @Override
    protected void onPause() {
        super.onPause();
        timer.cancel();
        finish();
    }
}
