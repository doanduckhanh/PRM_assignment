package com.example.cafemanagerapp.Entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.sql.Date;

@Entity(tableName = "nhanvien")
public class NhanVien {
    @PrimaryKey(autoGenerate = true)
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

    public NhanVien() {
    }

    public NhanVien(int MANV, String HOTENNV, String TENDN, String MATKHAU, String EMAIL, String SDT, Boolean GIOITINH, Date NGAYSINH, String MAQUYEN) {
        this.MANV = MANV;
        this.HOTENNV = HOTENNV;
        this.TENDN = TENDN;
        this.MATKHAU = MATKHAU;
        this.EMAIL = EMAIL;
        this.SDT = SDT;
        this.GIOITINH = GIOITINH;
        this.NGAYSINH = NGAYSINH;
        this.MAQUYEN = MAQUYEN;
    }

    public int getMANV() {
        return MANV;
    }

    public void setMANV(int MANV) {
        this.MANV = MANV;
    }

    public String getHOTENNV() {
        return HOTENNV;
    }

    public void setHOTENNV(String HOTENNV) {
        this.HOTENNV = HOTENNV;
    }

    public String getTENDN() {
        return TENDN;
    }

    public void setTENDN(String TENDN) {
        this.TENDN = TENDN;
    }

    public String getMATKHAU() {
        return MATKHAU;
    }

    public void setMATKHAU(String MATKHAU) {
        this.MATKHAU = MATKHAU;
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public Boolean getGIOITINH() {
        return GIOITINH;
    }

    public void setGIOITINH(Boolean GIOITINH) {
        this.GIOITINH = GIOITINH;
    }

    public Date getNGAYSINH() {
        return NGAYSINH;
    }

    public void setNGAYSINH(Date NGAYSINH) {
        this.NGAYSINH = NGAYSINH;
    }

    public String getMAQUYEN() {
        return MAQUYEN;
    }

    public void setMAQUYEN(String MAQUYEN) {
        this.MAQUYEN = MAQUYEN;
    }
}
