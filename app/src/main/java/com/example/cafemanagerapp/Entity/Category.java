package com.example.cafemanagerapp.Entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Category {
    @PrimaryKey
    int id;

    @ColumnInfo(name = "KindName")
    String KindName;

    public Category() {
    }

    public Category(int id, String kindName) {
        this.id = id;
        KindName = kindName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKindName() {
        return KindName;
    }

    public void setKindName(String kindName) {
        KindName = kindName;
    }
}
