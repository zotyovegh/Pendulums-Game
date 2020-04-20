package com.example.pendulumtestjava.main.listFragment.shared;


import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.pendulumtestjava.main.listFragment.doubleP.DoublePendulumObject;
import com.example.pendulumtestjava.main.listFragment.doubleP.DoublePendulumRepository;
import com.example.pendulumtestjava.main.listFragment.singleP.SinglePendulumObject;
import com.example.pendulumtestjava.main.listFragment.singleP.SinglePendulumRepository;

import java.util.List;

public class DbViewModel extends AndroidViewModel {

    private SinglePendulumRepository singlePendulumRepository;
    private DoublePendulumRepository doublePendulumRepository;
    private LiveData<List<SinglePendulumObject>> allSinglePendulums;
    private LiveData<List<DoublePendulumObject>> allDoublePendulums;

    public DbViewModel(@NonNull Application application) {
        super(application);
        singlePendulumRepository = new SinglePendulumRepository(application);
        doublePendulumRepository = new DoublePendulumRepository(application);
        allSinglePendulums = singlePendulumRepository.getAllSinglePendulums();
        allDoublePendulums = doublePendulumRepository.getAllDoublePendulums();

    }

    public void insertSinglePendulum(SinglePendulumObject pendulum) {
        singlePendulumRepository.insertSinglePendulum(pendulum);
    }

    public void insertDoublePendulum(DoublePendulumObject pendulum)
    {
        doublePendulumRepository.insertDoublePendulum(pendulum);
    }

    public void deleteSinglePendulum(SinglePendulumObject pendulum) {
        singlePendulumRepository.deleteSinglePendulum(pendulum);
    }

    public void deleteDoublePendulum(DoublePendulumObject pendulum)
    {
        doublePendulumRepository.deleteDoublePendulum(pendulum);
    }

    public LiveData<List<SinglePendulumObject>> getAllSinglePendulums() {
        return allSinglePendulums;
    }

    public LiveData<List<DoublePendulumObject>> getAllDoublePendulums() {
        return allDoublePendulums;
    }


}
