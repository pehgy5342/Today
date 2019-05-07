package com.example.today.activity

import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.today.R
import com.example.today.adapter.LuckyAdapter
import com.example.today.mydata.ConstellationData
import kotlinx.android.synthetic.main.activity_constellation.*

class ConstellationActivity : AppCompatActivity() {

    lateinit var adapter: LuckyAdapter
    var starList = ArrayList<ConstellationData.Lucky>()

    //    var constellationList: ArrayList<ConstellationData.Data>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_constellation)


        val name = intent.getStringExtra("name")
        val starMoney = intent.getStringExtra("starMoney")
        val descMoney = intent.getStringExtra("descMoney")
        val starWork = intent.getStringExtra("starWork")
        val descWork = intent.getStringExtra("descWork")

        tb_title.title = name

        println("nnnnnnnnnnn$name")

        if (starMoney == null) {
            val builder = AlertDialog.Builder(this)
            val dialogView = layoutInflater.inflate(R.layout.custom_dialog, null)
            builder.setView(dialogView)
            builder.setCancelable(true)
            val dialog = builder.create()
            dialog.show()
            Handler().postDelayed({ dialog.dismiss() }, 6000)
            Glide.with(this).load(R.drawable.white_error).into(iv_toolbar)
            Toast.makeText(this, "資料錯誤", Toast.LENGTH_SHORT).show()

        } else {
            Glide.with(this).load(R.drawable.starts).into(iv_toolbar)
            starList.add(ConstellationData.Lucky(starMoney, descMoney))
            starList.add(ConstellationData.Lucky(starWork, descWork))
            updateView(starList)
        }

    }


    fun initView() {

        val view = layoutInflater.inflate(R.layout.loadging_item, null)
        adapter = LuckyAdapter(starList)
        rv_lucky.layoutManager = LinearLayoutManager(this)
        rv_lucky.adapter = adapter

//        val builder = AlertDialog.Builder(context!!)
//        val dialogView = layoutInflater.inflate(R.layout.custom_dialog, null)
//        builder.setView(dialogView)
//        builder.setCancelable(true)
//        val dialog = builder.create()
//        dialog.show()
//        Handler().postDelayed({ dialog.dismiss() }, 6000)

    }

    fun updateView(list: ArrayList<ConstellationData.Lucky>) {
        adapter = LuckyAdapter(list)
        rv_lucky.layoutManager = LinearLayoutManager(this)
        rv_lucky.adapter = adapter
        adapter.updateList(newList = starList)

    }
}





