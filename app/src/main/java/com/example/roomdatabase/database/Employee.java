package com.example.roomdatabase.database;

import android.provider.ContactsContract;

import java.io.Serializable;
import java.util.Date;
import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

@Entity (tableName = "Employee_table")
@TypeConverters({DataConverter.class})
public class Employee implements Serializable {
    //Employee{id,name,birthdata,email}
    @PrimaryKey
    @NonNull
    private long id;
    @NonNull
    private String name;
    @NonNull
    private String email;
    private Date birthDate;

    public Employee() {
    }

    public Employee(long id, @NonNull String name, @NonNull String email, Date birthDate) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.birthDate = birthDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    @NonNull
    public String getEmail() {
        return email;
    }

    public void setEmail(@NonNull String email) {
        this.email = email;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}
