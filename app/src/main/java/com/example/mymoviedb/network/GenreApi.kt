package com.example.mymoviedb.network

import com.example.mymoviedb.model.ListGenreRemoteModel
import io.reactivex.Observable
import retrofit2.http.GET

interface GenreApi {

    @GET("genre/movie/list")
    fun getMovieGenres(): Observable<ListGenreRemoteModel>

    @GET("genre/tv/list")
    fun getTVGenres(): Observable<ListGenreRemoteModel>
}
