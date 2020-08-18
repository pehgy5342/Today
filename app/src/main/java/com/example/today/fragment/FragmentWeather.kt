package com.example.today.fragment


import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.today.R
import com.example.today.mydata.API
import com.example.today.mydata.WeatherData
import com.example.today.okhttp.WeatherToday
import kotlinx.android.synthetic.main.fragment_weather.*
import kotlinx.android.synthetic.main.fragment_weather.view.*
import okhttp3.*
import org.json.JSONObject
import java.io.IOException
import java.util.*


class FragmentWeather : Fragment() {
    //    inline fun <reified T> Gson.fromJson(json: String) = this.fromJson<T>(json, object : TypeToken<T>() {}.type)
    lateinit var fragView: View
    var position = 0f
    var cityNameList = ArrayList<String>()
    var weatherList = ArrayList<WeatherData.Aweather>()
    var locationWeatherList: ((ArrayList<WeatherData.Aweather>) -> Unit)? = null



    //CharSequence與arraylist不同之處為她為固定資料，不可增加或移除
    val list: Array<CharSequence> = arrayOf(
        "基隆市",
        "新北市",
        "臺北市",
        "宜蘭縣",
        "桃園市",
        "新竹縣",
        "新竹市",
        "苗栗縣",
        "臺中市",
        "彰化縣",
        "南投縣",
        "雲林縣",
        "嘉義市",
        "嘉義縣",
        "臺南市",
        "高雄市",
        "屏東縣",
        "臺東縣",
        "花蓮縣",
        "澎湖縣",
        "金門縣",
        "連江縣"
    )

//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//        json()
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        connect()


    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        fragView = inflater.inflate(R.layout.fragment_weather, container, false)




        fragView.btn_searchCity.setOnClickListener {
            Log.i("wwww", "$weatherList")

            val builder = AlertDialog.Builder(fragView.context)
            builder.setTitle("請選擇縣市")
            builder.setIcon(R.drawable.taiwanflag2)

            builder.setSingleChoiceItems(list, 0) { dialogInterface: DialogInterface?, i: Int ->

                val selectedCityName = list[i]


                Log.i("wwww", "$weatherList")

                weatherList.forEach {

                    Log.i("111", "${it.locationName}")
                    Log.i("111", "$selectedCityName")

                    if (it.locationName == selectedCityName) {
                        Log.i("222", "==")
                        tv_cityName.text = it.locationName
                        tv_Wx.text = it.Wx
                        tv_AT.text = "體感 ${it.AT}°C"
                        tv_T.text = "${it.T}°C"
                        tv_CI.text = it.CI
                        tv_PoP6h.text = "降雨量 ${it.PoP6h}%"

                    }

//                    when (it.Wx) {
//
//                        "晴" ->
//                            Glide.with(this).load(R.drawable.sun).into(iv_Wx)
//                        "晴時多雲" ->
//                            Glide.with(this).load(R.drawable.sun_cloudy).into(iv_Wx)
//                        "多雲" ->
//                            Glide.with(this).load(R.drawable.cloudys).into(iv_Wx)
//                        "多雲短暫陣雨" ->
//                            Glide.with(this).load(R.drawable.rain_cloudy).into(iv_Wx)
//                        "短暫陣雨" ->
//                            Glide.with(this).load(R.drawable.rain).into(iv_Wx)
//                        "陰" ->
//                            Glide.with(this).load(R.drawable.cloud).into(iv_Wx)
//                        "短暫陣雨或雷雨" ->
//                            Glide.with(this).load(R.drawable.rain_thunder).into(iv_Wx)
//                    }


//                    when (it.CI) {
//                        "舒適" -> Glide.with(this).load(R.drawable.cool).into(iv_CI)
//                        "悶熱" -> Glide.with(this).load(R.drawable.hot).into(iv_CI)
//                        else -> Glide.with(this).load(R.drawable.fit).into(iv_CI)
//                    }

                }
            }
                .setPositiveButton("確定") { dialog, which ->

//                    val builder = AlertDialog.Builder(this@FragmentWeather.context!!)
//                    val dialogView = layoutInflater.inflate(R.layout.dialog_loading, null)
//                    builder.setView(dialogView)
//                    builder.setCancelable(true)
//                    val dialog = builder.create()
//                    dialog.show()

                }
                .setNegativeButton("取消") { dialog, which ->
                    //Toast.makeText(this, "已取消", Toast.LENGTH_SHORT).show()
                    val toast = Toast(fragView.context)
                    //畫面顯示位置
                    toast.setGravity(Gravity.BOTTOM, 0, 200)
                    toast.duration = Toast.LENGTH_SHORT
                    toast.view = layoutInflater.inflate(R.layout.toast_cancel, null)
                    toast.show()
                }
                .create().show()
        }

        return fragView
    }
