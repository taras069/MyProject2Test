package com.example.comp_admin.a25retrofittest.network

import com.example.comp_admin.a25retrofittest.app.Config
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {

    companion object{

        fun getInstance(): Retrofit {
            var retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Config.BASE_URL)
                .build()

            return retrofit
        }

    }


}