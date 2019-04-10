package com.example.today

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.today.adapter.MyViewPagerAdapter
import com.example.today.fragment.FragmentConstellation
import com.example.today.fragment.FragmentEarthquake
import com.example.today.fragment.FragmentWeather
import com.example.today.okhttp.Constellation
import com.example.today.okhttp.Weather
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_weather.*

class MainActivity : AppCompatActivity() {

    private var fragmentWea = FragmentWeather()
    private var fragmentCon = FragmentConstellation()
    private var fragmentEar = FragmentEarthquake()

    //    var weatherDataList = ArrayList<WeatherData>()
    var weather = Weather()
    var constellation = Constellation()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        getJsonWeatherCityName()
        getJsonConstellationName()
        weather.connect()
        constellation.connect()
        initView()
    }


    fun getJsonWeatherCityName() {
        weather.weatherLocationNameList = {
            runOnUiThread {
                tv_cityName.text = it[0]

//                var SA = SunAdapter(list = it)
//                rv_sun.layoutManager = LinearLayoutManager(this)
//                rv_sun.adapter = SA
            }


        }



        weather.weatherElementList = {
            runOnUiThread {


                if (it[0] == "晴時多雲") {
                    Glide.with(this).load(R.drawable.sun_cloudy).into(iv_Wx)
                } else if (it[0] == "多雲") {
                    Glide.with(this).load(R.drawable.cloudys).into(iv_Wx)
                } else if (it[0] == "多雲短暫陣雨") {
                    Glide.with(this).load(R.drawable.rain_cloudy).into(iv_Wx)
                } else if (it[0] == "短暫陣雨") {
                    Glide.with(this).load(R.drawable.rain).into(iv_Wx)
                }


                Glide.with(this).load(R.drawable.pop).into(iv_PoP)
                Glide.with(this).load(R.drawable.max_t).into(iv_AT)
                Glide.with(this).load(R.drawable.min_t).into(iv_CI)
//                Glide.with(this).load(R.drawable.max_t).into(iv_maxT)
//                Glide.with(this).load(R.drawable.min_t).into(iv_minT)


                tv_Wx.text = it[0]
                tv_AT.text = "體感 ${it[1]}°C"
                tv_T.text = "${it[2]} °C"
                tv_CI.text = it[3]
                tv_PoP6h.text = "降雨量 ${it[1]}%"

//                tv_MinT.text = "最低溫度 ${it[2]}°C"
//                tv_MaxT.text = "最高溫度 ${it[4]}°C"

            }

        }

    }

    fun test() {
        fragmentWea.fragmentManager
    }


    fun getJsonConstellationName() {
        constellation.constellationList = {
            runOnUiThread {
                //                tv_conName.text = it[0]

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

        val tabIcon: IntArray = intArrayOf(R.drawable.sun, R.drawable.planets, R.drawable.earth)
        tabLayout.getTabAt(0)!!.setIcon(tabIcon[0])
        tabLayout.getTabAt(1)!!.setIcon(tabIcon[1])
        tabLayout.getTabAt(2)!!.setIcon(tabIcon[2])

    }
}
