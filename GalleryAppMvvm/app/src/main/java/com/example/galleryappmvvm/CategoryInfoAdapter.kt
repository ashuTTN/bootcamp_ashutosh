package com.example.galleryappmvvm

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide


class CategoryInfoAdapter(private val mContext:Context) :RecyclerView.Adapter<CategoryInfoAdapter.RecyclerViewHolder>(){

    private val TAG = "CategoryInfoAdapter"
    private lateinit var mCategoryData:List<CategoryImages>
    fun setCategoriesInfo(category: List<CategoryImages>){
        mCategoryData = category
        notifyDataSetChanged()
    }


    class RecyclerViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        var imageView:ImageView = itemView.findViewById(R.id.imageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        return RecyclerViewHolder(LayoutInflater.from(mContext).inflate(R.layout.category_info_item,parent,false))
    }

    override fun getItemCount(): Int {
        return mCategoryData.size
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val currentCategory = mCategoryData[position]
        val imageUrl = currentCategory.categoryImageUrl.toString()
        Glide.with(mContext).load(imageUrl).into(holder.imageView);
    }

}
