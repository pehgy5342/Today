package com.example.today.fragment


import android.content.DialogInterface
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

import com.example.today.R
import kotlinx.android.synthetic.main.fragment_weather.*
import kotlinx.android.synthetic.main.fragment_weather.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class FragmentWeather : Fragment() {
    lateinit var fragView: View
    var position = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragView =  inflater.inflate(R.layout.fragment_weather,container,false)


        fragView.fab_searchCity.setOnClickListener {
            AlertDialog.Builder(fragView.context)
                .setTitle("請選擇縣市")
                .setIcon(R.drawable.taiwanflag2)

                .setSingleChoiceItems(R.array.cityNames, 0) { dialogInterface: DialogInterface?, i: Int ->
                    position = i


                }

                .setPositiveButton("確定") { dialog, which ->

                }
                .setNegativeButton("取消") { dialog, which ->
                    //Toast.makeText(this, "已取消", Toast.LENGTH_SHORT).show()
                    val toast = Toast(fragView.context)
                    //畫面顯示位置
                    toast.setGravity(Gravity.BOTTOM, 0, 200)
                    toast.duration = Toast.LENGTH_SHORT
                    toast.view = layoutInflater.inflate(R.layout.toast_cacel, null)
                    toast.show()
                }
                .create().show()


        }
        return fragView
    }


}
