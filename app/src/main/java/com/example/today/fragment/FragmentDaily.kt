package com.example.today.fragment


import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.today.R
import com.example.today.adapter.DailyAdapter
import com.example.today.mydata.DailyData
import kotlinx.android.synthetic.main.dialog_daily.*
import kotlinx.android.synthetic.main.dialog_daily.view.*
import kotlinx.android.synthetic.main.fragment_daily.*
import kotlinx.android.synthetic.main.fragment_daily.view.*
import kotlinx.android.synthetic.main.fragment_daily.view.rv_daily


class FragmentDaily : Fragment() {

    var textList = ArrayList<DailyData.Daily>()
    var dailyAdapter = DailyAdapter(textList)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val fragView = inflater.inflate(R.layout.fragment_daily, container, false)
//        dailyView(fragView)


        fragView.btn_write.setOnClickListener {
            val builder = AlertDialog.Builder(this.context)
            val dialogView = layoutInflater.inflate(R.layout.dialog_daily, null)
            builder.setView(dialogView)

                .setTitle("請輸入文字")
                .setPositiveButton("確定") { _, _ ->
                    var et_input = dialogView.et_content.text.toString()

                    if (et_input.length > 0) {


                        textList.addAll(dailyAdapter.list)

                    }



                }
                .setNegativeButton("取消") { _, _ ->

                    Toast.makeText(this.context, "已取消", Toast.LENGTH_SHORT).show()
                }

            val dialog = builder.create()
            dialog.show()


        }


        initView(fragView)


        return fragView
    }


    fun initView(view: View) {

        val recyclerView = view.findViewById<RecyclerView>(R.id.rv_daily)
        recyclerView.adapter = dailyAdapter
        recyclerView.layoutManager = LinearLayoutManager(this.context)

    }


}
