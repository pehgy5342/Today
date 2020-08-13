package com.example.today.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.example.today.R
import com.example.today.adapter.ConViewPagerAdapter
import com.example.today.adapter.LuckyAdapter
import com.example.today.mydata.ConstellationData
import kotlinx.android.synthetic.main.activity_constellation.*

class ConstellationActivity : AppCompatActivity() {

    lateinit var adapter: LuckyAdapter
    lateinit var pagerAdapter: ConViewPagerAdapter
    var starList = ArrayList<ConstellationData.Lucky>()


    //    var constellationList: ArrayList<ConstellationData.Constellation>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_constellation)

        ibtn_back.setOnClickListener {
            finish()
        }

        vp_con.addOnPageChangeListener(object :ViewPager.OnPageChangeListener {

            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {


            }

            override fun onPageSelected(position: Int) {


            }
        })

//        val name = intent.getStringExtra("name")
//        val starMoney = intent.getStringExtra("starMoney")
//        val descMoney = intent.getStringExtra("descMoney")
//        val starWork = intent.getStringExtra("starWork")
//        val descWork = intent.getStringExtra("descWork")

//        tb_title.title = name

//        println("nnnnnnnnnnn$name")

//        if (starMoney == null) {
//            val builder = AlertDialog.Builder(this)
//            val dialogView = layoutInflater.inflate(R.layout.dialog_loading, null)
//            builder.setView(dialogView)
//            builder.setCancelable(true)
//            val dialog = builder.create()
//            dialog.show()
//            Handler().postDelayed({ dialog.dismiss() }, 6000)
//            Glide.with(this).load(R.drawable.white_error).into(iv_toolbar)
//            Toast.makeText(this, "資料錯誤", Toast.LENGTH_SHORT).show()
//
//        } else {
//            Glide.with(this).load(R.drawable.starts).into(iv_toolbar)
//            starList.add(ConstellationData.Lucky(starMoney, descMoney))
//            starList.add(ConstellationData.Lucky(starWork, descWork))
//            updateView(starList)
//        }
//        var list : ConstellationData.Constellation? = null
//        list = intent.getSerializableExtra("object") as ConstellationData.Constellation

        val list = intent.getSerializableExtra("object") as ConstellationData.Constellation


        println("kkkkkkkkkkkkkk$list")

//        tb_title.title = list.name
        val starTitle = list.name
        val title = starTitle.substring(5, 8)

        tv_title.text = title

        val starMoney = list.STAR_MONEY
        val money = starMoney.substring(0, 9)

        val starWork = list.STAR_WORK
        val work = starWork.substring(0, 9)

        val starLove = list.STAR_LOVE
        val love = starLove.substring(0, 9)

        val starEntirety = list.STAR_ENTIRETY
        val entirety = starEntirety.substring(0, 9)

//        Glide.with(this).load(R.drawable.star).into(iv_toolbar)

        Glide.with(this).load(R.drawable.star).into(iv_bg)

        starList.add(ConstellationData.Lucky(money, list.DESC_MONEY))
        starList.add(ConstellationData.Lucky(work, list.DESC_WORK))
        starList.add(ConstellationData.Lucky(love, list.DESC_LOVE))
        starList.add(ConstellationData.Lucky(entirety, list.DESC_ENTIRETY))

//        updateView(starList)

        updateView(starList)

        tv_word.text = "${list.TODAY_WORD}"
        tv_num.text = "幸運數字 : ${list.LUCKY_NUMERAL}"
        tv_color.text = "幸運顏色 : ${list.LUCKY_COLOR}"
        tv_astro.text = "配對星座 : ${list.LUCKY_ASTRO}"
        tv_direction.text = "幸運方位 : ${list.LUCKY_DIRECTION}"
        tv_time.text = "良辰吉時 : ${list.LUCKY_TIME}"





    }


    fun initView() {
//
//        val view = layoutInflater.inflate(R.layout.progress_item, null)
//        adapter = LuckyAdapter(starList)
//        rv_lucky.layoutManager = LinearLayoutManager(this)
//        rv_lucky.adapter = adapter

//        val builder = AlertDialog.Builder(context!!)
//        val dialogView = layoutInflater.inflate(R.layout.dialog_loading, null)
//        builder.setView(dialogView)
//        builder.setCancelable(true)
//        val dialog = builder.create()
//        dialog.show()
//        Handler().postDelayed({ dialog.dismiss() }, 6000)

    }


    fun updateView(list: ArrayList<ConstellationData.Lucky>) {
        pagerAdapter = ConViewPagerAdapter(list)
        vp_con.adapter = pagerAdapter
        vp_con.setPageTransformer(true, OutPageTransformer())

    }



}





