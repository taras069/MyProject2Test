package com.example.comp_admin.a27retrofitlogin.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.comp_admin.a27retrofitlogin.R
import com.example.comp_admin.a27retrofitlogin.models.User
import com.example.comp_admin.a27retrofitlogin.network.ApiClient
import com.example.comp_admin.a27retrofitlogin.network.ApiService
import kotlinx.android.synthetic.main.activity_register.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        init()
    }

    private fun init() {

        button_register_register.setOnClickListener(this)
        text_view_login_register.setOnClickListener(this)
    }

    private fun registerUser(user: User) {


        var apiService = ApiClient.getInstance().create(ApiService::class.java)

        val requestPost = apiService.postRegistration(user)

        requestPost.enqueue(object : Callback<User> {

            override fun onResponse(call: Call<User>, response: Response<User>) {

                if(response.isSuccessful)
                {
                    var newlyCreatedRegistration =
                        response.body() //you can save it from here if you like
                    Toast.makeText(applicationContext, response.toString(), Toast.LENGTH_SHORT).show()
                    Log.d("debugR", response.toString())
                    startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
                }
                else{
                    Toast.makeText(applicationContext, "not registered failed response", Toast.LENGTH_SHORT).show()
                    Log.d("debugR", "unsuccessful"+response.toString())

                }


            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                Toast.makeText(applicationContext, t.toString(), Toast.LENGTH_SHORT).show()
                Log.d("debugR", "fail"+t.toString())

            }


        })

    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.button_register_register -> {
                var name = edit_text_first_name_register.text.toString()
                var email = edit_text_email_register.text.toString()
                var password = edit_text_password_register.text.toString()
                var mobile = edit_text_mobile_register.text.toString()


                var user = User(firstName = name, email = email, password = password, mobile = mobile)
                registerUser(user)
            }

            R.id.text_view_login_register ->{
                startActivity(Intent(this, LoginActivity::class.java))
            }
        }
    }
}