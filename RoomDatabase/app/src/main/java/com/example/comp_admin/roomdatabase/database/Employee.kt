package com.example.comp_admin.roomdatabase.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "employee_table")
data class Employee (

    @PrimaryKey
    var id: String,

    @ColumnInfo(name = "emp_name")
    var name: String,

    @ColumnInfo(name="emp_email")
    var email: String
)