package com.example.mymoviedb.view.main

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.mymoviedb.utils.Const
import com.example.mymoviedb.view.explore.ExploreFragment
import com.example.mymoviedb.view.favorite.FavoriteFragment
import com.example.mymoviedb.view.home.HomeFragment

class MainPagerAdapter(fragment: Fragment): FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = Const.TOTAL_PAGE_SIZE

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> HomeFragment()
            1 -> ExploreFragment()
            else -> FavoriteFragment()
        }
    }
}