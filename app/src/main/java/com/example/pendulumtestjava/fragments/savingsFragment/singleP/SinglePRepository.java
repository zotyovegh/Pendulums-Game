package com.example.pendulumtestjava.fragments.savingsFragment.singleP;

import android.app.Application;
import android.os.AsyncTask;

import com.example.pendulumtestjava.fragments.savingsFragment.shared.PendulumDatabase;
import com.example.pendulumtestjava.fragments.pendulumFragments.repositories.SinglePModelRepo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class SinglePRepository {
    private SinglePDao singlePDao;

    private SinglePModelRepo dataS = SinglePModelRepo.getInstance();

    public SinglePRepository(Application application) {
        PendulumDatabase database = PendulumDatabase.getInstance(application);
        singlePDao = database.singlePDao();
    }

    public void insertSinglePendulum(SinglePObject pendulum) {
        new SinglePRepository.InsertSinglePendulumAsyncTask(singlePDao).execute(pendulum);
    }

    public void deleteSinglePendulum(SinglePObject pendulum) {
        new DeleteSinglePendulumAsyncTask(singlePDao).execute(pendulum);
    }

    public void deleteAllSinglePendulum() {
        new DeleteAllSinglePendulumsAsyncTask(singlePDao).execute();
    }

    public SinglePObject getSinglePendulum(int id) {
        return singlePDao.getSinglePObject(id);
    }

    public void installSinglePendulum(SinglePObject pendulum) {
        Gson gson = new Gson();
        Type listType = new TypeToken<List<Float>>() {
        }.getType();
        ArrayList<Float> temp = gson.fromJson(pendulum.getPointsJson(), listType);
        dataS.setA(Math.toDegrees(pendulum.getA()));
        dataS.setR(pendulum.getR());
        dataS.setGravity(pendulum.getG() * 10);
        dataS.setDamping(pendulum.getDamping());
        dataS.setTrace(pendulum.getTrace());
        dataS.setBallDrawColor(pendulum.getBallColor());
        dataS.setTraceDrawColor(pendulum.getTraceColor());
        dataS.setPoints(temp);
        dataS.setStop(true);
        dataS.setEndlessTrace(pendulum.isInfinity());
        dataS.setTraceOn(pendulum.isTraceOn());
    }

    private static class InsertSinglePendulumAsyncTask extends AsyncTask<SinglePObject, Void, Void> {
        private SinglePDao dbDao;

        private InsertSinglePendulumAsyncTask(SinglePDao dbDao) {
            this.dbDao = dbDao;
        }

        @Override
        protected Void doInBackground(SinglePObject... singlePendulumObjects) {
            dbDao.insertSingleP(singlePendulumObjects[0]);
            return null;
        }
    }

    private static class DeleteSinglePendulumAsyncTask extends AsyncTask<SinglePObject, Void, Void> {
        private SinglePDao dbDao;

        private DeleteSinglePendulumAsyncTask(SinglePDao dbDao) {
            this.dbDao = dbDao;
        }

        @Override
        protected Void doInBackground(SinglePObject... singlePendulumObjects) {
            dbDao.deleteSingleP(singlePendulumObjects[0]);
            return null;
        }
    }

    private static class DeleteAllSinglePendulumsAsyncTask extends AsyncTask<Void, Void, Void> {
        private SinglePDao dbDao;

        private DeleteAllSinglePendulumsAsyncTask(SinglePDao dbDao) {
            this.dbDao = dbDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            dbDao.deleteAllSinglePendulum();
            return null;
        }
    }
}
