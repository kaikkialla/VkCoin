package com.example.vkcoin.database.server;

import com.example.vkcoin.model.CPUmodel;
import com.example.vkcoin.model.ServerModel;

import java.util.ArrayList;
import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface ServerDao {

    @Insert
    void insert(ServerModel serverModel);

    @Query("SELECT * FROM servertx")
    ServerModel getAll();


    @Delete
    void delete(ServerModel serverModel);

    @Query("DELETE FROM servertx")
    void deleteAll();
}
