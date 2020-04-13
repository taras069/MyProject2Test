package com.example.comp_admin.a25retrofittest.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.comp_admin.a25retrofittest.R
import com.example.comp_admin.a25retrofittest.adapters.AdapterPost
import com.example.comp_admin.a25retrofittest.models.Post
import com.example.comp_admin.a25retrofittest.network.ApiClient
import com.example.comp_admin.a25retrofittest.network.ApiService
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var adapterPost: AdapterPost
    var mList: List<Post> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inti()
        getData()
    }

    private fun inti() {
        recycler_view.layoutManager = LinearLayoutManager(this)
        adapterPost = AdapterPost(this)
        recycler_view.adapter = adapterPost
    }

    private fun getData() {
        // var apiClient = ApiClient.create()
        var apiService = ApiClient.getInstance().create(ApiService::class.java)

        var call  = apiService.getPost()
        call.enqueue(object: Callback<List<Post>> {
            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                mList = response.body()!!
                adapterPost.setData(mList)
                progress_bar.visibility = View.GONE

            }

        })
    }
}
