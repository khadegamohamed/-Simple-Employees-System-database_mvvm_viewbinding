package com.example.roomdatabase.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.TypeConverters;
import androidx.room.Update;

import java.util.Date;
import java.util.List;

@Dao
@TypeConverters({DataConverter.class})
public interface SalaryDAO {
    @Insert
    void insertSalary(Salary... salaries);

    @Update
    void updataSalary(Salary... salaries);

    @Delete
    void deleteSalary(Salary... salaries);

    @Query("select * from Salary where empId=:emp_id AND data >=:from AND data<=:to")
    LiveData<List<Salary>> getSalarybydataandId(long emp_id, Date from, Date to);

    @Query("select * from Salary where empId=:empId order by data desc ")
    LiveData<List<Salary>> getSalary(long empId);

    @Query("select * from Salary where  data >=:from AND data<=:to order by data desc")
    LiveData<List<Salary>> getSalarybydata(Date from, Date to);

    @Query("select sum(amount) from salary where empId=:emp_id")
    double getSalariesSum(long emp_id);

    @Query("select * from Salary  ")
    LiveData<List<Salary>> getallSalary();
}
