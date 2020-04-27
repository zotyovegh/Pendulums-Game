package com.example.pendulumtestjava.singlePendulum;

import androidx.lifecycle.ViewModel;

import com.example.pendulumtestjava.DrawingPath;
import com.example.pendulumtestjava.fragments.listFragment.shared.DbViewModel;
import com.example.pendulumtestjava.fragments.listFragment.singleP.SinglePObject;
import com.google.gson.Gson;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SinglePendulumViewModel extends ViewModel {

    private SinglePendulumModel model = SinglePendulumModel.getInstance();

    public double a = model.getA();
    private float gravity = model.getGravity();
    public float damping = model.getDamping();
    public int traceDrawColor = model.getTraceDrawColor();
    public int ballDrawColor = model.getBallDrawColor();
    private boolean endlessTrace = model.isEndlessTrace();
    public boolean isTraceOn = model.isTraceOn();
    public int trace = model.getTrace();
    public double r = model.getR();
    private double widthMiddleBall, heightMiddleBall;
    private double angularAcc, angularVel;
    public double x, y;
    private DrawingPath path;
    private DbViewModel dbViewModel;

    public void defineVariables(double widthMiddleBall, double heightMiddleBall, DrawingPath path, DbViewModel dbViewModel) {

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

        calcPositions();
        drawTrace();
    }

    public void calcPositions()
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
}
