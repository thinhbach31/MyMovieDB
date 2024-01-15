package com.example.mymoviedb.repository

import android.util.Log
import com.example.mymoviedb.data_sources.GenresDAO
import com.example.mymoviedb.model.ListGenreLocalModel
import com.example.mymoviedb.network.GenreApi
import com.example.mymoviedb.utils.Const
import com.example.mymoviedb.utils.DataResult
import com.example.mymoviedb.utils.ListGenreRemoteMapper
import javax.inject.Inject

class ExploreRepository @Inject constructor(
    private val api: GenreApi,
    private val localDataSource: GenresDAO,
    private val mapper: ListGenreRemoteMapper,
) {

    private val TAG = "Status genre repo"
    suspend fun getMovieGenres(): DataResult<ListGenreLocalModel> {
        return try {
            val result = api.getMovieGenres()
            val localResult = result.body()?.let { mapper.to(it, Const.MOVIE_GENRE) }
            saveDataLocal(localResult)
            Log.d(TAG, "movie + ${result.body().toString()}")
            DataResult(DataResult.Status.SUCCESS, localResult, result.message())
        } catch (e: Exception) {
            val localResult = localDataSource.getGenresByType(Const.MOVIE_GENRE)
            Log.d(TAG, "movie error $localResult")
            DataResult(DataResult.Status.ERROR, localResult, e.message)
        }
    }

    suspend fun getTVGenres(): DataResult<ListGenreLocalModel> {
        return try {
            val result = api.getTVGenres()
            val localResult = result.body()?.let { mapper.to(it, Const.TV_GENRE) }
            saveDataLocal(localResult)
            Log.d(TAG, "tv + ${result.body().toString()}")
            DataResult(DataResult.Status.SUCCESS, localResult, result.message())
        } catch (e: Exception) {
            val localResult = localDataSource.getGenresByType(Const.TV_GENRE)
            Log.d(TAG, "tv error $localResult")
            DataResult(DataResult.Status.ERROR, localResult, e.message)
        }
    }

    private suspend fun saveDataLocal(listGenreLocalModel: ListGenreLocalModel?) {
        try {
            listGenreLocalModel?.let { localDataSource.insertAll(it) }
            Log.d(TAG, "${listGenreLocalModel?.type} saved")
        } catch (e: Exception) {
            Log.d(TAG, "${listGenreLocalModel?.type} error ${e.message}")
        }
    }

    suspend fun deleteDataLocal() = localDataSource.delete()
}
