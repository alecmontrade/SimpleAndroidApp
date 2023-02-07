package com.example.izipaytest.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.izipaytest.data.LikedMovie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = arrayOf(LikedMovie::class), version = 1, exportSchema = false)
abstract class LikedMovieRoomDatabase : RoomDatabase() {
    abstract fun movieDao(): MoviesDAO

    private class LikedMovieDatabaseCallback(
        private val scope: CoroutineScope
    ) : Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    val movieDao = database.movieDao()
                    movieDao.deleteAll()
                }
            }
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: LikedMovieRoomDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): LikedMovieRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    LikedMovieRoomDatabase::class.java,
                    "movie_database"
                ).addCallback(LikedMovieDatabaseCallback(scope)).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}
