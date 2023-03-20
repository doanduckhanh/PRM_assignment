package com.example.cafemanagerapp.Entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "payment")
public class Payment {
    @PrimaryKey
    int id;
    @ColumnInfo(name = "food_name")
    String food_name;
    @ColumnInfo(name = "amount")
    int amount;
    @ColumnInfo(name = "price")
    int price;
    @ColumnInfo(name = "image")
    byte[] image;

    public Payment() {
    }

    public Payment(int id, String food_name, int amount, int price, byte[] image) {
        this.id = id;
        this.food_name = food_name;
        this.amount = amount;
        this.price = price;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFood_name() {
        return food_name;
    }

    public void setFood_name(String food_name) {
        this.food_name = food_name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
