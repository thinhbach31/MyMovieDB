package com.example.mymoviedb.view.explore

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.mymoviedb.base.BaseViewModel
import com.example.mymoviedb.model.ListGenreLocalModel
import com.example.mymoviedb.repository.ExploreRepository
import com.example.mymoviedb.utils.DataResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExploreViewModel @Inject constructor(private val repository: ExploreRepository) :
    BaseViewModel() {

    private val _movieGenresLiveData = MutableLiveData<DataResult<ListGenreLocalModel>>()
    val movieGenresLiveData: LiveData<DataResult<ListGenreLocalModel>> = _movieGenresLiveData

    private val _tvGenresLiveData = MutableLiveData<DataResult<ListGenreLocalModel>>()
    val tvGenresLiveData: LiveData<DataResult<ListGenreLocalModel>> = _tvGenresLiveData

    var isMovieSelected = true

    fun getMovieGenres() {
        viewModelScope.launch(exceptionHandler) {
            showProgressBar()
            Log.d("Status genre vm", "movie call")
            val result = repository.getMovieGenres()
            _movieGenresLiveData.postValue(result)
            hideProgressBar()
        }
    }

    fun getTVGenres() {
        viewModelScope.launch(exceptionHandler) {
            showProgressBar()
            Log.d("Status genre vm", "tv call")
            val result = repository.getTVGenres()
            _tvGenresLiveData.postValue(result)
            hideProgressBar()
        }
    }
}
