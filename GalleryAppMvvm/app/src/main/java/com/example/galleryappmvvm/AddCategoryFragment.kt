package com.example.galleryappmvvm

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.add_category_layout.*
import kotlinx.android.synthetic.main.add_category_layout.view.*

class AddCategoryFragment:Fragment() {
    private lateinit var viewModel:FirebaseViewModel
    private var selectedPhotoUri: Uri? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.add_category_layout,container,false)
        view.add_category_image_view.setOnClickListener{
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent,1)
        }

        view.add_category_button.setOnClickListener {
            viewModel = ViewModelProviders.of(this).get(FirebaseViewModel::class.java)
            viewModel.addCategory(activity!!,selectedPhotoUri,add_category_name.text.toString())
        }
        return view
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 1 && resultCode == Activity.RESULT_OK && data != null){
            selectedPhotoUri = data.data
        }
    }
}