package com.example.cafemanagerapp.Entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.sql.Date;

@Entity
public class NhanVien {
    @PrimaryKey
    public int MANV;

    @ColumnInfo(name = "HOTENNV")
    public String HOTENNV;

    @ColumnInfo(name = "TENDN")
    public String TENDN;

    @ColumnInfo(name = "MATKHAU")
    public String MATKHAU;

    @ColumnInfo(name = "EMAIL")
    public String EMAIL;

    @ColumnInfo(name = "SDT")
    public String SDT;

    @ColumnInfo(name = "GIOITINH")
    public Boolean GIOITINH;

    @ColumnInfo(name = "NGAYSINH")
    public Date NGAYSINH;

    @ColumnInfo(name = "MAQUYEN")
    public String MAQUYEN;
}
