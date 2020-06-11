package com.example.pendulumtestjava.fragments.pendulumFragments.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.pendulumtestjava.fragments.connection.RandomizerRepository;
import com.example.pendulumtestjava.fragments.connection.Response.DoublePRandom;

public class DoubleSettingsViewModel extends ViewModel {
    private RandomizerRepository randomizerRepository;

    public DoubleSettingsViewModel() {
        randomizerRepository = RandomizerRepository.getInstance();
    }

    public void requestDoubleRandom() {
        randomizerRepository.requestDoubleRandom();
    }

    public LiveData<DoublePRandom> getDoublePRandom() {
        return randomizerRepository.getDoublePRandom();
    }
}
