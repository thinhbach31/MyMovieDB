package com.example.mymoviedb.repository

import com.example.mymoviedb.network.MovieDBApi

class HomeRepository(private val movieDBApi: MovieDBApi){

    fun getTrendingAll() = movieDBApi.getTrendingAll()
    fun getTrendingMovies() = movieDBApi.getTrendingMovies()
    fun getTrendingTVSeries() = movieDBApi.getTrendingTVSeries()
}
