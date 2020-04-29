package com.example.galleryappmvvm.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.galleryappmvvm.R
import com.example.galleryappmvvm.viewmodel.FirebaseViewModel

class TimelineFragment :Fragment(){
    private val TAG = "TIMELINE_FRAG"
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.tmeline_fragment_layout,container,false)
        val viewModel = ViewModelProviders.of(this).get(FirebaseViewModel::class.java)

        val recyclerView: RecyclerView = view.findViewById(R.id.timeline_recycler_view)
        val recyclerAdapter =
            TimelineAdapter(this.context!!, this)

        viewModel.fetchTimeline().observe(viewLifecycleOwner, Observer { times ->
            times?.let {
                Log.d(TAG, "$it")
                recyclerAdapter.setImageTime(it)
                recyclerView.adapter = recyclerAdapter
                recyclerView.layoutManager = GridLayoutManager(this.context,2)
            }
        })
        return view
    }
}