package com.example.galleryappmvvm.viewmodel

import android.net.Uri
import android.text.TextUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.galleryappmvvm.model.Repository

private val TAG = AddCategoryViewModel::class.java.simpleName

class AddCategoryViewModel(private var repository: Repository) : ViewModel() {
    private var errMessage = MutableLiveData<String>()
    private var status = MediatorLiveData<AddCatStat>()

    fun addCategory(selectedPhotoUri: Uri?, addCategoryName: String) {
        if(selectedPhotoUri == null){
            errMessage.value = "Please select a category image"
            return
        }
        if(TextUtils.isEmpty(addCategoryName)){
            errMessage.value = "Please Enter a category name"
            return
        }


        status.value = AddCatStat.SHOW_PROGRESS
        status.addSource(repository.addCategory1(selectedPhotoUri, addCategoryName), Observer {
            it.onSuccess {
                status.value = AddCatStat.HIDE_PROGRESS
                status.value = AddCatStat.COMPLETE
            }
            it.onFailure {
                status.value = AddCatStat.HIDE_PROGRESS
                errMessage.value = "ADD CATEGORY ERROR"
            }
        })
    }

    fun getErrMessage(): LiveData<String> {
        return errMessage
    }

    fun getStatus(): LiveData<AddCatStat> {
        return status
    }

    enum class AddCatStat {
        HIDE_PROGRESS,
        SHOW_PROGRESS,
        COMPLETE
    }
}