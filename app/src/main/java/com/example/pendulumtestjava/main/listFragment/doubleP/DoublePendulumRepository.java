package com.example.pendulumtestjava.main.listFragment.doubleP;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.pendulumtestjava.doublePendulum.DoublePendulum;
import com.example.pendulumtestjava.main.listFragment.shared.Db;
import com.example.pendulumtestjava.main.listFragment.shared.DbDao;

import java.util.List;

public class DoublePendulumRepository {

    private DbDao dbDao;
    private LiveData<List<DoublePendulumObject>> allDoublePendulums;

    public DoublePendulumRepository(Application application) {
        Db database = Db.getInstance(application);
        dbDao = database.dbDao();
        allDoublePendulums = dbDao.getAllDoublePendulums();
    }

    public void insertDoublePendulum(DoublePendulumObject pendulum)
    {
        new InsertDoublePendulumAsyncTask(dbDao).execute(pendulum);
    }

    public void deleteDoublePendulum(DoublePendulumObject pendulum)
    {
        new DeleteDoublePendulumAsyncTask(dbDao).execute(pendulum);
    }

    public LiveData<List<DoublePendulumObject>> getAllDoublePendulums()
    {
        return allDoublePendulums;
    }

    private static class InsertDoublePendulumAsyncTask extends AsyncTask<DoublePendulumObject, Void, Void>
    {
        private DbDao dbDao;

        private InsertDoublePendulumAsyncTask(DbDao dbDao)
        {
            this.dbDao = dbDao;
        }
        @Override
        protected Void doInBackground(DoublePendulumObject... doublePendulumObjects) {
            dbDao.insertDoublePendulum(doublePendulumObjects[0]);
            return null;
        }
    }

    private static class DeleteDoublePendulumAsyncTask extends AsyncTask<DoublePendulumObject, Void, Void>
    {
        private DbDao dbDao;

        private DeleteDoublePendulumAsyncTask(DbDao dbDao)
        {
            this.dbDao = dbDao;
        }
        @Override
        protected Void doInBackground(DoublePendulumObject... doublePendulumObjects) {
            dbDao.deleteDoublePendulum(doublePendulumObjects[0]);
            return null;
        }
    }
}
