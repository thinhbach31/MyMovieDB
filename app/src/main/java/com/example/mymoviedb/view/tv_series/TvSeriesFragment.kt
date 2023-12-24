package com.example.mymoviedb.view.tv_series

import android.util.Log
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mymoviedb.base.BaseFragment
import com.example.mymoviedb.databinding.FragmentTvSeriesBinding
import com.example.mymoviedb.model.ListFilmWithTitle
import com.example.mymoviedb.model.Result
import com.example.mymoviedb.view.home.HomeViewModel
import com.example.mymoviedb.view.home.ListFilmWithTitleAdapter
import com.example.mymoviedb.view.home.OnListFilmWithTitleClickListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TvSeriesFragment: BaseFragment<FragmentTvSeriesBinding>(FragmentTvSeriesBinding::inflate) {
    private val viewModel: HomeViewModel by activityViewModels()
    private lateinit var tvAdapter: ListFilmWithTitleAdapter

    override fun observeData() {
        viewModel.apply {
            airingTodayTVSeriesLiveData.observe(this@TvSeriesFragment) { movieList ->
                tvAdapter.addItem(ListFilmWithTitle(movieList.title, movieList.results))
                tvAdapter.notifyDataSetChanged()
            }

            onTheAirTVSeriesLiveData.observe(this@TvSeriesFragment) { movieList ->
                tvAdapter.addItem(ListFilmWithTitle(movieList.title, movieList.results))
                tvAdapter.notifyDataSetChanged()
            }

            popularTVSeriesLiveData.observe(this@TvSeriesFragment) { movieList ->
                tvAdapter.addItem(ListFilmWithTitle(movieList.title, movieList.results))
                tvAdapter.notifyDataSetChanged()
            }

            topRatedTVSeriesLiveData.observe(this@TvSeriesFragment) { movieList ->
                tvAdapter.addItem(ListFilmWithTitle(movieList.title, movieList.results))
                tvAdapter.notifyDataSetChanged()
            }
        }
    }

    override fun requestData() {}

    override fun initUIComponents() {

        tvAdapter =
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
        binding.recyclerTv.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = tvAdapter
            setHasFixedSize(true)
        }
    }
}
