package com.example.galleryappmvvm.viewmodel

import android.net.Uri
import android.text.TextUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.galleryappmvvm.model.Repository


private val TAG = SignUpViewModel::class.java.simpleName
class SignUpViewModel(private val repository: Repository):ViewModel() {
    private var errMessage = MutableLiveData<String>()
    private var signUpState = MediatorLiveData<SignUpState>()

    fun onSignUpClicked(name:String,email:String,password:String,selectedPhotoUri: Uri?){
        if(TextUtils.isEmpty(name)){
            errMessage.value = "Name cannot be blank"
            return
        }
        if(TextUtils.isEmpty(password)){
            errMessage.value = "Password cannot be blank"
            return
        }
        if(TextUtils.isEmpty(email)){
            errMessage.value = "Email cannot be blank"
        }
        if(selectedPhotoUri == null) {
            errMessage.value = "Please select a photo"
            return
        }
        signUpState.value = SignUpState.SHOW_PROGRESS
        signUpState.addSource(repository.signup1(name,email,password,selectedPhotoUri), Observer {
            it.onSuccess {
                signUpState.value = SignUpState.HIDE_PROGRESS
                signUpState.value = SignUpState.GO_TO_LOGIN_SCREEN
            }
            it.onFailure {
                signUpState.value = SignUpState.HIDE_PROGRESS
                errMessage.value = "Login Error !! "
            }
        })
    }
    fun getErrMessage():LiveData<String>{
        return errMessage
    }
    fun getSignUpState():LiveData<SignUpState>{
        return signUpState
    }
    enum class SignUpState{
        SHOW_PROGRESS,
        HIDE_PROGRESS,
        GO_TO_LOGIN_SCREEN
    }
}