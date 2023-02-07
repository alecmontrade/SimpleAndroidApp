package com.example.izipaytest.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.izipaytest.data.LikedMovie
import kotlinx.coroutines.flow.Flow

@Dao
interface MoviesDAO {
    @Query("SELECT * FROM movies_table ORDER BY word ASC")
    fun getAlphabetizedMovies(): Flow<List<LikedMovie>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(movie: LikedMovie)

    @Query("DELETE FROM movies_table")
    suspend fun deleteAll()

}