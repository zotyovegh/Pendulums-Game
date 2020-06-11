package com.example.pendulumtestjava.fragments.connection;

import com.example.pendulumtestjava.fragments.connection.Response.DoublePRandom;
import com.example.pendulumtestjava.fragments.connection.Response.SinglePRandom;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RandomizerRepository {
    private static RandomizerRepository instance;

    private RandomizerRepository()
    {

    }

    public static synchronized RandomizerRepository getInstance()
    {
        if(instance == null)
        {
            instance = new RandomizerRepository();
        }
        return  instance;
    }

    public void requestSingleRandom()
    {
        RandomApi randomApi = ServiceGenerator.getRandomApi();
        Call<SinglePRandom> call = randomApi.getSinglRandom();
        call.enqueue(new Callback<SinglePRandom>() {
            @Override
            public void onResponse(Call<SinglePRandom> call, Response<SinglePRandom> response) {
                System.out.println("Response code: " + response.code());
            }

            @Override
            public void onFailure(Call<SinglePRandom> call, Throwable t) {

            }
        });
    }

    public void requestDoubleRandom()
    {
        RandomApi randomApi = ServiceGenerator.getRandomApi();
        Call<DoublePRandom> call = randomApi.getDoubleRandom();
        call.enqueue(new Callback<DoublePRandom>() {
            @Override
            public void onResponse(Call<DoublePRandom> call, Response<DoublePRandom> response) {
                System.out.println("Response code: " + response.code());
            }

            @Override
            public void onFailure(Call<DoublePRandom> call, Throwable t) {

            }
        });
    }

}
