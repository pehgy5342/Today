package com.example.today.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.today.R
import com.example.today.adapter.ConstellationAdapter

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class FragmentConstellation : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val frgView = inflater.inflate(R.layout.fragment_constellation, container, false)

        conView(frgView)
        return frgView
    }


    fun conView(view: View) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.rv_constellation)
        val conAdapter = ConstellationAdapter()
        recyclerView.layoutManager = GridLayoutManager(context, 3)!!
        recyclerView.adapter = conAdapter
    }

}
