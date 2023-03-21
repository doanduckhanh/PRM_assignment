package com.example.cafemanagerapp.Entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class OrderDetail {

    @PrimaryKey(autoGenerate = true)
    public int orderdetail_id;
    @ColumnInfo(name = "order_id")
    public int order_id;
    @ColumnInfo(name = "food_id")
    int food_id;
    @ColumnInfo(name = "quantity")
    int quantity;

    public OrderDetail() {
    }

    public OrderDetail(int orderdetail_id,int order_id, int food_id, int quantity) {
        this.orderdetail_id = orderdetail_id;
        this.order_id = order_id;
        this.food_id = food_id;
        this.quantity = quantity;
    }

    public int getOrderdetail_id() {
        return orderdetail_id;
    }

    public void setOrderdetail_id(int orderdetail_id) {
        this.orderdetail_id = orderdetail_id;
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
