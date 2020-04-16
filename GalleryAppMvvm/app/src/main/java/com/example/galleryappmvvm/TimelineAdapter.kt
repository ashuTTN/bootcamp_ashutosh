package com.example.galleryappmvvm

import android.content.Context
import android.util.Log
import android.util.StatsLog.logEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.gms.tasks.Task
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*


class TimelineAdapter(private val mContext: Context):RecyclerView.Adapter<TimelineAdapter.RecyclerViewHolder>(){
    private val TAG = "TimelineAdapter"
    private lateinit var mTimelineData:List<ImageTime>
    fun setImageTime(time:List<ImageTime>){
        mTimelineData = time.sortedByDescending {
            it.timestamp as Long
        }
        notifyDataSetChanged()
    }

    class RecyclerViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        var timelineImageView: ImageView = itemView.findViewById(R.id.timelineImageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        return RecyclerViewHolder(LayoutInflater.from(mContext).inflate(R.layout.time_item,parent,false))
    }

    override fun getItemCount(): Int {
        return mTimelineData.size
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val currentTime = mTimelineData[position]
        val imageUrl: Task<*> = currentTime.imageUrl as Task<*>
        Log.d(TAG,imageUrl.toString())
        imageUrl.addOnSuccessListener {
            Glide.with(mContext).load(it.toString()).into(holder.timelineImageView)
        }
    }

}