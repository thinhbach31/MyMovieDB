package com.example.mymoviedb.view.main

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import com.example.mymoviedb.base.BaseVBActivity
import com.example.mymoviedb.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseVBActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addFragment(binding.containerMainFragment.id, MainFragment())

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (supportFragmentManager.backStackEntryCount > 1) {
                    supportFragmentManager.popBackStackImmediate()
                } else {
                    finish()
                }
            }
        })
    }
}
