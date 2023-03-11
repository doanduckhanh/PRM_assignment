package com.example.cafemanagerapp.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.cafemanagerapp.Entity.TableSeat;

import java.util.List;

@Dao
public interface TableSeatDAO {
    @Query("SELECT * FROM table_seat")
     List<TableSeat> getAll();
    @Insert
     boolean insert(TableSeat tableSeat);

    @Update
     void update(TableSeat tableSeat);

    @Delete
     void delete(TableSeat tableSeat);

    @Query("SELECT * FROm table_seat where table_id like '%' || :id || '%'")
    TableSeat getById(int id);

}
