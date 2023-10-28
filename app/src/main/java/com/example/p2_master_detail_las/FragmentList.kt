package com.example.p2_master_detail_las

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.p2_master_detail_las.model.Show

class FragmentList : Fragment() {

    private var columnCount = 2

    var listener:OnItemClick? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is OnItemClick) { listener = context }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_list, container, false)

        if (view is RecyclerView) {
            with(view)
            {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context,columnCount)
                }
                adapter = MyItemRecyclerViewAdapter(Show.showList,listener)
            }
        }

        return view
    }



    companion object {

        const val ARG_COLUMN_COUNT = "column-count"
        fun newInstance(columnCount:Int) =
            FragmentList().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}