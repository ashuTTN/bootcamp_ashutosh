package com.example.galleryappmvvm.view.fragments

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.galleryappmvvm.R
import com.example.galleryappmvvm.view.dialog.LoadingDialog
import com.example.galleryappmvvm.view.interfaces.CategoryInfoClickListener
import com.example.galleryappmvvm.view.recyclerviewadapter.CategoryInfoAdapter
import com.example.galleryappmvvm.viewmodel.CategoryInfoViewModel
import com.example.galleryappmvvm.viewmodel.MyViewModelfactory
import kotlinx.android.synthetic.main.category_inforamtion_fragment_layout.view.*


private val TAG = CategoryInfoViewModel::class.java.simpleName

class CategoryInfoFragment : Fragment(),CategoryInfoClickListener {
    private lateinit var loadingDialog: LoadingDialog
    private val mViewModel by lazy {
        ViewModelProvider(this, MyViewModelfactory()).get(CategoryInfoViewModel::class.java)
    }
    private var selectedPhotoUri: Uri? = null
    private lateinit var categoryId: String
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        loadingDialog =
            LoadingDialog(activity!!)
        val view = inflater.inflate(R.layout.category_inforamtion_fragment_layout, container, false)
        val bundle = this.arguments
        categoryId = bundle!!.getString("categoryID")!!
        setObserver()
        setListeners(view)
        val recyclerView: RecyclerView = view.findViewById(R.id.category_information_recyclerview)
        val recyclerAdapter =
            CategoryInfoAdapter(
                this.context!!,
                this
            )

        mViewModel.fetchCategoryInfo(categoryId)
            .observe(viewLifecycleOwner, Observer { categories ->
                categories?.let {
                    Log.d(TAG, it.toString())
                    recyclerAdapter.setCategoriesInfo(it)
                    recyclerView.adapter = recyclerAdapter
                    recyclerView.layoutManager = GridLayoutManager(this.context, 2)
                }
            })
        return view
    }

    private fun setObserver() {
        mViewModel.getStatus().observe(viewLifecycleOwner, Observer {
            when (it) {
                CategoryInfoViewModel.Status.SHOW_PROGRESS -> showProgress()
                CategoryInfoViewModel.Status.HIDE_PROGRESS -> hideProgress()
            }
        })
        mViewModel.getError().observe(viewLifecycleOwner, Observer {
            Toast.makeText(view!!.context, it, Toast.LENGTH_LONG).show()
        })
    }

    private fun hideProgress() {
        loadingDialog.dismissDialog()
    }

    private fun showProgress() {
        loadingDialog.startLoadingAnimation()
    }

    private fun setListeners(view: View) {
        view.floatingActionButtonCategoryInformation.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, 2)
            Log.d(TAG, "$selectedPhotoUri")
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 2 && resultCode == Activity.RESULT_OK && data != null) {
            selectedPhotoUri = data.data!!
            mViewModel.uploadCategoryImage(selectedPhotoUri!!, categoryId)
        }
    }

    override fun onClick(imageUrl: String, categoryId: String, currentImageId: String) {
        val fullscreenViewFragment =
            FullscreenViewFragment()
            val args: Bundle = Bundle()
            args.putString("imageUrl",imageUrl)
            args.putString("categoryId",categoryId)
            args.putString("currentImageId",currentImageId)
            fullscreenViewFragment.arguments = args
            val transaction = activity!!.supportFragmentManager.beginTransaction()
            transaction.add(R.id.fragment_container2,fullscreenViewFragment)
            transaction.addToBackStack(null);
            transaction.commit();
    }
}