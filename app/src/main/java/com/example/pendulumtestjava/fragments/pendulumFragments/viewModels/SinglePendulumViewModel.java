package com.example.pendulumtestjava.fragments.pendulumFragments.viewModels;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.example.pendulumtestjava.fragments.connection.RandomizerRepository;
import com.example.pendulumtestjava.fragments.pendulumFragments.views.DrawingPathView;
import com.example.pendulumtestjava.fragments.savingsFragment.shared.DbViewModel;
import com.example.pendulumtestjava.fragments.savingsFragment.singleP.SinglePObject;
import com.example.pendulumtestjava.fragments.pendulumFragments.models.SinglePendulumModel;
import com.google.gson.Gson;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SinglePendulumViewModel extends ViewModel {

    private SinglePendulumModel model = SinglePendulumModel.getInstance();
    private double a = model.getA();
    private float gravity = model.getGravity();
    private float damping = model.getDamping();
    private int traceDrawColor = model.getTraceDrawColor();
    private int ballDrawColor = model.getBallDrawColor();
    private boolean endlessTrace = model.isEndlessTrace();
    private boolean isTraceOn = model.isTraceOn();
    private int trace = model.getTrace();
    private double r = model.getR();
    private double widthMiddleBall, heightMiddleBall;
    private double angularAcc, angularVel;
    private double x, y;
    @SuppressLint("StaticFieldLeak")
    private DrawingPathView path;
    private DbViewModel dbViewModel;

    public void defineVariables(double widthMiddleBall, double heightMiddleBall, DrawingPathView path, DbViewModel dbViewModel) {

        this.widthMiddleBall = widthMiddleBall - 30;
        this.heightMiddleBall = heightMiddleBall - 30;
        this.path = path;
        this.dbViewModel = dbViewModel;
    }

    public void calc() {
        angularAcc = (-1 * gravity / r) * Math.sin(a);
        angularVel += angularAcc;
        angularVel *= damping;
        a += angularVel;

        optimization();
        calcPositions();
        drawTrace();
    }

    private void optimization()
    {
        double aDegree = (Math.toDegrees(a)) % 360;
        a = Math.toRadians(aDegree);
        angularVel %= 6.00000000000E280;
    }

    private void calcPositions()
    {
        x = widthMiddleBall + (r * Math.sin(a));
        y = heightMiddleBall + (r * Math.cos(a));
    }

    public void drawTrace() {
        if(isTraceOn) {
            path.setVariables(x, y, trace, traceDrawColor, endlessTrace);
        }
    }

    public void onHold(int newx, int newy) {
        x = newx;
        y = newy;
        if (newy - heightMiddleBall > 0) {
            a = Math.atan((newx - widthMiddleBall) / (newy - heightMiddleBall));
        }
        if (newy - heightMiddleBall < 0) {
            a = Math.atan((newx - widthMiddleBall) / (newy - heightMiddleBall)) + Math.PI;
        }
        if (newy - heightMiddleBall == 0) {
            if (newx - widthMiddleBall >= 0) {
                a = Math.PI / 2;
            } else {
                a = -(Math.PI / 2);
            }
        }
        r = Math.sqrt(((newx - widthMiddleBall) * (newx - widthMiddleBall)) + ((newy - heightMiddleBall) * (newy - heightMiddleBall)));
        angularVel = 0;
        angularAcc = 0;
        drawTrace();
    }

    public void save(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String millisInString  = dateFormat.format(new Date());

        String json = new Gson().toJson(path.getArray());

        SinglePObject pendulum = new SinglePObject(a, r, gravity, damping, trace, ballDrawColor, traceDrawColor, json, millisInString, endlessTrace, isTraceOn);
        dbViewModel.insertSingleP(pendulum);
    }

    public void resetVariables() {
        a = model.getA();
        r = model.getR();
        gravity = model.getGravity();
        damping = model.getDamping();
        trace = model.getTrace();
        traceDrawColor = model.getTraceDrawColor();
        ballDrawColor = model.getBallDrawColor();
        endlessTrace = model.isEndlessTrace();
        isTraceOn = model.isTraceOn();

        path.reset();
        angularVel = 0;
        angularAcc = 0;

        calcPositions();
    }

    public double getA() {
        return a;
    }

    public int getBallDrawColor() {
        return ballDrawColor;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getR() {
        return r;
    }
}
