package com.example.comp_admin.myfirebasetest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        auth = FirebaseAuth.getInstance()

        if (auth.currentUser == null) {
            startActivity(Intent(this, MainActivity::class.java))
        }

        init()

    }

    private fun init() {
        var user = auth.currentUser
        text_view.text = user?.email

        button_logout.setOnClickListener{
           auth.signOut()
        }
    }
}
