package com.example.mymoviedb.utils

import android.view.View

object Functions {

    fun View?.show() {
        if (this == null) return
        this.visibility = View.VISIBLE
    }

    fun View?.hide() {
        if (this == null) return
        this.visibility = View.GONE
    }
}