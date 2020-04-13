package com.example.comp_admin.mycrudroomdatabase.activities.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.room.Room
import com.example.comp_admin.mycrudroomdatabase.R
import com.example.comp_admin.mycrudroomdatabase.activities.database.Employee
import com.example.comp_admin.mycrudroomdatabase.activities.database.MyDatabase
import kotlinx.android.synthetic.main.activity_create.*

class CreateActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)

        init()
    }

    private fun init() {
        button_create.setOnClickListener {
            var id = edit_text_id.text.toString()
            var name = edit_text_name.text.toString()
            var email = edit_text_email.text.toString()

            var employee = Employee(id,name,email)

            var myDb = Room.databaseBuilder(this, MyDatabase::class.java, "empDB")
                .allowMainThreadQueries()
                .build()
            myDb.myDao().addEmployee(employee)
            Toast.makeText(this, "inserted", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this,ReadActivity::class.java))
        }
    }
}
