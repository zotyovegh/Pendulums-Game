package com.example.pendulumtestjava.main.listFragment.shared;

import androidx.lifecycle.LiveData;
import androidx.room.ColumnInfo;
import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.RoomWarnings;

import com.example.pendulumtestjava.main.listFragment.doubleP.DoublePObject;
import com.example.pendulumtestjava.main.listFragment.singleP.SinglePObject;

import java.util.List;


@Dao
public interface PendulumsDao {

    @Query("SELECT timeStamp, id, 'single' as type FROM single_table UNION SELECT timeStamp, id, 'double' as type FROM double_table")
    LiveData<List<SaveObjectModel>> getAllPendulums();
}
