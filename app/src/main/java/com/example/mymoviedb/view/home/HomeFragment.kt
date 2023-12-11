package com.example.mymoviedb.view.home

import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mymoviedb.R
import com.example.mymoviedb.base.BaseFragment
import com.example.mymoviedb.databinding.FragmentHomeBinding
import com.example.mymoviedb.model.HomeFilter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment(): BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {
    private lateinit var filterAdapter: HomeFilterAdapter
    private val viewModel: HomeViewModel by viewModels()

    override fun observeData() {
        viewModel.observeMovieLiveData().observe(this, Observer { movieList ->
            Toast.makeText(context, "success", Toast.LENGTH_SHORT).show()
        })
    }

    override fun requestData() {
        viewModel.getTrendingAll()
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
