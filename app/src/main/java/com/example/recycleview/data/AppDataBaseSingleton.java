package com.example.recycleview.data;

import android.content.Context;

import androidx.room.Room;

public class AppDataBaseSingleton {
    public static AppDataBaseSingleton instance = null;
    public AppDatabase appDatabase;
    private AppDataBaseSingleton(Context context) {
        appDatabase = Room.databaseBuilder(context, AppDatabase.class, "pokemon").build();
    }
    public static AppDataBaseSingleton getInstance(Context context) {
        if (instance == null) {
            instance = new AppDataBaseSingleton(context);
        }
        return instance;
    }
}

