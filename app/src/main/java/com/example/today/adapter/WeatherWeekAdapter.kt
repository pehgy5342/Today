package com.example.today.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.today.R
import com.example.today.mydata.WeatherWeekData
import kotlinx.android.synthetic.main.weather_week_item.view.*

class WeatherWeekAdapter : RecyclerView.Adapter<WeatherWeekAdapter.CustomHolder>() {

//    val weatherWeekList  = arrayListOf(WeatherWeekData())

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.weather_item, parent, false)
        return CustomHolder(view)
    }

    override fun getItemCount(): Int = 7



    override fun onBindViewHolder(holder: CustomHolder, position: Int) {

//        holder.bind(weatherWeekList[position])

    }


    inner class CustomHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val tempUnit = itemView.tv_temp
        val popUnit = itemView.tv_PoP
//        val tempImg = itemView.iv_temp
//        val popImg = itemView.iv_PoP

        fun bind(wd: WeatherWeekData) {

            tempUnit.text = wd.temp
            popUnit.text = wd.PoP
//            Glide.with(itemView.context).load(R.drawable.temperature).into(tempImg)
//            Glide.with(itemView.context).load(R.drawable.humidity).into(popImg)


        }

    }
}