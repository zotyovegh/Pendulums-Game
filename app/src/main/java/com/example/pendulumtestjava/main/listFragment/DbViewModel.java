package com.example.pendulumtestjava.main.listFragment;


import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class DbViewModel extends AndroidViewModel {

    private SinglePendulumRepository singlePendulumRepository;
    private LiveData<List<SinglePendulumObject>> allSinglePendulums;

    public DbViewModel(@NonNull Application application) {
        super(application);
        singlePendulumRepository = new SinglePendulumRepository(application);
        allSinglePendulums = singlePendulumRepository.getAllSinglePendulums();

    }

    public void insertSinglePendulum(SinglePendulumObject pendulum) {
        singlePendulumRepository.insertSinglePendulum(pendulum);
    }

    public void updateSinglePendulum(SinglePendulumObject pendulum) {
        singlePendulumRepository.updateSinglePendulum(pendulum);
    }

    public void deleteSinglePendulum(SinglePendulumObject pendulum) {
        singlePendulumRepository.deleteSinglePendulum(pendulum);
    }

    public void deleteAllSinglePendulums() {
        singlePendulumRepository.deleteAllSinglePendulums();
    }

    public LiveData<List<SinglePendulumObject>> getAllSinglePendulums() {
        return allSinglePendulums;
    }
}
