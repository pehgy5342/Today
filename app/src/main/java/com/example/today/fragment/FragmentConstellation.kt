package com.example.today.fragment


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.today.R
import com.example.today.activity.ConstellationActivity
import com.example.today.adapter.ConstellationAdapter
import com.example.today.mydata.API
import com.example.today.mydata.ConstellationData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_constellation.*
import okhttp3.*
import org.json.JSONArray
import java.io.IOException


class FragmentConstellation : Fragment() {

    inline fun <reified T> Gson.fromJson(json: String) = this.fromJson<T>(json, object : TypeToken<T>() {}.type)

    //    var constellationList: ((ArrayList<String>) -> Unit)? = null
    var constellationList = ArrayList<ConstellationData.Data>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val frgView = inflater.inflate(R.layout.fragment_constellation, container, false)

        conView(frgView)
        return frgView
    }


    fun conView(view: View) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.rv_constellation)
        val adapter = ConstellationAdapter()
        val intent = Intent(this@FragmentConstellation.context, ConstellationActivity::class.java)
        adapter.setOnItemClickListener(object : ConstellationAdapter.OnItemClickListener {
            override fun onItemClick(name: String) {


                println("987654${constellationList}")

                constellationList.forEach {
                    println("987654${constellationList}")
                    Log.i("iiiiiii","$name..${it.name}")
                    if (it.name.contains(name)) {
                        println("DDDDDDDDD${it.DESC_WORK}")
                        println("iiiiiiiiiiiiiii${it.name}")
                        intent.putExtra("name", it.name)

                        intent.putExtra("starMoney", it.STAR_MONEY)
                        intent.putExtra("descMoney", it.DESC_MONEY)

                    }

                }
                startActivity(intent)
            }
        })



        recyclerView.layoutManager = GridLayoutManager(context, 3) as RecyclerView.LayoutManager
        recyclerView.adapter = adapter
    }


    fun connect() {
        val api = API()
        val url = api.ConstellationAPI

        val client = OkHttpClient()

        val request = Request.Builder()
            .url(url)
            .build()



        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {

                Log.d("msg from Constellation", "Fail from Constellation")

            }

            override fun onResponse(call: Call, response: Response) {


                val responseStr = response.body()!!.string()
                println("6666666666666666$responseStr")
                var myResponse = JSONArray(responseStr)
                println("mmmmmmmmmmmmmmmmmmmmmm$myResponse")

                constellationList = Gson().fromJson<ArrayList<ConstellationData.Data>>(responseStr)
                println("5555555$constellationList")

//                for (i in 0 until dataList.size){
//
//
//                    constellationList.add(ConstellationData.Data())
//                }


//                for (i in 0 until myResponse.length()) {
//                    val name = myResponse.getJSONObject(i).getString("name")
//                    val todayWord = myResponse.getJSONObject(i).getString("TODAY_WORD")
//                    val luckyNumeral = myResponse.getJSONObject(i).getString("LUCKY_NUMERAL")
//                    val luckyColor = myResponse.getJSONObject(i).getString("LUCKY_COLOR")
//
//                    println("nnnnnnnnnnn$name")
//
//                    constellationList.add(ConstellationData.Data(name, todayWord, luckyNumeral,luckyColor))
//                }
//                println("cccccccc$constellationList")

//                constellationList!!.invoke(constellationNameList)
            }


        })


    }

}
