package com.example.comp_admin.a26loginwithvolley.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.comp_admin.a26loginwithvolley.R
import com.example.comp_admin.a26loginwithvolley.app.Endpoints
import com.example.comp_admin.a26loginwithvolley.helpers.SessionManager
import com.example.comp_admin.a26loginwithvolley.models.LoginResponse
import com.example.comp_admin.a26loginwithvolley.models.User
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_login.*
import org.json.JSONObject

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        init()
    }

    private fun init() {
        button_login.setOnClickListener(this)
        tv_register.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.button_login -> {
                pb_login.visibility = View.VISIBLE
                var email = edit_text_email.text.toString()
                var password = edit_text_password.text.toString()
                var user = User(email = email, password = password)
                login(user)
            }
            R.id.tv_register -> {
                startActivity(Intent(this,RegisterActivity::class.java))
            }
        }
    }

    private fun login(user: User) {var params = JSONObject()
        params.put(User.KEY_EMAIL, user.email)
        params.put(User.KEY_PASSWORD, user.password)


        var requestQueue = Volley.newRequestQueue(this)
        var request = JsonObjectRequest(
            Request.Method.POST,
            Endpoints.getLoginUrl(),
            params,
            Response.Listener { response ->
                pb_login.visibility = View.GONE
                var gson = GsonBuilder().create()
                var loginResponse = gson.fromJson(response.toString(), LoginResponse::class.java)
                sessionManager.saveUser(loginResponse.user, loginResponse.token)
                startActivity(Intent(this, MainActivity::class.java))
            },
            Response.ErrorListener {
                pb_login.visibility = View.GONE
                Toast.makeText(applicationContext, it.message, Toast.LENGTH_SHORT).show()
            }
        )
        requestQueue.add(request)

    }
}
