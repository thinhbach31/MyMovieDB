package com.example.mymoviedb.view.trending

import android.util.Log
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mymoviedb.base.BaseFragment
import com.example.mymoviedb.databinding.FragmentTrendingBinding
import com.example.mymoviedb.model.ListFilmWithTitle
import com.example.mymoviedb.model.Result
import com.example.mymoviedb.utils.Functions.setupCarousel
import com.example.mymoviedb.view.home.HomeViewModel
import com.example.mymoviedb.view.home.ListFilmWithTitleAdapter
import com.example.mymoviedb.view.home.OnListFilmWithTitleClickListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TrendingAllFragment :
    BaseFragment<FragmentTrendingBinding>(FragmentTrendingBinding::inflate) {
    private val viewModel: HomeViewModel by activityViewModels()
    private lateinit var highlightAdapter: TrendingHighlightAdapter
    private lateinit var trendingAdapter: ListFilmWithTitleAdapter

    override fun observeData() {
        viewModel.apply {
            trendingAllLiveData.observe(this@TrendingAllFragment) { movieList ->
                highlightAdapter.updateData(movieList.results)
                highlightAdapter.notifyDataSetChanged()
            }

            trendingMoviesLiveData.observe(this@TrendingAllFragment) { movieList ->
                trendingAdapter.addItem(ListFilmWithTitle(movieList.title, movieList.results))
                trendingAdapter.notifyDataSetChanged()
            }

            trendingTVSeriesLiveData.observe(this@TrendingAllFragment) { movieList ->
                trendingAdapter.addItem(ListFilmWithTitle(movieList.title, movieList.results))
                trendingAdapter.notifyDataSetChanged()
            }
        }
    }

    override fun requestData() {}

    override fun initUIComponents() {
        highlightAdapter =
            TrendingHighlightAdapter(ArrayList(), object : OnTrendingHighlightClickListener {
                override fun onItemHighlightClick(result: Result) {
                    val a = result.title ?: result.name
                    Log.d(
                        "detail",
                        "go to detail of " + result.mediaType + " named: " + a
                    )
                }
            })

        context?.let { binding.carouselTrending.setupCarousel(it, highlightAdapter) }

        trendingAdapter =
            ListFilmWithTitleAdapter(ArrayList(), object : OnListFilmWithTitleClickListener {
                override fun onTitleClickListener(title: String) {
                    Log.d("detail", "go to list $title")
                }

                override fun onFilmItemClickListener(result: Result) {
                    Log.d(
                        "detail",
                        "go to detail of " + result.mediaType + " named: " + result.title
                    )
                }
            })
        binding.recyclerTrending.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = trendingAdapter
            setHasFixedSize(true)
            isNestedScrollingEnabled = false
        }
    }
}
