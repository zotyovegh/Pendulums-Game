package com.example.pendulumtestjava.main.listFragment;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface SinglePDao {

    @Insert
    void insert(SinglePendulumObject pendulum);

    @Update
    void update(SinglePendulumObject pendulum);

    @Delete
    void delete(SinglePendulumObject pendulum);

    @Query("DELETE FROM single_pendulum_table")
    void deleteAllSinglePendulums();

    @Query("SELECT * FROM single_pendulum_table ORDER BY timeStamp DESC")
        LiveData<List<SinglePendulumObject>> getAllSinglePendulums();


}
