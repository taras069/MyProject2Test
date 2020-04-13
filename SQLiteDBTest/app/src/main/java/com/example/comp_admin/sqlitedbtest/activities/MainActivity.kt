package com.example.comp_admin.sqlitedbtest.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.comp_admin.sqlitedbtest.R
import com.example.comp_admin.sqlitedbtest.helpers.DBHelper
import com.example.comp_admin.sqlitedbtest.models.Employee
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
    }

    private fun init() {
        button_insert.setOnClickListener {
            var id = edit_text_id.text.toString().toInt()
            var name = edit_text_name.text.toString()
            var email = edit_text_email.text.toString()

            var employee = Employee(id, name, email)

            var dbHelper = DBHelper(this)
            dbHelper.addEmpployee(employee)
            Toast.makeText(this, "inserted", Toast.LENGTH_SHORT).show()
        }
    }

}