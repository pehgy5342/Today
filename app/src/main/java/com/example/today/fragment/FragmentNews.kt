package com.example.today.fragment

import android.content.Intent
import android.icu.text.CaseMap
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.today.R
import com.example.today.activity.NewsActivity
import com.example.today.adapter.NewsAdapter
import com.example.today.mydata.API
import com.example.today.mydata.NewsData
import kotlinx.android.synthetic.main.fragment_news.*
import okhttp3.*
import org.json.JSONObject
import org.w3c.dom.Text
import java.io.IOException


class FragmentNews : Fragment() {

    var newsList = ArrayList<NewsData.Anews>()
//    var todayNewsList: ((ArrayList<NewsData.Anews>) -> Unit)? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        connect()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val frgView = inflater.inflate(R.layout.fragment_news, container, false)
        conView(frgView)
        return frgView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        connect()
        sr_news.setOnRefreshListener(listen)
    }


    fun connect() {
        val api = API()
        val url = api.NewsAPI

        val client = OkHttpClient()

        val request = Request.Builder()
            .url(url)
            .build()


        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {

            }

            override fun onResponse(call: Call, response: Response) {
                val responseList = ArrayList<JSONObject>()

                val responseStr = response.body()!!.string()

                val newsResponse = JSONObject(responseStr)

                responseList.add(newsResponse)

                val articles = newsResponse.getJSONArray("articles")

                for (i in 0 until articles.length()) {


                    val name = articles.getJSONObject(i).getJSONObject("source").getString("name")


                    val author = articles.getJSONObject(i).getString("author")
                    val title = articles.getJSONObject(i).getString("title")
                    val url = articles.getJSONObject(i).getString("url")
                    val image = articles.getJSONObject(i).getString("urlToImage")
                    val time = articles.getJSONObject(i).getString("publishedAt")

                    newsList.add(NewsData.Anews(name, author, title, url, image, time))


                }
//                todayNewsList!!.invoke(newsList)
                Log.i("NewsData", "$newsList")
            }
        })


    }

    fun conView(view: View) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.rv_news)
        val newsAdapter = NewsAdapter(newsList)

        val intent = Intent(this@FragmentNews.context, NewsActivity::class.java)

        newsAdapter.setItemClickListener(object : NewsAdapter.onItemClickListener {
            override fun onItemClick(title: String) {
                if (newsList.isEmpty()) {
                    Toast.makeText(context, "目前無資料", Toast.LENGTH_SHORT).show()
                } else {

                    newsList.forEach {

                        if (it.title.contains(title)) {
                            intent.putExtra("url", it)

                        }


                    }

                    startActivity(intent)

                }
            }
        })
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        recyclerView.adapter = newsAdapter


    }

    val listen = SwipeRefreshLayout.OnRefreshListener {

        val newsAdapter = NewsAdapter(newsList)
        newsAdapter.updateList(newsList)
        sr_news.isRefreshing = false
    }


}