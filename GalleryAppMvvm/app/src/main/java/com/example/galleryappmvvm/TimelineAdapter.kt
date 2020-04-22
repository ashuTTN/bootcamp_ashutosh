package com.example.galleryappmvvm

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
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
        var timelineImageView: ImageView = itemView.findViewById(R.id.timelineImageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        return RecyclerViewHolder(
            LayoutInflater.from(mContext).inflate(R.layout.time_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return mTimelineData.size
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val currentTime = mTimelineData[position]
        val imageUrl: Task<*> = currentTime.imageUrl as Task<*>
        Log.d(TAG, imageUrl.toString())
        imageUrl.addOnSuccessListener {
            Glide.with(mContext).load(it.toString()).into(holder.timelineImageView)
        }
        holder.itemView.setOnClickListener {
            imageUrl.addOnSuccessListener {
                val fullscreenViewFragment = FullscreenViewFragment()
                var args:Bundle = Bundle()
                args.putString("data",it.toString())
                fullscreenViewFragment.setArguments(args)
                val transaction = timelineFragment.activity!!.supportFragmentManager.beginTransaction()
                transaction.add(R.id.fragment_container2,fullscreenViewFragment)
                transaction.addToBackStack(null);
                transaction.commit();
            }

        }
    }



}