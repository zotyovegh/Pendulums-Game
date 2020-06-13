package com.example.pendulumtestjava.fragments.savingsFragment.doubleP;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface DoublePDao {
    @Insert
    void insertDoubleP(DoublePObject pendulum);

    @Delete
    void deleteDoubleP(DoublePObject pendulum);

    @Query("SELECT * FROM double_table WHERE id = :arg0 ")
    DoublePObject getDoublePObject(int arg0);

    @Query("DELETE FROM double_table")
    void deleteAllDoublePendulum();
}
