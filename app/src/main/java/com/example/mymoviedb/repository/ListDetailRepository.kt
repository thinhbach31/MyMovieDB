package com.example.mymoviedb.repository

import com.example.mymoviedb.model.FilmModel
import com.example.mymoviedb.network.ListDetailApi
import com.example.mymoviedb.utils.Const
import com.example.mymoviedb.utils.DataResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ListDetailRepository @Inject constructor(
    private val api: ListDetailApi,
) {
    suspend fun getMoviesByGenre(isMovie: Boolean, page: Int, id: Int): DataResult<FilmModel> {
        return withContext(Dispatchers.IO) {
            try {
                val result: FilmModel? = if (isMovie) {
                    api.getMovieByGenre(page, id).body()
                } else {
                    api.getTVByGenre(page, id).body()
                }
                DataResult(DataResult.Status.SUCCESS, result, "")

            } catch (e: Exception) {
                DataResult(DataResult.Status.SUCCESS, null, "")
            }
        }
    }

    suspend fun getMoviesByTitle(title: String, page: Int): DataResult<FilmModel> {
        return withContext(Dispatchers.IO) {
            try {
                val result = when (title) {
                    Const.TRENDING_ALL -> api.getTrendingAll(page).body()
                    Const.TRENDING_MOVIE -> api.getTrendingMovies(page).body()
                    Const.TRENDING_TV -> api.getTrendingTVSeries(page).body()

                    Const.AIRING_TV -> api.getAiringTodayTVSeries(page).body()
                    Const.TOP_RATED_TV -> api.getTopRatedTVSeries(page).body()
                    Const.POPULAR_TV -> api.getPopularTVSeries(page).body()
                    Const.ON_THE_AIR_TV -> api.getOnTheAirTVSeries(page).body()

                    Const.POPULAR_MOVIE -> api.getPopularMovie(page).body()
                    Const.NOW_PLAYING_MOVIE -> api.getNowPlayingMovie(page).body()
                    Const.TOP_RATED_MOVIE -> api.getTopRatedMovie(page).body()
                    Const.UPCOMING_MOVIE -> api.getUpcomingMovie(page).body()
                    else -> null
                }
                DataResult(DataResult.Status.SUCCESS, result, "")

            } catch (e: Exception) {
                DataResult(DataResult.Status.ERROR, null, "")
            }
        }
    }
}