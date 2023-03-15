package com.example.cafemanagerapp.Entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "table_seat")
public class TableSeat {
    @PrimaryKey(autoGenerate = true)
    int table_id;
    @ColumnInfo(name = "table_name")
    String table_name;
    @ColumnInfo(name = "or_status")
    boolean or_status;

    public TableSeat() {
    }

    public TableSeat(int table_id, String table_name, boolean or_status) {
        this.table_id = table_id;
        this.table_name = table_name;
        this.or_status = or_status;
    }

    public int getTable_id() {
        return table_id;
    }

    public void setTable_id(int table_id) {
        this.table_id = table_id;
    }

    public String getTable_name() {
        return table_name;
    }

    public void setTable_name(String table_name) {
        this.table_name = table_name;
    }

    public boolean isOr_status() {
        return or_status;
    }

    public void setOr_status(boolean or_status) {
        this.or_status = or_status;
    }
}
