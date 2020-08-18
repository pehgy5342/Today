package com.example.today.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.today.R
import com.example.today.adapter.NewsAdapter
import com.example.today.adapter.fragViewPagerAdapter
import com.example.today.fragment.FragmentConstellation
import com.example.today.fragment.FragmentDaily
import com.example.today.fragment.FragmentNews
import com.example.today.fragment.FragmentWeather
import com.example.today.mydata.NewsData
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_news.*
import kotlinx.android.synthetic.main.fragment_weather.*

class MainActivity : AppCompatActivity() {

    private var fragmentWea = FragmentWeather()
    private var fragmentCon = FragmentConstellation()
    private var fragmentDai = FragmentDaily()
    private var fragmentNews = FragmentNews()
    val manager = supportFragmentManager
    val transaction = manager.beginTransaction()

//    var weather = WeatherToday()
//    var constellation = Constellation()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        intent.getStringExtra("Hello")

        initView()
        getJsonWeather()


    }


    fun initView() {

        val tabAdapter = fragViewPagerAdapter(manager)


        tabAdapter.addFragment(fragmentWea, "天氣")
        tabAdapter.addFragment(fragmentNews, "新聞")
        tabAdapter.addFragment(fragmentDai, "記事")
        viewPager.offscreenPageLimit = 2
        viewPager.adapter = tabAdapter
        tabLayout.setupWithViewPager(viewPager)
        setTabIcon()


    }


    fun setTabIcon() {

        val tabIcon: IntArray = intArrayOf(
            R.drawable.sun,
            R.drawable.news,
            R.drawable.note
        )

        tabLayout.getTabAt(0)!!.setIcon(tabIcon[0])
        tabLayout.getTabAt(1)!!.setIcon(tabIcon[1])
        tabLayout.getTabAt(2)!!.setIcon(tabIcon[2])

    }

    fun getJsonWeather() {

        fragmentWea.locationWeatherList = {
            runOnUiThread {


//                when (it[0].Wx) {
//                    "晴" ->
//                        Glide.with(this).load(R.drawable.sun).into(iv_Wx)
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


                Glide.with(this).load(R.drawable.at).into(iv_AT)


//                when (it[0].CI) {
//                    "舒適" -> Glide.with(this).load(R.drawable.cool).into(iv_CI)
//                    "悶熱" -> Glide.with(this).load(R.drawable.hot).into(iv_CI)
//                    else -> Glide.with(this).load(R.drawable.fit).into(iv_CI)
//                }


                Glide.with(this).load(R.drawable.pop).into(iv_PoP6h)


                tv_cityName.text = it[0].locationName
                tv_T.text = "${it[0].T}°C"
                tv_Wx.text = it[0].Wx
                tv_AT.text = "體感 ${it[0].AT}°C"
                tv_PoP6h.text = "降雨量 ${it[0].PoP6h}%"
                tv_CI.text = it[0].CI


            }
        }


    }


//    fun getJsonNews() {
//        fragmentNews.todayNewsList = {
//
//            runOnUiThread {
//
//
//
//            }
//
//        }
//
//
//    }






}
