package com.example.comp_admin.roomdatabase.database

import androidx.room.Dao
import androidx.room.Insert

@Dao
interface MyDao {

    @Insert
    fun addEmployee(employee: Employee)

}