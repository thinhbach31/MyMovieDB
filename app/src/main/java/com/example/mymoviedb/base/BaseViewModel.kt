package com.example.mymoviedb.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

open class BaseViewModel : ViewModel() {

    val loading = MutableLiveData<Boolean>()

    protected fun showProgressBar() {
        viewModelScope.launch {
            loading.postValue(true)
        }
    }

    protected fun hideProgressBar() {
        viewModelScope.launch {
            loading.postValue(false)
        }
    }
}
