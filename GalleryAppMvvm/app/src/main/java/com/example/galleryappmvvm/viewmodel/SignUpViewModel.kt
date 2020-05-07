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
    private var errState = MutableLiveData<SignUpViewModel.ErrState>()


    fun onSignUpClicked(name:String,email:String,password:String,selectedPhotoUri: Uri?){
        var flag = 0
        if(TextUtils.isEmpty(name)){
            errState.value = ErrState.NAME_BLANK
            flag = 1
        }
        if(TextUtils.isEmpty(password)){
            errState.value = ErrState.PASSWORD_BLANK
            flag = 1
        }
        if(TextUtils.isEmpty(email)){
            errState.value = ErrState.EMAIL_BLANK
            flag = 1
        }
        if(selectedPhotoUri == null) {
            errMessage.value = "Please select a photo"
            flag = 1
        }
        if(flag == 1){
            return
        }


        signUpState.value = SignUpState.SHOW_PROGRESS
        signUpState.addSource(repository.signup1(name,email,password,selectedPhotoUri!!), Observer {
            it.onSuccess {
                signUpState.value = SignUpState.HIDE_PROGRESS
                signUpState.value = SignUpState.GO_TO_LOGIN_SCREEN
            }
            it.onFailure {
                signUpState.value = SignUpState.HIDE_PROGRESS
                errMessage.value = it.message
            }
        })
    }
    fun getErrMessage():LiveData<String>{
        return errMessage
    }
    fun getSignUpState():LiveData<SignUpState>{
        return signUpState
    }
    fun getErrState():LiveData<ErrState>{
        return errState
    }
    enum class SignUpState{
        SHOW_PROGRESS,
        HIDE_PROGRESS,
        GO_TO_LOGIN_SCREEN
    }
    enum class ErrState{
        PASSWORD_BLANK,
        EMAIL_BLANK,
        NAME_BLANK
    }
}