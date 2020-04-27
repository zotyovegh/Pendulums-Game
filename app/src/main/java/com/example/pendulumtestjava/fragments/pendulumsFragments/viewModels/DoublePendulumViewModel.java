package com.example.pendulumtestjava.fragments.pendulumsFragments.viewModels;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.example.pendulumtestjava.fragments.pendulumsFragments.views.DrawingPathView;
import com.example.pendulumtestjava.fragments.savingsFragment.doubleP.DoublePObject;
import com.example.pendulumtestjava.fragments.savingsFragment.shared.DbViewModel;
import com.example.pendulumtestjava.fragments.pendulumsFragments.models.DoublePendulumModel;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DoublePendulumViewModel extends ViewModel {
    private DoublePendulumModel model = DoublePendulumModel.getInstance();

    public double widthMiddleBall, heightMiddleBall;
    public double x1, y1, x2, y2;

    public double a1_v = 0;
    public double a2_v = 0;
    public double r1 = model.getR1();
    public double r2 = model.getR2();
    public double a1 = model.getA1();
    public double a2 = model.getA2();
    public double g = model.getG();
    public double m1 = model.getM1();
    public double m2 = model.getM2();
    public int trace1 = model.getTrace1();
    public int trace2 = model.getTrace2();
    public int trace1Color = model.getTrace1Color();
    public int trace2Color = model.getTrace2Color();
    public int ball1Color = model.getBall1Color();
    public int ball2Color = model.getBall2Color();
    public boolean endlessTrace1 = model.isEndlessTrace1();
    public boolean endlessTrace2 = model.isEndlessTrace2();
    public boolean isTrace1On = model.isTrace1On();
    public boolean isTrace2On = model.isTrace2On();
    public DrawingPathView path, path2;

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

        calcPositions();
        drawTraces();
    }

    public void calcPositions() {
        x1 = widthMiddleBall + (r1 * Math.sin(a1));
        y1 = heightMiddleBall + (r1 * Math.cos(a1));
        x2 = x1 + (r2 * Math.sin(a2));
        y2 = y1 + (r2 * Math.cos(a2));
    }

    public void drawTraces()
    {
        if (isTrace1On) {

            path.setVariables(x1, y1, trace1, trace1Color, endlessTrace1);
        }
        if (isTrace2On) {
            path2.setVariables(x2, y2, trace2, trace2Color, endlessTrace2);
        }
    }

    public void onHold(boolean check, int newx, int newy)
    {
        if(check)
        {
            Log.i("TAG", "FIRST");
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
        }else{
            Log.i("TAG", "SECOND");
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
        }
        a1_v = 0;
        a2_v = 0;
        drawTraces();
    }

    public void save() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
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



}
