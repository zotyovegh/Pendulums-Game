package com.example.pendulumtestjava.fragments.connection.Response;

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
}
