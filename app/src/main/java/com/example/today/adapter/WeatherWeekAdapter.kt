package com.example.today.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.today.R
import com.example.today.mydata.WeatherData
import kotlinx.android.synthetic.main.weather_week_item.view.*

class WeatherWeekAdapter : RecyclerView.Adapter<WeatherWeekAdapter.CustomHolder>() {

    val weatherWeekList = arrayListOf(
        WeatherData.week("4/11", "30°C", "10%"),
        WeatherData.week("4/12", "30°C", "10%"),
        WeatherData.week("4/13", "30°C", "10%"),
        WeatherData.week("4/14", "30°C", "10%"),
        WeatherData.week("4/15", "30°C", "10%"),
        WeatherData.week("4/16", "30°C", "10%"),
        WeatherData.week("4/17", "30°C", "10%")
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.weather_week_item, parent, false)
        return CustomHolder(view)
    }

    override fun getItemCount(): Int = weatherWeekList.size


    override fun onBindViewHolder(holder: CustomHolder, position: Int) {

        holder.bind(weatherWeekList[position])

    }


    inner class CustomHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        val date = itemView.tv_date
        val tempUnit = itemView.tv_temp
        val popUnit = itemView.tv_PoP
//        val tempImg = itemView.iv_temp
//        val popImg = itemView.iv_PoP

        fun bind(wd:WeatherData.week) {

//            date.text = wd.myDate

//            tempUnit.text = wd.temp
//            popUnit.text = wd.PoP
//            Glide.with(itemView.context).load(R.drawable.temperature).into(tempImg)
//            Glide.with(itemView.context).load(R.drawable.humidity).into(popImg)


        }

    }
}