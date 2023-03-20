package com.example.cafemanagerapp.DAO;

import androidx.room.Dao;
import androidx.room.Query;
import com.example.cafemanagerapp.Entity.Payment;

import java.util.List;

@Dao
public interface PaymentDAO {
    @Query("SELECT o.order_id as id,d.food_name,o.quantity as amount,d.price*o.quantity as price,d.image FROM orderdetail o, food d WHERE o.food_id = d.food_id AND order_id LIKE '%' || :madondat || '%'")
    List<Payment> GetListFoodByOrderId(int madondat);
}
