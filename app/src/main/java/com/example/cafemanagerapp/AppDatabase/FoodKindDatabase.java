package com.example.cafemanagerapp.AppDatabase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.cafemanagerapp.DAO.FoodDAO;
import com.example.cafemanagerapp.DAO.FoodKindDAO;
import com.example.cafemanagerapp.Entity.Food;
import com.example.cafemanagerapp.Entity.FoodKind;

@Database(entities = {FoodKind.class},version = 1)
public abstract class FoodKindDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "foodkind.db";
    private static FoodKindDatabase instance;

    public static synchronized FoodKindDatabase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), FoodKindDatabase.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }
    public abstract FoodKindDAO foodKindDAO();
}
