package com.example.roomdatabase.database;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;
import androidx.room.Update;

import java.util.Date;
@Entity(foreignKeys={@ForeignKey(entity = Employee.class,parentColumns = {"id"},childColumns = {"empId"},onUpdate = ForeignKey.CASCADE,onDelete = ForeignKey.CASCADE)})
@TypeConverters({DataConverter.class})
public class Salary {
    //salary{id,amount,data,empId}
    @PrimaryKey(autoGenerate = true)
    private int id;
    @NonNull
    private double amount;
    @NonNull
    private Date data;
    @NonNull
    private long empId;

    public Salary() {
    }

    public Salary(int id, double amount, Date data, long empId) {
        this.id = id;
        this.amount = amount;
        this.data = data;
        this.empId = empId;
    }

    public Salary( double amount, Date data, long empId) {
        this.amount = amount;
        this.data = data;
        this.empId = empId;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public long getEmpId() {
        return empId;
    }

    public void setEmpId(long empId) {
        this.empId = empId;
    }
}
