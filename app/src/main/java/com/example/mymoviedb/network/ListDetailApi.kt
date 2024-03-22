package com.example.mymoviedb.network

import com.example.mymoviedb.model.FilmModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ListDetailApi {
    @GET("discover/movie")
    suspend fun getMovieByGenre(
        @Query("page") page: Int, @Query("with_genres") withGenres: Int,
    ): Response<FilmModel>

    @GET("discover/tv")
    suspend fun getTVByGenre(
        @Query("page") page: Int, @Query("with_genres") withGenres: Int,
    ): Response<FilmModel>

    @GET("trending/all/day")
    suspend fun getTrendingAll(@Query("page") page: Int): Response<FilmModel>

    @GET("trending/movie/day")
    suspend fun getTrendingMovies(@Query("page") page: Int): Response<FilmModel>

    @GET("trending/tv/day")
    suspend fun getTrendingTVSeries(@Query("page") page: Int): Response<FilmModel>

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovie(@Query("page") page: Int): Response<FilmModel>

    @GET("movie/popular")
    suspend fun getPopularMovie(@Query("page") page: Int): Response<FilmModel>

    @GET("movie/top_rated")
    suspend fun getTopRatedMovie(@Query("page") page: Int): Response<FilmModel>

    @GET("movie/upcoming")
    suspend fun getUpcomingMovie(@Query("page") page: Int): Response<FilmModel>

    @GET("tv/airing_today")
    suspend fun getAiringTodayTVSeries(@Query("page") page: Int): Response<FilmModel>

    @GET("tv/on_the_air")
    suspend fun getOnTheAirTVSeries(@Query("page") page: Int): Response<FilmModel>

    @GET("tv/popular")
    suspend fun getPopularTVSeries(@Query("page") page: Int): Response<FilmModel>

    @GET("tv/top_rated")
    suspend fun getTopRatedTVSeries(@Query("page") page: Int): Response<FilmModel>
}
