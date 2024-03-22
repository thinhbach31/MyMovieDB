package com.example.mymoviedb.view.home

import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mymoviedb.base.BaseFragment
import com.example.mymoviedb.databinding.FragmentHomeBinding
import com.example.mymoviedb.model.HomeFilter
import com.example.mymoviedb.utils.Const
import com.example.mymoviedb.view.movie.MovieFragment
import com.example.mymoviedb.view.trending.TrendingAllFragment
import com.example.mymoviedb.view.tv_series.TvSeriesFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment() : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate),
    HomeFilterClickListener {
    private lateinit var filterAdapter: HomeFilterAdapter
    private val viewModel: HomeViewModel by activityViewModels()

    override fun observeData() {

    }

    override fun requestData() {
        viewModel.getTrendingData(
            Const.TRENDING_ALL,
            Const.TRENDING_MOVIE,
            Const.TRENDING_TV
        )
        viewModel.getMovieData(
            Const.NOW_PLAYING_MOVIE,
            Const.POPULAR_MOVIE,
            Const.TOP_RATED_MOVIE,
            Const.UPCOMING_MOVIE,
        )
        viewModel.getTVData(
            Const.AIRING_TV,
            Const.POPULAR_TV,
            Const.ON_THE_AIR_TV,
            Const.TOP_RATED_TV
        )
    }

    override fun initUIComponents() {
        filterAdapter = HomeFilterAdapter(initFilters(), this)
        binding.recyclerFilterHome.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = filterAdapter
            setHasFixedSize(true)
//            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.HORIZONTAL))
        }

        replaceFragment(binding.containerHome.id, TrendingAllFragment())
    }

    override fun onFilterItemCLick(id: Int) {
        filterAdapter.updateFilterSelectedStatus(id)
        filterAdapter.notifyDataSetChanged()
        when (id) {
            0 -> {
                replaceFragment(binding.containerHome.id, TrendingAllFragment())
            }

            1 -> {
                replaceFragment(binding.containerHome.id, MovieFragment())
            }

            2 -> {
                replaceFragment(binding.containerHome.id, TvSeriesFragment())
            }
        }
    }

    private fun initFilters(): ArrayList<HomeFilter> = ArrayList<HomeFilter>().apply {
        add(HomeFilter(0, Const.HOME_FILTER_ALL, true))
        add(HomeFilter(1, Const.HOME_FILTER_MOVIE, false))
        add(HomeFilter(2, Const.HOME_FILTER_TV, false))
    }
}
