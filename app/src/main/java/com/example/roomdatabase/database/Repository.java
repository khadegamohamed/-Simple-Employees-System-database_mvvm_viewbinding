package com.example.roomdatabase.database;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;

import java.util.Date;
import java.util.List;

public class Repository {
    EmployeeDAO employeeDAO;
    SalaryDAO salaryDAO;
    private static final String TAG = "Repository";

    public Repository(Application application) {
        MyRoomDatebase myRoomDatebase = MyRoomDatebase.getDatabase(application);
        employeeDAO = myRoomDatebase.employeeDAO();
        salaryDAO = myRoomDatebase.salaryDAO();
    }

    public void insertEmployee(Context context, Employee... employees) {
        Log.d(TAG, "insertEmployee: " + employees);
        MyRoomDatebase myRoomDatebase= MyRoomDatebase.getDatabase(context);
       MyRoomDatebase.databaseWriteExecutor.execute(new Runnable() {
           @Override
           public void run() {
              myRoomDatebase.employeeDAO().insertEmployee(employees);
           }
       });

    }

    public void updateEmployee(Employee... employees) {
        MyRoomDatebase.databaseWriteExecutor.execute(() -> employeeDAO.updataEmployee(employees));
    }

    public void deleteEmployee(Employee... employees) {
        MyRoomDatebase.databaseWriteExecutor.execute(() -> employeeDAO.deleteEmployee(employees));
    }

    public void deleteEmployeeByEmail(String email) {
        MyRoomDatebase.databaseWriteExecutor.execute(() -> employeeDAO.deleteEmployeebyemail(email));
    }

    public LiveData<List<Employee>> getAllEmployees() {
        return employeeDAO.getallEmployee();
    }

    public LiveData<List<Employee>> getEmployeeByEmail(String email) {
        return employeeDAO.getEmployeebyemail(email);
    }

    public LiveData<List<Employee>> getEmployeeByName(String name) {
        return employeeDAO.getEmployeebyname(name);
    }

    public void insertSalary(Context context,Salary... salaries) {
        MyRoomDatebase myRoomDatebase = MyRoomDatebase.getDatabase(context);
        MyRoomDatebase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                myRoomDatebase.salaryDAO().insertSalary(salaries);
            }
        });
    }
    public void updateSalary(Salary... salaries) {
        MyRoomDatebase.databaseWriteExecutor.execute(() -> salaryDAO.updataSalary(salaries));
    }

    public void deleteSalary(Salary... salaries) {
        MyRoomDatebase.databaseWriteExecutor.execute(() -> salaryDAO.deleteSalary(salaries));
    }

    public LiveData<List<Salary>> getSalaryByDateAndId(long emp_id, Date from, Date to) {
        return salaryDAO.getSalarybydataandId(emp_id, from, to);
    }

    public LiveData<List<Salary>> getSalary(long empId) {
        return salaryDAO.getSalary(empId);
    }

    public LiveData<List<Salary>> getSalaryByData(Date from, Date to) {
        return salaryDAO.getSalarybydata(from, to);
    }

    public LiveData<List<Salary>> getAllSalary() {
        return salaryDAO.getallSalary();
    }

    public void getSalariesSum(long emp_id, DoubleValueListener listener) {
        MyRoomDatebase.databaseWriteExecutor.execute(() -> {
            double sum = salaryDAO.getSalariesSum(emp_id);
            listener.onValueSubmit(sum);
        });
    }
}
