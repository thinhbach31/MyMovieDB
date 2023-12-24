package com.example.mymoviedb.view.home

import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mymoviedb.R
import com.example.mymoviedb.base.BaseFragment
import com.example.mymoviedb.databinding.FragmentHomeBinding
import com.example.mymoviedb.model.HomeFilter
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
            resources.getString(R.string.title_trending_all),
            resources.getString(R.string.title_trending_movie),
            resources.getString(R.string.title_trending_tv_series)
        )
        viewModel.getMovieData(
            resources.getString(R.string.title_movie_now_playing),
            resources.getString(R.string.title_movie_popular),
            resources.getString(R.string.title_movie_top_rated),
            resources.getString(R.string.title_movie_upcoming)
        )
        viewModel.getTVData(
            resources.getString(R.string.title_tv_airing),
            resources.getString(R.string.title_tv_on_the_air),
            resources.getString(R.string.title_tv_popular),
            resources.getString(R.string.title_tv_top_rated)
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
        add(HomeFilter(0, "all",  true))
        add(HomeFilter(1, "Movies",  false))
        add(HomeFilter(2, "TV Shows",  false))
    }
}
