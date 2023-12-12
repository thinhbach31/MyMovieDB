package com.example.mymoviedb.view.home

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mymoviedb.R
import com.example.mymoviedb.base.BaseFragment
import com.example.mymoviedb.databinding.FragmentHomeBinding
import com.example.mymoviedb.model.HomeFilter
import com.example.mymoviedb.view.trending.TrendingAllFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment(): BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate), HomeFilterClickListener {
    private lateinit var filterAdapter: HomeFilterAdapter
    private val viewModel: HomeViewModel by viewModels()

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

        addFragment(binding.containerHome.id, TrendingAllFragment())
    }

    override fun onFilterItemCLick(id: Int) {
        when(id) {
            0 -> {
                replaceFragment(binding.containerHome.id, TrendingAllFragment())
            }
            1 -> {

            }
            2 -> {

            }
        }
    }

    private fun initFilters(): ArrayList<HomeFilter> = ArrayList<HomeFilter>().apply {
        add(HomeFilter(0, "clear", R.drawable.ic_clear, false))
        add(HomeFilter(1, "Movies", null, false))
        add(HomeFilter(2, "TV Shows", null, false))
    }
}
