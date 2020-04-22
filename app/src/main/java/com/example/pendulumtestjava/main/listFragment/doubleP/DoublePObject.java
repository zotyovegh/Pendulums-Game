package com.example.pendulumtestjava.main.listFragment.doubleP;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "double_table")
public class DoublePObject {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private double a1;
    private double a2;
    private double r1;
    private double r2;
    private double g;
    private double m1;
    private double m2;
    private int trace1;
    private int trace2;
    private int traceColor1;
    private int traceColor2;
    private int ballColor1;
    private int ballColor2;
    private String points1Json;
    private String points2Json;
    private String timeStamp;
    private boolean endlessTrace1;
    private boolean endlessTrace2;
    private boolean isTrace1On;
    private boolean isTrace2On;



    public DoublePObject(double a1, double a2, double r1, double r2, double g,
                         double m1, double m2, int trace1, int trace2,
                         int traceColor1, int traceColor2, int ballColor1,
                         int ballColor2, String points1Json, String points2Json,
                         String timeStamp, boolean endlessTrace1, boolean endlessTrace2,
                         boolean isTrace1On, boolean isTrace2On) {
        this.a1 = a1;
        this.a2 = a2;
        this.r1 = r1;
        this.r2 = r2;
        this.g = g;
        this.m1 = m1;
        this.m2 = m2;
        this.trace1 = trace1;
        this.trace2 = trace2;
        this.traceColor1 = traceColor1;
        this.traceColor2 = traceColor2;
        this.ballColor1 = ballColor1;
        this.ballColor2 = ballColor2;
        this.points1Json = points1Json;
        this.points2Json = points2Json;
        this.timeStamp = timeStamp;
        this.endlessTrace1 = endlessTrace1;
        this.endlessTrace2 = endlessTrace2;
        this.isTrace1On = isTrace1On;
        this.isTrace2On = isTrace2On;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public double getA1() {
        return a1;
    }

    public double getA2() {
        return a2;
    }

    public double getR1() {
        return r1;
    }

    public double getR2() {
        return r2;
    }

    public double getG() {
        return g;
    }

    public double getM1() {
        return m1;
    }

    public double getM2() {
        return m2;
    }

    public int getTrace1() {
        return trace1;
    }

    public int getTrace2() {
        return trace2;
    }

    public int getTraceColor1() {
        return traceColor1;
    }

    public int getTraceColor2() {
        return traceColor2;
    }

    public int getBallColor1() {
        return ballColor1;
    }

    public int getBallColor2() {
        return ballColor2;
    }

    public String getPoints1Json() {
        return points1Json;
    }

    public String getPoints2Json() {
        return points2Json;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public boolean isEndlessTrace1() {
        return endlessTrace1;
    }

    public boolean isEndlessTrace2() {
        return endlessTrace2;
    }

    public boolean isTrace1On() {
        return isTrace1On;
    }

    public boolean isTrace2On() {
        return isTrace2On;
    }
}
