package com.example.pendulumtestjava.fragments.listFragment.shared;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class PendulumsRepository {
    private LiveData<List<SavePendulumModel>> allPendulums;

    public PendulumsRepository(Application application)
    {
        PendulumDatabase database = PendulumDatabase.getInstance(application);
        PendulumsDao pendulumsDao = database.pendulumsDao();
        allPendulums = pendulumsDao.getAllPendulums();
    }

    public LiveData<List<SavePendulumModel>> getAllPendulums()
    {
        return allPendulums;
    }
}
