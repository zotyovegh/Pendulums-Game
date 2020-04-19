package com.example.pendulumtestjava.singlePendulum;

import java.util.ArrayList;

public class SinglePData {
    private static SinglePData instance;
    private int trace = 100;
    private float gravity = (float) 1;
    private float damping = (float) 0.999;
    private double r = 300;
    private double a = Math.PI / 2;
    private int traceDrawColor = 0xF0000000;
    private int ballDrawColor = 0xFFFF0000;
    private boolean endlessTrace = false;
    private boolean isTraceOn = true;
    private ArrayList<Float> points = new ArrayList<>();
    private boolean stop;

    public boolean isStop() {
        return stop;
    }

    public void setStop(boolean stop) {
        this.stop = stop;
    }

    public ArrayList<Float> getPoints() {
        return points;
    }

    public void setPoints(ArrayList<Float> points) {
        this.points = points;
    }

    public boolean isTraceOn() {
        return isTraceOn;
    }

    public void setTraceOn(boolean traceOn) {
        isTraceOn = traceOn;
    }

    public boolean isEndlessTrace() {
        return endlessTrace;
    }

    public void setEndlessTrace(boolean endless) {
        this.endlessTrace = endless;
    }

    public int getTraceDrawColor() {
        return traceDrawColor;
    }

    public void setTraceDrawColor(int traceDrawColor) {
        this.traceDrawColor = traceDrawColor;
    }

    public int getBallDrawColor() {
        return ballDrawColor;
    }

    public void setBallDrawColor(int ballDrawColor) {
        this.ballDrawColor = ballDrawColor;
    }

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = Math.toRadians(a);
    }

    public int getTrace() {
        return trace;
    }

    public void setTrace(int trace) {
        this.trace = trace;
    }

    public float getGravity() {
        return gravity;
    }

    public void setGravity(float gravity) {
        this.gravity = gravity;
    }

    public float getDamping() {
        return damping;
    }

    public void setDamping(float damping) {
        this.damping = damping;
    }

    public double getR() {
        return this.r;
    }

    public void setR(double r) {
        this.r = r;
    }

    public static synchronized SinglePData getInstance() {
        if(instance==null)
        {
            instance = new SinglePData();
        }
        return instance;
    }
}
