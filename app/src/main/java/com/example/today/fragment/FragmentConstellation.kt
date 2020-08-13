package com.example.today.fragment


import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
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
import okhttp3.*
import org.json.JSONArray
import java.io.IOException


class FragmentConstellation : Fragment() {

    inline fun <reified T> Gson.fromJson(json: String) = this.fromJson<T>(json, object : TypeToken<T>() {}.type)

    //    var constellationList: ((ArrayList<String>) -> Unit)? = null
    var constellationList = ArrayList<ConstellationData.Constellation>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        connect()
    }

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


                if (constellationList.isEmpty()) {
                    Toast.makeText(context, "目前無資料", Toast.LENGTH_SHORT).show()
//                    val builder = AlertDialog.Builder(this@FragmentConstellation.context)
//                    val dialogView = layoutInflater.inflate(R.layout.progress_item, null)
//                    val msg = dialogView.findViewById<TextView>(R.id.tv_msg)
//                    msg.text = "資料讀取中，請稍後．．．"
//                    builder.setView(dialogView)
//                    builder.setCancelable(true)
//                    val dialog = builder.create()
//                    dialog.show()



                } else {

                    constellationList.forEach {


                        println("987654${constellationList}")
                        Log.i("iiiiiii", "$name..${it.name}")

                        if (it.name.contains(name)) {

                            println("iiiiiiiiiiiiiii${it.name}")


                            //使用Serializable自訂義類型的object整個intent
                            intent.putExtra("object", it)

                        }

                    }
                    startActivity(intent)


                }


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
//                var myResponse = JSONArray(responseStr)
//                println("mmmmmmmmmmmmmmmmmmmmmm$myResponse")

                constellationList = Gson().fromJson<ArrayList<ConstellationData.Constellation>>(responseStr)
                println("5555555$constellationList")


            }


        })


    }

}
