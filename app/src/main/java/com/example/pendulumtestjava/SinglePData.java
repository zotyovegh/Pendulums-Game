package com.example.pendulumtestjava;

public class SinglePData {
    private static SinglePData instance;
    private int trace = 100;
    private float gravity = (float) 1;
    private float damping = (float) 0.999;
    private double r = 300;
    private double a = Math.PI / 2;
    private int drawColor = 0xF0000000;

    public int getDrawColor() {
        return drawColor;
    }

    public void setDrawColor(int color) {
        this.drawColor = color;
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

    public static synchronized SinglePData getInstance()
    {
        if(instance==null)
        {
            instance = new SinglePData();
        }
        return instance;
    }
}
