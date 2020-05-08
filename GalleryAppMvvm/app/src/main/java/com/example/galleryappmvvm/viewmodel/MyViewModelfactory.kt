package com.example.galleryappmvvm.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.galleryappmvvm.model.Repository
import com.example.galleryappmvvm.view.GalleryApp

class MyViewModelfactory:ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(LoginViewModel::class.java)){
            return LoginViewModel( GalleryApp.getInstance().applicationContext,Repository()) as T
        }
        if(modelClass.isAssignableFrom(SignUpViewModel::class.java)){
            return SignUpViewModel(GalleryApp.getInstance(),Repository()) as T
        }
        if(modelClass.isAssignableFrom(CategoryViewModel::class.java)){
            return CategoryViewModel(Repository()) as T
        }
        if(modelClass.isAssignableFrom(AddCategoryViewModel::class.java)){
            return AddCategoryViewModel(GalleryApp.getInstance(),Repository()) as T
        }
        if(modelClass.isAssignableFrom(CategoryInfoViewModel::class.java)){
            return CategoryInfoViewModel(GalleryApp.getInstance(),Repository()) as T
        }
        if(modelClass.isAssignableFrom(UserProfileViewModel::class.java)){
            return UserProfileViewModel(GalleryApp.getInstance(),Repository()) as T
        }
        if(modelClass.isAssignableFrom(TimelineViewModel::class.java)){
            return TimelineViewModel(GalleryApp.getInstance(),Repository()) as T
        }
        if(modelClass.isAssignableFrom(FullScreenViewModel::class.java)){
            return FullScreenViewModel(GalleryApp.getInstance(),Repository()) as T
        }
        throw(IllegalAccessException("Unknown viewmodel class"))
    }
}