package com.example.today.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.today.fragment.FragmentConstellation
import com.example.today.fragment.FragmentEarthquake
import com.example.today.fragment.FragmentWeather

class MyViewPagerAdapter(manager: FragmentManager) : FragmentPagerAdapter(manager) {
    val fragmentList: ArrayList<Fragment> = ArrayList()
    val titleList: ArrayList<String> = ArrayList()

    override fun getItem(position: Int): Fragment {
        return fragmentList[position]
    }

    override fun getCount(): Int {
        return fragmentList.size
    }


    override fun getPageTitle(position: Int): CharSequence? {
        return titleList[position]

    }

    fun addFragment(fragment: Fragment, title: String) {
        fragmentList.add(fragment)
        titleList.add(title)
    }


}