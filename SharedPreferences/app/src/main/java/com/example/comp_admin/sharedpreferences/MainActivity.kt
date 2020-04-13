package com.example.comp_admin.sharedpreferences

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
    }

    private fun init() {
        button_save.setOnClickListener {
            var name = et_enter_text.text.toString()
            var sharedPreferences = getSharedPreferences("my key", Context.MODE_PRIVATE)
            var editor = sharedPreferences.edit()

            editor.putString("name", name)
            editor.commit()

           // tv_result.text = name
        }
        button_read.setOnClickListener{
            var sharedPreferences = getSharedPreferences("my_file", Context.MODE_PRIVATE)
            var name =  sharedPreferences.getString("name", null)
            tv_result.text = name
        }
    }
}
