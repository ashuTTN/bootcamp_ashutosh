package com.example.galleryappmvvm.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.galleryappmvvm.*
import com.example.galleryappmvvm.view.interfaces.CategoryClickListener
import com.example.galleryappmvvm.view.recyclerviewadapter.CategoryAdapter
import com.example.galleryappmvvm.viewmodel.CategoryViewModel
import com.example.galleryappmvvm.viewmodel.MyViewModelfactory
import kotlinx.android.synthetic.main.category_fragment_layout.*
import kotlinx.android.synthetic.main.category_fragment_layout.view.addCategoryFloatingActionButton


class CategoryFragment : Fragment(),
    CategoryClickListener {
    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerAdapter: CategoryAdapter
    private val mViewModel by lazy {
        ViewModelProvider(this, MyViewModelfactory()).get(CategoryViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.category_fragment_layout, container, false)
        fetchCategories(view)
        setListeners(view)
        return view
    }

    private fun fetchCategories(view: View) {
        recyclerView = view.findViewById(R.id.category_recycler_view)
        recyclerAdapter =
            CategoryAdapter(
                this.context!!,
                this
            )

        mViewModel.getSavedCategories().observe(viewLifecycleOwner, Observer {
            it?.let {
                if (!it.isEmpty()) {
                    recyclerAdapter.setCategories(it)
                    recyclerView.adapter = recyclerAdapter
                    recyclerView.layoutManager = GridLayoutManager(this.context, 2)
                } else
                    empty_text_category_fragmnet.visibility = View.VISIBLE
            }
        })
    }


    private fun setListeners(view: View) {
        view.addCategoryFloatingActionButton.setOnClickListener {
            addAddCategoryFragment()
        }
    }


    private fun addAddCategoryFragment() {
        val nextFrag =
            AddCategoryFragment()
        activity!!.supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container2, nextFrag, "add_category")
            .addToBackStack(null)
            .commit()
    }

    override fun onClick(categoryID: Any) {
        val categoryInformationFragment =
            CategoryInfoFragment()
        val args = Bundle()
        args.putString("categoryID", "${categoryID}")
        categoryInformationFragment.arguments = args
        val transaction = activity!!.supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container2, categoryInformationFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}

