package com.example.pendulumtestjava.main.listFragment.shared;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;


@Dao
public interface PendulumsDao {


    @Query("SELECT timeStamp, id, 'single' as type FROM single_table UNION SELECT timeStamp, id, 'double' as type FROM double_table")
    LiveData<List<Object>> getAllPendulums();
}
