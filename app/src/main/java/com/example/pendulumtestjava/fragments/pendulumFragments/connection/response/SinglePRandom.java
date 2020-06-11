package com.example.pendulumtestjava.fragments.pendulumFragments.connection.response;

import com.google.gson.annotations.SerializedName;

public class SinglePRandom {
    @SerializedName("a")
    private double a;
    @SerializedName("r")
    private double r;
    @SerializedName("g")
    private float g;
    @SerializedName("damping")
    private float damping;
    @SerializedName("trace")
    private int trace;

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
}
