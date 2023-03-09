package com.example.cafemanagerapp.AppDatabase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.cafemanagerapp.DAO.CategoryDAO;
import com.example.cafemanagerapp.DAO.TableDAO;
import com.example.cafemanagerapp.DAO.UserDAO;
import com.example.cafemanagerapp.Entity.Category;
import com.example.cafemanagerapp.Entity.Food;
import com.example.cafemanagerapp.Entity.Table;
import com.example.cafemanagerapp.Entity.User;

@Database(entities = {User.class, Food.class, Category.class, Table.class},version = 3)
public abstract class AppDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "cafemanagerapp.db";
    private static AppDatabase instance;

    public static synchronized AppDatabase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }
    public abstract UserDAO userDAO();
    public abstract CategoryDAO categoryDAO();
    public abstract TableDAO tableDAO();
}
