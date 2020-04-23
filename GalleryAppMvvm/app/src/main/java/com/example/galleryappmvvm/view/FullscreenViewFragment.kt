package com.example.galleryappmvvm.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.bumptech.glide.Glide
import com.example.galleryappmvvm.R
import com.example.galleryappmvvm.viewmodel.FirebaseViewModel
import kotlinx.android.synthetic.main.fullscreen_fragment_layout.view.*

class FullscreenViewFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fullscreen_fragment_layout, container, false)
        val bundle = this.arguments
        val imageUrl = bundle!!.getString("imageUrl")
        val categoryId = bundle.getString("categoryId")
        val currentImageId = bundle.getString("currentImageId")

        Glide.with(this).load(imageUrl).into(view.fullscreenImageView)
        view.deleteFab.setOnClickListener {
            val viewModel = ViewModelProvider(this).get(FirebaseViewModel::class.java)
            viewModel.deleteImage(imageUrl, categoryId, currentImageId)
            activity!!.supportFragmentManager.popBackStack()
        }
        return view
    }
}
