package com.example.galleryappmvvm.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.galleryappmvvm.R
import com.example.galleryappmvvm.viewmodel.FirebaseViewModel
import com.example.galleryappmvvm.viewmodel.MyViewModelfactory
import com.example.galleryappmvvm.viewmodel.TimelineViewModel


private val TAG = "TIMELINE_FRAG"
class TimelineFragment :Fragment(){
    private val mViewModel by lazy {
        ViewModelProvider(this,MyViewModelfactory()).get(TimelineViewModel::class.java)
    }
    private lateinit var loadingDialog: LoadingDialog
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.tmeline_fragment_layout,container,false)
        loadingDialog = LoadingDialog(activity!!)

        val recyclerView: RecyclerView = view.findViewById(R.id.timeline_recycler_view)
        val recyclerAdapter =
            TimelineAdapter(this.context!!, this)

        mViewModel.fetchTimeline().observe(viewLifecycleOwner, Observer { times->
            times?.let{
                Log.d(TAG,"$it")
                recyclerAdapter.setImageTime(it)
                recyclerView.adapter = recyclerAdapter
                recyclerView.layoutManager = GridLayoutManager(this.context,2)
            }
        })

        mViewModel.getStatus().observe(viewLifecycleOwner, Observer {
            when(it){
                TimelineViewModel.TimelineStatus.SHOW_PROGRESS -> loadingDialog.startLoadingAnimation()
                TimelineViewModel.TimelineStatus.HIDE_PROGRESS -> loadingDialog.dismissDialog()
                else -> loadingDialog.dismissDialog()
            }
        })
        mViewModel.getErrorMessage().observe(viewLifecycleOwner, Observer {
            Toast.makeText(activity!!,it,Toast.LENGTH_LONG).show()
        })

        return view
    }
}