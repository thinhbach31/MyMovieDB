package com.example.mymoviedb.view.custom

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration


class GenreItemDecoration(private var space: Int) : ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State,
    ) {
        outRect.left = space
        outRect.right = space
        outRect.bottom = space
        outRect.top = 0
    }
}
