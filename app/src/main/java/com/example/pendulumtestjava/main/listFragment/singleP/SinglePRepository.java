package com.example.pendulumtestjava.main.listFragment.singleP;

import android.app.Application;
import android.os.AsyncTask;

import com.example.pendulumtestjava.main.listFragment.shared.PendulumDatabase;

public class SinglePRepository {
    private SinglePDao singlePDao;

    public SinglePRepository(Application application)
    {
        PendulumDatabase database = PendulumDatabase.getInstance(application);
        singlePDao = database.singlePDao();
    }

    public void insertSinglePendulum(SinglePObject pendulum)
    {
        new InsertSinglePendulumAsyncTask(singlePDao).execute(pendulum);
    }

    public void deleteSinglePendulum(SinglePObject pendulum)
    {
        new DeleteSinglePendulumAsyncTask(singlePDao).execute(pendulum);
    }

    public SinglePObject getSinglePendulum(int id)
        {
            return singlePDao.getSinglePObject(id);
    }

    private static class InsertSinglePendulumAsyncTask extends AsyncTask<SinglePObject, Void, Void>
    {
        private SinglePDao dbDao;

        private InsertSinglePendulumAsyncTask(SinglePDao dbDao)
        {
            this.dbDao = dbDao;
        }
        @Override
        protected Void doInBackground(SinglePObject... singlePendulumObjects) {
            dbDao.insertSingleP(singlePendulumObjects[0]);
            return null;
        }
    }

    private static class DeleteSinglePendulumAsyncTask extends AsyncTask<SinglePObject, Void, Void>
    {
        private SinglePDao dbDao;

        private DeleteSinglePendulumAsyncTask(SinglePDao dbDao)
        {
            this.dbDao = dbDao;
        }
        @Override
        protected Void doInBackground(SinglePObject... singlePendulumObjects) {
            dbDao.deleteSingleP(singlePendulumObjects[0]);
            return null;
        }
    }
}
