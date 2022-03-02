package com.example.roomdatabase;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.roomdatabase.database.Employee;
import com.example.roomdatabase.database.MyRoomDatebase;
import com.example.roomdatabase.database.MyViewModel;
import com.example.roomdatabase.databinding.ActivityMainBinding;
import com.example.roomdatabase.views.SalariesEmployeeAdepter;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    MyViewModel viewModel;
    ActivityMainBinding binding;
    SalariesEmployeeAdepter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(MyViewModel.class);
        // adepter = new SalariesEmployeeAdepter(new ArrayList<>(),viewModel);
        // adapter = new SalariesEmployeeAdepter();
        RecyclerView.LayoutManager rm = new LinearLayoutManager(this);
        binding.recyclermain.setLayoutManager(rm);
        binding.recyclermain.setHasFixedSize(true);
        viewModel.getAllEmployee().observe(MainActivity.this, employees -> {
            Log.d("view_model", "onChanged: " + employees);
            adapter = new SalariesEmployeeAdepter(employees,viewModel);
            binding.recyclermain.setAdapter(adapter);
            adapter.setEmployees(employees);
        });
        ActivityResultLauncher<Intent> arl = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        Employee employee = (Employee) result.getData().getSerializableExtra(AddEmployee.Employee_Key);
                        Log.d("Employee_object", "onActivityResult:" + employee.getName());
                        viewModel.insertEmployee(employee);
                    }
                });
        binding.addEmployee.setOnClickListener(v -> {
            Intent intent = new Intent(getBaseContext(), AddEmployee.class);
            arl.launch(intent);

        });
    }

    //addsalary
    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        getMenuInflater().inflate(R.menu.salary, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.addsalary) {
            Intent intent = new Intent(this, AddSalary.class);
            startActivity(intent);
            return true;
        } else {
            return false;
        }
    }
}