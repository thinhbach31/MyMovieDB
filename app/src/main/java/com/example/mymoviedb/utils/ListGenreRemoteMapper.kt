package com.example.mymoviedb.utils

import com.example.mymoviedb.base.Mapper
import com.example.mymoviedb.model.GenreLocalModel
import com.example.mymoviedb.model.GenreRemoteModel
import com.example.mymoviedb.model.ListGenreLocalModel
import com.example.mymoviedb.model.ListGenreRemoteModel

class ListGenreRemoteMapper : Mapper<ListGenreLocalModel, ListGenreRemoteModel> {
    override fun from(i: ListGenreLocalModel, type: String?): ListGenreRemoteModel {
        val list = arrayListOf<GenreRemoteModel>()
        i.genres?.forEach {
            list.add(GenreRemoteModel(it.id, it.name))
        }
        return ListGenreRemoteModel(list)
    }

    override fun to(o: ListGenreRemoteModel, type: String?): ListGenreLocalModel {
        val list = arrayListOf<GenreLocalModel>()
        o.genres?.forEach {
            list.add(GenreLocalModel(it.id, it.name))
        }
        return ListGenreLocalModel(type.orEmpty(), list)
    }
}
