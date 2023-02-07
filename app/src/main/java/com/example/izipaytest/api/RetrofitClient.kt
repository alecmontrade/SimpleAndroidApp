package com.example.izipaytest.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val API_KEY = "2210bda7"
    private const val BASE_URL: String = "https://www.omdbapi.com/?apikey=$API_KEY&"

    fun getInstance(): Retrofit {

        val mOkHttpClient = OkHttpClient
            .Builder()
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(mOkHttpClient)
            .build()
    }
}