package com.example.cafemanagerapp.AppDatabase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.cafemanagerapp.DAO.CategoryDAO;
import com.example.cafemanagerapp.Entity.Category;


@Database(entities = {Category.class},version = 1)
public abstract class CategoryDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "category.db";
    private static CategoryDatabase instance;

    public static synchronized CategoryDatabase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), CategoryDatabase.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }
    public abstract CategoryDAO categoryDAO();
}
