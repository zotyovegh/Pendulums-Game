package com.example.pendulumtestjava.main.listFragment;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.ArrayList;

@Database(entities = {SinglePendulumObject.class}, version = 1)
public abstract class SinglePendulumDatabase extends RoomDatabase {

    private static SinglePendulumDatabase instance;

    public abstract SinglePDao singlePDao();

    public static synchronized SinglePendulumDatabase getInstance(Context context)
    {
        if(instance == null)
        {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    SinglePendulumDatabase.class, "single_pendulum_database")
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

        private SinglePDao singlePDao;

        private PopulateDbAsyncTask(SinglePendulumDatabase db){
            singlePDao = db.singlePDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            singlePDao.insert(new SinglePendulumObject(1, 1, 1,  1, 1, 1, 1, "json 1","TimeStamp 1"));
            singlePDao.insert(new SinglePendulumObject(2, 2, 2,  2, 2, 2, 2, "json 2","TimeStamp 2"));
            singlePDao.insert(new SinglePendulumObject(3, 3, 3,  3, 3, 3, 3, "json 3","TimeStamp 3"));

            return null;
        }
    }
}
