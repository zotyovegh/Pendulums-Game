package com.example.pendulumtestjava.main.listFragment.shared;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class PendulumsRepository {
    private PendulumsDao pendulumsDao;
    private LiveData<List<SaveObjectModel>> allPendulums;

    public PendulumsRepository(Application application)
    {
        PendulumDatabase database = PendulumDatabase.getInstance(application);
        pendulumsDao = database.pendulumsDao();
        allPendulums = pendulumsDao.getAllPendulums();
    }

    public LiveData<List<SaveObjectModel>> getAllPendulums()
    {
        return allPendulums;
    }
}
