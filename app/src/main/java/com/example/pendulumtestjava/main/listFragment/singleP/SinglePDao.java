package com.example.pendulumtestjava.main.listFragment.singleP;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface SinglePDao {
    @Insert
    void insertSingleP(SinglePObject pendulum);

    @Delete
    void deleteSingleP(SinglePObject pendulum);

    @Query("SELECT * FROM single_table WHERE id = :id ")
    SinglePObject getSinglePObject(int id);
}
