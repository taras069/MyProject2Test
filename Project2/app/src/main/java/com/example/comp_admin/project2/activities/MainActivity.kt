package com.example.comp_admin.project2.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.comp_admin.project2.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
    }

    private fun init() {
        button_login.setOnClickListener(this)
        button_register.setOnClickListener(this)
    }

    override fun onClick(p0: View) {
        when(p0.id){
            R.id.button_register -> {
                startActivity(Intent(this, RegisterActivity::class.java))
            }
            R.id.button_login -> {
                Toast.makeText(this, "Login Clicked", Toast.LENGTH_SHORT).show()
            }
        }
    }
}