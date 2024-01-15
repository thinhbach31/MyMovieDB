package com.example.mymoviedb.view.explore

import android.util.Log
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mymoviedb.R
import com.example.mymoviedb.base.BaseFragment
import com.example.mymoviedb.databinding.FragmentExploreBinding
import com.example.mymoviedb.utils.DataResult
import com.example.mymoviedb.utils.Functions.orEmpty
import com.example.mymoviedb.view.custom.GenreItemDecoration
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ExploreFragment(): BaseFragment<FragmentExploreBinding>(FragmentExploreBinding::inflate) {

    private val viewModel: ExploreViewModel by activityViewModels()
    private lateinit var movieGenresAdapter: ExploreGenreAdapter
    private lateinit var tvGenresAdapter: ExploreGenreAdapter

    override fun observeData() {
        viewModel.apply {
            movieGenresLiveData.observe(this@ExploreFragment) {
                movieGenresAdapter.apply {
                    when(it.status) {
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
                tvGenresAdapter.apply {
                    when(it.status) {
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
        viewModel.apply {
            getMovieGenres()
            getTVGenres()
        }
    }

    override fun initUIComponents() {
        movieGenresAdapter = ExploreGenreAdapter(ArrayList(), object : ExploreGenreClickListener {
            override fun onItemGenreClick(id: Int) {

            }
        })
        binding.recyclerMovieGenre.apply {
            layoutManager = GridLayoutManager(context, 2)
            addItemDecoration(GenreItemDecoration(resources.getDimensionPixelSize(R.dimen.dp_10)))
            adapter = movieGenresAdapter
            setHasFixedSize(true)
            isNestedScrollingEnabled = false
        }

        tvGenresAdapter = ExploreGenreAdapter(ArrayList(), object : ExploreGenreClickListener {
            override fun onItemGenreClick(id: Int) {

            }
        })
        binding.recyclerTvGenre.apply {
            layoutManager = GridLayoutManager(context, 2)
            addItemDecoration(GenreItemDecoration(resources.getDimensionPixelSize(R.dimen.dp_10)))
            adapter = tvGenresAdapter
            setHasFixedSize(true)
            isNestedScrollingEnabled = false
        }
    }
}
