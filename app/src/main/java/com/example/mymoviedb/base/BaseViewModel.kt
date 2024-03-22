package com.example.mymoviedb.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

open class BaseViewModel : ViewModel() {

    val loading = MutableLiveData<Boolean>()
    val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }
    val errorMessage = MutableLiveData<String>()

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

    private fun onError(message: String) {
        errorMessage.value = message
        loading.value = false
    }
}
