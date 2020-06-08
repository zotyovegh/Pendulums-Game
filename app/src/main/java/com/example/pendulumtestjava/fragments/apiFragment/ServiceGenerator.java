package com.example.pendulumtestjava.fragments.apiFragment;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {
    private static Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
            .baseUrl("BASEURL")
            .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = retrofitBuilder.build();
    private static RandomApi randomApi = retrofit.create(RandomApi.class);

    public static RandomApi getRandomApi(){
        return randomApi;
    }
}
