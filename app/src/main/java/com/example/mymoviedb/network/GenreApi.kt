package com.example.mymoviedb.network

import com.example.mymoviedb.model.ListGenreRemoteModel
import retrofit2.Response
import retrofit2.http.GET

interface GenreApi {

    @GET("genre/movie/list")
    suspend fun getMovieGenres(): Response<ListGenreRemoteModel>

    @GET("genre/tv/list")
    suspend fun getTVGenres(): Response<ListGenreRemoteModel>
}
