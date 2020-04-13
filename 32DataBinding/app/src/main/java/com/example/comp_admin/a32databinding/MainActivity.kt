package com.example.comp_admin.a32databinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.comp_admin.a32databinding.databinding.ActivityMainBinding
import com.example.comp_admin.a32databinding.models.User

class MainActivity : AppCompatActivity() {

    lateinit var mBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        //mBinding.user = getUser()
        mBinding.userInfo = getUser()

        init()
    }

    private fun init() {
//        text_name.text = getUser().name
//        text_email.text = getUser().email
    }

    private fun getUser(): User {
        var user = User("mark2", "mark2@gmail.com")
        return user
    }
}
