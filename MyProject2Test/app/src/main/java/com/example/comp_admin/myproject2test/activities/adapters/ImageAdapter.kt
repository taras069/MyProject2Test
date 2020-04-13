package com.example.comp_admin.myproject2test.activities.adapters

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.comp_admin.myproject2test.R
import com.example.comp_admin.myproject2test.activities.models.Image
import kotlinx.android.synthetic.main.rv_images.view.*

class ImageAdapter(
    private var mContext: Context
) : RecyclerView.Adapter<ImageAdapter.MyViewHolder>() {

    private var mList: ArrayList<Image> = ArrayList();
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var view = LayoutInflater.from(mContext).inflate(R.layout.rv_images, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(mList.get(position))
    }

    fun setData(list: ArrayList<Image>) {
        mList = list
        notifyDataSetChanged()
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(image: Image) {
            itemView.my_cart_image.setImageURI(Uri.parse(image.path))

        }
    }
}

