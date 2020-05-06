package com.example.galleryappmvvm.view

import android.app.Activity
import android.content.Intent
import android.net.Uri
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
import com.example.galleryappmvvm.viewmodel.AddCategoryViewModel
import com.example.galleryappmvvm.viewmodel.FirebaseViewModel
import com.example.galleryappmvvm.viewmodel.MyViewModelfactory
import kotlinx.android.synthetic.main.add_category_layout.*
import kotlinx.android.synthetic.main.add_category_layout.view.*
import kotlinx.android.synthetic.main.custom_dialog.*

class AddCategoryFragment : Fragment() {
    private val mViewModel by lazy {
        ViewModelProvider(this, MyViewModelfactory()).get(AddCategoryViewModel::class.java)
    }
    private lateinit var loadingDialog: LoadingDialog
    private lateinit var viewModel: FirebaseViewModel
    private var selectedPhotoUri: Uri? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.add_category_layout, container, false)
        loadingDialog = LoadingDialog(activity!!)

        setObservers()
        setListeners(view)

        return view
    }

    private fun setObservers() {
        mViewModel.getStatus().observe(viewLifecycleOwner, Observer {
            when (it) {
                AddCategoryViewModel.AddCatStat.HIDE_PROGRESS -> hideProgress()
                AddCategoryViewModel.AddCatStat.COMPLETE -> complete()
                AddCategoryViewModel.AddCatStat.SHOW_PROGRESS -> showProgress()
                else->hideProgress()
            }
        })
        mViewModel.getErrMessage().observe(viewLifecycleOwner, Observer {
            Toast.makeText(activity, it, Toast.LENGTH_LONG).show()
        })
    }

    private fun hideProgress() {
        loadingDialog.dismissDialog()
    }

    private fun complete() {
        loadingDialog.dismissDialog()
        Toast.makeText(activity,"Category Added",Toast.LENGTH_LONG).show()
    }

    private fun showProgress() {
        loadingDialog.startLoadingAnimation()
    }

    private fun setListeners(view: View) {
        view.add_category_image_view.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, 1)
        }
        view.add_category_button.setOnClickListener {
            mViewModel.addCategory(selectedPhotoUri, add_category_name.text.toString())
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == Activity.RESULT_OK && data != null) {
            selectedPhotoUri = data.data
            Glide.with(view!!).load(selectedPhotoUri).into(view!!.add_category_image_view)
        }
    }
}