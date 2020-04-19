package com.example.pendulumtestjava.main.listFragment;


import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class SinglePViewModel extends AndroidViewModel {

    private SinglePendulumRepository repository;
    private LiveData<List<SinglePendulumObject>> allPendulums;

    public SinglePViewModel(@NonNull Application application) {
        super(application);
        repository = new SinglePendulumRepository(application);
        allPendulums = repository.getAllSinglePendulums();

    }

    public void insert(SinglePendulumObject pendulum)
    {
        repository.insert(pendulum);
    }

    public void update(SinglePendulumObject pendulum)
    {
        repository.update(pendulum);
    }

    public void delete(SinglePendulumObject pendulum)
    {
        repository.delete(pendulum);
    }

    public void deleteAllSinglePendulums()
    {
        repository.deleteAllSinglePendulums();
    }

    public LiveData<List<SinglePendulumObject>> getAllPendulums()
    {
        return  allPendulums;
    }
}
