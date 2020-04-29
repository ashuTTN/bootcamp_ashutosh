package com.example.galleryappmvvm.view

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.galleryappmvvm.R


class CategoryInfoAdapter(private val mContext:Context,_categoryInfoFragment:CategoryInfoFragment)
    :RecyclerView.Adapter<CategoryInfoAdapter.RecyclerViewHolder>(){

    private val TAG = "CategoryInfoAdapter"
    private lateinit var mCategoryData:List<CategoryImages>
    private var categoryInfoFragment:CategoryInfoFragment = _categoryInfoFragment

    fun setCategoriesInfo(category: List<CategoryImages>){
        mCategoryData = category
        notifyDataSetChanged()
    }


    class RecyclerViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        var categoryInfoItemImageView:ImageView = itemView.findViewById(R.id.categoryInfoItemimageView)
        var categoryInfoItemProgressBar:ProgressBar = itemView.findViewById(R.id.categoryInfoItemProgressBar)
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

        holder.categoryInfoItemProgressBar.visibility == View.VISIBLE
        Glide.with(mContext).load(imageUrl)
            .listener(object : RequestListener<Drawable> {
                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: com.bumptech.glide.load.DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    holder.categoryInfoItemProgressBar.visibility = View.GONE
                    return false
                }
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    TODO("Not yet implemented")
                }
            })
            .into(holder.categoryInfoItemImageView)

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
