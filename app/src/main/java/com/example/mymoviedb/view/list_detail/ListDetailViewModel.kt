package com.example.mymoviedb.view.list_detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.mymoviedb.base.BaseViewModel
import com.example.mymoviedb.model.FilmModel
import com.example.mymoviedb.repository.ListDetailRepository
import com.example.mymoviedb.utils.DataResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListDetailViewModel @Inject constructor(private val repository: ListDetailRepository) :
    BaseViewModel() {

    private var title: String = ""
    private val _filmsLiveData = MutableLiveData<DataResult<FilmModel>>()
    val filmsLiveData: LiveData<DataResult<FilmModel>> = _filmsLiveData

    fun setTitle(title: String) {
        this.title = title
    }

    fun getFilmsByTitle(page: Int) {
        viewModelScope.launch {
            repository.getMoviesByTitle(title, page).apply {
                _filmsLiveData.postValue(this)
            }
        }
    }
}
