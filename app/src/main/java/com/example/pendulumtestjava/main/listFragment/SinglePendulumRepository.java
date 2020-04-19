package com.example.pendulumtestjava.main.listFragment;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class SinglePendulumRepository {

    private SinglePDao singlePDao;
    private LiveData<List<SinglePendulumObject>> allSinglePendulums;

    public SinglePendulumRepository(Application application)
    {
        SinglePendulumDatabase database = SinglePendulumDatabase.getInstance(application);
        singlePDao = database.singlePDao();
        allSinglePendulums = singlePDao.getAllSinglePendulums();
    }

    public void insert(SinglePendulumObject pendulum)
    {
        new InsertSinglePendulumAsyncTask(singlePDao).execute(pendulum);
    }

    public void update(SinglePendulumObject pendulum)
    {
        new UpdateSinglePendulumAsyncTask(singlePDao).execute(pendulum);
    }

    public void delete(SinglePendulumObject pendulum)
    {
        new DeleteSinglePendulumAsyncTask(singlePDao).execute(pendulum);
    }

    public void deleteAllSinglePendulums()
    {
        new DeleteAllSinglePendulumAsyncTask(singlePDao).execute();
    }

    public LiveData<List<SinglePendulumObject>> getAllSinglePendulums()
    {
        return allSinglePendulums;
    }


    private static class InsertSinglePendulumAsyncTask extends AsyncTask<SinglePendulumObject, Void, Void>
    {
        private SinglePDao singlePDao;

        private InsertSinglePendulumAsyncTask(SinglePDao singlePDao)
        {
            this.singlePDao = singlePDao;
        }
        @Override
        protected Void doInBackground(SinglePendulumObject... singlePendulumObjects) {
            singlePDao.insert(singlePendulumObjects[0]);
            return null;
        }
    }

    private static class UpdateSinglePendulumAsyncTask extends AsyncTask<SinglePendulumObject, Void, Void>
    {
        private SinglePDao singlePDao;

        private UpdateSinglePendulumAsyncTask(SinglePDao singlePDao)
        {
            this.singlePDao = singlePDao;
        }
        @Override
        protected Void doInBackground(SinglePendulumObject... singlePendulumObjects) {
            singlePDao.update(singlePendulumObjects[0]);
            return null;
        }
    }

    private static class DeleteSinglePendulumAsyncTask extends AsyncTask<SinglePendulumObject, Void, Void>
    {
        private SinglePDao singlePDao;

        private DeleteSinglePendulumAsyncTask(SinglePDao singlePDao)
        {
            this.singlePDao = singlePDao;
        }
        @Override
        protected Void doInBackground(SinglePendulumObject... singlePendulumObjects) {
            singlePDao.delete(singlePendulumObjects[0]);
            return null;
        }
    }

    private static class DeleteAllSinglePendulumAsyncTask extends AsyncTask<Void, Void, Void>
    {
        private SinglePDao singlePDao;

        private DeleteAllSinglePendulumAsyncTask(SinglePDao singlePDao)
        {
            this.singlePDao = singlePDao;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            singlePDao.deleteAllSinglePendulums();
            return null;
        }
    }

}
