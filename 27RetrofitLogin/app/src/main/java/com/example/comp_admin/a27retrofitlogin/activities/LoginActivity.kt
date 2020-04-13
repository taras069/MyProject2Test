package com.example.comp_admin.a27retrofitlogin.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.comp_admin.a27retrofitlogin.R
import com.example.comp_admin.a27retrofitlogin.helpers.SessionManager
import com.example.comp_admin.a27retrofitlogin.models.LoginResponse
import com.example.comp_admin.a27retrofitlogin.models.User
import com.example.comp_admin.a27retrofitlogin.network.ApiClient
import com.example.comp_admin.a27retrofitlogin.network.ApiService
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() , View.OnClickListener {
    lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        sessionManager = SessionManager(this)

        init()
    }

    private fun init() {
        button_login_login.setOnClickListener(this)
        text_view_register_login.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when(view.id){
            R.id.button_login_login ->{
                var email = edit_text_email_login.text.toString()
                var password = edit_text_password_login.text.toString()
                var user = User(email = email, password = password) /**specifying what goes into the model class, so valeus don't get mixed*/
                login(user)
            }

            R.id.text_view_register_login ->{
                startActivity(Intent(this,RegisterActivity::class.java))
            }
        }

    }

    private fun login(user: User) {

        var apiService = ApiClient.getInstance().create(ApiService::class.java)
        val requestPost = apiService.postLogin(user)
        requestPost.enqueue(object : Callback<LoginResponse> {

            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {

                if(response.isSuccessful)
                {
                    var newlyLoggedUser =
                        response.body() 

                    var loginResponse = newlyLoggedUser

                    Toast.makeText(applicationContext, response.toString(), Toast.LENGTH_SHORT).show()
                    Log.d("debugL", "success"+response.toString())
                    sessionManager.saveUser(loginResponse!!.user, loginResponse.token)
                    startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                }
                else{
                    Toast.makeText(applicationContext, "not registered failed response"+response.toString(), Toast.LENGTH_SHORT).show()
                    Log.d("debugL", "unsuccessful"+response.toString())

                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Toast.makeText(applicationContext, t.toString(), Toast.LENGTH_SHORT).show()
                Log.d("debugL", "fail")            }

        })
    }
}