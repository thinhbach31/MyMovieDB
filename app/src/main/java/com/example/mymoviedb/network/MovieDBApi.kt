package com.example.mymoviedb.network

import com.example.mymoviedb.model.FilmModel
import io.reactivex.Observable
import retrofit2.http.GET

interface MovieDBApi {

    @GET("trending/all/day")
    fun getTrendingAll(): Observable<FilmModel>

    @GET("trending/movie/day")
    fun getTrendingMovies(): Observable<FilmModel>

    @GET("trending/tv/day")
    fun getTrendingTVSeries(): Observable<FilmModel>

    @GET("movie/now_playing")
    fun getNowPlayingMovie(): Observable<FilmModel>

    @GET("movie/popular")
    fun getPopularMovie(): Observable<FilmModel>

    @GET("movie/top_rated")
    fun getTopRatedMovie(): Observable<FilmModel>

    @GET("movie/upcoming")
    fun getUpcomingMovie(): Observable<FilmModel>

    @GET("tv/airing_today")
    fun getAiringTodayTVSeries(): Observable<FilmModel>

    @GET("tv/on_the_air")
    fun getOnTheAirTVSeries(): Observable<FilmModel>

    @GET("tv/popular")
    fun getPopularTVSeries(): Observable<FilmModel>

    @GET("tv/top_rated")
    fun getTopRatedTVSeries(): Observable<FilmModel>
}
