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
import com.example.comp_admin.a26loginwithvolley.models.User
import kotlinx.android.synthetic.main.activity_register.*
import org.json.JSONObject

class RegisterActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        init()
    }

    private fun init() {
        button_register.setOnClickListener(this)
        tv_login.setOnClickListener(this)
    }


    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.button_register -> {
                pb_register.visibility = View.VISIBLE
                var email = edit_text_email.text.toString()
                var password = edit_text_password.text.toString()
                var firstName = edit_text_name.text.toString()
                var mobile = edit_text_mobile.text.toString()

                var user = User(firstName, mobile, email, password)
                register(user)
               

            }
            R.id.tv_login -> {
                startActivity(Intent(this, LoginActivity::class.java))
            }
        }
    }

    private fun register(user: User) {
        var params = JSONObject()
        params.put(User.KEY_FIRST_NAME, user.firstName)
        params.put(User.KEY_EMAIL, user.email)
        params.put(User.KEY_MOBILE, user.mobile)
        params.put(User.KEY_PASSWORD, user.password)

        var requestQueue = Volley.newRequestQueue(this)
        var request = JsonObjectRequest(
            Request.Method.POST,
            Endpoints.getRegisterUrl(),
            params,
            Response.Listener { response ->
                pb_register.visibility = View.GONE
                Toast.makeText(applicationContext, response.toString(), Toast.LENGTH_SHORT).show()
            },
            Response.ErrorListener {
                pb_register.visibility = View.GONE
                Toast.makeText(applicationContext, "error", Toast.LENGTH_SHORT).show()
            }
        )
        requestQueue.add(request)

    }

}

