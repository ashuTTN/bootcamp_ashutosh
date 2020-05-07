package com.example.galleryappmvvm.viewmodel

import Utils.ValidationUtils
import Utils.isNetworkAvailable
import android.content.Context
import android.text.TextUtils
import androidx.lifecycle.*
import com.example.galleryappmvvm.model.Repository

private val TAG:String = LoginViewModel::class.java.simpleName

class LoginViewModel(private val repository:Repository): ViewModel() {

    private var errMessage = MutableLiveData<String>()
    private var signInState = MediatorLiveData<SignInState>()
    init{
        checkIsAlreadyLoggedIn()
    }


    fun onLogInClicked(email:String , password:String){
        //Check validations
        if(TextUtils.isEmpty(email)){
            errMessage.value = "Email cannot be left blank"
            return
        } else if(!ValidationUtils.isValidEmail(email)){
            errMessage.value = "Please enter valid email address"
            return
        } else if(TextUtils.isEmpty(password)){
            errMessage.value = "Password cannot be left blank"
        }

//        if(!context.isNetworkAvailable()){
//            errMessage.value = "No internet connection."
//            return
//        }
        //validations passed
        //Connect to firebase
        signInState.value = SignInState.SHOW_PROGRESS
        signInState.addSource(repository.login1(email,password), Observer {
            it.onSuccess {
                signInState.value = SignInState.HIDE_PROGRESS
                signInState.value = SignInState.GO_TO_CATEGORY_SCREEN
            }
            it.onFailure {
                signInState.value = SignInState.HIDE_PROGRESS
                errMessage.value = "LOGIN FAILED !! ${it.message}"
            }
        })

    }


    private fun checkIsAlreadyLoggedIn(){
        signInState.addSource(
            repository.isLoggedIn(), Observer {
                if(it){
                    signInState.value = SignInState.GO_TO_CATEGORY_SCREEN
                }
            }
        )
    }

    fun getErrMessage():LiveData<String>{
        return errMessage
    }

    fun getSignInState():LiveData<SignInState>{
        return signInState
    }

    enum class SignInState{
        SHOW_PROGRESS,
        HIDE_PROGRESS,
        GO_TO_CATEGORY_SCREEN
    }
}