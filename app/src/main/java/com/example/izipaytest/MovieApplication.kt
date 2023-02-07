package com.example.izipaytest

import android.app.Application
import com.example.izipaytest.database.LikedMovieRoomDatabase
import com.example.izipaytest.repository.MovieRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class MovieApplication: Application() {

    val applicationScope = CoroutineScope(SupervisorJob())

    val database by lazy { LikedMovieRoomDatabase.getDatabase(this, applicationScope) }
    val repository by lazy { MovieRepository(database.movieDao()) }
}