package com.example.comp_admin.a25retrofittest.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.comp_admin.a25retrofittest.R
import com.example.comp_admin.a25retrofittest.models.Post
import kotlinx.android.synthetic.main.rv_items.view.*

class AdapterPost(val mContext: Context) : RecyclerView.Adapter<AdapterPost.ViewHolder>() {

    var mList: List<Post> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.rv_items, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var post = mList[position]
        holder.bindView(post, position)
    }

    fun setData(list: List<Post>){
        mList = list
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindView(post: Post, position: Int){
            itemView.text_view_title.text = post.title
            itemView.text_view_description.text = post.body
        }
    }
}