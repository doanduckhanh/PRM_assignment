package com.example.cafemanagerapp.DAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.cafemanagerapp.Entity.OrderDetail;

import java.util.List;

@Dao
public interface OrderDetailDAO {
    @Query("select * from orderdetail where order_id like '%' || :or_id || '%' and food_id like '%' || :food_id || '%'")
    OrderDetail getQuantityByOrIdAndFoodId(int or_id, int food_id);

    @Update
    void update(OrderDetail orderDetail);

    @Insert
    void insert(OrderDetail orderDetail);

}
