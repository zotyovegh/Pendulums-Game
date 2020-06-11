package com.example.pendulumtestjava.fragments.connection.Response;

import com.google.gson.annotations.SerializedName;

public class DoublePRandom {
    @SerializedName("a1")
    private double a1;
    @SerializedName("a2")
    private double a2;
    @SerializedName("r1")
    private double r1;
    @SerializedName("r2")
    private double r2;
    @SerializedName("g")
    private float g;
    @SerializedName("m1")
    private double m1;
    @SerializedName("m2")
    private double m2;
    @SerializedName("trace1")
    private int trace1;
    @SerializedName("trace2")
    private int trace2;

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

    public float getG() {
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
}
