package com.example.comp_admin.recycleviewtest.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.comp_admin.recycleviewtest.R
import com.example.comp_admin.recycleviewtest.models.News
import kotlinx.android.synthetic.main.recycle_view_item.view.*

class MyRecycleViewAdapter(
    var mContext: Context,
    var mList: ArrayList<News>
) : RecyclerView.Adapter<MyRecycleViewAdapter.MyViewHolder>() {

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(news: News) {
            itemView.title.text = news.title
            itemView.description.text = news.description
            itemView.my_image_view.setImageResource(news.imageRes)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var view = LayoutInflater.from(mContext).inflate(R.layout.recycle_view_item,parent,false)
        var mViewHolder = MyViewHolder(view)
        return mViewHolder
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(mList.get(position))
    }
}
