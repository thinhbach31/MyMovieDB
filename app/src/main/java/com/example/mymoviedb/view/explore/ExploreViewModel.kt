package com.example.mymoviedb.view.explore

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mymoviedb.model.GenreRemoteModel
import com.example.mymoviedb.repository.ExploreRepository
import com.example.mymoviedb.utils.Const
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class ExploreViewModel @Inject constructor(private val repository: ExploreRepository) :
    ViewModel() {

    private val _movieGenresLiveData = MutableLiveData<ArrayList<GenreRemoteModel>>()
    val movieGenresLiveData: LiveData<ArrayList<GenreRemoteModel>> = _movieGenresLiveData

    private val _tvGenresLiveData = MutableLiveData<ArrayList<GenreRemoteModel>>()
    val tvGenresLiveData: LiveData<ArrayList<GenreRemoteModel>> = _tvGenresLiveData

    fun getMovieGenres() {
        repository.run {
            getMovieGenres().observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())
                .subscribe(
                    {
                        if (it?.genres != null) {
                            _movieGenresLiveData.value = it.genres!!
                        }
                    }, {
                        Log.e(Const.ERROR_TAG, it.message.toString())
                    }
                )
        }
    }

    fun getTVGenres() {
        repository.run {
            getTVGenres().observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())
                .subscribe(
                    {
                        if (it?.genres != null) {
                            _tvGenresLiveData.value = it.genres!!
                        }
                    }, {
                        Log.e(Const.ERROR_TAG, it.message.toString())
                    }
                )
        }
    }
}
