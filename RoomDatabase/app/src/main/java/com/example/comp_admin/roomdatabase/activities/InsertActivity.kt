package com.example.comp_admin.roomdatabase.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.room.Room
import com.example.comp_admin.roomdatabase.R
import com.example.comp_admin.roomdatabase.database.Employee
import com.example.comp_admin.roomdatabase.database.MyAppDatabase
import kotlinx.android.synthetic.main.activity_insert.*

class InsertActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert)

        init()
    }

    private fun init() {
        button_insert.setOnClickListener {
            var id = edit_text_id.text.toString()
            var name = edit_text_name.text.toString()
            var email = edit_text_email.text.toString()

            var employee = Employee(id, name, email)

            var mydb = Room.databaseBuilder(this, MyAppDatabase::class.java, "empdb")
                .allowMainThreadQueries()
                .build()

            mydb.myDao().addEmployee(employee)
            Toast.makeText(applicationContext, "Record inserted", Toast.LENGTH_SHORT).show()

        }

    }
}
