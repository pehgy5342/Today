package com.example.today

import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.today.adapter.MyViewPagerAdapter
import com.example.today.fragment.FragmentConstellation
import com.example.today.fragment.FragmentEarthquake
import com.example.today.fragment.FragmentWeather
import com.example.today.okhttp.Constellation
import com.example.today.okhttp.WeatherToday
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var fragmentWea = FragmentWeather()
    private var fragmentCon = FragmentConstellation()
    private var fragmentEar = FragmentEarthquake()

    var weather = WeatherToday()
    var constellation = Constellation()

    val manager = supportFragmentManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        getJsonWeather()
        getJsonConstellationName()
        weather.connect()
        constellation.connect()
        initView()
        jsonBundle()
    }

//    inline fun <reified T> Gson.toJson(json: String) = this.toJson<T>(json, object : TypeToken<T>() {}.type)


    fun jsonBundle() {

        val transaction = manager.beginTransaction()

        transaction.add(0, fragmentWea).commit()
        val bundle = Bundle()
        val gson = Gson()

        weather.weatherList = {
            println("wwwwwwwwwwwww${it}")
            val json = gson.toJson(it)
            bundle.putString("weather", json)
            fragmentWea.arguments = bundle


            println("bbbbbbbbbbbbbbbbbbb$bundle")
        }


    }

    fun getJsonWeather() {

        weather.weatherList = {
            //            runOnUiThread {
//
//
//                when (it[0].Wx) {
//                    "晴時多雲" ->
//                        Glide.with(this).load(R.drawable.sun_cloudy).into(iv_Wx)
//                    "多雲" ->
//                        Glide.with(this).load(R.drawable.cloudys).into(iv_Wx)
//                    "多雲短暫陣雨" ->
//                        Glide.with(this).load(R.drawable.rain_cloudy).into(iv_Wx)
//                    "短暫陣雨" ->
//                        Glide.with(this).load(R.drawable.rain).into(iv_Wx)
//                    "陰" ->
//                        Glide.with(this).load(R.drawable.cloud).into(iv_Wx)
//                    "短暫陣雨或雷雨" ->
//                        Glide.with(this).load(R.drawable.rain_thunder).into(iv_Wx)
//
//                }
//
//
//                Glide.with(this).load(R.drawable.at).into(iv_AT)
//
//
//                when (it[0].CI) {
//                    "舒適" -> Glide.with(this).load(R.drawable.cool).into(iv_CI)
//                    "悶熱" -> Glide.with(this).load(R.drawable.hot).into(iv_CI)
//                    else -> Glide.with(this).load(R.drawable.fit).into(iv_CI)
//                }
//
//
//                Glide.with(this).load(R.drawable.pop).into(iv_PoP6h)
//
//
//                tv_cityName.text = it[0].locationName
//                tv_T.text = "${it[0].T}°C"
//                tv_Wx.text = it[0].Wx
//                tv_AT.text = "體感 ${it[0].AT}°C"
//                tv_PoP6h.text = "降雨量 ${it[0].PoP6h}%"
//                tv_CI.text = it[0].CI
//
//
//            }
        }


    }


    fun getJsonConstellationName() {
        constellation.constellationList = {
            runOnUiThread {
                //                tv_conName.text = it[0]

            }
        }


    }

    fun initView() {

        val tabAdapter = MyViewPagerAdapter(manager)
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
