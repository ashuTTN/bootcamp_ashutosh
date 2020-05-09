package com.example.galleryappmvvm.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.galleryappmvvm.R
import com.example.galleryappmvvm.view.dialog.LoadingDialog
import com.example.galleryappmvvm.viewmodel.FullScreenViewModel
import com.example.galleryappmvvm.viewmodel.MyViewModelfactory
import kotlinx.android.synthetic.main.fullscreen_fragment_layout.view.*

class FullscreenViewFragment : Fragment() {
    private val mViewModel by lazy {
        ViewModelProvider(this, MyViewModelfactory()).get(FullScreenViewModel::class.java)
    }
    private lateinit var loadingDialog: LoadingDialog
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fullscreen_fragment_layout, container, false)
        loadingDialog =
            LoadingDialog(activity!!)
        val bundle = this.arguments
        val imageUrl = bundle!!.getString("imageUrl")
        val categoryId = bundle.getString("categoryId")
        val currentImageId = bundle.getString("currentImageId")

        Glide.with(this).load(imageUrl).into(view.fullscreenImageView)


        setObservers()

        view.deleteFab.setOnClickListener {
            mViewModel.deleteImage(imageUrl!!, categoryId!!, currentImageId!!)
        }


        return view
    }

    private fun setObservers() {
        mViewModel.getErrorMessage().observeForever {
            Toast.makeText(activity, it, Toast.LENGTH_LONG).show()
        }
        mViewModel.getDeleteStatus().observe(viewLifecycleOwner, Observer {
            when (it) {
                FullScreenViewModel.DelteStatus.SHOW_PROGRESS -> {
                    loadingDialog.startLoadingAnimation()
                    loadingDialog.setTitle("Deleting Image")
                }
                FullScreenViewModel.DelteStatus.HIDE_PROGRESS -> loadingDialog.dismissDialog()
                FullScreenViewModel.DelteStatus.COMPLETE -> activity!!.supportFragmentManager.popBackStack()
            }
        })
    }
}
