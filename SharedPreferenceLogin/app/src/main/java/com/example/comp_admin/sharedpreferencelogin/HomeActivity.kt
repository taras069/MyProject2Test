package com.example.comp_admin.sharedpreferencelogin

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.comp_admin.sharedpreferencelogin.helpers.SessionManager
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        init()
    }

    private fun init() {
        button_logout.setOnClickListener {
            SessionManager(this).logout()

            startActivity(Intent(this,LoginActivity::class.java))
        }
    }
}
