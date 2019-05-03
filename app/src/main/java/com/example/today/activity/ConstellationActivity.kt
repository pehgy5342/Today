package com.example.today.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.today.R
import com.example.today.mydata.ConstellationData
import kotlinx.android.synthetic.main.activity_constellation.*
import kotlinx.android.synthetic.main.constellation_item.*

class ConstellationActivity : AppCompatActivity() {

    var constellationList: ArrayList<ConstellationData.Data>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_constellation)


        val name = intent.getStringExtra("name")
        val starMoney = intent.getStringExtra("starMoney")
        val descMoney = intent.getStringExtra("descMoney")
        println("nnnnnnnnnnn$name")
        tb_title.title = name
        tv_conLucky.text = starMoney
        tv_conInfo.text = descMoney


    }
}
