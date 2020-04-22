package com.example.pendulumtestjava.main.listFragment.singleP;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface SinglePDao {
    @Insert
    void insertSingleP(SinglePObject pendulum);

    @Delete
    void deleteSingleP(SinglePObject pendulum);

    @Query("SELECT * FROM single_table WHERE id = :id ")
    LiveData<List<SinglePObject>> getSinglePObject(int id);
}
