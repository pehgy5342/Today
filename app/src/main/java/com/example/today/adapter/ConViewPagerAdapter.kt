package com.example.today.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.example.today.R
import com.example.today.mydata.ConstellationData
import kotlinx.android.synthetic.main.constellation_item.view.*

class ConViewPagerAdapter(var luckyList: ArrayList<ConstellationData.Lucky>) : PagerAdapter() {

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun getCount(): Int = luckyList.size

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)


    }

    override fun getPageWidth(position: Int): Float {
        return 0.5f
    }


    override fun instantiateItem(container: ViewGroup, position: Int): Any {
//        return super.instantiateItem(container, position)
        val view = LayoutInflater.from(container.context).inflate(R.layout.constellation_item, container, false)
        val conStar = view.tv_conStar
        val conDesc = view.tv_conDesc

        conStar.text = luckyList[position].star
        conDesc.text = luckyList[position].desc

        container.addView(view)
        return view

    }
}