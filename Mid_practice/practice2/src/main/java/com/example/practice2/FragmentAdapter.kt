package com.example.practice2

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class FragmentAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    private val fragmentlist: List<Fragment>
    init {
        fragmentlist = listOf(OneFragment(), TwoFragment(), ThreeFragment())
    }
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment = fragmentlist[position]
}