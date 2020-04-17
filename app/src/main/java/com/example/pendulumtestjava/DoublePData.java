package com.example.pendulumtestjava;

public class DoublePData {
    private static DoublePData instance;

    private double r1 = 250;
    private double r2 = 250;
    private double a1 = Math.PI / 2;
    private double a2 = Math.PI / 2;
    private double g = 1;
    private double m1 = 10;
    private double m2 = 10;
    private int trace1 = 100;
    private int trace2 = 20000;
    private int trace1Color = 0xFFFF0000;
    private int trace2Color = 0xFF0000FF;
    private int ball1Color = 0xFFFF0000;
    private int ball2Color = 0xFF0000FF;

    public int getBall1Color() {
        return ball1Color;
    }

    public void setBall1Color(int ball1Color) {
        this.ball1Color = ball1Color;
    }

    public int getBall2Color() {
        return ball2Color;
    }

    public void setBall2Color(int ball2Color) {
        this.ball2Color = ball2Color;
    }

    public double getR1() {
        return r1;
    }

    public void setR1(double r1) {
        this.r1 = r1;
    }

    public double getR2() {
        return r2;
    }

    public void setR2(double r2) {
        this.r2 = r2;
    }

    public double getA1() {
        return a1;
    }

    public void setA1(double a1) {
        this.a1 = Math.toRadians(a1);
    }

    public double getA2() {
        return a2;
    }

    public void setA2(double a2) {
        this.a2 = Math.toRadians(a2);
    }

    public double getG() {
        return g;
    }

    public void setG(double g) {
        this.g = g;
    }

    public double getM1() {
        return m1;
    }

    public void setM1(double m1) {
        this.m1 = m1;
    }

    public double getM2() {
        return m2;
    }

    public void setM2(double m2) {
        this.m2 = m2;
    }

    public int getTrace1() {
        return trace1;
    }

    public void setTrace1(int trace1) {
        this.trace1 = trace1;
    }

    public int getTrace2() {
        return trace2;
    }

    public void setTrace2(int trace2) {
        this.trace2 = trace2;
    }

    public int getTrace1Color() {
        return trace1Color;
    }

    public void setTrace1Color(int color1) {
        this.trace1Color = color1;
    }

    public int getTrace2Color() {
        return trace2Color;
    }

    public void setTrace2Color(int color2) {
        this.trace2Color = color2;
    }

    public static synchronized DoublePData getInstance() {
        if(instance==null)
        {
            instance = new DoublePData();
        }
        return instance;
    }
}
