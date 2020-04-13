package com.example.comp_admin.networkcall1test

import android.net.nsd.NsdManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.comp_admin.networkcall1test.adapters.CategoryAdapter
import com.example.comp_admin.networkcall1test.models.Category
import com.example.comp_admin.networkcall1test.models.CategoryResponse
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main2.*
import android.widget.Toast
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*

class Main2Activity : AppCompatActivity() {

    var mList: ArrayList<Category> = ArrayList()
    lateinit var categoryAdapter: CategoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        init()
    }

    private fun init() {
        getData()


        my_rv.layoutManager = LinearLayoutManager(this)
        categoryAdapter = CategoryAdapter(this, mList)
        my_rv.adapter = categoryAdapter
    }

    private fun getData() {
        val url = "https://apolis-grocery.herokuapp.com/api/category"
        var requestQueue = Volley.newRequestQueue(this)

        var stringRequest = StringRequest(Request.Method.GET, url,
            Response.Listener {
                var gson = GsonBuilder().create()
                var categoryResponse = gson.fromJson(it.toString(), CategoryResponse::class.java)
                mList = categoryResponse.data
                Toast.makeText(this, mList.size.toString(), Toast.LENGTH_SHORT).show()
                //adapterCategory.notifyDataSetChanged()
                categoryAdapter.setData(mList)

            },
            Response.ErrorListener {
                Log.e("data", it.message)
            })

        requestQueue.add(stringRequest)

    }
}
