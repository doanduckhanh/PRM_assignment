package com.example.cafemanagerapp.Entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Food {
    @PrimaryKey
    int Id;
    @ColumnInfo(name = "FoodName")
    String FoodName;
    @ColumnInfo(name = "Price")
    String Price;
    @ColumnInfo(name = "Status")
    String Status;
    @ColumnInfo(name = "Image")
    byte[] Image;
    @ColumnInfo(name = "KindId")
    int KindId;

    public Food() {
    }

    public Food(int id, String foodName, String price, String status, byte[] image, int kindId) {
        Id = id;
        FoodName = foodName;
        Price = price;
        Status = status;
        Image = image;
        KindId = kindId;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getFoodName() {
        return FoodName;
    }

    public void setFoodName(String foodName) {
        FoodName = foodName;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public byte[] getImage() {
        return Image;
    }

    public void setImage(byte[] image) {
        Image = image;
    }

    public int getKindId() {
        return KindId;
    }

    public void setKindId(int kindId) {
        KindId = kindId;
    }
}
