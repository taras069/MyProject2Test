package com.example.comp_admin.sharedpreferencelogin

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.comp_admin.sharedpreferencelogin.helpers.SessionManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        init()
    }

    private fun init() {
        button_submit.setOnClickListener {
            var name = et_name.text.toString()
            var email = et_email.text.toString()
            var password = et_password.text.toString()

            var mySessionManager = SessionManager(this)
            mySessionManager.register(name,email,password)
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}
