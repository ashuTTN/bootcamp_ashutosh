package com.example.galleryappmvvm.model

import android.app.Activity
import android.net.Uri


class Repository() {
    private val firebase: FirebaseSource =
        FirebaseSource()
    fun login(email: String, password: String) = firebase.login(email, password)

    fun signup(name: String, email: String, password: String, selectedPhotoUri: Uri?) =
        firebase.signup(name, email, password, selectedPhotoUri)

    fun currentUser() = firebase.currentUser()
    fun logout() = firebase.logout()


    fun addCategory(activity: Activity, selectedPhotoUri: Uri?, categoryName: String) =
        firebase.addCategory(activity , selectedPhotoUri, categoryName)

    fun getSavedCategories() = firebase.getSavedCategories()

    fun fetchCategoryInfo(categoryId: String) = firebase.fetchCategoryInfo(categoryId)

    fun fetchUserDetails() = firebase.fetchUserDetails()
    fun uploadCategoryImage(selectedPhotoUri: Uri?, categoryId: String) =
        firebase.uploadCategoryImage(selectedPhotoUri, categoryId)

    fun fetchTimeline() = firebase.fetchTimeline()

    fun updateUserProfile(selectedPhotoUri: Uri?) = firebase.updateUserProfile(selectedPhotoUri)
    fun deleteImage(imageUrl: String? , categoryId: String? , currentImageId:String?) = firebase.deleteImage(imageUrl,categoryId,currentImageId)

}