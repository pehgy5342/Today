package com.example.today.mydata

import androidx.annotation.NonNull
import androidx.appcompat.widget.DialogTitle
import java.security.CodeSource
import javax.xml.transform.Source

class NewsData {
    data class News(
        val status: String,
        val totoalResult: Int,
        val articles: List<ArticlesBean>


    ) {

        data class ArticlesBean(
            val source: SourceBean,
            val author: String,
            val title: String,
            val urlToImage: String,
            val description: String,
            val url: String,

            val publishedAt: String,
            var content: String? = null

        ) {
            data class SourceBean(
                var id: String? = null,
                val name: String
            )

        }


    }


    data class Anews(
        var name: String?,
        var author: String,
        var title: String,
//        var description: String,
//        var url: String,
        var image: String,
        var time: String

    ) {}


}