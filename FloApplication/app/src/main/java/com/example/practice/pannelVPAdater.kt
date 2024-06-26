package com.example.practice

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class pannelVPAdater(fragment:Fragment) : FragmentStateAdapter(fragment){
    private val fragmentlist : ArrayList<Fragment> = ArrayList()

    override fun getItemCount(): Int = fragmentlist.size
    override fun createFragment(position: Int): Fragment = fragmentlist[position]

    fun addfragment(fragment: Fragment){
        fragmentlist.add(fragment)
        notifyItemInserted(fragmentlist.size-1)
    }

}