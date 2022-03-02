package com.example.roomdatabase.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.TypeConverters;
import androidx.room.Update;

import java.util.List;

@Dao
@TypeConverters({DataConverter.class})
public interface EmployeeDAO {
    @Insert
    void insertEmployee(Employee... employees);
    @Update
    void updataEmployee(Employee... employees);
    @Delete
    void deleteEmployee(Employee... employees);
    @Query("delete from Employee_table where email=:email")
    void deleteEmployeebyemail(String email);
    @Query("select * from Employee_table order by name asc")
    LiveData<List<Employee>> getallEmployee();
    @Query("select * from Employee_table where email =:email")
    LiveData<List<Employee>> getEmployeebyemail(String email);
    @Query("select * from Employee_table where name like '%'||:name||'%'")
    LiveData<List<Employee>> getEmployeebyname(String name);
}
