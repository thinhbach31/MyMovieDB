package com.example.mymoviedb.view.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mymoviedb.model.Result
import com.example.mymoviedb.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: HomeRepository) : ViewModel() {
    private val _trendingAllLiveData = MutableLiveData<ArrayList<Result>>()
    val trendingAllLiveData: LiveData<ArrayList<Result>> = _trendingAllLiveData

    private val _trendingMoviesLiveData = MutableLiveData<ArrayList<Result>>()
    val trendingMoviesLiveData: LiveData<ArrayList<Result>> = _trendingMoviesLiveData

    private val _trendingTVSeriesLiveData = MutableLiveData<ArrayList<Result>>()
    val trendingTVSeriesLiveData: LiveData<ArrayList<Result>> = _trendingTVSeriesLiveData

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
}
