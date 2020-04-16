package com.example.galleryappmvvm

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.gallleryapp1.View.CategoryFragment

class CategoryRecyclerViewAdapter (private val mContext:Context,_categoryFragment: CategoryFragment):RecyclerView.Adapter<CategoryRecyclerViewAdapter.RecyclerViewHolder>(){
    private val TAG = "RecyclerViewAdapter"
    private lateinit var mCategoryData: List<Category>
    private lateinit var categoryFragment:CategoryFragment
    fun setCategories(category:List<Category>){
        mCategoryData = category
        notifyDataSetChanged()
    }
    init{
        this.categoryFragment = _categoryFragment
    }
    class RecyclerViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        var categoryTitle : TextView = itemView.findViewById(R.id.categoryTitle)
        var categoryImage: ImageView = itemView.findViewById(R.id.categoryImageView)

    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CategoryRecyclerViewAdapter.RecyclerViewHolder {
        return RecyclerViewHolder(LayoutInflater.from(mContext).inflate(R.layout.category_item,parent,false))
    }

    override fun getItemCount(): Int {
        return mCategoryData.size
    }

    override fun onBindViewHolder(
        holder: CategoryRecyclerViewAdapter.RecyclerViewHolder,
        position: Int
    ) {
        val currentCategory = mCategoryData[position]
        holder.categoryTitle.text = currentCategory.name.toString()
        Glide.with(mContext).load(currentCategory.categoryProfileImage.toString()).into(holder.categoryImage);
        holder.itemView.setOnClickListener {
            Log.d(TAG,"Recler item clicked,${currentCategory.categoryID}")
            val categoryInformationFragment = CategoryInformationFragment()
            var args:Bundle = Bundle()
            args.putString("data","${currentCategory.categoryID}")
            categoryInformationFragment.setArguments(args)
            val transaction = categoryFragment.activity!!.supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container2,categoryInformationFragment)
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }

}
