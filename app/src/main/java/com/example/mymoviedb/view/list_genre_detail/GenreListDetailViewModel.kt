package com.example.mymoviedb.view.list_genre_detail

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
class GenreListDetailViewModel @Inject constructor(private val repository: ListDetailRepository) :
    BaseViewModel() {

    private var _moviesByGenre = MutableLiveData<DataResult<FilmModel>?>()
    val movieByGenre: MutableLiveData<DataResult<FilmModel>?> = _moviesByGenre

    fun getMovieByGenre(isMovie: Boolean, id: Int) {
        viewModelScope.launch(exceptionHandler) {
            showProgressBar()
            repository.getMoviesByGenre(isMovie, Const.DEFAULT_RESULT_PAGE, id).apply {
                _moviesByGenre.postValue(this)
            }
            hideProgressBar()
        }
    }
}
