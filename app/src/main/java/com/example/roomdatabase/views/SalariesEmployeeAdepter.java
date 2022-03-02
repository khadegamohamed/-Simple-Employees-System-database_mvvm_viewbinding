package com.example.roomdatabase.views;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roomdatabase.MainActivity;
import com.example.roomdatabase.database.DoubleValueListener;
import com.example.roomdatabase.database.Employee;
import com.example.roomdatabase.database.MyViewModel;
import com.example.roomdatabase.database.Repository;
import com.example.roomdatabase.databinding.ListItemMainBinding;

import java.util.List;

public class SalariesEmployeeAdepter extends RecyclerView.Adapter<SalariesEmployeeAdepter.Viewholder> {
   private List<Employee> employees;
private MyViewModel myViewModel;

    public SalariesEmployeeAdepter(List<Employee> employees, MyViewModel myViewModel) {
        this.employees = employees;
        this.myViewModel = myViewModel;
    }



    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Viewholder(ListItemMainBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
Employee employee = employees.get(position);
holder.listItemMainBinding.employee.setText(employee.getName());
myViewModel.getSalariesSum(employee.getId(), new DoubleValueListener() {
    @Override
    public void onValueSubmit(Double value) {
        holder.listItemMainBinding.salary.setText(value+"");
    }
});
        Log.d("Adepter", "onBindViewHolder: "+employee.getName());

    }

    @Override
    public int getItemCount() {
        return employees.size();
    }
    public static class Viewholder extends RecyclerView.ViewHolder {
        ListItemMainBinding listItemMainBinding;
        public Viewholder( ListItemMainBinding listItemMainBinding) {
            super(listItemMainBinding.getRoot());
            this.listItemMainBinding = listItemMainBinding;
        }

    }
}
