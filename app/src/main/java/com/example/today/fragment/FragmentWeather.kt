package com.example.today.fragment


import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.today.R
import com.example.today.adapter.WeatherWeekAdapter
import com.example.today.mydata.WeatherData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.fragment_weather.*
import kotlinx.android.synthetic.main.fragment_weather.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
inline fun <reified T> Gson.fromJson(json: String) = this.fromJson<T>(json, object : TypeToken<T>() {}.type)

class FragmentWeather : Fragment() {
    lateinit var fragView: View
    var getJson: String? = null
    //CharSequence與arraylist不同之處為她為固定資料，不可增加或移除
    val list: Array<CharSequence> = arrayOf("基隆市", "新北市", "臺北市", "宜蘭縣", "桃園市", "新竹縣", "新竹市", "苗栗縣", "臺中市", "彰化縣", "南投縣", "雲林縣", "嘉義市", "嘉義縣", "臺南市", "高雄市", "屏東縣", "臺東縣", "花蓮縣", "澎湖縣", "金門縣", "連江縣")


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        fragView = inflater.inflate(R.layout.fragment_weather, container, false)


        arguments?.let {
            getJson = it.getString("weather")

        }
        val json = getJson
//        val data = Gson().fromJson(getJson,WeatherData.Aweather::class.java)

        println("777777777777777777$json")






        fragView.btn_searchCity.setOnClickListener {

            //            arguments?.let {
//                getJson = it.getString("weather") }
//            val weatherJson = getJson
//
//
//            println("777777777777777777$weatherJson")

            val builder = AlertDialog.Builder(fragView.context)
            builder.setTitle("請選擇縣市")
            builder.setIcon(R.drawable.taiwanflag2)

            builder.setSingleChoiceItems(list, 0) { dialogInterface: DialogInterface?, i: Int ->

                tv_cityName.text = list[i]


            }
                .setPositiveButton("確定") { dialog, which ->


                }
                .setNegativeButton("取消") { dialog, which ->
                    //Toast.makeText(this, "已取消", Toast.LENGTH_SHORT).show()
                    val toast = Toast(fragView.context)
                    //畫面顯示位置
                    toast.setGravity(Gravity.BOTTOM, 0, 200)
                    toast.duration = Toast.LENGTH_SHORT
                    toast.view = layoutInflater.inflate(R.layout.toast_cacel, null)
                    toast.show()
                }
                .create().show()
        }
        initView(fragView)
        return fragView
    }

    fun initView(view: View) {
        val weekAdapter = WeatherWeekAdapter()
        val recyclerView = view.findViewById<RecyclerView>(R.id.rv_weatherWeek)
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.adapter = weekAdapter

    }


}
