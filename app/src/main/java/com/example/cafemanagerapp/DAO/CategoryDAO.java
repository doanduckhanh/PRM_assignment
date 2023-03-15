package com.example.cafemanagerapp.DAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.cafemanagerapp.Entity.Category;

import java.util.List;

@Dao
public interface CategoryDAO {
    @Query("SELECT * FROM Category")
    List<Category> getAll();
    @Insert
    void insert(Category category);
}
