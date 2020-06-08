package com.example.pendulumtestjava.fragments.apiFragment;

import com.example.pendulumtestjava.fragments.apiFragment.Response.DoublePendulumResponse;
import com.example.pendulumtestjava.fragments.apiFragment.Response.SinglePendulumResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RandomApi {

    @GET("single")
    Call<SinglePendulumResponse> getSinglePendulum();

    @GET("double")
    Call<DoublePendulumResponse> getDoublePendulum();
}
