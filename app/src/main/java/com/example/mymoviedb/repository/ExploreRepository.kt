package com.example.mymoviedb.repository

import com.example.mymoviedb.network.GenreApi
import javax.inject.Inject

class ExploreRepository @Inject constructor(private val api: GenreApi) {

    fun getMovieGenres() = api.getMovieGenres()
    fun getTVGenres() = api.getTVGenres()
}
