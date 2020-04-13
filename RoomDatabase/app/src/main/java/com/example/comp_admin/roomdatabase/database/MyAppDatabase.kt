package com.example.comp_admin.roomdatabase.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Employee::class), version = 1)
abstract class MyAppDatabase : RoomDatabase() {

    abstract fun myDao(): MyDao

}