package com.example.cafemanagerapp.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.cafemanagerapp.Entity.User;

import java.util.List;

@Dao
public interface UserDAO {
    @Query("SELECT * FROM User")
    List<User> getAll();

    @Insert
    void insert(User user);

    @Update
    void update(User user);

    @Delete
    void delete(User user);

    @Query("SELECT * FROM User WHERE TENDN = (:TENDN) AND MATKHAU = (:MATKHAU)")
    int KiemTraDN(String TENDN, String MATKHAU);

    @Query("SELECT * FROM User WHERE MANV=(:MANV)")
    User LayNVTheoMa(int MANV);

    @Query("SELECT MAQUYEN FROM User WHERE MANV=(:MANV)")
    int LayQuyenNV(int MANV);
}
