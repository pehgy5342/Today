package com.example.today.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.today.R

class WeatherAdapter : RecyclerView.Adapter<WeatherAdapter.CustomHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.weather_item, parent, false)
        return CustomHolder(view)
    }

    override fun getItemCount(): Int = 3


    override fun onBindViewHolder(holder: CustomHolder, position: Int) {

    }


    inner class CustomHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {



    }
}