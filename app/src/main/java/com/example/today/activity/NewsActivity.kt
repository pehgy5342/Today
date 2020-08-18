package com.example.today.activity

import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.example.today.R
import com.example.today.mydata.NewsData
import kotlinx.android.synthetic.main.activity_news.*


class NewsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)


        val newsWebView = findViewById<WebView>(R.id.wv_news)

        //接收資訊
        val list = intent.getSerializableExtra("url") as NewsData.Anews


        //
        newsWebView.settings.javaScriptEnabled = true
        //不調用瀏覽器
        newsWebView.webViewClient = WebViewClient()
        newsWebView.loadUrl("${list.url}")

    }
}