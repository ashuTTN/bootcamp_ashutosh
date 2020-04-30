package com.example.roomexercise;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface EmployeeDao {

    @Query("SELECT * from Employee")
    List<EmployeeEntity> getEmployeeList();

    @Insert
    void insert(EmployeeEntity employeeEntity);

    @Delete
    void delete(EmployeeEntity employeeEntity);

    @Update
    void update(EmployeeEntity employeeEntity);


}
