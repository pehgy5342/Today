package com.example.today

import android.content.DialogInterface
import android.os.Bundle
import android.view.Gravity
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

import com.example.today.adapter.MyViewPagerAdapter
import com.example.today.fragment.FragmentConstellation
import com.example.today.fragment.FragmentEarthquake
import com.example.today.fragment.FragmentWeather
import com.example.today.mydata.WeatherData
import com.example.today.okhttp.OKhttp
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_weather.*

class MainActivity : AppCompatActivity() {

    private var fragmentWea = FragmentWeather()
    private var fragmentCon = FragmentConstellation()
    private var fragmentEar = FragmentEarthquake()

    var weatherDataList = ArrayList<WeatherData>()
    var okhttp = OKhttp()
    var position = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)




        getJsonWeatherCityName()
        okhttp.connect()
        initView()
    }


    fun getJsonWeatherCityName() {
        okhttp.dataList = {
            runOnUiThread {
                //                var SA = SunAdapter(list = it)
//                rv_sun.layoutManager = LinearLayoutManager(this)
//                rv_sun.adapter = SA
            }


        }

    }

    fun initView() {

        val tabAdapter = MyViewPagerAdapter(supportFragmentManager)

        tabAdapter.addFragment(fragmentWea, "天氣")
        tabAdapter.addFragment(fragmentCon, "星座")
        tabAdapter.addFragment(fragmentEar, "地震")

        viewPager.adapter = tabAdapter
        tabLayout.setupWithViewPager(viewPager)
        setTabIcon()

    }


    fun setTabIcon() {

        val tabIcon: IntArray = intArrayOf(
            R.drawable.sun,
            R.drawable.planets,
            R.drawable.earth


        )

        tabLayout.getTabAt(0)!!.setIcon(tabIcon[0])
        tabLayout.getTabAt(1)!!.setIcon(tabIcon[1])
        tabLayout.getTabAt(2)!!.setIcon(tabIcon[2])


    }
}
