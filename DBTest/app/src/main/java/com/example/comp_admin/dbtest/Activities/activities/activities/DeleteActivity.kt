package com.example.comp_admin.dbtest.Activities.activities.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.comp_admin.dbtest.Activities.activities.helpers.DBHelper
import com.example.comp_admin.dbtest.Activities.activities.models.Employee
import com.example.comp_admin.dbtest.R
import kotlinx.android.synthetic.main.activity_delete.*

class DeleteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete)

        init()
    }

    private fun init() {
        button_delete_table.setOnClickListener {
            var id = et_id_delete.text.toString().toInt()
            var dbHelper = DBHelper(this)
            var employee = Employee(id)
            dbHelper.deleteEmployee(employee)
            Toast.makeText(this, "deleted", Toast.LENGTH_SHORT).show()

        }
    }
}
