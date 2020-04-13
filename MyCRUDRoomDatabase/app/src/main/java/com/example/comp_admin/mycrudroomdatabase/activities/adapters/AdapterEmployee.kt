package com.example.comp_admin.mycrudroomdatabase.activities.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.comp_admin.mycrudroomdatabase.R
import com.example.comp_admin.mycrudroomdatabase.activities.activities.UpdateActivity
import com.example.comp_admin.mycrudroomdatabase.activities.database.Employee
import com.example.comp_admin.mycrudroomdatabase.activities.database.MyDatabase
import kotlinx.android.synthetic.main.rv_employes.view.*

class AdapterEmployee(
    var mContext: Context,
    var mList: List<Employee>
) : RecyclerView.Adapter<AdapterEmployee.MyViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var view = LayoutInflater.from(mContext).inflate(R.layout.rv_employes, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onBindViewHolder(holder: AdapterEmployee.MyViewHolder, position: Int) {
        var employee = mList.get(position)
        holder.bind(employee,position)
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(employee: Employee, position: Int) {
            itemView.tv_name.text = employee.name

            itemView.button_edit.setOnClickListener {
                var intent = Intent(mContext, UpdateActivity::class.java)
                intent.putExtra(Employee.KEY, employee)
                mContext.startActivity(intent)
            }

            itemView.button_delete.setOnClickListener {
                var myDb = Room.databaseBuilder(mContext, MyDatabase::class.java, "empDB")
                    .allowMainThreadQueries()
                    .build()
                myDb.myDao().deleteEmployee(employee)
                notifyItemRemoved(position)
            }
        }

    }

}