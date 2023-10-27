package com.example.mymoviedb.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import java.lang.reflect.ParameterizedType

abstract class BaseVBActivity<VB : ViewBinding>(val bindingFactory: (LayoutInflater) -> VB) :
    BaseActivity() {

    lateinit var binding: VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = bindingFactory.invoke(layoutInflater)
        setContentView(binding.root)
    }
}
