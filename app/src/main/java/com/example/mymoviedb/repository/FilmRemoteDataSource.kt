package com.example.mymoviedb.repository

import com.example.mymoviedb.network.MovieDBApi
import javax.inject.Inject

class FilmRemoteDataSource @Inject constructor(private val movieDBApi: MovieDBApi) {

    fun getTrendingAll() = movieDBApi.getTrendingAll()
    fun getTrendingMovies() = movieDBApi.getTrendingMovies()
    fun getTrendingTVSeries() = movieDBApi.getTrendingTVSeries()

    fun getNowPlayingMovie() = movieDBApi.getNowPlayingMovie()
    fun getPopularMovie() = movieDBApi.getPopularMovie()
    fun getTopRatedMovie() = movieDBApi.getTopRatedMovie()
    fun getUpcomingMovie() = movieDBApi.getUpcomingMovie()

    fun getAiringTodayTVSeries() = movieDBApi.getAiringTodayTVSeries()
    fun getOnTheAirTVSeries() = movieDBApi.getOnTheAirTVSeries()
    fun getPopularTVSeries() = movieDBApi.getPopularTVSeries()
    fun getTopRatedTVSeries() = movieDBApi.getTopRatedTVSeries()
}
