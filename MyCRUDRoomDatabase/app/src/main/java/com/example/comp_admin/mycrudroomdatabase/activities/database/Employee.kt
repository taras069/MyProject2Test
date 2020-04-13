package com.example.comp_admin.mycrudroomdatabase.activities.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "employee_table")
data class Employee(
    @PrimaryKey
    var id:String,

    @ColumnInfo(name = "employee_name")
    var name:String,

    @ColumnInfo(name = "employee_email")
    var email:String
):Serializable{
    companion object{
        const val KEY = "employee"
    }
}