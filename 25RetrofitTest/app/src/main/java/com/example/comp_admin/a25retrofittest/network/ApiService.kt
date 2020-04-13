package com.example.comp_admin.a25retrofittest.network

import com.example.comp_admin.a25retrofittest.models.Post
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("posts")
    fun getPost(): Call<List<Post>>
}