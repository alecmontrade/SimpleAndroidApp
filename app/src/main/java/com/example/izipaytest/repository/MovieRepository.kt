package com.example.izipaytest.repository

import android.util.Log
import androidx.annotation.WorkerThread
import com.example.izipaytest.api.ApiInterface
import com.example.izipaytest.api.RetrofitClient
import com.example.izipaytest.data.LikedMovie
import com.example.izipaytest.data.Movie
import com.example.izipaytest.database.MoviesDAO
import kotlinx.coroutines.flow.Flow

class MovieRepository(private val moviesDAO: MoviesDAO) {

    private val retrofit = RetrofitClient.getInstance()
    private val userApi = retrofit.create(ApiInterface::class.java)

    val allLikedMovies: Flow<List<LikedMovie>> = moviesDAO.getAlphabetizedMovies()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun fetchMovie(name: String) : Movie? {
        try {
            val response = userApi.getMovie(name)
            return if(response.isSuccessful && response.body()?.Title != null) {
                response.body()
            } else {
                null
            }
        } catch (e: Exception) {
            Log.e("ViewModel:Fetch movie", e.toString())
        }
        return null
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(likedMovie: LikedMovie) {
        moviesDAO.insert(likedMovie)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun clear() {
        moviesDAO.deleteAll()
    }


}