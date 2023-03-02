package com.example.cafemanagerapp.AppDatabase;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.cafemanagerapp.DAO.NhanVienDAO;
import com.example.cafemanagerapp.Entity.NhanVien;

@Database(entities = {NhanVien.class},version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract NhanVienDAO nhanVienDAO();
}
