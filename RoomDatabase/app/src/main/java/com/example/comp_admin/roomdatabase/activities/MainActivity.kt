package com.example.comp_admin.roomdatabase.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.comp_admin.roomdatabase.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() , View.OnClickListener{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
    }

    private fun init() {
        button_insert.setOnClickListener(this)

    }

    override fun onClick(view: View) {
        when(view.id){
            R.id.button_insert -> {
                startActivity(Intent(this, InsertActivity::class.java))
            }
        }
    }
}
