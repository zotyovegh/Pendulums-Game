package com.example.pendulumtestjava.fragments.connection;

import com.example.pendulumtestjava.fragments.connection.Response.DoublePRandom;
import com.example.pendulumtestjava.fragments.connection.Response.SinglePRandom;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RandomApi {

    @GET("single")
    Call<SinglePRandom> getSingleRandom();

    @GET("double")
    Call<DoublePRandom> getDoubleRandom();
}
