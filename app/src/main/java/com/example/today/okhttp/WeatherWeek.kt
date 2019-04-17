package com.example.today.okhttp

import android.util.Log
import com.example.today.mydata.API
import okhttp3.*
import java.io.IOException

class WeatherWeek {


    fun connect() {
        val api = API()

        val url = api.WeatherWeekAPI

        val client = OkHttpClient()

        val request = Request.Builder().url(url).build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {

                Log.i("msg from WeatherWeek", "Fail from WeatherWeek")
            }

            override fun onResponse(call: Call, response: Response) {

                val responseStr = response.body()!!.string()
//
//                val myResponse =

            }


        })
    }

}