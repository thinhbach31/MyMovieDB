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
    private val movieLiveData = MutableLiveData<List<Result>>()

    fun getTrendingAll() {
        repository.getTrendingAll().observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                if (it.results != null){
                    movieLiveData.value = it.results
                }
            }, {
                Log.e("dd","")
            })
    }

    fun observeMovieLiveData(): LiveData<List<Result>> {
        return movieLiveData
    }
}
