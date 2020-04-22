package com.example.pendulumtestjava.main.listFragment.doubleP;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.pendulumtestjava.main.listFragment.doubleP.DoublePObject;
import com.example.pendulumtestjava.main.listFragment.singleP.SinglePObject;

import java.util.List;

@Dao
public interface DoublePDao {
    @Insert
    void insertDoubleP(DoublePObject pendulum);

    @Delete
    void deleteDoubleP(DoublePObject pendulum);

    @Query("SELECT * FROM double_table WHERE id = :id ")
    LiveData<List<DoublePObject>> getDoublePObject(int id);

}
