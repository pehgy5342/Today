package com.example.today.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.today.R
import com.example.today.mydata.NewsData
import kotlinx.android.synthetic.main.news_item.view.*

class NewsAdapter(
    var newsList: ArrayList<NewsData.Anews>
) : RecyclerView.Adapter<NewsAdapter.CustomHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_item, null)
        return CustomHolder(view)

    }

    override fun getItemCount(): Int = newsList.size

    override fun onBindViewHolder(holder: CustomHolder, position: Int) {
        holder.bind(newsList[position])
    }

    inner class CustomHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val name = itemView.tv_newsName
        val title = itemView.tv_newsTitle
        val time = itemView.tv_newTime
        val image = itemView.iv_newsImage
//        val desc = itemView.tv_newsDesc
        val author = itemView.tv_newsAuthor


        fun bind(news: NewsData.Anews) {
            name.text = news.name
            title.text = news.title
            time.text = news.time
            Glide.with(itemView).load(news.image).into(image)
//            desc.text = news.description
            author.text = news.author


        }


    }


}