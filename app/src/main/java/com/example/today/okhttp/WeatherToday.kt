package com.example.today.okhttp

import android.util.Log
import com.example.today.mydata.API
import com.example.today.mydata.WeatherData

import okhttp3.*
import org.json.JSONObject
import java.io.IOException
import java.util.*


class WeatherToday {

//    inline fun <reified T> Gson.fromJson(json: String) = this.fromJson<T>(json, object : TypeToken<T>() {}.type)

    var weatherList: ((ArrayList<WeatherData.Aweather>) -> Unit)? = null
    var weatherElementList: ((ArrayList<WeatherData.Aweather>) -> Unit)? = null
    var weatherResponseList: ((ArrayList<JSONObject>) -> Unit)? = null
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
                Log.d("msg from WeatherToday", "Fail from ")
            }

            override fun onResponse(call: Call, response: Response) {

                val responseList = ArrayList<JSONObject>()
                //取得回傳資料的內容，.string()是指並不是轉字串而是回傳response
                val responseStr = response.body()!!.string()

                val myResponse = JSONObject(responseStr)

                responseList.add(myResponse)

                weatherResponseList?.invoke(responseList)

                println("11111111111$myResponse")

//val c = WeatherExtraction().extraction(jaonArr)
                val records = myResponse.getJSONObject("records")
                val locations = records.getJSONArray("locations")
                val location = locations.getJSONObject(0).getJSONArray("location")
//                val weatherElement = location.getJSONObject(0).getJSONArray("weatherElement")
//
//                val elementList = ArrayList<WeatherData.Aweather>()
//
//                val locationName = location.getJSONObject(0).getString("locationName")
//
//
//                val Wx =
//                    weatherElement.getJSONObject(0).getJSONArray("time").getJSONObject(0).getJSONArray("elementValue")
//                        .getJSONObject(0).getString("value")
//
//                val AT =
//                    weatherElement.getJSONObject(1).getJSONArray("time").getJSONObject(0).getJSONArray("elementValue")
//                        .getJSONObject(0).getString("value")
//                println("aaaaaaaaaaa$AT")
//
//                val T =
//                    weatherElement.getJSONObject(2).getJSONArray("time").getJSONObject(0).getJSONArray("elementValue")
//                        .getJSONObject(0).getString("value")
//
//                val CI =
//                    weatherElement.getJSONObject(3).getJSONArray("time").getJSONObject(0).getJSONArray("elementValue")
//                        .getJSONObject(1).getString("value")
//
//                val PoP6h =
//                    weatherElement.getJSONObject(4).getJSONArray("time").getJSONObject(0).getJSONArray("elementValue")
//                        .getJSONObject(0).getString("value")


//                elementList.add(WeatherData.Aweather(locationName, Wx, AT, T, CI, PoP6h))
//
//                weatherElementList!!.invoke(elementList)
//
//                println("eeeeeee$elementList")


                val locationNameList = ArrayList<WeatherData.Aweather>()
                for (i in 0 until location.length()) {
//                    var locationName = location.getJSONObject(i).getString("locationName")

                    val locationName = location.getJSONObject(i).getString("locationName")
                    println("oooooooooooooo $locationName")

                    val Wx =
                        location.getJSONObject(i).getJSONArray("weatherElement").getJSONObject(0).getJSONArray("time")
                            .getJSONObject(0).getJSONArray("elementValue").getJSONObject(0).getString("value")

                    val AT =
                        location.getJSONObject(i).getJSONArray("weatherElement").getJSONObject(1).getJSONArray("time")
                            .getJSONObject(0).getJSONArray("elementValue").getJSONObject(0).getString("value")

                    val T =
                        location.getJSONObject(i).getJSONArray("weatherElement").getJSONObject(2).getJSONArray("time")
                            .getJSONObject(0).getJSONArray("elementValue").getJSONObject(0).getString("value")

                    val CI =
                        location.getJSONObject(i).getJSONArray("weatherElement").getJSONObject(3).getJSONArray("time")
                            .getJSONObject(0).getJSONArray("elementValue").getJSONObject(1).getString("value")

                    val PoP6h =
                        location.getJSONObject(i).getJSONArray("weatherElement").getJSONObject(4).getJSONArray("time")
                            .getJSONObject(0).getJSONArray("elementValue").getJSONObject(0).getString("value")


                    locationNameList.add(WeatherData.Aweather(locationName, Wx, AT, T, CI, PoP6h))



                }

                println("***************** $locationNameList")

                weatherList!!.invoke(locationNameList)


            }

        })


    }


}

//                var value = ""
//                var unit = ""
//                try {
//                        value = location.getJSONObject(i).getString("parameterValue")
//                } catch (e: Exception) {
//                    try {
//                            unit = location.getJSONObject(i).getString("parameterValue")
//                    } catch (e: Exception) {
//                    }
//                }


//                val data = Gson().fromJson(responseStr, WeatherData::class.java)
//                var cityList = arrayOfNulls<String>(data.records.location.size)
//
//
//                Log.i("6666666", "$cityList")
//                for (i in 0 until data.records.location.size) {
//                    cityList[i] = "\n縣市:${data.records.location[i].locationName}"
//                    Log.i("888888888", "${cityList[i]}")
//                }
//                Log.i("888888888", cityList.toString())
