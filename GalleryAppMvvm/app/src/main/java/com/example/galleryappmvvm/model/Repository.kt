package com.example.galleryappmvvm.model

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.galleryappmvvm.view.modelclass.CategoryImages
import com.example.galleryappmvvm.view.modelclass.ImageTime
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentSnapshot


class Repository {
    private val firebase: FirebaseSource = FirebaseSource()


    fun isLoggedIn(): LiveData<Boolean> {
        return firebase.isLoggedIn()
    }

    fun login1(email: String, password: String): LiveData<Result<Boolean>> {
        val result: MutableLiveData<Result<Boolean>> = MutableLiveData()
        firebase.login1(email, password).observeForever {
            it.onSuccess {
                result.value = Result.success(true)
            }
            it.onFailure {
                result.value = Result.failure(it)
            }
        }
        return result
    }

    fun signup1(
        name: String,
        email: String,
        password: String,
        selectedPhotoUri: Uri
    ): LiveData<Result<Boolean>> {
        val result: MutableLiveData<Result<Boolean>> = MutableLiveData()
        firebase.signup1(name, email, password, selectedPhotoUri).observeForever {
            it.onSuccess {
                result.value = Result.success(true)
            }
            it.onFailure {
                result.value = Result.failure(it)
            }
        }
        return result
    }

    fun fetchUserDetails1(): LiveData<Result<DocumentSnapshot>> {
        val result: MutableLiveData<Result<DocumentSnapshot>> = MutableLiveData()
        firebase.fetchUserDetails1().observeForever {
            it.onSuccess {
                result.value = Result.success(it)
            }
            it.onFailure {
                result.value = Result.failure(it)
            }
        }
        return result
    }


    fun getSavedCategories1(): CollectionReference {
        return firebase.getSavedCategories1()
    }

    fun addCategory1(selectedPhotoUri: Uri, addCategoryName: String): LiveData<Result<Boolean>> {
        val result: MutableLiveData<Result<Boolean>> = MutableLiveData()
        firebase.addCategory1(selectedPhotoUri, addCategoryName).observeForever {
            it.onSuccess {
                result.value = Result.success(true)
            }
            it.onFailure {
                result.value = Result.failure(it)
            }
        }
        return result
    }

    fun uploadCategoryImage1(selectedPhotoUri: Uri, categoryId: String): LiveData<Result<Boolean>> {
        val result: MutableLiveData<Result<Boolean>> = MutableLiveData()
        firebase.uploadCategoryImage1(selectedPhotoUri, categoryId).observeForever {
            it.onSuccess {
                result.value = Result.success(it)
            }
            it.onFailure {
                result.value = Result.failure(it)
            }
        }
        return result
    }

    fun fetchCategoryInfo1(categoryId: String): MutableLiveData<List<CategoryImages>> {
        return firebase.fetchCategoryInfo1(categoryId)
    }

    fun updateUserProfile(selectedPhotoUri: Uri?): LiveData<Result<Boolean>> {
        val result = MutableLiveData<Result<Boolean>>()
        firebase.updateUserProfile(selectedPhotoUri).observeForever {
            it.onSuccess {
                result.value = Result.success(true)
            }
            it.onFailure {
                result.value = Result.failure(it)
            }
        }
        return result
    }

    fun currentUser() = firebase.currentUser()


    fun deleteImage(imageUrl: String?, categoryId: String?, currentImageId: String?): MutableLiveData<Result<Boolean>> {
        val result = MutableLiveData<Result<Boolean>>()
        firebase.deleteImage(imageUrl, categoryId, currentImageId).observeForever {
            it.onSuccess {
                result.value = Result.success(true)
            }
            it.onFailure {
                result.value = Result.failure(it)
            }
        }
        return result
    }




    // fun fetchTimeline1() = firebase.fetchTimeline()

    fun fetchTimeline(): LiveData<Result<LiveData<List<ImageTime>>>> {
        val result = MutableLiveData<Result<LiveData<List<ImageTime>>>>()
        firebase.fetchTimeline().observeForever {
            it.onSuccess {
                result.value = Result.success(it)
            }
            it.onFailure {
                result.value = Result.failure(it)
            }
        }
        return result
    }


    fun logout() {
        firebase.logout()
    }


}