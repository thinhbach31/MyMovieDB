package com.example.mymoviedb.view.movie

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mymoviedb.base.BaseFragment
import com.example.mymoviedb.databinding.FragmentMovieBinding
import com.example.mymoviedb.model.ListFilmWithTitle
import com.example.mymoviedb.model.Result
import com.example.mymoviedb.utils.Const
import com.example.mymoviedb.view.home.HomeViewModel
import com.example.mymoviedb.view.home.ListFilmWithTitleAdapter
import com.example.mymoviedb.view.home.OnListFilmWithTitleClickListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieFragment() : BaseFragment<FragmentMovieBinding>(FragmentMovieBinding::inflate) {

    private val viewModel: HomeViewModel by viewModels()
    private lateinit var movieAdapter: ListFilmWithTitleAdapter

    override fun observeData() {
        viewModel.apply {
            nowPlayingMoviesLiveData.observe(this@MovieFragment) { movieList ->
                movieAdapter.addItem(ListFilmWithTitle(Const.NOW_PLAYING_MOVIE, movieList))
                movieAdapter.notifyDataSetChanged()
            }

            popularMoviesLiveData.observe(this@MovieFragment) { movieList ->
                movieAdapter.addItem(ListFilmWithTitle(Const.POPULAR_MOVIE, movieList))
                movieAdapter.notifyDataSetChanged()
            }

            topRatedMoviesLiveData.observe(this@MovieFragment) { movieList ->
                movieAdapter.addItem(ListFilmWithTitle(Const.TOP_RATED_MOVIE, movieList))
                movieAdapter.notifyDataSetChanged()
            }

            upcomingMoviesLiveData.observe(this@MovieFragment) { movieList ->
                movieAdapter.addItem(ListFilmWithTitle(Const.UPCOMING_MOVIE, movieList))
                movieAdapter.notifyDataSetChanged()
            }
        }
    }

    override fun requestData() {
        viewModel.apply {
            getNowPlayingMovie()
            getPopularMovie()
            getTopRatedMovie()
            getUpcomingMovie()
        }
    }

    override fun initUIComponents() {

        movieAdapter =
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
        binding.recyclerMovie.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = movieAdapter
            setHasFixedSize(true)
        }
    }
}
