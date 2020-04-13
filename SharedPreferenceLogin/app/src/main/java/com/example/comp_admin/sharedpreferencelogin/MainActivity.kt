package com.example.comp_admin.sharedpreferencelogin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
        button_register.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
        button_login.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}
