package com.example.comp_admin.a27retrofitlogin.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.comp_admin.a27retrofitlogin.R
import com.example.comp_admin.a27retrofitlogin.helpers.SessionManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sessionManager = SessionManager(this)
        if (!sessionManager.isLoggedIn()) {
            startActivity(Intent(this, LoginActivity::class.java))
        }
        init()
    }

    private fun init() {
        var user = sessionManager.getUser()
        text_view_main.text = "Welcome ${user.firstName}"

        button_logout.setOnClickListener{
            sessionManager.logout()
            startActivity(Intent(this,LoginActivity::class.java))
            finish()
        }
    }
}