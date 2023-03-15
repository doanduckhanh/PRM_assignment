package com.example.cafemanagerapp.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


import com.example.cafemanagerapp.Entity.Category;

import java.util.List;

@Dao
public interface CategoryDAO {
    @Query("SELECT * FROM Category")
    List<Category> getAllCategory();
    @Insert
    void addCategory(Category c);
    @Update
    void updateCategory(Category c);
    @Delete
    void deleteFood(Category c);
}
