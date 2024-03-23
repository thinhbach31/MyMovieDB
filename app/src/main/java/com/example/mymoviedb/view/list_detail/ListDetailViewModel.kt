package com.example.mymoviedb.view.list_detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.mymoviedb.base.BaseViewModel
import com.example.mymoviedb.model.FilmModel
import com.example.mymoviedb.repository.ListDetailRepository
import com.example.mymoviedb.utils.Const
import com.example.mymoviedb.utils.DataResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListDetailViewModel @Inject constructor(private val repository: ListDetailRepository) :
    BaseViewModel() {

    var title: String = ""
    var isMovieGenreOrTV = false
    var genreId = 0
    var isFromGenreOrFilmTag = false

    private val _filmsLiveData = MutableLiveData<DataResult<FilmModel>>()
    val filmsLiveData: LiveData<DataResult<FilmModel>> = _filmsLiveData

    fun getDataListDetail(pageNumber: Int) {
        if (isFromGenreOrFilmTag) getMovieByGenre(pageNumber) else getFilmsByTitle(pageNumber)
    }

    private fun getFilmsByTitle(pageNumber: Int) {
        viewModelScope.launch {
            repository.getMoviesByTitle(title, pageNumber).apply {
                _filmsLiveData.postValue(this)
            }
        }
    }

    private fun getMovieByGenre(pageNumber: Int) {
        viewModelScope.launch(exceptionHandler) {
            showProgressBar()
            repository.getMoviesByGenre(isMovieGenreOrTV, pageNumber, genreId).apply {
                _filmsLiveData.postValue(this)
            }
            hideProgressBar()
        }
    }
}
