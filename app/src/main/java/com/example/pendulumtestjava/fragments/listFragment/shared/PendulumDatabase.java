package com.example.pendulumtestjava.fragments.listFragment.shared;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.pendulumtestjava.fragments.listFragment.doubleP.DoublePDao;
import com.example.pendulumtestjava.fragments.listFragment.doubleP.DoublePObject;
import com.example.pendulumtestjava.fragments.listFragment.singleP.SinglePDao;
import com.example.pendulumtestjava.fragments.listFragment.singleP.SinglePObject;

@Database(entities = {SinglePObject.class, DoublePObject.class}, version = 1)
public abstract class PendulumDatabase extends RoomDatabase {
    private static PendulumDatabase instance;

    public abstract SinglePDao singlePDao();
    public abstract DoublePDao doublePDao();
    public abstract PendulumsDao pendulumsDao();

    public static synchronized PendulumDatabase getInstance(Context context)
    {
        if(instance == null)
        {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    PendulumDatabase.class, "PendulumDatabase")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {

        private SinglePDao singlePDao;
        private DoublePDao doublePDao;
        private PendulumsDao pendulumsDao;

        private PopulateDbAsyncTask(PendulumDatabase db){
            singlePDao = db.singlePDao();
            doublePDao = db.doublePDao();
            pendulumsDao = db.pendulumsDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            singlePDao.insertSingleP(new SinglePObject(90, 300, 1, 1, 500, 0xFFFF0000, 0xFFFF0000, null, "Default time", false, true));
//            singlePDao.insertSingleP(new SinglePObject(2, 2, 2, 2, 2, 2, 2, "asd2", "time2Single", true, true));
//            doublePDao.insertDoubleP(new DoublePObject(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, "asd", "asd", "time1Double", true, true, true, true ));
//            doublePDao.insertDoubleP(new DoublePObject(2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, "asd2", "asd2", "time2Double", true, true, true, true ));
//            doublePDao.insertDoubleP(new DoublePObject(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, "asd", "asd", "time1Double", true, true, true, true ));
//            doublePDao.insertDoubleP(new DoublePObject(2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, "asd2", "asd2", "time2Double", true, true, true, true ));

            return null;
        }
    }
}
