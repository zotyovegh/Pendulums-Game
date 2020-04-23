package com.example.pendulumtestjava.main.listFragment.doubleP;

import android.app.Application;
import android.os.AsyncTask;
import com.example.pendulumtestjava.main.listFragment.shared.PendulumDatabase;

public class DoublePRepository {
    private DoublePDao doublePDao;

    public DoublePRepository(Application application) {
        PendulumDatabase database = PendulumDatabase.getInstance(application);
        doublePDao = database.doublePDao();
    }

    public void insertDoublePendulum(DoublePObject pendulum)
    {
        new InsertDoublePendulumAsyncTask(doublePDao).execute(pendulum);
    }

    public void deleteDoublePendulum(DoublePObject pendulum)
    {
        new DeleteDoublePendulumAsyncTask(doublePDao).execute(pendulum);
    }

    public DoublePObject getDoublePendulum(int id)
    {
        return doublePDao.getDoublePObject(id);
    }

    private static class InsertDoublePendulumAsyncTask extends AsyncTask<DoublePObject, Void, Void>
    {
        private DoublePDao dbDao;

        private InsertDoublePendulumAsyncTask(DoublePDao dbDao)
        {
            this.dbDao = dbDao;
        }
        @Override
        protected Void doInBackground(DoublePObject... doublePendulumObjects) {
            dbDao.insertDoubleP(doublePendulumObjects[0]);
            return null;
        }
    }

    private static class DeleteDoublePendulumAsyncTask extends AsyncTask<DoublePObject, Void, Void>
    {
        private DoublePDao dbDao;

        private DeleteDoublePendulumAsyncTask(DoublePDao dbDao)
        {
            this.dbDao = dbDao;
        }
        @Override
        protected Void doInBackground(DoublePObject... doublePendulumObjects) {
            dbDao.deleteDoubleP(doublePendulumObjects[0]);
            return null;
        }
    }
}
