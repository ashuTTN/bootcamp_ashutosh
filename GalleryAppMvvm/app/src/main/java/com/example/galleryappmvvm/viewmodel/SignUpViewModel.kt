package com.example.galleryappmvvm.viewmodel

import com.example.galleryappmvvm.Utils.isNetworkAvailable
import android.content.Context
import android.net.Uri
import android.text.TextUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.galleryappmvvm.model.Repository


private val TAG = SignUpViewModel::class.java.simpleName
class SignUpViewModel(private val context: Context,private val repository: Repository):ViewModel() {
    private var validationMessage = MutableLiveData<SignUpValidaiton>()
    private var errMessage = MutableLiveData<String>()
    private var signUpState = MediatorLiveData<SignUpState>()

    fun onSignUpClicked(name:String,email:String,password:String,selectedPhotoUri: Uri?){
        var flag = 0
        if(!(context.isNetworkAvailable())){
            errMessage.value = "Network Not Available"
            flag = 1
        }
        if(TextUtils.isEmpty(name)){
            validationMessage.value = SignUpValidaiton.NAME_BLANK
            flag = 1
        }
        if(TextUtils.isEmpty(password)){
            validationMessage.value = SignUpValidaiton.PASSWORD_BLANK
            flag = 1
        }
        if(TextUtils.isEmpty(email)){
            validationMessage.value = SignUpValidaiton.EMAIL_BLANK
            flag = 1
        }
        if(selectedPhotoUri == null) {
            errMessage.value = "Please select a photo"
            flag = 1
        }
        if (flag == 1){
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
    fun getValidationMessage():LiveData<SignUpValidaiton>{
        return validationMessage
    }
    enum class SignUpValidaiton{
        EMAIL_BLANK,
        PASSWORD_BLANK,
        NAME_BLANK
    }
    enum class SignUpState{
        SHOW_PROGRESS,
        HIDE_PROGRESS,
        GO_TO_LOGIN_SCREEN
    }
}