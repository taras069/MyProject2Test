package com.example.comp_admin.mycrudroomdatabase.activities.database

import androidx.room.*

@Dao
interface MyDao {
    @Insert
    fun addEmployee(employee: Employee)

    @Query("select * from employee_table")
    fun getEmployees(): List<Employee>

    @Update
    fun updateEmployee(employee: Employee)

    @Delete
    fun deleteEmployee(employee: Employee)

    @Query("select * from employee_table where id = :empId")
    fun getEmployeeById(empId: String) :Employee

}