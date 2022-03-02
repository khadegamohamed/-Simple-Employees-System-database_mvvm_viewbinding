package com.example.roomdatabase.database;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.Date;
import java.util.List;

public class MyViewModel extends AndroidViewModel {
    private Repository repository;
    private LiveData<List<Employee>> allEmployee;

    public MyViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
        allEmployee = repository.getAllEmployees();
    }

    public void insertEmployee(Employee... employees) {
        repository.insertEmployee(getApplication(),employees);
    }

    public void updateEmployee(Employee... employees) {
        repository.updateEmployee();
    }

    public void deleteEmployee(Employee... employees) {
        repository.deleteEmployee();
    }

    public void deleteEmployeeByEmail(String email) {
        repository.deleteEmployeeByEmail(email);
    }

    public LiveData<List<Employee>> getAllEmployee() {
        return allEmployee;
    }

    public LiveData<List<Employee>> getEmployeeByEmail(String email) {
        return repository.getEmployeeByEmail(email);
    }

    public LiveData<List<Employee>> getEmployeeByName(String name) {
        return repository.getEmployeeByName(name);
    }

    public void insertSalary(Salary... salaries) {
        repository.insertSalary(getApplication(),salaries);
    }

    public void updateSalary(Salary... salaries) {
        repository.updateSalary();
    }

    public void deleteSalary(Salary... salaries) {
        repository.deleteSalary();
    }

    public LiveData<List<Salary>> getSalaryByDataAndId(long emp_id, Date from, Date to) {
        return repository.getSalaryByDateAndId(emp_id, from, to);
    }

    public LiveData<List<Salary>> getSalary(long empId) {
        return repository.getSalary(empId);
    }

    public LiveData<List<Salary>> getSalaryByDate(Date from, Date to) {
        return repository.getSalaryByData(from, to);
    }

    public void getSalariesSum(long emp_id, DoubleValueListener listener) {
        repository.getSalariesSum(emp_id, listener);
    }

    public LiveData<List<Salary>> getAllSalary() {
        return repository.getAllSalary();
    }
}
