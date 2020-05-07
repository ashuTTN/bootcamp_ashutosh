package com.example.galleryappmvvm.view

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.galleryappmvvm.R
import com.example.galleryappmvvm.view.interfaces.CategoryClickListener


class CategoryAdapter(
    private val mContext: Context,
    private val categoryClickListener: CategoryClickListener
) : RecyclerView.Adapter<CategoryAdapter.RecyclerViewHolder>() {
    private val TAG = "RecyclerViewAdapter"
    private lateinit var mCategoryData: List<Category>

    fun setCategories(category: List<Category>) {
        mCategoryData = category
        notifyDataSetChanged()
    }

    class RecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var categoryTitle: TextView = itemView.findViewById(R.id.categoryTitle)
        var categoryImage: ImageView = itemView.findViewById(R.id.categoryImageView)
        var categoryItemProgress: ProgressBar = itemView.findViewById(R.id.categoryItemProgress)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerViewHolder {
        return RecyclerViewHolder(
            LayoutInflater.from(mContext).inflate(
                R.layout.category_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return mCategoryData.size
    }

    override fun onBindViewHolder(
        holder: RecyclerViewHolder,
        position: Int
    ) {
        val currentCategory = mCategoryData[position]
        holder.categoryTitle.text = currentCategory.name.toString()

        holder.categoryItemProgress.visibility = View.VISIBLE
        Glide.with(mContext).load(currentCategory.categoryProfileImage.toString())
            .listener(object : RequestListener<Drawable> {
                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: com.bumptech.glide.load.DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    holder.categoryItemProgress.visibility = View.GONE
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
            .into(holder.categoryImage)

//        holder.itemView.setOnClickListener {
//            Log.d(TAG,"Recler item clicked,${currentCategory.categoryID}")
//            val categoryInformationFragment = CategoryInfoFragment()
//            val args = Bundle()
//            args.putString("categoryID","${currentCategory.categoryID}")
//            categoryInformationFragment.arguments = args
//            val transaction = categoryFragment.activity!!.supportFragmentManager.beginTransaction()
//            transaction.replace(R.id.fragment_container2,categoryInformationFragment)
//            transaction.addToBackStack(null)
//            transaction.commit()
//        }
        holder.itemView.setOnClickListener {
            categoryClickListener.onClick(currentCategory.categoryID)
        }
    }
}
