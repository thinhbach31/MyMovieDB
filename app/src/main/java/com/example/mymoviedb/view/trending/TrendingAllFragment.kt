package com.example.mymoviedb.view.trending

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.example.mymoviedb.base.BaseFragment
import com.example.mymoviedb.databinding.FragmentTrendingBinding
import com.example.mymoviedb.model.Result
import com.example.mymoviedb.view.home.BasicFilmAdapter
import com.example.mymoviedb.view.home.HomeViewModel
import com.example.mymoviedb.view.home.OnBasicItemListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TrendingAllFragment :
    BaseFragment<FragmentTrendingBinding>(FragmentTrendingBinding::inflate) {
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var highlightAdapter: TrendingHighlightAdapter
    private lateinit var trendingMovieAdapter: BasicFilmAdapter
    private lateinit var trendingTVAdapter: BasicFilmAdapter

    override fun observeData() {
        viewModel.apply {
            trendingAllLiveData.observe(this@TrendingAllFragment) { movieList ->
                highlightAdapter.updateData(movieList)
                highlightAdapter.notifyDataSetChanged()
            }

            trendingMoviesLiveData.observe(this@TrendingAllFragment) { movieList ->
                trendingMovieAdapter.updateData(movieList)
                trendingMovieAdapter.notifyDataSetChanged()
            }

            trendingTVSeriesLiveData.observe(this@TrendingAllFragment) { movieList ->
                trendingTVAdapter.updateData(movieList)
                trendingTVAdapter.notifyDataSetChanged()
            }
        }
    }

    override fun requestData() {
        viewModel.apply {
            getTrendingAll()
            getTrendingMovies()
            getTrendingTVSeries()
        }
    }

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
        binding.recyclerTrendingHighlight.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = highlightAdapter
            setHasFixedSize(true)
            PagerSnapHelper().attachToRecyclerView(this)
        }

        trendingMovieAdapter = BasicFilmAdapter(ArrayList(), object : OnBasicItemListener {
            override fun onBasicItemClick(result: Result) {
                Log.d(
                    "detail",
                    "go to detail of " + result.mediaType + " named: " + result.title
                )
            }
        })
        binding.recyclerTrendingMovie.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = trendingMovieAdapter
            setHasFixedSize(true)
        }

        trendingTVAdapter = BasicFilmAdapter(ArrayList(), object : OnBasicItemListener {
            override fun onBasicItemClick(result: Result) {
                Log.d(
                    "detail",
                    "go to detail of " + result.mediaType + " named: " + result.title
                )
            }
        })
        binding.recyclerTrendingTv.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = trendingTVAdapter
            setHasFixedSize(true)
        }
    }
}
