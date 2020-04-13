package com.example.comp_admin.mycrudroomdatabase.activities.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Employee::class), version = 2)
abstract class MyDatabase : RoomDatabase() {
    abstract fun myDao(): MyDao
}