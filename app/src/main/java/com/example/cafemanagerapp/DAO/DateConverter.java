package com.example.cafemanagerapp.DAO;

import androidx.room.TypeConverter;

import java.util.Date;

public class DateConverter {
    @TypeConverter
    public static java.sql.Date toDate(Long dateLong){
        return dateLong == null ? null: new java.sql.Date(dateLong);
    }

    @TypeConverter
    public static Long fromDate(Date date){
        return date == null ? null : date.getTime();
    }
}
