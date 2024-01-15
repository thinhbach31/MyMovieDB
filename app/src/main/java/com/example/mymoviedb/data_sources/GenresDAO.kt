package com.example.mymoviedb.data_sources

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mymoviedb.model.ListGenreLocalModel

@Dao
interface GenresDAO {
    @Query("SELECT * FROM genres WHERE type LIKE :type")
    suspend fun getGenresByType(type: String): ListGenreLocalModel

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg genres: ListGenreLocalModel)

    @Query("DELETE FROM genres")
    suspend fun delete()
}
