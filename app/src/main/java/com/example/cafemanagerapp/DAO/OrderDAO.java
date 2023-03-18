package com.example.cafemanagerapp.DAO;

import android.database.Cursor;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.cafemanagerapp.Entity.Order;

import java.util.List;

@Dao
public interface OrderDAO {
    @Insert
    void insert(Order order);

    @Query("select * from 'order'")
    List<Order> getAll();

    @Update
    void update(Order order);

    @Query("select * from `order` where table_id like '%' || :table_id || '%'")
    Order getByTableId(int table_id);

    @Query("SELECT order_id FROM `order` WHERE table_id LIKE '%' || :maban || '%' AND order_status LIKE '%' || :tinhtrang || '%'")
    int getOrderIdByTable(int maban, String tinhtrang);
}
