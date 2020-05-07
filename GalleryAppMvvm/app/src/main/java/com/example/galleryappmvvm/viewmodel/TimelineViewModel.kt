package com.example.galleryappmvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.galleryappmvvm.model.Repository
import com.example.galleryappmvvm.view.ImageTime
private val TAG = TimelineViewModel::class.java.simpleName

class TimelineViewModel(private var repository: Repository):ViewModel(){
    private var status = MediatorLiveData<TimelineStatus>()
    private var errMessage = MutableLiveData<String>()
    private var timelineList = MutableLiveData<List<ImageTime>>()

    fun fetchTimeline(): MutableLiveData<List<ImageTime>> {
        status.value = TimelineStatus.SHOW_PROGRESS
        status.addSource(repository.fetchTimeline(), Observer {
            it.onSuccess {
                timelineList.value = it.value
                status.value = TimelineStatus.HIDE_PROGRESS
                status.value = TimelineStatus.COMPLETE
            }
            it.onFailure {
                errMessage.value = it.message.toString()
                status.value = TimelineStatus.HIDE_PROGRESS
            }
        })
        return timelineList
    }

    fun getStatus(): LiveData<TimelineStatus> {
        return status
    }

    fun getErrorMessage():LiveData<String>{
        return errMessage
    }

    enum class TimelineStatus {
        SHOW_PROGRESS,
        HIDE_PROGRESS,
        COMPLETE
    }
}