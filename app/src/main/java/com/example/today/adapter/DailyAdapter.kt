package com.example.today.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.today.R
import com.example.today.mydata.DailyData
import kotlinx.android.synthetic.main.dialog_daily.view.*

class DailyAdapter(var list: ArrayList<DailyData.Daily>) : RecyclerView.Adapter<DailyAdapter.CustomHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.dialog_daily, parent, false)
        return CustomHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: CustomHolder, position: Int) {

        holder.bind(list[position])

    }

    inner class CustomHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var title = itemView.et_title.text.toString()
        var content = itemView.et_content.text.toString()


        fun bind(daily: DailyData.Daily) {

            title = daily.title
            content = daily.content

        }
    }
}