package com.example.mymoviedb.utils

import androidx.room.TypeConverter
import com.example.mymoviedb.model.GenreLocalModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ObjectConverter {
    @TypeConverter
    fun fromString(value: String): ArrayList<GenreLocalModel> {
        val listType = object : TypeToken<ArrayList<GenreLocalModel>>() {}.type
        return try {
            Gson().fromJson(value, listType)
        } catch (e: Exception) {
            arrayListOf()
        }
    }

    @TypeConverter
    fun fromArrayList(list: ArrayList<GenreLocalModel>): String = Gson().toJson(list)
}
