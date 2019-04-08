package com.example.today.okhttp

import android.util.Log
import com.example.today.mydata.API
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.*
import org.json.JSONArray
import java.io.IOException

class Constellation {

     var constellationList:((ArrayList<String>) -> Unit)?  = null
    //inline 多次調用時可用(目前還沒更好的解釋)，reified具體化T參數，
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

                var myResponse = JSONArray(responseStr)


//                val data = Gson().fromJson<ArrayList<ConstellationData>>(responseStr)
//                println("5555555$data")

                var constellationNameList = ArrayList<String>()
                for (i in 0 until myResponse.length()) {
                    val name = myResponse.getJSONObject(i).getString("name")

                    println("nnnnnnnnnnn$name")
                    constellationNameList.add(name.toString())
                }
                println("cccccccc$constellationNameList")

                constellationList!!.invoke(constellationNameList)
            }


        })


    }

}