package com.example.galleryappmvvm

import android.net.Uri
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.QuerySnapshot


class Repository() {
    private val firebase: FirebaseSource = FirebaseSource()
    fun login(email: String, password: String) = firebase.login(email, password)

    fun signup(name: String, email: String, password: String, selectedPhotoUri: Uri?) =
        firebase.signup(name, email, password, selectedPhotoUri)

    fun currentUser() = firebase.currentUser()
    fun logout() = firebase.logout()

    fun uploadCategoryProfileImage(selectedPhotoUri: Uri?, add_category_name: String) =
        firebase.addCategory(selectedPhotoUri, add_category_name)

    fun addCategory(selectedPhotoUri: Uri?, categoryName: String) =
        firebase.addCategory(selectedPhotoUri, categoryName)

    fun getSavedCategories() = firebase.getSavedCategories()

    fun fetchCategoryInfo(categoryId: String) = firebase.fetchCategoryInfo(categoryId)

    fun fetchUserDetails() = firebase.fetchUserDetails()
    fun uploadCategoryImage(selectedPhotoUri: Uri?, categoryId: String) =
        firebase.uploadCategoryImage(selectedPhotoUri, categoryId)

    fun fetchTimeline() = firebase.fetchTimeline()


}