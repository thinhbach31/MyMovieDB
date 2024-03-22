package com.example.mymoviedb.repository

import android.util.Log
import com.example.mymoviedb.data_sources.GenresDAO
import com.example.mymoviedb.model.ListGenreLocalModel
import com.example.mymoviedb.network.GenreApi
import com.example.mymoviedb.utils.Const
import com.example.mymoviedb.utils.DataResult
import com.example.mymoviedb.utils.ListGenreRemoteMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ExploreRepository @Inject constructor(
    private val api: GenreApi,
    private val localDataSource: GenresDAO,
    private val mapper: ListGenreRemoteMapper,
) {

    private val TAG = "Status genre repo"
    suspend fun getMovieGenres(): DataResult<ListGenreLocalModel> {
        return withContext(Dispatchers.IO) {
            try {
                val result = api.getMovieGenres()
                val localResult = result.body()?.let { mapper.to(it, Const.MOVIE_GENRE) }
                if (localResult != null) {
                    saveDataLocal(localResult)
                    Log.d(TAG, "movie + ${result.body().toString()}")
                    DataResult(DataResult.Status.SUCCESS, localResult, result.message())
                } else {
                    getDataLocal(Const.MOVIE_GENRE)
                }
            } catch (e: Exception) {
                getDataLocal(Const.MOVIE_GENRE).let {
                    if (it.data == null) {
                        DataResult(DataResult.Status.ERROR, null, e.message)
                    } else {
                        it
                    }
                }
            }
        }
    }

    suspend fun getTVGenres(): DataResult<ListGenreLocalModel> {
        return withContext(Dispatchers.IO) {
            try {
                val result = api.getTVGenres()
                val localResult = result.body()?.let { mapper.to(it, Const.TV_GENRE) }
                if (localResult != null) {
                    saveDataLocal(localResult)
                    Log.d(TAG, "tv + ${result.body().toString()}")
                    DataResult(DataResult.Status.SUCCESS, localResult, result.message())
                } else {
                    getDataLocal(Const.TV_GENRE)
                }
            } catch (e: Exception) {
                getDataLocal(Const.TV_GENRE).let {
                    if (it.data == null) {
                        DataResult(DataResult.Status.ERROR, null, e.message)
                    } else {
                        it
                    }
                }
            }
        }
    }

    private suspend fun saveDataLocal(listGenreLocalModel: ListGenreLocalModel?) {
        withContext(Dispatchers.IO) {
            try {
                listGenreLocalModel?.let { localDataSource.insertAll(it) }
            } catch (e: Exception) {
                Log.d(TAG, "${listGenreLocalModel?.type} error ${e.message}")
            }
        }
    }

    private suspend fun getDataLocal(genre: String): DataResult<ListGenreLocalModel> {
        return withContext(Dispatchers.IO) {
            DataResult(
                DataResult.Status.SUCCESS,
                localDataSource.getGenresByType(genre),
                "api error, fetch from local"
            )
        }
    }

    suspend fun deleteDataLocal() = localDataSource.delete()
}
