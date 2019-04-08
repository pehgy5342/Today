package com.example.today

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.today.adapter.MyViewPagerAdapter
import com.example.today.fragment.FragmentConstellation
import com.example.today.fragment.FragmentEarthquake
import com.example.today.fragment.FragmentWeather
import com.example.today.mydata.WeatherData
import com.example.today.okhttp.Constellation
import com.example.today.okhttp.Weather
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var fragmentWea = FragmentWeather()
    private var fragmentCon = FragmentConstellation()
    private var fragmentEar = FragmentEarthquake()

    var weatherDataList = ArrayList<WeatherData>()
    var okhttp = Weather()
    var constellation = Constellation()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        constellation.connect()
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
