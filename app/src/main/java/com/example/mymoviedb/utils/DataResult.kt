package com.example.mymoviedb.utils

data class DataResult<out T>(val status: Status, val data: T?, val message: String?) {
    enum class Status {
        SUCCESS,
        ERROR
    }
}
