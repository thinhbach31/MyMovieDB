package com.example.mymoviedb.data_sources

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.mymoviedb.model.ListGenreLocalModel
import com.example.mymoviedb.utils.ObjectConverter

@TypeConverters(ObjectConverter::class)
@Database(entities = [ListGenreLocalModel::class], version = 1, exportSchema = false)
abstract class GenreDatabase : RoomDatabase() {
    abstract fun genresDao(): GenresDAO
}
