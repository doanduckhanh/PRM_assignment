package com.example.cafemanagerapp.DAO;

import androidx.room.Dao;
import androidx.room.Query;

import com.example.cafemanagerapp.Entity.Food;

import java.util.List;

@Dao
public interface FoodDAO {
    @Query("Select * from food where category_id like '%' || :cate_id || '%'")
    List<Food> getByCateId(int cate_id);
}
