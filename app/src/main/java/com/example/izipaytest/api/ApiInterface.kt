package com.example.izipaytest.api

import com.example.izipaytest.data.Movie
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiInterface {


    @GET("?apikey=2210bda7&i=tt3896198")
    suspend fun getAllMovies(): Response<Movie>

    @GET("?apikey=2210bda7&")
    suspend fun getMovie(@Query("t") movie: String): Response<Movie>
}