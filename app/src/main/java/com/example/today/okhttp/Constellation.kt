package com.example.today.okhttp

import android.util.Log
import com.example.today.mydata.API
import com.example.today.mydata.ConstellationData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.*
import org.json.JSONArray
import java.io.IOException

class Constellation {

    inline fun <reified T> Gson.fromJson(json: String) = this.fromJson<T>(json, object : TypeToken<T>() {}.type)
    fun connect() {
        val api = API()
        val url = api.ConstellationAPI

        val client = OkHttpClient()

        val request = Request.Builder()
            .url(url)
            .build()



        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {

                Log.d("msg", "Fail")

            }

            override fun onResponse(call: Call, response: Response) {

                Log.d("msg", "success")

                val responseStr = response.body()!!.string()


//                var myResponse = JSONArray(responseStr)

//                println("5555555$myResponse")
                val mydata = Gson().fromJson<ArrayList<ConstellationData>>(responseStr)

           println("5555555$mydata")

//                println("11111111111$mydata")

            }


        })


    }

}