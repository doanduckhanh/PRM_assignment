package com.example.cafemanagerapp.DAO;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.cafemanagerapp.Entity.Food;
import com.example.cafemanagerapp.Entity.User;

import java.util.List;

public interface FoodDAO {
    @Query("SELECT * FROM Food")
    List<Food> getAllFood();
    @Insert
    void addFood(Food food);
    @Update
    void updateFood(Food food);
    @Delete
    void deleteFood(Food food);
}
