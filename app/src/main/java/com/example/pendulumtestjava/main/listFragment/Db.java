package com.example.pendulumtestjava.main.listFragment;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {SinglePendulumObject.class}, version = 2)
public abstract class Db extends RoomDatabase {

    private static Db instance;

    public abstract DbDao singlePDao();

    public static synchronized Db getInstance(Context context)
    {
        if(instance == null)
        {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    Db.class, "single_pendulum_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
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

        private DbDao dbDao;

        private PopulateDbAsyncTask(Db db){
            dbDao = db.singlePDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            dbDao.insertSinglePendulum(new SinglePendulumObject(1, 1, 1,  1, 1, 1, 1, "json 1","TimeStamp 1",true, true));
            dbDao.insertSinglePendulum(new SinglePendulumObject(2, 2, 2,  2, 2, 2, 2, "json 2","TimeStamp 2", true, true));
            dbDao.insertSinglePendulum(new SinglePendulumObject(3, 3, 3,  3, 3, 3, 3, "json 3","TimeStamp 3", true, true));

            return null;
        }
    }
}
