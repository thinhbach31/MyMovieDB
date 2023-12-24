package com.example.mymoviedb.view.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.mymoviedb.model.ListFilmWithTitle
import com.example.mymoviedb.repository.FilmRepository
import com.example.mymoviedb.utils.Const
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: FilmRepository,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val _trendingAllLiveData = MutableLiveData<ListFilmWithTitle>()
    val trendingAllLiveData: LiveData<ListFilmWithTitle> = _trendingAllLiveData

    private val _trendingMoviesLiveData = MutableLiveData<ListFilmWithTitle>()
    val trendingMoviesLiveData: LiveData<ListFilmWithTitle> = _trendingMoviesLiveData

    private val _trendingTVSeriesLiveData = MutableLiveData<ListFilmWithTitle>()
    val trendingTVSeriesLiveData: LiveData<ListFilmWithTitle> = _trendingTVSeriesLiveData

    private val _nowPlayingMoviesLiveData = MutableLiveData<ListFilmWithTitle>()
    val nowPlayingMoviesLiveData: LiveData<ListFilmWithTitle> = _nowPlayingMoviesLiveData

    private val _popularMoviesLiveData = MutableLiveData<ListFilmWithTitle>()
    val popularMoviesLiveData: LiveData<ListFilmWithTitle> = _popularMoviesLiveData

    private val _topRatedMoviesLiveData = MutableLiveData<ListFilmWithTitle>()
    val topRatedMoviesLiveData: LiveData<ListFilmWithTitle> = _topRatedMoviesLiveData

    private val _upcomingMoviesLiveData = MutableLiveData<ListFilmWithTitle>()
    val upcomingMoviesLiveData: LiveData<ListFilmWithTitle> = _upcomingMoviesLiveData

    private val _airingTodayTVSeriesLiveData = MutableLiveData<ListFilmWithTitle>()
    val airingTodayTVSeriesLiveData: LiveData<ListFilmWithTitle> = _airingTodayTVSeriesLiveData

    private val _onTheAirTVSeriesLiveData = MutableLiveData<ListFilmWithTitle>()
    val onTheAirTVSeriesLiveData: LiveData<ListFilmWithTitle> = _onTheAirTVSeriesLiveData

    private val _popularTVSeriesLiveData = MutableLiveData<ListFilmWithTitle>()
    val popularTVSeriesLiveData: LiveData<ListFilmWithTitle> = _popularTVSeriesLiveData

    private val _topRatedTVSeriesLiveData = MutableLiveData<ListFilmWithTitle>()
    val topRatedTVSeriesLiveData: LiveData<ListFilmWithTitle> = _topRatedTVSeriesLiveData

    fun getTrendingData(allTitle: String, movieTitle: String, tvTitle: String) {
        getTrendingAll(allTitle)
        getTrendingMovies(movieTitle)
        getTrendingTVSeries(tvTitle)
    }

    fun getMovieData(np: String, ppl: String, tr: String, uc: String) {
        getNowPlayingMovie(np)
        getPopularMovie(ppl)
        getTopRatedMovie(tr)
        getUpcomingMovie(uc)
    }

    fun getTVData(airing: String, ota: String, ppl: String, tr: String) {
        getAiringTodayTVSeries(airing)
        getOnTheAirTVSeries(ota)
        getPopularTVSeries(ppl)
        getTopRatedTVSeries(tr)
    }

    private fun getTrendingAll(title: String) {
        repository.run {
            getTrendingAll().observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    ListFilmWithTitle(title, it.results).let { list ->
                        _trendingAllLiveData.value = list
                        saveLocalListFilmWithTitleData(Const.TRENDING_ALL, list)
                    }
                }, {
                    Log.e(Const.ERROR_TAG, it.message.toString())
                })
        }
    }

    private fun getTrendingMovies(title: String) {
        repository.run {
            getTrendingMovies().observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    ListFilmWithTitle(title, it.results).let { list ->
                        _trendingMoviesLiveData.value = list
                        saveLocalListFilmWithTitleData(Const.TRENDING_MOVIE, list)
                    }
                }, {
                    Log.e(Const.ERROR_TAG, it.message.toString())
                })
        }
    }

    private fun getTrendingTVSeries(title: String) {
        repository.run {
            getTrendingTVSeries().observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    ListFilmWithTitle(title, it.results).let { list ->
                        _trendingTVSeriesLiveData.value = list
                        saveLocalListFilmWithTitleData(Const.TRENDING_TV, list)
                    }
                }, {
                    Log.e(Const.ERROR_TAG, it.message.toString())
                })
        }
    }

    private fun getNowPlayingMovie(title: String) {
        repository.run {
            getNowPlayingMovie().observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    ListFilmWithTitle(title, it.results).let { list ->
                        _nowPlayingMoviesLiveData.value = list
                        saveLocalListFilmWithTitleData(Const.NOW_PLAYING_MOVIE, list)
                    }
                }, {
                    Log.e(Const.ERROR_TAG, it.message.toString())
                })
        }
    }

    private fun getPopularMovie(title: String) {
        repository.run {
            getPopularMovie().observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    ListFilmWithTitle(title, it.results).let { list ->
                        _popularMoviesLiveData.value = list
                        saveLocalListFilmWithTitleData(Const.POPULAR_MOVIE, list)
                    }
                }, {
                    Log.e(Const.ERROR_TAG, it.message.toString())
                })
        }
    }

    private fun getTopRatedMovie(title: String) {
        repository.run {
            getTopRatedMovie().observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    ListFilmWithTitle(title, it.results).let { list ->
                        _topRatedMoviesLiveData.value = list
                        saveLocalListFilmWithTitleData(Const.TOP_RATED_MOVIE, list)
                    }
                }, {
                    Log.e(Const.ERROR_TAG, it.message.toString())
                })
        }
    }

    private fun getUpcomingMovie(title: String) {
        repository.run {
            getUpcomingMovie().observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    ListFilmWithTitle(title, it.results).let { list ->
                        _upcomingMoviesLiveData.value = list
                        saveLocalListFilmWithTitleData(Const.UPCOMING_MOVIE, list)
                    }
                }, {
                    Log.e(Const.ERROR_TAG, it.message.toString())
                })
        }
    }

    private fun getAiringTodayTVSeries(title: String) {
        repository.run {
            getAiringTodayTVSeries().observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    ListFilmWithTitle(title, it.results).let { list ->
                        _airingTodayTVSeriesLiveData.value = list
                        saveLocalListFilmWithTitleData(Const.AIRING_TV, list)
                    }
                }, {
                    Log.e(Const.ERROR_TAG, it.message.toString())
                })
        }
    }

    private fun getOnTheAirTVSeries(title: String) {
        repository.run {
            getOnTheAirTVSeries().observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    ListFilmWithTitle(title, it.results).let { list ->
                        _onTheAirTVSeriesLiveData.value = list
                        saveLocalListFilmWithTitleData(Const.ON_THE_AIR_TV, list)
                    }
                }, {
                    Log.e(Const.ERROR_TAG, it.message.toString())
                })
        }
    }

    private fun getPopularTVSeries(title: String) {
        repository.run {
            getPopularTVSeries().observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    ListFilmWithTitle(title, it.results).let { list ->
                        _popularTVSeriesLiveData.value = list
                        saveLocalListFilmWithTitleData(Const.POPULAR_TV, list)
                    }
                }, {
                    Log.e(Const.ERROR_TAG, it.message.toString())
                })
        }
    }

    private fun getTopRatedTVSeries(title: String) {
        repository.run {
            getTopRatedTVSeries().observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    ListFilmWithTitle(title, it.results).let { list ->
                        _topRatedTVSeriesLiveData.value = list
                        saveLocalListFilmWithTitleData(Const.TOP_RATED_TV, list)
                    }
                }, {
                    Log.e(Const.ERROR_TAG, it.message.toString())
                })
        }
    }

    override fun onCleared() {
        super.onCleared()
        repository.clearPref()
    }
}
