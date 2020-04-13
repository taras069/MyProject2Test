package com.example.comp_admin.sharedpreferencelogin

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.comp_admin.sharedpreferencelogin.helpers.SessionManager
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        init()
    }

    private fun init() {
        button_submit_login.setOnClickListener {
            var email = et_email_login.text.toString()
            var password = et_password_login.text.toString()

            var mySessionManager = SessionManager(this)
            if (mySessionManager.login(email,password)){
                startActivity(Intent(this, HomeActivity::class.java))
            } else
                Toast.makeText(this, "Invalid", Toast.LENGTH_SHORT).show()
        }
    }
}