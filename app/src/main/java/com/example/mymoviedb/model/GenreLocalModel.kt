package com.example.mymoviedb.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "genre")
data class GenreLocalModel(
    @PrimaryKey(false)
    val id: Int,
    @ColumnInfo("name")
    val name: String,
)

@Entity(tableName = "genres")
data class ListGenreLocalModel(
    @PrimaryKey(false)
    val type: String,
    @ColumnInfo("genre_list")
    val genres: ArrayList<GenreLocalModel>?,
)
