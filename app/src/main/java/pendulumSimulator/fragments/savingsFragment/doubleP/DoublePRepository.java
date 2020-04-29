package pendulumSimulator.fragments.savingsFragment.doubleP;

import android.app.Application;
import android.os.AsyncTask;

import pendulumSimulator.fragments.pendulumsFragments.models.DoublePendulumModel;
import pendulumSimulator.fragments.savingsFragment.shared.PendulumDatabase;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class DoublePRepository {
    private DoublePDao doublePDao;

    private DoublePendulumModel dataD = DoublePendulumModel.getInstance();

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

    public void installDoublePendulum(DoublePObject pendulum)
    {
        Gson gson = new Gson();
        Type listType = new TypeToken<List<Float>>(){}.getType();
        ArrayList<Float> json1 = gson.fromJson(pendulum.getPoints1Json(), listType);
        ArrayList<Float> json2 = gson.fromJson(pendulum.getPoints2Json(), listType);
        dataD.setA1(Math.toDegrees(pendulum.getA1()));
        dataD.setA2(Math.toDegrees(pendulum.getA2()));
        dataD.setR1(pendulum.getR1());
        dataD.setR2(pendulum.getR2());
        dataD.setG(pendulum.getG());
        dataD.setM1(pendulum.getM1());
        dataD.setM2(pendulum.getM2());
        dataD.setTrace1(pendulum.getTrace1());
        dataD.setTrace2(pendulum.getTrace2());
        dataD.setTrace1Color(pendulum.getTraceColor1());
        dataD.setTrace2Color(pendulum.getTraceColor2());
        dataD.setBall1Color(pendulum.getBallColor1());
        dataD.setBall2Color(pendulum.getBallColor2());
        dataD.setPoints1(json1);
        dataD.setPoints2(json2);
        dataD.setEndlessTrace1(pendulum.isEndlessTrace1());
        dataD.setEndlessTrace2(pendulum.isEndlessTrace2());
        dataD.setTrace1On(pendulum.isTrace1On());
        dataD.setTrace2On(pendulum.isTrace2On());
        dataD.setStop(true);
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
