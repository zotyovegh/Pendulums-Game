package com.example.pendulumtestjava.main.listFragment.shared;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.pendulumtestjava.main.listFragment.doubleP.DoublePObject;
import com.example.pendulumtestjava.main.listFragment.doubleP.DoublePRepository;
import com.example.pendulumtestjava.main.listFragment.singleP.SinglePObject;
import com.example.pendulumtestjava.main.listFragment.singleP.SinglePRepository;

import java.util.List;

public class DbViewModel extends AndroidViewModel {

    private SinglePRepository singlePRepository;
    private DoublePRepository doublePRepository;
    private PendulumsRepository pendulumsRepository;
    private LiveData<List<SavePendulumModel>> allPendulums;

    public DbViewModel(@NonNull Application application) {
        super(application);

        singlePRepository = new SinglePRepository(application);
        doublePRepository = new DoublePRepository(application);
        pendulumsRepository = new PendulumsRepository(application);

        allPendulums = pendulumsRepository.getAllPendulums();
    }

    public void insertSingleP(SinglePObject pendulum)
    {
        singlePRepository.insertSinglePendulum(pendulum);
    }

    public void insertDoubleP(DoublePObject pendulum)
    {
        doublePRepository.insertDoublePendulum(pendulum);
    }

    public void deleteSingleP(SinglePObject pendulum)
    {
        singlePRepository.deleteSinglePendulum(pendulum);
    }

    public void deleteDoubleP(DoublePObject pendulum)
    {
        doublePRepository.deleteDoublePendulum(pendulum);
    }

    public SinglePObject getSinglePendulum(int id)
    {
        return singlePRepository.getSinglePendulum(id);
    }

    public  DoublePObject getDoublePendulum(int id)
    {
        return  doublePRepository.getDoublePendulum(id);
    }

    public void installSinglePendulum(SinglePObject pendulum)
    {
        singlePRepository.installSinglePendulum(pendulum);
    }

    public void installDoublePendulum(DoublePObject pendulum)
    {
        doublePRepository.installDoublePendulum(pendulum);
    }

    public LiveData<List<SavePendulumModel>> getAllPendulums()
    {
        return allPendulums;
    }


}
