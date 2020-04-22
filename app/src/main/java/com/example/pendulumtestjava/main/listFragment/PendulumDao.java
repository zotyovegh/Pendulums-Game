package com.example.pendulumtestjava.main.listFragment;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.pendulumtestjava.main.listFragment.doubleP.DoublePObject;
import com.example.pendulumtestjava.main.listFragment.singleP.SinglePObject;

import java.util.List;

@Dao
public interface PendulumDao {
    @Insert
    void insertSingle(SinglePObject pendulum);

    @Insert
    void insertDouble(DoublePObject pendulum);

    @Delete
    void deleteSingle(SinglePObject pendulum);

    @Delete
    void deleteDouble(DoublePObject pendulum);

    @Query("SELECT timeStamp, id FROM single_table UNION SELECT timeStamp, id FROM double_table")
    LiveData<List<Object>> getAllPendulums();
}
