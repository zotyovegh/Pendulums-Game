package com.example.pendulumtestjava.main.listFragment;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

@Entity(tableName = "single_pendulum_table")
public class SinglePendulumObject {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private double a;
    private double r;
    private float g;
    private float damping;
    private int trace;
    private int ballColor;
    private int traceColor;
    private String pointsJson;
    private String timeStamp;
    private boolean infinity;


    public SinglePendulumObject(double a, double r, float g, float damping, int trace, int ballColor, int traceColor, String pointsJson, String timeStamp, boolean infinity) {
        this.a = a;
        this.r = r;
        this.g = g;
        this.damping = damping;
        this.trace = trace;
        this.ballColor = ballColor;
        this.traceColor = traceColor;
        this.pointsJson = pointsJson;
        this.timeStamp = timeStamp;
        this.infinity = infinity;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isInfinity() {
        return infinity;
    }

    public int getId() {
        return id;
    }

    public double getA() {
        return a;
    }

    public double getR() {
        return r;
    }

    public float getG() {
        return g;
    }

    public float getDamping() {
        return damping;
    }

    public int getTrace() {
        return trace;
    }

    public int getBallColor() {
        return ballColor;
    }

    public int getTraceColor() {
        return traceColor;
    }

    public String getPointsJson() {
        return pointsJson;
    }

    public String getTimeStamp() {
        return timeStamp;
    }
}
