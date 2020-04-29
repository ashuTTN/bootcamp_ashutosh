package com.example.galleryappmvvm.view

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.Log
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
import com.google.android.gms.tasks.Task


class TimelineAdapter(private val mContext: Context,_timelineFragment: TimelineFragment) :
    RecyclerView.Adapter<TimelineAdapter.RecyclerViewHolder>() {
    private val TAG = "TimelineAdapter"
    private lateinit var mTimelineData: List<ImageTime>
    private var timelineFragment: TimelineFragment
    init {
        this.timelineFragment = _timelineFragment
    }

    fun setImageTime(time: List<ImageTime>) {
        mTimelineData = time.sortedByDescending {
            it.timestamp as Long
        }
        notifyDataSetChanged()
    }

    class RecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var timelineItemImageView: ImageView = itemView.findViewById(R.id.timelineItemImageView)
        var tiemlineItemProgressBar: ProgressBar = itemView.findViewById(R.id.timelineItemProgressBar)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        return RecyclerViewHolder(
            LayoutInflater.from(mContext).inflate(
                R.layout.time_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return mTimelineData.size
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val currentTime = mTimelineData[position]
        val imageUrl: Task<*> = currentTime.imageUrl as Task<*>
        Log.d(TAG, imageUrl.toString())
        holder.tiemlineItemProgressBar.visibility = View.VISIBLE
        imageUrl.addOnSuccessListener {
            Glide.with(mContext).load(it.toString())
                .listener(object : RequestListener<Drawable> {
                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: com.bumptech.glide.load.DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        holder.tiemlineItemProgressBar.visibility = View.GONE
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
                .into(holder.timelineItemImageView)
        }
    }

}