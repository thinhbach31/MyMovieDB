package com.example.mymoviedb.utils

import android.content.Context
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.example.mymoviedb.R
import com.example.mymoviedb.view.custom.HorizontalMarginItemDecoration
import com.example.mymoviedb.view.trending.TrendingHighlightAdapter

object Functions {

    fun View?.show() {
        if (this == null) return
        this.visibility = View.VISIBLE
    }

    fun View?.hide() {
        if (this == null) return
        this.visibility = View.GONE
    }

    fun ViewPager2.setupCarousel(context: Context, adapter: TrendingHighlightAdapter) {

        this.adapter = adapter
        offscreenPageLimit = 1

        val nextItemVisiblePx = resources.getDimension(R.dimen.dp_30)
        val currentItemHorizontalMarginPx = resources.getDimension(R.dimen.dp_30)
        val pageTranslationX = nextItemVisiblePx + currentItemHorizontalMarginPx
        val pageTransformer = ViewPager2.PageTransformer { page: View, position: Float ->
            page.translationX = -pageTranslationX * position
//            // Next line scales the item's height. You can remove it if you don't want this effect
            page.scaleY = 1 - (0.25f * kotlin.math.abs(position))
//            // If you want a fading effect uncomment the next line:
            page.alpha = 0.25f + (1 - kotlin.math.abs(position))
        }
        setPageTransformer(pageTransformer)
//
//// The ItemDecoration gives the current (centered) item horizontal margin so that
//// it doesn't occupy the whole screen width. Without it the items overlap
        val itemDecoration = HorizontalMarginItemDecoration(
            context,
            R.dimen.dp_30
        )
        addItemDecoration(itemDecoration)
    }

    fun <E> ArrayList<E>?.orEmpty(): ArrayList<E> {
        if (this == null) return arrayListOf()
        return this
    }

    fun generateTotalPageSize(total: Int): ArrayList<Int> {
        return ArrayList<Int>().apply {
            while (size < total) {
                add(size + 1)
            }
        }
    }

    fun String.getNameByTitle(context: Context): String {
        return when (this) {
            Const.TRENDING_ALL -> context.resources.getString(R.string.title_trending_all)
            Const.TRENDING_MOVIE -> context.resources.getString(R.string.title_trending_movie)
            Const.TRENDING_TV -> context.resources.getString(R.string.title_trending_tv_series)

            Const.AIRING_TV -> context.resources.getString(R.string.title_tv_airing)
            Const.TOP_RATED_TV -> context.resources.getString(R.string.title_tv_top_rated)
            Const.POPULAR_TV -> context.resources.getString(R.string.title_tv_popular)
            Const.ON_THE_AIR_TV -> context.resources.getString(R.string.title_tv_on_the_air)

            Const.POPULAR_MOVIE -> context.resources.getString(R.string.title_movie_popular)
            Const.NOW_PLAYING_MOVIE -> context.resources.getString(R.string.title_movie_now_playing)
            Const.TOP_RATED_MOVIE -> context.resources.getString(R.string.title_movie_top_rated)
            Const.UPCOMING_MOVIE -> context.resources.getString(R.string.title_movie_upcoming)
            else -> ""
        }
    }
}