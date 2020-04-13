package com.example.comp_admin.myfirebasetest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        init()
    }

    private fun init() {
        button_login.setOnClickListener {
            var email = edit_text_email.text.toString()
            var password = edit_text_password.text.toString()

            var auth = FirebaseAuth.getInstance()
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, object: OnCompleteListener<AuthResult> {
                    override fun onComplete(task: Task<AuthResult>) {
                        if(task.isSuccessful){
                            Toast.makeText(applicationContext, "success", Toast.LENGTH_SHORT).show()
                        }else{
                            Toast.makeText(applicationContext, "failed", Toast.LENGTH_SHORT).show()
                        }

                    }

                })
        }

    }
}
