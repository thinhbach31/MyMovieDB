package com.example.mymoviedb.model

import com.google.gson.annotations.SerializedName

data class GenreRemoteModel(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
)

data class ListGenreRemoteModel(
    @SerializedName("genres")
    val genres: ArrayList<GenreRemoteModel>?
)
