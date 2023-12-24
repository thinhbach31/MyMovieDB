package com.example.mymoviedb.repository

import android.content.SharedPreferences
import com.example.mymoviedb.model.ListFilmWithTitle
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import javax.inject.Inject

class FilmRepository @Inject constructor(
    private val remoteDataSource: FilmRemoteDataSource,
    private val homePreferences: SharedPreferences,
) {

    fun getTrendingAll() = remoteDataSource.getTrendingAll()
    fun getTrendingMovies() = remoteDataSource.getTrendingMovies()
    fun getTrendingTVSeries() = remoteDataSource.getTrendingTVSeries()

    fun getNowPlayingMovie() = remoteDataSource.getNowPlayingMovie()
    fun getPopularMovie() = remoteDataSource.getPopularMovie()
    fun getTopRatedMovie() = remoteDataSource.getTopRatedMovie()
    fun getUpcomingMovie() = remoteDataSource.getUpcomingMovie()

    fun getAiringTodayTVSeries() = remoteDataSource.getAiringTodayTVSeries()

    fun getOnTheAirTVSeries() = remoteDataSource.getOnTheAirTVSeries()

    fun getPopularTVSeries() = remoteDataSource.getPopularTVSeries()

    fun getTopRatedTVSeries() = remoteDataSource.getTopRatedTVSeries()

    fun getLocalListFilmWithTitleData(title: String): ListFilmWithTitle? =
        GsonBuilder().create().fromJson(
            homePreferences.getString(title, null), ListFilmWithTitle::class.java
        )

    fun saveLocalListFilmWithTitleData(title: String, listFilm: ListFilmWithTitle) {
        homePreferences.edit().putString(title, Gson().toJson(listFilm)).apply()
    }

    fun clearPref() {
        homePreferences.edit().clear().apply()
    }
}
