package com.example.cafemanagerapp.Entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class OrderDetail {
    @PrimaryKey
    public int order_id;
@ColumnInfo(name = "food_id")
    int food_id;
@ColumnInfo(name = "quantity")
    int quantity;

    public OrderDetail() {
    }

    public OrderDetail(int order_id, int food_id, int quantity) {
        this.order_id = order_id;
        this.food_id = food_id;
        this.quantity = quantity;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getFood_id() {
        return food_id;
    }

    public void setFood_id(int food_id) {
        this.food_id = food_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
