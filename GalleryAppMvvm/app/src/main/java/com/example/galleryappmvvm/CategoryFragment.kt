package com.example.gallleryapp1.View

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.galleryappmvvm.*
import kotlinx.android.synthetic.main.category_fragment_layout.view.*
import kotlinx.android.synthetic.main.signup_fragment_layout.*


class CategoryFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerAdapter: CategoryRecyclerViewAdapter
    private lateinit var viewModel: FirebaseViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.category_fragment_layout, container, false)
        recyclerView = view.findViewById(R.id.category_recycler_view)

        viewModel = ViewModelProviders.of(this).get(FirebaseViewModel::class.java)
        recyclerAdapter = CategoryRecyclerViewAdapter(this.context!!,this)
        viewModel.getSavedCategories().observe(viewLifecycleOwner, Observer { categories ->
            categories?.let {
                Log.d("hahahahahahahahahahaha", "$it")
                recyclerAdapter.setCategories(it)
                recyclerView.adapter = recyclerAdapter
                recyclerView.layoutManager = GridLayoutManager(this.context,3)
            }
        })



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

