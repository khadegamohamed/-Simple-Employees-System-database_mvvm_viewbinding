package com.example.roomdatabase.database;

import androidx.room.TypeConverter;

import java.util.Date;

public class DataConverter {
    @TypeConverter
    public static Date toData(Long millisecound){
        if(millisecound != null)
            return new Date(millisecound);
            return null;
    }
    @TypeConverter
    public static Long fromData(Date data){
        if(data != null)
            return data.getTime();
        return null;
    }
}
