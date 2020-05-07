package com.example.galleryappmvvm.viewmodel

import android.net.Uri
import androidx.lifecycle.*
import com.example.galleryappmvvm.model.Repository
import com.google.firebase.firestore.DocumentSnapshot

class UserProfileViewModel(private var repository: Repository): ViewModel() {
    private var errMessage = MutableLiveData<String>()
    private var status = MediatorLiveData<Status>()
    private var documentSnapshot = MutableLiveData<DocumentSnapshot>()



    fun updateUserProfile(selectedPhotoUri: Uri){
        status.value = Status.SHOW_PROGRESS_UPDATE_USER_IMAGE
        status.addSource(repository.updateUserProfile(selectedPhotoUri),Observer{
            it.onSuccess {
                status.value = Status.HIDE_PROGRESS
                status.value = Status.COMPLETE
            }
            it.onFailure {
                errMessage.value = it.toString()
                status.value = Status.HIDE_PROGRESS
            }
        })
    }
    fun fetchUserDetails():LiveData<DocumentSnapshot>{
        status.value = Status.SHOW_PROGRESS

        status.addSource(repository.fetchUserDetails1(), Observer {
            it.onSuccess {
                documentSnapshot.value = it
                status.value = Status.HIDE_PROGRESS
            }
            it.onFailure {
                errMessage.value = it.toString()
                status.value = Status.HIDE_PROGRESS
            }
        })
        return documentSnapshot
    }
    fun logout(){
        repository.logout()
    }


    fun getUserProfileStatus(): MediatorLiveData<Status> {
        return status
    }
    fun getErrorMessage():LiveData<String>{
        return errMessage
    }


    enum class Status{
        SHOW_PROGRESS,
        HIDE_PROGRESS,
        COMPLETE,
        SHOW_PROGRESS_UPDATE_USER_IMAGE
    }
}