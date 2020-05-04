package com.example.galleryappmvvm.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.galleryappmvvm.model.Repository

class MyViewModelfactory:ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(LoginViewModel::class.java)){
            return LoginViewModel(Repository()) as T
        }
        if(modelClass.isAssignableFrom(SignUpViewModel::class.java)){
            return SignUpViewModel(Repository()) as T
        }
        if(modelClass.isAssignableFrom(CategoryViewModel::class.java)){
            return CategoryViewModel(Repository()) as T
        }
        if(modelClass.isAssignableFrom(AddCategoryViewModel::class.java)){
            return AddCategoryViewModel(Repository()) as T
        }
        if(modelClass.isAssignableFrom(CategoryInfoViewModel::class.java)){
            return CategoryInfoViewModel(Repository()) as T
        }
        if(modelClass.isAssignableFrom(UserProfileViewModel::class.java)){
            return UserProfileViewModel(Repository()) as T
        }
        if(modelClass.isAssignableFrom(TimelineViewModel::class.java)){
            return TimelineViewModel(Repository()) as T
        }
        if(modelClass.isAssignableFrom(FullScreenViewModel::class.java)){
            return FullScreenViewModel(Repository()) as T
        }
        throw(IllegalAccessException("Unknown viewmodel class"))
    }
}