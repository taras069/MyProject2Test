package com.example.comp_admin.dbtest.Activities.activities.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.comp_admin.dbtest.Activities.activities.helpers.DBHelper
import com.example.comp_admin.dbtest.Activities.activities.models.Employee
import com.example.comp_admin.dbtest.R
import kotlinx.android.synthetic.main.activity_create.*
import kotlinx.android.synthetic.main.activity_main.*

class CreateActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)

        init()
    }

    private fun init() {
        button_create_table.setOnClickListener {
            var id = et_id.text.toString().toInt()
            var name = et_name.text.toString()
            var email = et_email.text.toString()

            var employee = Employee(id, name, email)

            var dbHelper = DBHelper(this)
            dbHelper.createEmployee(employee)
            Toast.makeText(this, "inserted", Toast.LENGTH_SHORT).show()
        }
    }
}
