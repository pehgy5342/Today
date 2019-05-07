package com.example.today.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.today.R
import com.example.today.mydata.ConstellationData
import kotlinx.android.synthetic.main.constellation_item.view.*

class LuckyAdapter(var luckyList: ArrayList<ConstellationData.Lucky>) :
    RecyclerView.Adapter<LuckyAdapter.CustomHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.constellation_item, parent, false)
        return CustomHolder(view)
    }

    override fun getItemCount(): Int = luckyList.size

    override fun onBindViewHolder(holder: CustomHolder, position: Int) {

        holder.bind(luckyList[position])
    }


    inner class CustomHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val conStar = itemView.tv_conStar
        val conDesc = itemView.tv_conDesc


        fun bind(con: ConstellationData.Lucky) {

            conStar.text = con.star
            conDesc.text = con.desc


        }

    }


    fun updateList(newList: ArrayList<ConstellationData.Lucky>) {
        this.luckyList = newList
        this.notifyDataSetChanged()

    }
}