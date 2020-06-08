package com.example.pendulumtestjava.fragments.apiFragment;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.pendulumtestjava.fragments.apiFragment.Response.DoublePendulumReceivedObject;
import com.example.pendulumtestjava.fragments.apiFragment.Response.DoublePendulumResponse;
import com.example.pendulumtestjava.fragments.apiFragment.Response.SinglePendulumReceivedObject;
import com.example.pendulumtestjava.fragments.apiFragment.Response.SinglePendulumResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DescriptionRepository {
    private static DescriptionRepository instance;
    private MutableLiveData<SinglePendulumReceivedObject> singleObject;
    private MutableLiveData<DoublePendulumReceivedObject> doubleObject;

    private DescriptionRepository() {
        singleObject = new MutableLiveData<>();
        doubleObject = new MutableLiveData<>();
    }

    public static synchronized DescriptionRepository getInstance() {
        if (instance == null) {
            instance = new DescriptionRepository();
        }
        return instance;
    }

    public void randomizeSinglePendulum() {
        RandomApi api = ServiceGenerator.getRandomApi();
        Call<SinglePendulumResponse> call = api.getSinglePendulum();
        call.enqueue(new Callback<SinglePendulumResponse>() {
            @Override
            public void onResponse(Call<SinglePendulumResponse> call, Response<SinglePendulumResponse> response) {

            }

            @Override
            public void onFailure(Call<SinglePendulumResponse> call, Throwable t) {

            }
        });
    }

    public void randomizeDoublePendulum() {
        RandomApi api = ServiceGenerator.getRandomApi();
        Call<DoublePendulumResponse> call = api.getDoublePendulum();
        call.enqueue(new Callback<DoublePendulumResponse>() {
            @Override
            public void onResponse(Call<DoublePendulumResponse> call, Response<DoublePendulumResponse> response) {

            }

            @Override
            public void onFailure(Call<DoublePendulumResponse> call, Throwable t) {

            }
        });
    }

    public LiveData<SinglePendulumReceivedObject> getSinglePendulumObject() {
        return singleObject;
    }

    public LiveData<DoublePendulumReceivedObject> getDoublePendulumObject() {
        return doubleObject;
    }
}
