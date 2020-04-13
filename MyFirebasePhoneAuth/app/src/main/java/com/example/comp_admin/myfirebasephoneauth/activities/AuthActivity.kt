package com.example.comp_admin.myfirebasephoneauth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle


class AuthActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        init()
    }

    private fun init() {
        supportFragmentManager.beginTransaction().add(R.id.fragment_container, LoginFragment()).commit()

    }
}
