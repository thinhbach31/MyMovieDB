package com.example.mymoviedb.view.home

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mymoviedb.R
import com.example.mymoviedb.base.BaseFragment
import com.example.mymoviedb.databinding.FragmentHomeBinding
import com.example.mymoviedb.model.HomeFilter

class HomeFragment(): BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {
    private lateinit var filterAdapter: HomeFilterAdapter

    override fun observeData() {
    }

    override fun requestData() {
    }

    override fun initUIComponents() {
        filterAdapter = HomeFilterAdapter(initFilters())
        binding.recyclerFilterHome.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = filterAdapter
            setHasFixedSize(true)
        }
    }

    private fun initFilters(): ArrayList<HomeFilter> = ArrayList<HomeFilter>().apply {
        add(HomeFilter(0, "clear", R.drawable.ic_clear, false))
        add(HomeFilter(1, "Movies", null, false))
        add(HomeFilter(2, "TV Shows", null, false))
    }
}
