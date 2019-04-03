package com.example.today.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class MyViewPagerAdapter(manager: FragmentManager) : FragmentPagerAdapter(manager) {

    val fragmentList : ArrayList<Fragment> = ArrayList()
    val tagList:ArrayList<String> = ArrayList()

    override fun getItem(position: Int): Fragment {
        return fragmentList[position]
    }

    override fun getCount(): Int {
        return fragmentList.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return tagList[position]
    }

    fun addFragment(fragment: Fragment,tag: String){
        fragmentList.add(fragment)
        tagList.add(tag)
    }

}