package com.example.pendulumtestjava.main.listFragment.singleP;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.pendulumtestjava.main.listFragment.shared.Db;
import com.example.pendulumtestjava.main.listFragment.shared.DbDao;

import java.util.List;

public class SinglePendulumRepository {

    private DbDao dbDao;
    private LiveData<List<SinglePendulumObject>> allSinglePendulums;

    public SinglePendulumRepository(Application application)
    {
        Db database = Db.getInstance(application);
        dbDao = database.dbDao();
        allSinglePendulums = dbDao.getAllSinglePendulums();
    }

    public void insertSinglePendulum(SinglePendulumObject pendulum)
    {
        new InsertSinglePendulumAsyncTask(dbDao).execute(pendulum);
    }

    public void deleteSinglePendulum(SinglePendulumObject pendulum)
    {
        new DeleteSinglePendulumAsyncTask(dbDao).execute(pendulum);
    }

    public LiveData<List<SinglePendulumObject>> getAllSinglePendulums()
    {
        return allSinglePendulums;
    }


    private static class InsertSinglePendulumAsyncTask extends AsyncTask<SinglePendulumObject, Void, Void>
    {
        private DbDao dbDao;

        private InsertSinglePendulumAsyncTask(DbDao dbDao)
        {
            this.dbDao = dbDao;
        }
        @Override
        protected Void doInBackground(SinglePendulumObject... singlePendulumObjects) {
            dbDao.insertSinglePendulum(singlePendulumObjects[0]);
            return null;
        }
    }

    private static class DeleteSinglePendulumAsyncTask extends AsyncTask<SinglePendulumObject, Void, Void>
    {
        private DbDao dbDao;

        private DeleteSinglePendulumAsyncTask(DbDao dbDao)
        {
            this.dbDao = dbDao;
        }
        @Override
        protected Void doInBackground(SinglePendulumObject... singlePendulumObjects) {
            dbDao.deleteSinglePendulum(singlePendulumObjects[0]);
            return null;
        }
    }


}
