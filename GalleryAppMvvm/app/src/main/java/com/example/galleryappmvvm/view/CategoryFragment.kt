package com.example.gallleryapp1.View

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.galleryappmvvm.*
import com.example.galleryappmvvm.view.AddCategoryFragment
import com.example.galleryappmvvm.view.CategoryAdapter
import com.example.galleryappmvvm.view.LoadingDialog
import com.example.galleryappmvvm.viewmodel.FirebaseViewModel
import kotlinx.android.synthetic.main.category_fragment_layout.view.*


class CategoryFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerAdapter: CategoryAdapter
    private lateinit var viewModel: FirebaseViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.category_fragment_layout, container, false)
        recyclerView = view.findViewById(R.id.category_recycler_view)

        viewModel = ViewModelProvider(this).get(FirebaseViewModel::class.java)
        recyclerAdapter = CategoryAdapter(this.context!!, this)

        viewModel.getSavedCategories().observe(viewLifecycleOwner, Observer { categories ->
            categories?.let {
                recyclerAdapter.setCategories(it)
                recyclerView.adapter = recyclerAdapter
                recyclerView.layoutManager = GridLayoutManager(this.context,3)
            }
        })

//        viewModel.isLoaded.observe(viewLifecycleOwner, Observer {
//            val loadingDialog =
//                LoadingDialog(activity!!)
//            if(it == true){
//
//                loadingDialog.startLoadingAnimation()
//            }
//            else{
//                loadingDialog.dismissDialog()
//            }
//        })


        view.addCategoryFloatingActionButton.setOnClickListener {
            //-- recycler view is provided all categories
            addAddCategoryFragment()
        }

        return view
    }


    private fun addAddCategoryFragment() {
        val nextFrag = AddCategoryFragment()
        activity!!.supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container2, nextFrag, "add_category")
            .addToBackStack(null)
            .commit()
    }

}

