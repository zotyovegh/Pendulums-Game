package com.example.pendulumtestjava.main.listFragment;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.pendulumtestjava.main.listFragment.doubleP.DoublePObject;
import com.example.pendulumtestjava.main.listFragment.singleP.SinglePObject;

@Database(entities = {SinglePObject.class, DoublePObject.class}, version = 1)
public abstract class PendulumDatabase extends RoomDatabase {
    private static PendulumDatabase instance;

    public abstract PendulumDao pendulumDao();

    public static synchronized PendulumDatabase getInstance(Context context)
    {
        if(instance == null)
        {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    PendulumDatabase.class, "PendulumDatabase")
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

        private PendulumDao pendulumDao;

        private PopulateDbAsyncTask(PendulumDatabase db){
            pendulumDao = db.pendulumDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {


            return null;
        }
    }
}
