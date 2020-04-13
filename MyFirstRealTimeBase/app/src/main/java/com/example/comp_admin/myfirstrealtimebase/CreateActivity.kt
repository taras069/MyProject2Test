package com.example.comp_admin.myfirstrealtimebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.comp_admin.myfirstrealtimebase.models.User
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_create.*

class CreateActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)

        init()
    }

    private fun init() {
        button_insert.setOnClickListener {
           var name =  edit_text_name.text.toString()
            var email =  edit_text_email.text.toString()
            var mobile =  edit_text_mobile.text.toString()

            var user = User(name,email,mobile)

            var databaseReference = FirebaseDatabase
                .getInstance()
                .getReference("users")

            var userId  =databaseReference.push().key

            databaseReference.child(userId!!).setValue(user)

            Toast.makeText(this, "record inserted", Toast.LENGTH_SHORT).show()


        }
    }
}
