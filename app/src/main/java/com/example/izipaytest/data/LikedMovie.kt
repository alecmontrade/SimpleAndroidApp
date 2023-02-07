package com.example.izipaytest.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies_table")
data class LikedMovie(
    @ColumnInfo(name = "word")
    val name: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
