package com.example.today.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.today.R
import com.example.today.mydata.ConstellationData
import kotlinx.android.synthetic.main.constellation_home_item.view.*

class ConstellationAdapter :
    RecyclerView.Adapter<ConstellationAdapter.CustomHolder>() {

    var conList =
        arrayListOf(
            ConstellationData.Item(R.drawable.aquarius, "水瓶座"),
            ConstellationData.Item(R.drawable.pisces, "雙魚座"),
            ConstellationData.Item(R.drawable.aries, "牡羊座"),
            ConstellationData.Item(R.drawable.taurus, "金牛座"),
            ConstellationData.Item(R.drawable.gemini, "雙子座"),
            ConstellationData.Item(R.drawable.cancer, "巨蟹座"),
            ConstellationData.Item(R.drawable.leo, "獅子座"),
            ConstellationData.Item(R.drawable.virgo, "處女座"),
            ConstellationData.Item(R.drawable.libra, "天秤座"),
            ConstellationData.Item(R.drawable.scorpio, "天蠍座"),
            ConstellationData.Item(R.drawable.sagittarius, "射手座"),
            ConstellationData.Item(R.drawable.capricorn, "摩羯座")
        )

    interface OnItemClickListener {
        fun onItemClick(name: String)
    }

    var mOnItemClickListener: OnItemClickListener? = null

    fun setOnItemClickListener(mOnItemClickListener: OnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.constellation_home_item, parent, false)
        return CustomHolder(view)
    }

    override fun getItemCount(): Int = conList.size

    override fun onBindViewHolder(holder: CustomHolder, position: Int) {
        holder.bind(conList[position])
        holder.conItem.setOnClickListener {
            mOnItemClickListener?.onItemClick(conList[position].name)

        }

    }


    inner class CustomHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val conImage = itemView.iv_constellation
        val conTitle = itemView.tv_constellation
        val conItem = itemView.con_conItem

        fun bind(con: ConstellationData.Item) {

            Glide.with(itemView).load(con.image).into(conImage)
            conTitle.text = con.name


        }
    }


}