package com.example.mymoviedb.base

interface Mapper<I, O> {
    fun from(i: I, type: String?): O

    fun to(o: O, type: String?): I
}
