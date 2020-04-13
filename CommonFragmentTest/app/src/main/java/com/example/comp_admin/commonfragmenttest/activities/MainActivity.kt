package com.example.comp_admin.commonfragmenttest.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.comp_admin.commonfragmenttest.R
import com.example.comp_admin.commonfragmenttest.adapters.MyFragmentAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
    }

    private fun init() {
        var myAdapterFragment = MyFragmentAdapter(supportFragmentManager)
        myAdapterFragment.addFragment("Laptop")
        myAdapterFragment.addFragment("Desktop")
        myAdapterFragment.addFragment("Mobile")
        myAdapterFragment.addFragment("Table")
        myAdapterFragment.addFragment("Chair")

        my_view_pager.adapter = myAdapterFragment
        my_tab_layout.setupWithViewPager(my_view_pager)
    }
}
