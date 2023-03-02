package com.example.cafemanagerapp.AppDatabase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.cafemanagerapp.DAO.NhanVienDAO;
import com.example.cafemanagerapp.Entity.NhanVien;

@Database(entities = {NhanVien.class},version = 1)
public abstract class NhanVienDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "nhanvien.db";
    private static NhanVienDatabase instance;

    public static synchronized NhanVienDatabase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),NhanVienDatabase.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }
    public abstract NhanVienDAO nhanVienDAO();
}
