package com.example.cafemanagerapp.DAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.cafemanagerapp.Entity.Order;

import java.util.List;

@Dao
public interface OrderDAO {
    @Insert
    void insert(Order order);

    @Query("select * from 'order'")
    List<Order> getAll();

    @Query("select * from `order` where table_id like '%' || :table_id || '%'")
    Order getByTableId(int table_id);
}
