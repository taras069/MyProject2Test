package com.example.comp_admin.a27retrofitlogin.network

import com.example.comp_admin.a27retrofitlogin.models.LoginResponse
import com.example.comp_admin.a27retrofitlogin.models.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("login")
    fun postLogin(@Body user: User) : Call<LoginResponse>

    @POST("register")
    fun postRegistration(@Body newUser: User) : Call<User>
}