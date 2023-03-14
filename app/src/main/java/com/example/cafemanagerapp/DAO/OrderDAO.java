package com.example.cafemanagerapp.DAO;

import androidx.room.Dao;
import androidx.room.Insert;

import com.example.cafemanagerapp.Entity.Order;

@Dao
public interface OrderDAO {
    @Insert
    void insert(Order order);
}
