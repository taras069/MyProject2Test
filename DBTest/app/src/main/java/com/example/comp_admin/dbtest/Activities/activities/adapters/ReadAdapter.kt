package com.example.comp_admin.dbtest.Activities.activities.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.comp_admin.dbtest.Activities.activities.models.Employee
import com.example.comp_admin.dbtest.R
import kotlinx.android.synthetic.main.rv_item.view.*

class ReadAdapter  (
    var mContext: Context,
    var mList: ArrayList<Employee>
) : RecyclerView.Adapter<ReadAdapter.MyViewHolder>() {
    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(employee: Employee) {
            itemView.tv_id_read.text = employee.id.toString()
            itemView.tv_name_read.text = employee.name
            itemView.tv_email_read.text = employee.email
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var view = LayoutInflater.from(mContext).inflate(R.layout.rv_item,parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        return holder.bind(mList.get(position))
    }
}


