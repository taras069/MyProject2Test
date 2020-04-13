package com.example.comp_admin.listviewcustomadaptertest.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Toast
import com.example.comp_admin.listviewcustomadaptertest.R
import com.example.comp_admin.listviewcustomadaptertest.models.News
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.list_view_item.view.*

class MyCustomNewsAdapter(
    private val mContext: Context,
    private var mList: ArrayList<News>
) : BaseAdapter() {

    override fun getView(position: Int, p1: View?, p2: ViewGroup?): View {
        var view = LayoutInflater.from(mContext).inflate(R.layout.list_view_item,p2,false)
        var news = mList.get(position)

        view.my_tv_title.text = news.title
        view.my_tv_description.text = news.description
        view.image_view.setImageResource(news.imageRes)

        view.setOnClickListener{
            Toast.makeText(mContext, "Item " + news.title, Toast.LENGTH_SHORT).show()
        }


        return view
    }

    override fun getItem(p0: Int): Any {
        return mList.get(p0)
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }


    override fun getCount(): Int {
        return mList.size
    }

}