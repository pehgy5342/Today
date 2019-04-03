package com.example.today

import android.util.Log
import com.example.today.mydata.API

import com.example.today.mydata.WeatherData
import com.google.gson.Gson
import okhttp3.*
import org.json.JSONObject
import java.io.IOException


class OKhttp {
    //inline 多次調用時可用(目前還沒更好的解釋)，reified具體化T參數，
//    inline fun <reified T> Gson.fromJson(json: String) = this.fromJson<T>(json, object : TypeToken<T>() {}.type)

    lateinit var dataList: (ArrayList<String>) -> Unit
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
                Log.d("msg", "Fail")
            }

            override fun onResponse(call: Call, response: Response) {

                Log.d("msg", "success")

                //取得回傳資料的內容，.string()是指並不是轉字串而是回傳response
                val responseStr = response.body()!!.string()

                var myResponse = JSONObject(responseStr)

                var records = myResponse.getJSONObject("records")
                var location = records.getJSONArray("location")


                Log.i("45454", "$location")


                var locationNameList = ArrayList<String>()
                for (i in 0 until location.length()) {
                    var locationName = location.getJSONObject(i).getString("locationName")
                    println("***************** $locationName")
                    var weatherElement = location.getJSONObject(i).getJSONArray("weatherElement")
                    println("***************** $weatherElement")


                    locationNameList.add(locationName)

                }
                println("***************** $locationNameList")


                dataList.invoke(locationNameList)


                var value = ""
                var unit = ""

                try {

//                        value = location.getJSONObject(i).getString("parameterValue")

                } catch (e: Exception) {
                    try {
//                            unit = location.getJSONObject(i).getString("parameterValue")

                    } catch (e: Exception) {
                    }
                }

//                val data = Gson().fromJson<ArrayList<WeatherData>>(responseStr)

//                val ooList: (WeatherData) -> Unit
                val data = Gson().fromJson(responseStr, WeatherData::class.java)
                var cityList = arrayOfNulls<String>(data.records.location.size)


                Log.i("6666666", "$cityList")
                for (i in 0 until data.records.location.size) {
                    cityList[i] = "\n縣市:${data.records.location[i].locationName}"


                    Log.i("888888888", "${cityList[i]}")

                }
                Log.i("888888888", cityList.toString())

            }

        })


    }



}