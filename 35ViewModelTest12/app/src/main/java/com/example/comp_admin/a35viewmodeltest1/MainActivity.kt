package com.example.comp_admin.a35viewmodeltest1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //var number = RandomNumberGenerator()
        //text_view.text = number.getNumber()

        var myViewModel = ViewModelProviders.of(this).get(MyViewModel::class.java)

        text_view.text = myViewModel.getNumber()


    }
}
