package com.example.vkcoin.database.cpu;

import com.example.vkcoin.model.CPUmodel;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = CPUmodel.class, version = 1, exportSchema = false)
public abstract class CpuDatabase extends RoomDatabase {
    public abstract CpuDao getCpuDao();
}
