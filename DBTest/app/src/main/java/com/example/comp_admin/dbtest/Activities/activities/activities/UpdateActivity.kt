package com.example.comp_admin.dbtest.Activities.activities.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.comp_admin.dbtest.Activities.activities.helpers.DBHelper
import com.example.comp_admin.dbtest.Activities.activities.models.Employee
import com.example.comp_admin.dbtest.R
import kotlinx.android.synthetic.main.activity_update.*

class UpdateActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)

        init()
    }

    private fun init() {
        button_update_table.setOnClickListener {
            var id = et_id_update.text.toString().toInt()
            var name = et_name_update.text.toString()
            var email = et_email_update.text.toString()

            var employee = Employee(id,name,email)

            var dbHelper = DBHelper(this)
            dbHelper.updateEmployee(employee)
            Toast.makeText(this, "updated", Toast.LENGTH_SHORT).show()


        }
    }
}
