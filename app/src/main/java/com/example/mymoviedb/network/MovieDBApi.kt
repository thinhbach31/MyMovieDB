package com.example.mymoviedb.network

import com.example.mymoviedb.model.FilmModel
import io.reactivex.Observable
import retrofit2.http.GET

interface MovieDBApi {

    @GET("trending/all/day")
    fun getTrendingAll(): Observable<FilmModel>
}
