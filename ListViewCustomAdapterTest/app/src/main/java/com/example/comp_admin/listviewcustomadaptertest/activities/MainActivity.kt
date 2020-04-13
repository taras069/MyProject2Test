package com.example.comp_admin.listviewcustomadaptertest.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.comp_admin.listviewcustomadaptertest.R
import com.example.comp_admin.listviewcustomadaptertest.adapters.MyCustomNewsAdapter
import com.example.comp_admin.listviewcustomadaptertest.models.News
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
    }

    private fun init() {
        var newsList = ArrayList<News>()
        newsList.add(News("title 1", "description 1", R.drawable.ic_all_out_black_24dp))
        newsList.add(News("title 2", "description 2",R.drawable.ic_android_black_24dp))
        newsList.add(News("title 3", "description 3",R.drawable.ic_av_timer_black_24dp))
        newsList.add(News("title 4", "description 4",R.drawable.ic_insert_emoticon_black_24dp))

        var adapterNews = MyCustomNewsAdapter(this,newsList)
        my_list_view.adapter = adapterNews

    }
}
