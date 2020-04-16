package com.example.zemongatest.presentation.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.zemongatest.presentation.fragment.AllPostFragment
import com.example.zemongatest.presentation.fragment.FavoritePostsFragment

const val ALL_POST_LIST = 0
const val FAVORITE_POST_LIST = 1

class PostPagerAdapter (fragment: Fragment) :  FragmentStateAdapter(fragment){
    private val tabs = mapOf(
        ALL_POST_LIST to AllPostFragment(),
        FAVORITE_POST_LIST to FavoritePostsFragment()
    )

    override fun getItemCount(): Int {
        return tabs.size
    }

    override fun createFragment(position: Int): Fragment {
        return tabs.getValue(position)
    }
}