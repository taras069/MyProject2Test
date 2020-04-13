package com.example.comp_admin.a36viewmodelwithlivedata


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer

import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // demo without viewModel
        //var number = RandomNumberGenerator()
        //text_view.text = number.getNumber()


        // demo with viewModel
        //var myViewModel = ViewModelProviders.of(this).get(MyViewModel::class.java)
        //text_view.text = myViewModel.getNumber()


        // demo with viewModel and Livedata
        var myViewModel = ViewModelProviders.of(this).get(MyViewModel2::class.java)
        // create the instance of the live data
        var liveDataNumber = myViewModel.getNumber()

        liveDataNumber?.observe(this,
            Observer<String> {
                    t -> text_view.text = t })
        button_generate.setOnClickListener {
            myViewModel.createNumber()
        }


    }
}
