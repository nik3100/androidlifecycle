package com.savvytech.tabapicalldemo

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class MyViewPagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {

    private val NUM_TABS = 3

    override fun getItemCount(): Int = NUM_TABS

    override fun createFragment(position: Int): Fragment = when (position) {
        0 -> Tab1Fragment()
        1 -> Tab2Fragment()
        2 -> Tab3Fragment()
        else -> Tab1Fragment()

    }
}