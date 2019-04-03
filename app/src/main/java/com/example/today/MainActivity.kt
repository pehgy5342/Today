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
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    //    private var fragmentSun = FragmentSunday()
//    private var fragmentMon = FragmentMonday()
//    private var fragmentTue = FragmentTuesday()
//    private var fragmentWed = FragmentWednesday()
//    private var fragmentThu = FragmentThursday()
//    private var fragmentFri = FragmentFriday()
//    private var fragmentSat = FragmentSaturday()
    private var fragmentWea = FragmentWeather()
    private var fragmentCon = FragmentConstellation()
    private var fragmentEar = FragmentEarthquake()

    var weatherDataList = ArrayList<WeatherData>()
    var okhttp = OKhttp()
    var position = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_taiwan.setOnClickListener {

            AlertDialog.Builder(this)
                .setTitle("請選擇縣市")
                .setIcon(R.drawable.taiwanflag2)

                .setSingleChoiceItems(R.array.cityNames, 0) { dialogInterface: DialogInterface?, i: Int ->
                    position = i


                }

                .setPositiveButton("確定") { dialog, which ->

                }
                .setNegativeButton("取消") { dialog, which ->
                    //Toast.makeText(this, "已取消", Toast.LENGTH_SHORT).show()
                    val toast = Toast(this)
                    //畫面顯示位置
                    toast.setGravity(Gravity.BOTTOM, 0, 200)
                    toast.duration = Toast.LENGTH_SHORT
                    toast.view = layoutInflater.inflate(R.layout.toast_cacel, null)
                    toast.show()
                }
                .create().show()


        }

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
//        tabAdapter.addFragment(fragmentSun, "星期日")
//        tabAdapter.addFragment(fragmentMon, "星期一")
//        tabAdapter.addFragment(fragmentTue, "星期二")
//        tabAdapter.addFragment(fragmentWed, "星期三")
//        tabAdapter.addFragment(fragmentThu, "星期四")
//        tabAdapter.addFragment(fragmentFri, "星期五")
//        tabAdapter.addFragment(fragmentSat, "星期六")

        viewPager.adapter = tabAdapter
        tabLayout.setupWithViewPager(viewPager)
        setTabIcon()

    }


    fun setTabIcon() {

        val tabIcon: IntArray = intArrayOf(
            R.drawable.sun,
            R.drawable.planets,
            R.drawable.earth

//            R.drawable.ic_sunday_daily_calendar_page,
//            R.drawable.ic_monday_calendar_page,
//            R.drawable.ic_tuesday_daily_calendar_page,
//            R.drawable.ic_wednesday_calendar_daily_page,
//            R.drawable.ic_thursday_calendar_daily_page_interface_symbol,
//            R.drawable.ic_friday_daily_calendar_page,
//            R.drawable.ic_saturday_calendar_daily_page_interface_symbol

        )

        tabLayout.getTabAt(0)!!.setIcon(tabIcon[0])
        tabLayout.getTabAt(1)!!.setIcon(tabIcon[1])
        tabLayout.getTabAt(2)!!.setIcon(tabIcon[2])
//        tabLayout.getTabAt(0)!!.setIcon(tabIcon[0])
//        tabLayout.getTabAt(1)!!.setIcon(tabIcon[1])
//        tabLayout.getTabAt(2)!!.setIcon(tabIcon[2])
//        tabLayout.getTabAt(3)!!.setIcon(tabIcon[3])
//        tabLayout.getTabAt(4)!!.setIcon(tabIcon[4])
//        tabLayout.getTabAt(5)!!.setIcon(tabIcon[5])
//        tabLayout.getTabAt(6)!!.setIcon(tabIcon[6])

    }
}
