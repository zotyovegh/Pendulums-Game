package com.example.pendulumtestjava.fragments.pendulumFragments.repositories;

import java.util.ArrayList;

public class SinglePModelRepo {
    private static SinglePModelRepo instance;

    private float gravity, damping;
    private double r, a;
    private int traceDrawColor, ballDrawColor, trace;
    private boolean endlessTrace, isTraceOn, stop;
    private ArrayList<Float> points;

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
        return (float) (gravity * 0.1);
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

    public static synchronized SinglePModelRepo getInstance() {
        if (instance == null) {
            instance = new SinglePModelRepo();
        }
        return instance;
    }

    public void resetValues() {
        trace = 100;
        gravity = (float) 9.81;
        damping = (float) 0.999;
        r = 300;
        a = Math.PI / 2;
        traceDrawColor = 0xF0000000;
        ballDrawColor = 0xFFFF0000;
        endlessTrace = false;
        isTraceOn = true;
        points = new ArrayList<>();
        stop = false;
    }
}
