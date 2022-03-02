package com.example.roomdatabase;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.roomdatabase.database.Employee;
import com.example.roomdatabase.database.MyViewModel;
import com.example.roomdatabase.databinding.ActivityAddEmployeeBinding;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Calendar;

public class AddEmployee extends AppCompatActivity {
    public static final String Employee_Key = "employee";
    ActivityAddEmployeeBinding binding;
    MyViewModel viewModel;
    Calendar selectedDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddEmployeeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        viewModel = new ViewModelProvider(this).get(MyViewModel.class);
        binding.savebutton.setOnClickListener(v -> {
            String name = binding.employeename.getText().toString();
            String email = binding.employeeemail.getText().toString();
            String id = binding.employeeId.getText().toString();
            if (TextUtils.isEmpty(name) || TextUtils.isEmpty(email)
                    || TextUtils.isEmpty(id) || selectedDay == null) {
                Toast.makeText(AddEmployee.this, "Please enter valid data", Toast.LENGTH_SHORT).show();
                return;
            }
            long idlong = Long.parseLong(id);
            Employee employee = new Employee(idlong, name, email, selectedDay.getTime());
            Log.d("view_model", "onClick: " + employee.getName());
            Intent intent = new Intent();
            intent.putExtra(Employee_Key, employee);
            setResult(RESULT_OK, intent);
            finish();
        });
        binding.addData.setOnClickListener(v -> {
            DatePickerDialog dialog = DatePickerDialog.newInstance((datePickerDialog, i, i1, i2) -> {
                binding.addData.setText((i + "/" + i1 + "/" + i2 + "/"));
                selectedDay = Calendar.getInstance();
                selectedDay.set(Calendar.YEAR, i);
                selectedDay.set(Calendar.MONTH, i1);
                selectedDay.set(Calendar.DAY_OF_MONTH, i2);
            }, Calendar.getInstance());
            dialog.show(getSupportFragmentManager(), null);
        });
    }


}