package com.example.cafemanagerapp.Entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "order")
public class Order {
    @PrimaryKey(autoGenerate = true)
    int order_id;
    @ColumnInfo(name = "table_id")
    int table_id;
    @ColumnInfo(name = "user_id")
    int user_id;
    @ColumnInfo(name = "order_date")
    String order_date;
    @ColumnInfo(name="total")
    String total;
    @ColumnInfo(name = "order_status")
    String order_status;

    public Order() {
    }

    public Order(int order_id, int table_id, int user_id, String order_date, String total, String order_status) {
        this.order_id = order_id;
        this.table_id = table_id;
        this.user_id = user_id;
        this.order_date = order_date;
        this.total = total;
        this.order_status = order_status;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getTable_id() {
        return table_id;
    }

    public void setTable_id(int table_id) {
        this.table_id = table_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getOrder_date() {
        return order_date;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getOrder_status() {
        return order_status;
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }
}
