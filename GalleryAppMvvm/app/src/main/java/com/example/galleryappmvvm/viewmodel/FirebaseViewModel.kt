package com.example.galleryappmvvm.viewmodel


import android.app.Activity
import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.galleryappmvvm.view.Category
import com.example.galleryappmvvm.view.CategoryImages
import com.example.galleryappmvvm.view.ImageTime
import com.example.galleryappmvvm.model.Repository
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.QuerySnapshot

class FirebaseViewModel() : ViewModel() {


    private val TAG = "FIRESTORE_VIEW_MODEL"
    private var savedCaategories: MutableLiveData<List<Category>> = MutableLiveData()
    private var saveImagesUrl: MutableLiveData<List<CategoryImages>> = MutableLiveData()
    private var time1: MutableLiveData<List<ImageTime>> = MutableLiveData()
    private var repository: Repository = Repository()
    private val savedUsers = mutableMapOf<String, String>()
//    var isLoaded:MutableLiveData<Boolean> = MutableLiveData(false)


//    fun currentUser(): FirebaseUser? {
//        return repository.currentUser()
//    }

    fun login(email: String, password: String): Task<AuthResult> {
        return repository.login(email, password)
    }


    fun signup(name: String, email: String, password: String, selectedPhotoUri: Uri) {
        repository.signup(name, email, password, selectedPhotoUri)
    }


    fun addCategory(activity: Activity, selectedPhotoUri: Uri?, categoryName: String) {
        repository.addCategory(activity, selectedPhotoUri, categoryName)
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
                    Log.d(TAG, doc.id)
                    //var categoryItem = doc.toObject(Category::class.java)
                    val categoryItem = Category(
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


    fun fetchCategoryInfo(categoryId: String): LiveData<List<CategoryImages>> {
        repository.fetchCategoryInfo(categoryId)
            .addSnapshotListener(EventListener<QuerySnapshot> { value, e ->
                if (e != null) {
                    Log.w(TAG, "Listen Fail", e)
                    saveImagesUrl.value = null
                    return@EventListener
                }
                val savedImagesList: MutableList<CategoryImages> = mutableListOf()
                for (doc in value!!) {
                    val imageItem =
                        CategoryImages(
                            categoryId,
                            "${doc.id}",
                            "${doc.get("categoryImageUrl")}"
                        )
                    savedImagesList.add(imageItem)
                }
                saveImagesUrl.value = savedImagesList
                Log.d(TAG, savedImagesList.toString())
            })
        return saveImagesUrl
    }


    fun uploadCategoryImage(selectedPhotoUri: Uri?, categoryId: String) {
        repository.uploadCategoryImage(selectedPhotoUri, categoryId)
    }


    fun deleteImage(imageUrl: String?, categoryId: String?, currentImageId: String?) {
        repository.deleteImage(imageUrl, categoryId, currentImageId)

    }


    fun fetchUserDetails(): Task<DocumentSnapshot> {
        return repository.fetchUserDetails()
    }


    fun updateUserProfile(selectedPhotoUri: Uri?) {
        repository.updateUserProfile(selectedPhotoUri)
    }


    fun fetchTimeline(): LiveData<List<ImageTime>> {
        repository.fetchTimeline().listAll()
            .addOnSuccessListener {
                val time = mutableListOf<ImageTime>()
                for (i in it.items) {
                    i.metadata.addOnSuccessListener {
                        val imageTime =
                            ImageTime(
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


    fun logout() {
        repository.logout()
    }


}