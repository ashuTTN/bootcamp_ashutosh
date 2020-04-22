package com.example.galleryappmvvm.view

import android.app.Activity
import android.content.Intent
import android.net.Uri
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
import kotlinx.android.synthetic.main.category_inforamtion_fragment_layout.view.*

class CategoryInformationFragment : Fragment() {
    private var selectedPhotoUri: Uri? = null
    private val TAG = "CateInformationFragment"
    private lateinit var categoryId: String
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.category_inforamtion_fragment_layout, container, false)
        val bundle = this.arguments
        categoryId = bundle!!.getString("data")!!

        view.floatingActionButtonCategoryInformation.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, 2)
            Log.d(TAG, "$selectedPhotoUri")

        }
        val viewModel = ViewModelProviders.of(this).get(FirebaseViewModel::class.java)

        val recyclerView:RecyclerView = view.findViewById(R.id.category_information_recyclerview)


        val recyclerAdapter =
            CategoryInfoAdapter(this.context!!)
        viewModel.fetchCategoryInfo(categoryId).observe(viewLifecycleOwner, Observer { categories ->
            categories?.let {
                Log.d(TAG,it.toString())
                recyclerAdapter.setCategoriesInfo(it)
                recyclerView.adapter = recyclerAdapter
                recyclerView.layoutManager = GridLayoutManager(this.context,4)
            }
        })

        return view
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 2 && resultCode == Activity.RESULT_OK && data != null) {
            selectedPhotoUri = data.data!!
            val viewModel = ViewModelProviders.of(this).get(FirebaseViewModel::class.java)
            viewModel.uploadCategoryImage(selectedPhotoUri, categoryId)
            Log.d(TAG, "$selectedPhotoUri")
        }
    }
}