package com.example.vkcoin.database.server;


import com.example.vkcoin.model.ServerModel;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = ServerModel.class, version = 1, exportSchema = false)
public abstract class ServerDatabase extends RoomDatabase {
    public abstract ServerDao getServerDao();
}
