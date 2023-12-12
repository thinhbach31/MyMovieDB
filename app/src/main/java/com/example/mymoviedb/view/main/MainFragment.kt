package com.example.mymoviedb.view.main

import androidx.appcompat.content.res.AppCompatResources
import com.example.mymoviedb.R
import com.example.mymoviedb.base.BaseFragment
import com.example.mymoviedb.databinding.FragmentMainBinding
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment() : BaseFragment<FragmentMainBinding>(FragmentMainBinding::inflate) {
    private lateinit var mainPagerAdapter: MainPagerAdapter
    override fun observeData() {
    }

    override fun requestData() {
    }

    override fun initUIComponents() {
        mainPagerAdapter = MainPagerAdapter(this)
        binding.pagerMain.let { pager ->
            pager.adapter = mainPagerAdapter
            TabLayoutMediator(binding.tabMain, binding.pagerMain) { tab, pos ->
                initTabTitle().apply {
                    tab.icon = context?.let { AppCompatResources.getDrawable(it, this[pos]) }
                }
            }.attach()
            pager.isUserInputEnabled = false
        }
    }

    private fun initTabTitle() = arrayListOf<Int>().apply {
        add(R.drawable.ic_home)
        add(R.drawable.ic_explore)
        add(R.drawable.ic_favorite)
    }
}
