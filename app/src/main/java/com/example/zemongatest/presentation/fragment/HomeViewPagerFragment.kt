package com.example.zemongatest.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.zemongatest.R
import com.example.zemongatest.databinding.FragmentHomeViewPagerBinding
import com.example.zemongatest.presentation.adapter.ALL_POST_LIST
import com.example.zemongatest.presentation.adapter.FAVORITE_POST_LIST
import com.example.zemongatest.presentation.adapter.PostPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator

/**
 * A simple [Fragment] subclass.
 */
class HomeViewPagerFragment : Fragment() {

    private lateinit var binding: FragmentHomeViewPagerBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeViewPagerBinding.inflate(inflater, container, false)
        val tabLayout = binding.tabs
        val viewPager = binding.viewPager

        viewPager.adapter = PostPagerAdapter(this)
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = getTabTitle(position)
        }.attach()
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
        return binding.root
    }

    private fun getTabTitle(position: Int): String? {
        return when (position) {
            ALL_POST_LIST -> getString(R.string.tap_all_post)
            FAVORITE_POST_LIST -> getString(R.string.tap_favorite)
            else -> null
        }
    }
}
