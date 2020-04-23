package com.example.galleryappmvvm.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.galleryappmvvm.R


class CategoryInfoAdapter(private val mContext:Context,_categoryInfoFragment:CategoryInformationFragment)
    :RecyclerView.Adapter<CategoryInfoAdapter.RecyclerViewHolder>(){

    private val TAG = "CategoryInfoAdapter"
    private lateinit var mCategoryData:List<CategoryImages>
    private var categoryInfoFragment:CategoryInformationFragment = _categoryInfoFragment

    fun setCategoriesInfo(category: List<CategoryImages>){
        mCategoryData = category
        notifyDataSetChanged()
    }


    class RecyclerViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        var imageView:ImageView = itemView.findViewById(R.id.imageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        return RecyclerViewHolder(
            LayoutInflater.from(mContext).inflate(
                R.layout.category_info_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return mCategoryData.size
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val currentCategory = mCategoryData[position]
        val imageUrl = currentCategory.categoryImageUrl.toString()
        val categoryId = currentCategory.categoryId.toString()
        val currentImageId = currentCategory.categoryImageId.toString()

        Glide.with(mContext).load(imageUrl).into(holder.imageView)

        holder.itemView.setOnClickListener {
            val fullscreenViewFragment = FullscreenViewFragment()
            val args: Bundle = Bundle()
            args.putString("imageUrl",imageUrl)
            args.putString("categoryId",categoryId)
            args.putString("currentImageId",currentImageId)

            fullscreenViewFragment.arguments = args
            val transaction = categoryInfoFragment.activity!!.supportFragmentManager.beginTransaction()
            transaction.add(R.id.fragment_container2,fullscreenViewFragment)
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }

}
