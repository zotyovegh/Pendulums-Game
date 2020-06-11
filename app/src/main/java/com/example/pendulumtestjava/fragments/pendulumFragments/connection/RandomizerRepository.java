package com.example.pendulumtestjava.fragments.pendulumFragments.connection;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.pendulumtestjava.fragments.pendulumFragments.connection.response.DoublePRandom;
import com.example.pendulumtestjava.fragments.pendulumFragments.connection.response.SinglePRandom;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RandomizerRepository {
    private static RandomizerRepository instance;
    private MutableLiveData<SinglePRandom> singlePRandom;
    private MutableLiveData<DoublePRandom> doublePRandom;

    private RandomizerRepository() {
        singlePRandom = new MutableLiveData<>();
        doublePRandom = new MutableLiveData<>();
    }

    public static synchronized RandomizerRepository getInstance() {
        if (instance == null) {
            instance = new RandomizerRepository();
        }
        return instance;
    }

    public void requestSingleRandom() {
        RandomApi randomApi = ServiceGenerator.getRandomApi();
        Call<SinglePRandom> call = randomApi.getSingleRandom();
        call.enqueue(new Callback<SinglePRandom>() {
            @Override
            public void onResponse(Call<SinglePRandom> call, Response<SinglePRandom> response) {
                if (response.code() == 200) {
                    singlePRandom.setValue(response.body());
                }else{
                    Log.i("RandomApi","Response code (single): " + response.code());
                }
            }

            @Override
            public void onFailure(Call<SinglePRandom> call, Throwable t) {
                Log.i("RandomApi", "Something went wrong with singleP " + t.getMessage());
            }
        });
    }

    public void requestDoubleRandom() {
        RandomApi randomApi = ServiceGenerator.getRandomApi();
        Call<DoublePRandom> call = randomApi.getDoubleRandom();
        call.enqueue(new Callback<DoublePRandom>() {
            @Override
            public void onResponse(Call<DoublePRandom> call, Response<DoublePRandom> response) {
                if (response.code() == 200) {
                    doublePRandom.setValue(response.body());
                }else{
                    Log.i("RandomApi","Response code (double): " + response.code());
                }
            }

            @Override
            public void onFailure(Call<DoublePRandom> call, Throwable t) {
                Log.i("RandomApi", "Something went wrong with doubleP " + t.getMessage());
            }
        });
    }

    public LiveData<SinglePRandom> getSinglePRandom() {
        return singlePRandom;
    }

    public LiveData<DoublePRandom> getDoublePRandom() {
        return doublePRandom;
    }



}
