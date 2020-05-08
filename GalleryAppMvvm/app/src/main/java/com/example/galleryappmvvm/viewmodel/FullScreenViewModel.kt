package com.example.galleryappmvvm.viewmodel

import Utils.isNetworkAvailable
import android.content.Context
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.galleryappmvvm.model.Repository
private val TAG = FullScreenViewModel::class.java.simpleName
class FullScreenViewModel(private var context: Context,private var repository: Repository): ViewModel(){
    private var status = MediatorLiveData<DelteStatus>()
    private var errMessage = MutableLiveData<String>()

    fun deleteImage(imageUrl:String, categoryId:String, currentImageId:String){
        status.value = DelteStatus.SHOW_PROGRESS
        if(!context.isNetworkAvailable()){
            status.value = DelteStatus.HIDE_PROGRESS
            errMessage.value = "Network not available"
            return
        }
        status.addSource(repository.deleteImage(imageUrl,categoryId, currentImageId), Observer {
            it.onSuccess {
                status.value = DelteStatus.HIDE_PROGRESS
                status.value = DelteStatus.COMPLETE
            }
            it.onFailure {
                status.value = DelteStatus.HIDE_PROGRESS
                errMessage.value = it.toString()
            }
        })
    }

    fun getErrorMessage(): MutableLiveData<String> {
        return errMessage
    }

    fun getDeleteStatus(): MediatorLiveData<DelteStatus> {
        return status
    }

    enum class DelteStatus {
        SHOW_PROGRESS,
        HIDE_PROGRESS,
        COMPLETE
    }
}