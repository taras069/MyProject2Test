package com.example.comp_admin.mycrudroomdatabase.activities.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.comp_admin.mycrudroomdatabase.R
import com.example.comp_admin.mycrudroomdatabase.activities.adapters.AdapterEmployee
import com.example.comp_admin.mycrudroomdatabase.activities.database.Employee
import com.example.comp_admin.mycrudroomdatabase.activities.database.MyDatabase
import kotlinx.android.synthetic.main.activity_read.*

class ReadActivity : AppCompatActivity() {

    var mList: List<Employee> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_read)

        var myDb = Room.databaseBuilder(this, MyDatabase::class.java, "empDB")
            .allowMainThreadQueries()
            .build()
        mList = myDb.myDao().getEmployees()
        my_rv.adapter = AdapterEmployee(this,mList)
        my_rv.layoutManager = LinearLayoutManager(this)

    }
}
