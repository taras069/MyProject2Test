package com.example.comp_admin.mycrudroomdatabase.activities.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import com.example.comp_admin.mycrudroomdatabase.R
import com.example.comp_admin.mycrudroomdatabase.activities.database.Employee
import com.example.comp_admin.mycrudroomdatabase.activities.database.MyDatabase

class UpdateActivity : AppCompatActivity() {
    var mList: List<Employee> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)

        init()
    }

    private fun init() {
        var myDb = Room.databaseBuilder(this, MyDatabase::class.java, "empDB")
            .allowMainThreadQueries()
            .build()
        mList = myDb.myDao().getEmployees()


}