//    tv_cityName.text = it[0].locationName
//    tv_T.text = "${it[0].T}°C"
//    tv_Wx.text = it[0].Wx
//    tv_AT.text = "體感 ${it[0].AT}°C"
//    tv_PoP6h.text = "降雨量 ${it[0].PoP6h}%"
//    tv_CI.text = it[0].CI


    fun connect() {
        val api = API()
        val url = api.WeatherAPI

        //OkHttpClient實例化
        val client = OkHttpClient()

        //向HTTP發請求
        val request = Request.Builder()
            .url(url)
            .build()

        //向服務端請求傳回資料(get同步方法)
//        val response = client.newCall(request).execute()


//        activity?.runOnUiThread { textView.text = responseStr }

        //使用get中異步方法，好處是不會影響到主線程
        client.newCall(request).enqueue(object : Callback {

            override fun onFailure(call: Call, e: IOException) {
                Log.d("msg from WeatherToday", "Fail from Weather")

            }

            override fun onResponse(call: Call, response: Response) {

                val responseList = ArrayList<JSONObject>()
                //取得回傳資料的內容，.string()是指並不是轉字串而是回傳response
                val responseStr = response.body()!!.string()

                val weatherResponse = JSONObject(responseStr)

                responseList.add(weatherResponse)

//                weatherResponseList?.invoke(responseList)

                println("11111111111$weatherResponse")

//                val c = WeatherExtraction().extraction(jaonArr)
                val records = weatherResponse.getJSONObject("records")
                val locations = records.getJSONArray("locations")
                val location = locations.getJSONObject(0).getJSONArray("location")

//                for (i in 0 until location.length()) {
//
//                    val locationName = location.getJSONObject(i).getString("locationName")
//
//                    cityNameList.add(locationName)
//
//                }
//
//                println("cccccccccccc$cityNameList")


                for (i in 0 until location.length()) {


                    val locationName = location.getJSONObject(i).getString("locationName")
//                    println("oooooooooooooo $locationName")

                    val Wx =
                        location.getJSONObject(i).getJSONArray("weatherElement").getJSONObject(0)
                            .getJSONArray("time")
                            .getJSONObject(0).getJSONArray("elementValue").getJSONObject(0)
                            .getString("value")

                    val AT =
                        location.getJSONObject(i).getJSONArray("weatherElement").getJSONObject(1)
                            .getJSONArray("time")
                            .getJSONObject(0).getJSONArray("elementValue").getJSONObject(0)
                            .getString("value")

                    val T =
                        location.getJSONObject(i).getJSONArray("weatherElement").getJSONObject(2)
                            .getJSONArray("time")
                            .getJSONObject(0).getJSONArray("elementValue").getJSONObject(0)
                            .getString("value")

                    val CI =
                        location.getJSONObject(i).getJSONArray("weatherElement").getJSONObject(3)
                            .getJSONArray("time")
                            .getJSONObject(0).getJSONArray("elementValue").getJSONObject(1)
                            .getString("value")

                    val PoP6h =
                        location.getJSONObject(i).getJSONArray("weatherElement").getJSONObject(4)
                            .getJSONArray("time")
                            .getJSONObject(0).getJSONArray("elementValue").getJSONObject(0)
                            .getString("value")


                    weatherList.add(WeatherData.Aweather(locationName, Wx, AT, T, CI, PoP6h))

                }

                locationWeatherList!!.invoke(weatherList)
                Log.i("WeatherData onconnect", "$weatherList")
                Log.i("WeatherData onconnect", "${this@FragmentWeather}")


            }

        })


    }


    fun json() {


        tv_cityName.text = weatherList[0].locationName
        tv_T.text = "${weatherList[0].Wx}°C"
        tv_Wx.text = weatherList[0].Wx
        tv_AT.text = "體感 ${weatherList[0].AT}°C"
        tv_PoP6h.text = "降雨量 ${weatherList[0].PoP6h}%"
        tv_CI.text = weatherList[0].CI

    }


//    fun game() {
//
//        Glide.with(this).load(R.drawable.ground).into(fragView.iv_bg)
//        Glide.with(this).load(R.drawable.doge).into(fragView.iv_game)
//        Glide.with(this).load(R.drawable.left).into(fragView.ibtn_left)
//        Glide.with(this).load(R.drawable.right).into(fragView.ibtn_right)
//
//        fragView.ibtn_right.setOnClickListener {
//
//
//            val animator = ValueAnimator.ofFloat(position, position+100f)
//            animator.addUpdateListener { animation ->
//                iv_game.translationX = animation.animatedValue as Float
//
//            }
//            animator.duration = 200
//            animator.start()
//
//        }
//
//        fragView.ibtn_left.setOnClickListener {
//
//
//            val animator = ValueAnimator.ofFloat(position, position-100f)
//            animator.addUpdateListener { animation ->
//                iv_game.translationX = animation.animatedValue as Float
//
//            }
//            animator.duration = 200
//            animator.start()
//
//        }
//    }


}



