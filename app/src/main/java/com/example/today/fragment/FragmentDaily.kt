package com.example.today.fragment


import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.today.R
import com.example.today.mydata.DailyData
import kotlinx.android.synthetic.main.fragment_daily.view.*


class FragmentDaily : Fragment() {

    var textList = ArrayList<DailyData.Daily>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        val fragView = inflater.inflate(R.layout.fragment_daily, container, false)
//        dailyView(fragView)

        fragView.btn_write.setOnClickListener {
            val builder = AlertDialog.Builder(this.context)
            val dialogView = layoutInflater.inflate(R.layout.dialog_daily, null)
            builder.setView(dialogView)

                .setTitle("請輸入文字")
                .setPositiveButton("確定") { _, _ -> }
                .setNegativeButton("取消") { _, _ ->

                    Toast.makeText(this.context, "已取消", Toast.LENGTH_SHORT).show()
                }

            val dialog = builder.create()
            dialog.show()


        }



        return fragView
    }


}
