package com.example.galleryappmvvm

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.google.android.gms.tasks.Task
import kotlinx.android.synthetic.main.activity_category.*
import kotlinx.android.synthetic.main.activity_category.view.*
import kotlinx.android.synthetic.main.activity_category.view.bottom_nav_bar
import kotlinx.android.synthetic.main.fullscreen_fragment_layout.view.*

class FullscreenViewFragment: Fragment(){
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fullscreen_fragment_layout, container, false)
        val bundle = this.arguments
        var url = bundle!!.getString("data")!!
        Glide.with(this).load(url).into(view.fullscreenImageView)
        return view
    }
}