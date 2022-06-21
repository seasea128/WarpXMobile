package com.example.warpxmobile.api

import com.example.warpxmobile.utils.Settings
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val retrofit by lazy {
        Retrofit.Builder().baseUrl(Settings.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    val api: WarpXAPI by lazy {
        retrofit.create(WarpXAPI::class.java)
    }
}