package com.example.pendulumtestjava.fragments.pendulumFragments.viewModels.Pendulums;


import android.annotation.SuppressLint;

import androidx.lifecycle.ViewModel;

import com.example.pendulumtestjava.fragments.pendulumFragments.views.DrawingPathView;
import com.example.pendulumtestjava.fragments.savingsFragment.doubleP.DoublePObject;
import com.example.pendulumtestjava.fragments.savingsFragment.shared.DbViewModel;
import com.example.pendulumtestjava.fragments.pendulumFragments.repositories.DoublePModelRepo;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DoublePendulumViewModel extends ViewModel {
    private DoublePModelRepo model = DoublePModelRepo.getInstance();

    private double widthMiddleBall, heightMiddleBall;
    private double x1, y1, x2, y2;

    private double a1_v = 0;
    private double a2_v = 0;
    private double r1 = model.getR1();
    private double r2 = model.getR2();
    private double a1 = model.getA1();
    private double a2 = model.getA2();
    private float g = model.getG();
    private double m1 = model.getM1();
    private double m2 = model.getM2();
    private int trace1 = model.getTrace1();
    private int trace2 = model.getTrace2();
    private int trace1Color = model.getTrace1Color();
    private int trace2Color = model.getTrace2Color();
    private int ball1Color = model.getBall1Color();
    private int ball2Color = model.getBall2Color();
    private boolean endlessTrace1 = model.isEndlessTrace1();
    private boolean endlessTrace2 = model.isEndlessTrace2();
    private boolean isTrace1On = model.isTrace1On();
    private boolean isTrace2On = model.isTrace2On();
    @SuppressLint("StaticFieldLeak")
    private DrawingPathView path, path2;

    private DbViewModel dbViewModel;

    public void defineVariables(double widthMiddleBall, double heightMiddlePoint, DrawingPathView path, DrawingPathView path2, DbViewModel dbViewModel) {
        this.widthMiddleBall = widthMiddleBall - 30;
        this.heightMiddleBall = heightMiddlePoint - 30;
        this.path = path;
        this.path2 = path2;
        this.dbViewModel = dbViewModel;
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

        optimization();
        calcPositions();
        drawTraces();
    }

    private void optimization() {
        double a1Degree = (Math.toDegrees(a1)) % 360;
        double a2Degree = (Math.toDegrees(a2)) % 360;
        a1 = Math.toRadians(a1Degree);
        a2 = Math.toRadians(a2Degree);
        //In case the pendulum would get to a fastened loop, where it would crash out because of the limitations of the 'double':
        a1_v %= 6.00000000000E150;
        a2_v %= 6.00000000000E150;
    }

    public void calcPositions() {
        x1 = widthMiddleBall + (r1 * Math.sin(a1));
        y1 = heightMiddleBall + (r1 * Math.cos(a1));
        x2 = x1 + (r2 * Math.sin(a2));
        y2 = y1 + (r2 * Math.cos(a2));
    }

    public void drawTraces() {
        if (isTrace1On) {

            path.setVariables(x1, y1, trace1, trace1Color, endlessTrace1);
        }
        if (isTrace2On) {
            path2.setVariables(x2, y2, trace2, trace2Color, endlessTrace2);
        }
    }

    public void onHold(boolean check, int newx, int newy) {
        if (check) {
            x1 = newx;
            y1 = newy;
            if (newy - heightMiddleBall > 0) {
                a1 = Math.atan((newx - widthMiddleBall) / (newy - heightMiddleBall));
            }
            if (newy - heightMiddleBall < 0) {
                a1 = Math.atan((newx - widthMiddleBall) / (newy - heightMiddleBall)) + Math.PI;
            }
            if (newy - heightMiddleBall == 0) {
                if (newx - widthMiddleBall >= 0) {
                    a1 = Math.PI / 2;
                } else {
                    a1 = -(Math.PI / 2);
                }
            }
            r1 = Math.sqrt(((newx - widthMiddleBall) * (newx - widthMiddleBall)) + ((newy - heightMiddleBall) * (newy - heightMiddleBall)));
        } else {
            x2 = newx;
            y2 = newy;
            if (newy - y1 > 0) {
                a2 = Math.atan((newx - x1) / (newy - y1));
            }
            if (newy - y1 < 0) {
                a2 = Math.atan((newx - x1) / (newy - y1)) + Math.PI;
            }
            if (newy - y1 == 0) {
                if (newx - x1 >= 0) {
                    a2 = Math.PI / 2;
                } else {
                    a2 = -(Math.PI / 2);
                }
            }
            r2 = Math.sqrt(((newx - x1) * (newx - x1)) + ((newy - y1) * (newy - y1)));
        }
        a1_v = 0;
        a2_v = 0;
        drawTraces();
    }

    public void save() {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String millisInString = dateFormat.format(new Date());

        String json = new Gson().toJson(path.getArray());
        String json2 = new Gson().toJson(path2.getArray());

        DoublePObject pendulum = new DoublePObject(a1, a2, r1, r2, g, m1, m2, trace1, trace2, trace1Color, trace2Color,
                ball1Color, ball2Color, json, json2, millisInString, endlessTrace1, endlessTrace2, isTrace1On, isTrace2On);
        dbViewModel.insertDoubleP(pendulum);
    }

    public void resetVariables() {
        r1 = model.getR1();
        r2 = model.getR2();
        a1 = model.getA1();
        a2 = model.getA2();
        g = model.getG();
        m1 = model.getM1();
        m2 = model.getM2();
        trace1 = model.getTrace1();
        trace2 = model.getTrace2();
        trace1Color = model.getTrace1Color();
        trace2Color = model.getTrace2Color();
        ball1Color = model.getBall1Color();
        ball2Color = model.getBall2Color();
        endlessTrace1 = model.isEndlessTrace1();
        endlessTrace2 = model.isEndlessTrace2();
        isTrace1On = model.isTrace1On();
        isTrace2On = model.isTrace2On();

        path.reset();
        path2.reset();
        a1_v = 0;
        a2_v = 0;

        calcPositions();
    }

    public double getX1() {
        return x1;
    }

    public double getY1() {
        return y1;
    }

    public double getX2() {
        return x2;
    }

    public double getY2() {
        return y2;
    }

    public double getR1() {
        return r1;
    }

    public double getR2() {
        return r2;
    }

    public double getA1() {
        return a1;
    }

    public double getA2() {
        return a2;
    }

    public int getBall1Color() {
        return ball1Color;
    }

    public int getBall2Color() {
        return ball2Color;
    }

    public int getSizeCorrecter(boolean isFirstBall) {
        //original is 60
        return (getBallSize(isFirstBall) - 60) / 2;
    }

    public int getBallSize(boolean isFirstBall) {
        //Calc the new size from mass
        if (isFirstBall) {

            return 40 + ((int) (0.8 * model.getM1()));
        } else {
            return 40 + ((int) (0.8 * model.getM2()));
        }
    }
}
