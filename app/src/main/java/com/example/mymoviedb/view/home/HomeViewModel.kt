package com.example.mymoviedb.view.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.mymoviedb.model.Result
import com.example.mymoviedb.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: HomeRepository,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val _trendingAllLiveData = MutableLiveData<ArrayList<Result>>()
    val trendingAllLiveData: LiveData<ArrayList<Result>> = _trendingAllLiveData

    private val _trendingMoviesLiveData = MutableLiveData<ArrayList<Result>>()
    val trendingMoviesLiveData: LiveData<ArrayList<Result>> = _trendingMoviesLiveData

    private val _trendingTVSeriesLiveData = MutableLiveData<ArrayList<Result>>()
    val trendingTVSeriesLiveData: LiveData<ArrayList<Result>> = _trendingTVSeriesLiveData

    private val _nowPlayingMoviesLiveData = MutableLiveData<ArrayList<Result>>()
    val nowPlayingMoviesLiveData: LiveData<ArrayList<Result>> = _nowPlayingMoviesLiveData

    private val _popularMoviesLiveData = MutableLiveData<ArrayList<Result>>()
    val popularMoviesLiveData: LiveData<ArrayList<Result>> = _popularMoviesLiveData

    private val _topRatedMoviesLiveData = MutableLiveData<ArrayList<Result>>()
    val topRatedMoviesLiveData: LiveData<ArrayList<Result>> = _topRatedMoviesLiveData

    private val _upcomingMoviesLiveData = MutableLiveData<ArrayList<Result>>()
    val upcomingMoviesLiveData: LiveData<ArrayList<Result>> = _upcomingMoviesLiveData

    private val _airingTodayTVSeriesLiveData = MutableLiveData<ArrayList<Result>>()
    val airingTodayTVSeriesLiveData: LiveData<ArrayList<Result>> = _airingTodayTVSeriesLiveData

    private val _onTheAirTVSeriesLiveData = MutableLiveData<ArrayList<Result>>()
    val onTheAirTVSeriesLiveData: LiveData<ArrayList<Result>> = _onTheAirTVSeriesLiveData

    private val _popularTVSeriesLiveData = MutableLiveData<ArrayList<Result>>()
    val popularTVSeriesLiveData: LiveData<ArrayList<Result>> = _popularTVSeriesLiveData

    private val _topRatedTVSeriesLiveData = MutableLiveData<ArrayList<Result>>()
    val topRatedTVSeriesLiveData: LiveData<ArrayList<Result>> = _topRatedTVSeriesLiveData

    fun getTrendingAll() {
        repository.run {
            getTrendingAll().observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    _trendingAllLiveData.value = it.results
                }, {
                    Log.e("dd", "")
                })
        }
    }

    fun getTrendingMovies() {
        repository.run {
            getTrendingMovies().observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    _trendingMoviesLiveData.value = it.results
                }, {
                    Log.e("dd", "")
                })
        }
    }

    fun getTrendingTVSeries() {
        repository.run {
            getTrendingTVSeries().observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    _trendingTVSeriesLiveData.value = it.results
                }, {
                    Log.e("dd", "")
                })
        }
    }

    fun getNowPlayingMovie() {
        repository.run {
            getNowPlayingMovie().observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    _nowPlayingMoviesLiveData.value = it.results
                }, {
                    Log.e("dd", "")
                })
        }
    }

    fun getPopularMovie() {
        repository.run {
            getPopularMovie().observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    _popularMoviesLiveData.value = it.results
                }, {
                    Log.e("dd", "")
                })
        }
    }

    fun getTopRatedMovie() {
        repository.run {
            getTopRatedMovie().observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    _topRatedMoviesLiveData.value = it.results
                }, {
                    Log.e("dd", "")
                })
        }
    }

    fun getUpcomingMovie() {
        repository.run {
            getUpcomingMovie().observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    _upcomingMoviesLiveData.value = it.results
                }, {
                    Log.e("dd", "")
                })
        }
    }

    fun getAiringTodayTVSeries() {
        repository.run {
            getAiringTodayTVSeries().observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    _airingTodayTVSeriesLiveData.value = it.results
                }, {
                    Log.e("dd", "")
                })
        }
    }

    fun getOnTheAirTVSeries() {
        repository.run {
            getOnTheAirTVSeries().observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    _onTheAirTVSeriesLiveData.value = it.results
                }, {
                    Log.e("dd", "")
                })
        }
    }

    fun getPopularTVSeries() {
        repository.run {
            getPopularTVSeries().observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    _popularTVSeriesLiveData.value = it.results
                }, {
                    Log.e("dd", "")
                })
        }
    }

    fun getTopRatedTVSeries() {
        repository.run {
            getTopRatedTVSeries().observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    _topRatedTVSeriesLiveData.value = it.results
                }, {
                    Log.e("dd", "")
                })
        }
    }
}
