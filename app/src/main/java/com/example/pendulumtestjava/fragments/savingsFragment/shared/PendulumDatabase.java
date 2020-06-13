package com.example.pendulumtestjava.fragments.savingsFragment.shared;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.pendulumtestjava.fragments.savingsFragment.doubleP.DoublePDao;
import com.example.pendulumtestjava.fragments.savingsFragment.doubleP.DoublePObject;
import com.example.pendulumtestjava.fragments.savingsFragment.savedObject.PendulumsDao;
import com.example.pendulumtestjava.fragments.savingsFragment.singleP.SinglePDao;
import com.example.pendulumtestjava.fragments.savingsFragment.singleP.SinglePObject;

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
        private PopulateDbAsyncTask(PendulumDatabase db){}

        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }
    }
}
