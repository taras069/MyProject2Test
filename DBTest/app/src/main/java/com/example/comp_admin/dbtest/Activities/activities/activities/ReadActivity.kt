package com.example.comp_admin.dbtest.Activities.activities.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.comp_admin.dbtest.Activities.activities.adapters.ReadAdapter
import com.example.comp_admin.dbtest.Activities.activities.helpers.DBHelper
import com.example.comp_admin.dbtest.Activities.activities.models.Employee
import com.example.comp_admin.dbtest.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_read.*

class ReadActivity : AppCompatActivity() {

    lateinit var mList: ArrayList<Employee>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_read)

        init()
    }

    private fun init() {

            var myDb = DBHelper(this)
            mList = myDb.readEmployee()
            Toast.makeText(this," "+mList.size, Toast.LENGTH_SHORT).show()
            my_recycle.adapter = ReadAdapter(this, mList)
            my_recycle.layoutManager = LinearLayoutManager(this)



    }
}
