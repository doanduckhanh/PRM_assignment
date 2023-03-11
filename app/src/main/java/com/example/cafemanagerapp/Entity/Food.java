package com.example.cafemanagerapp.Entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Food {
    @PrimaryKey(autoGenerate = true)
    public int food_id;
    @ColumnInfo(name = "food_name")
    public String food_name;
    @ColumnInfo(name = "price")
    public String price;
    @ColumnInfo(name = "status")
    public String status;
    @ColumnInfo(name = "image")
    public byte[] image;
    @ColumnInfo(name = "category_id")
    public int category_id;

    public Food() {
    }

    public Food(int food_id, String food_name, String price, String status, byte[] image, int category_id) {
        this.food_id = food_id;
        this.food_name = food_name;
        this.price = price;
        this.status = status;
        this.image = image;
        this.category_id = category_id;
    }

    public int getFood_id() {
        return food_id;
    }

    public void setFood_id(int food_id) {
        this.food_id = food_id;
    }

    public String getFood_name() {
        return food_name;
    }

    public void setFood_name(String food_name) {
        this.food_name = food_name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }
}
