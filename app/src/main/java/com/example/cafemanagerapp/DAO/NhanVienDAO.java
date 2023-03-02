package com.example.cafemanagerapp.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.cafemanagerapp.Entity.NhanVien;

import java.util.List;

@Dao
public interface NhanVienDAO {
    @Query("SELECT * FROM nhanvien")
    List<NhanVien> getAll();

    @Insert
    void insert(NhanVien nhanVien);

    @Update
    void update(NhanVien nhanVien);

    @Delete
    void delete(NhanVien nhanVien);

    @Query("SELECT * FROM nhanvien WHERE TENDN = (:TENDN) AND MATKHAU = (:MATKHAU)")
    int KiemTraDN(String TENDN, String MATKHAU);

    @Query("SELECT * FROM nhanvien WHERE MANV=(:MANV)")
    NhanVien LayNVTheoMa(int MANV);

    @Query("SELECT MAQUYEN FROM nhanvien WHERE MANV=(:MANV)")
    int LayQuyenNV(int MANV);
}
