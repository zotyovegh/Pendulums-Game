package com.example.pendulumtestjava.fragments.pendulumFragments.connection;

import com.example.pendulumtestjava.fragments.pendulumFragments.connection.response.DoublePRandom;
import com.example.pendulumtestjava.fragments.pendulumFragments.connection.response.SinglePRandom;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RandomApi {
    @GET("single")
    Call<SinglePRandom> getSingleRandom();

    @GET("double")
    Call<DoublePRandom> getDoubleRandom();
}
