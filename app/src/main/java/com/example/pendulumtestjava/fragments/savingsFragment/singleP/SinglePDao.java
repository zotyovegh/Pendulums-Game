package com.example.pendulumtestjava.fragments.savingsFragment.singleP;

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

    @Query("SELECT * FROM single_table WHERE id = :arg0")
    SinglePObject getSinglePObject(int arg0);

    @Query("DELETE FROM single_table")
    void deleteAllSinglePendulum();
}
