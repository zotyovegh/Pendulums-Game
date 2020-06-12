package com.example.pendulumtestjava.fragments.pendulumFragments.viewModels.Settings;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.pendulumtestjava.fragments.pendulumFragments.connection.RandomizerRepository;
import com.example.pendulumtestjava.fragments.pendulumFragments.connection.response.SinglePRandom;

public class SingleSettingsViewModel extends ViewModel {
    private RandomizerRepository randomizerRepository;

    public SingleSettingsViewModel() {
        randomizerRepository = RandomizerRepository.getInstance();
    }

    public void requestSingleRandom() {
        randomizerRepository.requestSingleRandom();
    }

    public LiveData<SinglePRandom> getSinglePRandom() {
        return randomizerRepository.getSinglePRandom();
    }

    public LiveData<String> getErrorMessage() {
        return randomizerRepository.getErrorMessage();
    }

}
