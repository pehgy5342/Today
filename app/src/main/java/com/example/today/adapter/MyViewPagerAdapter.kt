package com.example.today.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.today.fragment.FragmentConstellation
import com.example.today.fragment.FragmentEarthquake
import com.example.today.fragment.FragmentWeather

class MyViewPagerAdapter(manager: FragmentManager) : FragmentPagerAdapter(manager) {


    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                FragmentWeather()
            }
            1 -> {
                FragmentConstellation()
            }
            else ->{

                FragmentEarthquake()
            }

        }


    }

    override fun getCount(): Int {
        return 3
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0->"天氣"
            1->"星座"
            else->"地震"
        }

    }



}