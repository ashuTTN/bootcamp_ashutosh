package com.example.galleryappmvvm


import android.app.Activity
import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.QuerySnapshot
import kotlin.time.days

class FirebaseViewModel() : ViewModel() {

    val TAG = "FIRESTORE_VIEW_MODEL"
    var savedCaategories: MutableLiveData<List<Category>> = MutableLiveData()
    var saveImagesUrl: MutableLiveData<List<CategoryImages>> = MutableLiveData()
    var time1: MutableLiveData<List<ImageTime>> = MutableLiveData()

    var repository: Repository = Repository()
    private val savedUsers = mutableMapOf<String, String>()

    fun login(email: String, password: String): Task<AuthResult> {
        return repository.login(email, password)
    }

    fun signup(name: String, email: String, password: String, selectedPhotoUri: Uri?) {
        repository.signup(name, email, password, selectedPhotoUri)
    }

    fun logout() {
        repository.logout()
    }


    fun addCategory(activity: Activity, selectedPhotoUri: Uri?, categoryName: String) {
        repository.addCategory(activity , selectedPhotoUri, categoryName)
    }

    fun fetchTimeline(): LiveData<List<ImageTime>> {
        repository.fetchTimeline().listAll()
            .addOnSuccessListener {
                var time = mutableListOf<ImageTime>()
                for (i in it.items) {
                    i.metadata.addOnSuccessListener {
                        var imageTime = ImageTime(
                            i.downloadUrl,
                            it.creationTimeMillis
                        )
                        time.add(imageTime)
                        time1.value = time
                    }
                }
            }
        return time1
    }


    fun fetchCategoryInfo(categoryId: String): LiveData<List<CategoryImages>> {
        repository.fetchCategoryInfo(categoryId)
            .addSnapshotListener(EventListener<QuerySnapshot> { value, e ->
                if (e != null) {
                    Log.w(TAG, "Listen Fail", e)
                    saveImagesUrl.value = null
                    return@EventListener
                }
                var savedImagesList: MutableList<CategoryImages> = mutableListOf()
                for (doc in value!!) {
                    var imageItem = CategoryImages(
                        "${doc.get("categoryImageUrl")}"
                    )
                    savedImagesList.add(imageItem)
                }
                saveImagesUrl.value = savedImagesList
                Log.d(TAG, savedImagesList.toString())
            })
        return saveImagesUrl
    }

    fun getSavedCategories(): LiveData<List<Category>> {
        repository.getSavedCategories()
            .addSnapshotListener(EventListener<QuerySnapshot> { value, e ->
                if (e != null) {
                    Log.w(TAG, "Listen failed.", e)
                    savedCaategories.value = null
                    return@EventListener
                }

                var savedCategoryList: MutableList<Category> = mutableListOf()
                for (doc in value!!) {
                    Log.d(TAG, "${doc.id}")
                    //var categoryItem = doc.toObject(Category::class.java)
                    var categoryItem = Category(
                        "${doc.get("name")}",
                        "${doc.get("categoryProfileImage")}",
                        "${doc.id}"
                    )

                    savedCategoryList.add(categoryItem)
                }
                savedCaategories.value = savedCategoryList

            })
        return savedCaategories
    }

    fun fetchUserDetails(): Task<DocumentSnapshot> {
        return repository.fetchUserDetails()
    }

    fun uploadCategoryImage(selectedPhotoUri: Uri?, categoryId: String) {
        repository.uploadCategoryImage(selectedPhotoUri, categoryId)
    }


}