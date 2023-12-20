package com.example.mymoviedb.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

typealias Inflate<T> = (LayoutInflater, ViewGroup?, Boolean) -> T

abstract class BaseFragment<VB : ViewBinding>(private val inflate: Inflate<VB>) : Fragment() {
    abstract fun observeData()
    abstract fun requestData()
    abstract fun initUIComponents()

    private var _binding: VB? = null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = inflate.invoke(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUIComponents()
        observeData()
        requestData()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun addFragment(container: Int, fragment: Fragment?) {
        fragment?.let {
            childFragmentManager.beginTransaction().apply {
                add(container, it)
                commit()
            }
        }
    }

    fun replaceFragment(container: Int, fragment: Fragment) {
        if (!childFragmentManager.popBackStackImmediate(fragment.javaClass.name, 0)) {
            childFragmentManager.beginTransaction().apply {
                replace(container, fragment)
                addToBackStack(null)
                commit()
            }
        }
    }
}
