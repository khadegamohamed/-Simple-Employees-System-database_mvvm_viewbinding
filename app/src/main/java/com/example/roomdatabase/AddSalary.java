package com.example.roomdatabase;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.roomdatabase.database.Employee;
import com.example.roomdatabase.database.MyViewModel;
import com.example.roomdatabase.database.Salary;
import com.example.roomdatabase.databinding.ActivityAddSalaryBinding;
import com.example.roomdatabase.views.Employeespinner;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AddSalary extends AppCompatActivity {
    ActivityAddSalaryBinding binding;
    Calendar selectedday;
    Employeespinner adepter;
    MyViewModel viewModel;

    private static final String TAG = "AddSalary";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddSalaryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        viewModel = new ViewModelProvider(this).get(MyViewModel.class);
        binding.adddata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dialog = DatePickerDialog.newInstance(new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePickerDialog datePickerDialog, int i, int i1, int i2) {
                        binding.adddata.setText(i + "/" + i1 + "/" + i2 + "/");
                        selectedday = Calendar.getInstance();
                        selectedday.set(Calendar.YEAR, i);
                        selectedday.set(Calendar.MONTH, i1);
                        selectedday.set(Calendar.DAY_OF_MONTH, i2);
                    }
                }, Calendar.getInstance());
                dialog.show(getSupportFragmentManager(), null);
            }
        });
        adepter = new Employeespinner(new ArrayList<>());
        binding.Emploeespinner.setAdapter(adepter);
        viewModel.getAllEmployee().observe(this, employees -> {
            Log.d(TAG, "onCreate: " + employees);
            adepter.setEmployees(employees);
        });

        binding.savebutton.setOnClickListener(v -> {
            String amountsalary = binding.addsalary.getText().toString();
            long empid = binding.Emploeespinner.getSelectedItemId();
            if (TextUtils.isEmpty(amountsalary) || selectedday == null) {
                Toast.makeText(AddSalary.this, "Please enter avalid data", Toast.LENGTH_SHORT).show();
                return;
            }
            double amount = Double.parseDouble(amountsalary);
            Salary salary = new Salary(amount, selectedday.getTime(), empid);
            viewModel.insertSalary(salary);
            finish();
        });
    }
}