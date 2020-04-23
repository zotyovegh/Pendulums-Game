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
    @Query("SELECT timeStamp, id, 'Single' as type FROM single_table UNION SELECT timeStamp, id, 'Double' as type FROM double_table ORDER by timeStamp DESC")
    LiveData<List<SaveObjectModel>> getAllPendulums();
}
