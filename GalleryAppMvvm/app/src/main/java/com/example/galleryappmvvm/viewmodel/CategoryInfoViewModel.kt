package com.example.galleryappmvvm.viewmodel

import com.example.galleryappmvvm.Utils.isNetworkAvailable
import android.content.Context
import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.galleryappmvvm.model.Repository
import com.example.galleryappmvvm.view.modelclass.CategoryImages

private val TAG = CategoryInfoViewModel::class.java.simpleName

class CategoryInfoViewModel(private var context: Context,private var repository: Repository) : ViewModel() {
    private var saveImagesUrl: MutableLiveData<List<CategoryImages>> = MutableLiveData()
    private var errMessage = MutableLiveData<String>()
    private var status = MediatorLiveData<Status>()

    enum class Status {
        SHOW_PROGRESS,
        HIDE_PROGRESS
    }

    fun fetchCategoryInfo(categoryId: String) : MutableLiveData<List<CategoryImages>>{
        return repository.fetchCategoryInfo1(categoryId)
    }

    fun uploadCategoryImage(selectedPhotoUri: Uri, categoryId: String) {
        if(!(context.isNetworkAvailable())){
            errMessage.value = "Network not available"
            return
        }
        status.value = Status.SHOW_PROGRESS
        status.addSource(repository.uploadCategoryImage1(selectedPhotoUri, categoryId), Observer {
            it.onSuccess {
                status.value = Status.HIDE_PROGRESS
            }
            it.onFailure {
                errMessage.value = it.toString()
                status.value = Status.HIDE_PROGRESS
            }
        })
    }

    fun getStatus():LiveData<Status>{
        return status
    }

    fun getError():LiveData<String>{
        return errMessage
    }
}