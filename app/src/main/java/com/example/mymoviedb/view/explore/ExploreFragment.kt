package com.example.mymoviedb.view.explore

import android.util.Log
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mymoviedb.R
import com.example.mymoviedb.base.BaseFragment
import com.example.mymoviedb.databinding.FragmentExploreBinding
import com.example.mymoviedb.model.GenreLocalModel
import com.example.mymoviedb.model.HomeFilter
import com.example.mymoviedb.utils.Const
import com.example.mymoviedb.utils.DataResult
import com.example.mymoviedb.utils.Functions.orEmpty
import com.example.mymoviedb.view.home.HomeFilterAdapter
import com.example.mymoviedb.view.home.HomeFilterClickListener
import com.example.mymoviedb.view.list_genre_detail.GenreListDetailFragment
import com.example.mymoviedb.view.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ExploreFragment() : BaseFragment<FragmentExploreBinding>(FragmentExploreBinding::inflate),
    HomeFilterClickListener {

    private val viewModel: ExploreViewModel by activityViewModels()
    private lateinit var filterAdapter: HomeFilterAdapter
    private lateinit var movieGenresAdapter: ExploreGenreAdapter

    override fun observeData() {
        viewModel.apply {
            movieGenresLiveData.observe(this@ExploreFragment) {
                movieGenresAdapter.apply {
                    when (it.status) {
                        DataResult.Status.SUCCESS -> {
                            Log.d("Status genre frag", "movie success")
                        }

                        DataResult.Status.ERROR -> {
                            Log.d("Status genre frag", "movie error")
                        }
                    }
                    updateData(it.data?.genres.orEmpty())
                    notifyDataSetChanged()
                }
            }
            tvGenresLiveData.observe(this@ExploreFragment) {
                movieGenresAdapter.apply {
                    when (it.status) {
                        DataResult.Status.SUCCESS -> {
                            Log.d("Status genre frag", "tv success")
                        }

                        DataResult.Status.ERROR -> {
                            Log.d("Status genre frag", "tv error")
                        }
                    }
                    updateData(it.data?.genres.orEmpty())
                    notifyDataSetChanged()
                }
            }
        }
    }

    override fun requestData() {
        viewModel.getMovieGenres()
    }

    override fun initUIComponents() {
        filterAdapter = HomeFilterAdapter(initFilters(), this)
        binding.recyclerFilterGenre.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = filterAdapter
            setHasFixedSize(true)
//            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.HORIZONTAL))
        }
        movieGenresAdapter = ExploreGenreAdapter(ArrayList(), object : ExploreGenreClickListener {
            override fun onItemGenreClick(genre: GenreLocalModel) {
                (activity as MainActivity).addFragment(
                    R.id.container_main_fragment,
                    GenreListDetailFragment.newInstance(viewModel.isMovieSelected, genre)
                )
            }
        })
        binding.recyclerMovieGenre.apply {
            layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
            adapter = movieGenresAdapter
            setHasFixedSize(true)
        }
    }

    private fun initFilters(): ArrayList<HomeFilter> = ArrayList<HomeFilter>().apply {
        add(HomeFilter(1, Const.HOME_FILTER_MOVIE, true))
        add(HomeFilter(2, Const.HOME_FILTER_TV, false))
    }

    override fun onFilterItemCLick(id: Int) {
        filterAdapter.updateFilterSelectedStatus(id)
        filterAdapter.notifyDataSetChanged()
        when (id) {
            1 -> {
                viewModel.apply {
                    isMovieSelected = true
                    getMovieGenres()
                }
            }

            2 -> {
                viewModel.apply {
                    isMovieSelected = false
                    getTVGenres()
                }
            }
        }
    }
}
