package com.example.vkcoin.database.cpu;

import com.example.vkcoin.model.CPUmodel;

import java.util.ArrayList;
import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface CpuDao {


    @Insert
    void insert(CPUmodel cpu);

    @Query("SELECT * FROM cputx")
    CPUmodel getAll();

    @Delete
    void delete(CPUmodel cpu);

    @Query("DELETE FROM cputx")
    void deleteAll();
}
