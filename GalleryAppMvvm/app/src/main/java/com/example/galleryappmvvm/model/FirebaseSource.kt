package com.example.galleryappmvvm.model


import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.galleryappmvvm.view.modelclass.CategoryImages
import com.example.galleryappmvvm.view.modelclass.ImageTime
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.*
import com.google.firebase.firestore.EventListener
import com.google.firebase.storage.FirebaseStorage
import java.util.*

private const val TAG = "FIREBASE_SOURCE"
class FirebaseSource {
    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private var fStore = FirebaseFirestore.getInstance()
    private var categoryProfileImageUrl: String = ""
    private var profileImageUrl: String = ""
    private var categoryImageUrl: String = ""


    fun currentUser(): FirebaseUser? {
        return FirebaseAuth.getInstance().currentUser
    }


    fun login1(email: String, password: String): LiveData<Result<Boolean>> {
        val loginResult: MutableLiveData<Result<Boolean>> = MutableLiveData()
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                loginResult.value = Result.success(true)
            }
            .addOnFailureListener {
                loginResult.value = Result.failure(it)
            }
        return loginResult
    }

    fun signup1(
        name: String,
        email: String,
        password: String,
        selectedPhotoUri: Uri
    ): MutableLiveData<Result<Boolean>> {
        val signUpResult: MutableLiveData<Result<Boolean>> = MutableLiveData()
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                uploadUser(selectedPhotoUri, name, email)
                signUpResult.value = Result.success(true)
            }
            .addOnFailureListener {
                signUpResult.value = Result.failure(it)
            }
        return signUpResult
    }

    //Fetch user details return Document Snapshot
    fun fetchUserDetails1(): MutableLiveData<Result<DocumentSnapshot>> {
        val result: MutableLiveData<Result<DocumentSnapshot>> = MutableLiveData()
        val userID = firebaseAuth.currentUser!!.uid
        fStore.collection("users").document(userID).get()
            .addOnSuccessListener {
                result.value = Result.success(it)
            }
            .addOnFailureListener {
                result.value = Result.failure(it)
            }
        return result
    }

    fun getSavedCategories1(): CollectionReference {
        val userID = firebaseAuth.currentUser!!.uid
        return fStore.collection("users")
            .document(userID)
            .collection("category")
    }

    fun addCategory1(
        selectedPhotoUri: Uri,
        categoryName: String
    ): MutableLiveData<Result<Boolean>> {
        val result: MutableLiveData<Result<Boolean>> = MutableLiveData()
        val userID = firebaseAuth.currentUser!!.uid
        val filename = UUID.randomUUID().toString()
        val storageRef = FirebaseStorage.getInstance()
            .getReference("/images/$userID/category_profile_images/${categoryName}/$filename")  //Save category profile image to FirebaseStorage
        storageRef.putFile(selectedPhotoUri)
            .addOnSuccessListener {
                storageRef.downloadUrl.addOnSuccessListener {
                    categoryProfileImageUrl =
                        it.toString()  //gets then url of category profile image from firebase storage
                    saveCategoryToFireStore(categoryName)  //Saves category to firestor
                    result.value = Result.success(true)
                }
            }
            .addOnFailureListener {
                result.value = Result.failure(it)
            }
        return result
    }

    fun uploadCategoryImage1(
        selectedPhotoUri: Uri,
        categoryId: String
    ): MutableLiveData<Result<Boolean>> {
        val result = MutableLiveData<Result<Boolean>>()
        val userID = firebaseAuth.currentUser!!.uid
        val filename = UUID.randomUUID().toString()
        val storageRef = FirebaseStorage.getInstance()
            .getReference("/images/$userID/category_images/$filename")
        storageRef.putFile(selectedPhotoUri)
            .addOnSuccessListener {
                storageRef.downloadUrl.addOnSuccessListener {
                    categoryImageUrl = it.toString()
                    saveCategoryImageUrlToFirestore(
                        categoryImageUrl,
                        categoryId
                    ).addOnSuccessListener {
                        result.value = Result.success(true)
                    }.addOnFailureListener {
                        result.value = Result.failure(it)
                    }// save category image in firestore under respective categoryId
                }
                result.value = Result.success(true)
            }
            .addOnFailureListener {
                result.value = Result.failure(it)
            }
        return result
    }

    fun fetchCategoryInfo1(categoryId: String): MutableLiveData<List<CategoryImages>> {
        val saveImagesUrl: MutableLiveData<List<CategoryImages>> = MutableLiveData()
        val userID = firebaseAuth.currentUser!!.uid

        val collectionReference = fStore.collection("users")
            .document(userID)
            .collection("category").document(categoryId).collection("CategoryImages")

        collectionReference.addSnapshotListener(EventListener<QuerySnapshot> { value, e ->
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


    //upload user profile image to firebase storage and calls saveUserToFireStoreDatabase
    private fun uploadUser(selectedPhotoUri: Uri, name: String, email: String) {
        val userID = firebaseAuth.currentUser!!.uid
        val filename = UUID.randomUUID().toString()
        val storageRef = FirebaseStorage.getInstance()
            .getReference("/images/$userID/profile_images/$filename")
        storageRef.putFile(selectedPhotoUri)
            .addOnSuccessListener {
                storageRef.downloadUrl.addOnSuccessListener {
                    Log.d(TAG, it.toString())
                    profileImageUrl = it.toString()
                    saveUserToFireStoreDatabase(name, email) //saves name and email to firesotre
                }
            }
            .addOnFailureListener {
                Log.d(TAG, "profile image upload failed ${it.message}")
            }
    }

    //save name , email and profile Image url to firestore
    private fun saveUserToFireStoreDatabase(name: String, email: String) {
        Log.d(TAG, "saveUserToFirestoreDB")
        val user = hashMapOf(
            "name" to name,
            "email" to email,
            "profileImage" to profileImageUrl
        )
        fStore.collection("users").document(FirebaseAuth.getInstance().currentUser!!.uid)
            .set(user)
    }



    //saves category name and its profile image to firestore
    private fun saveCategoryToFireStore(categoryName: String) {
        val userID = firebaseAuth.currentUser!!.uid
        val category = hashMapOf(
            "name" to categoryName,
            "categoryProfileImage" to categoryProfileImageUrl
        )
        val documentReference: DocumentReference = fStore.collection("users").document(userID)
        documentReference.collection("category").add(category)
    }


    // save category image in firestore under respective categoryId
    private fun saveCategoryImageUrlToFirestore(
        categoryImageUrl: String,
        categoryId: String
    ): Task<DocumentReference> {
        val userID = firebaseAuth.currentUser!!.uid
        val categoryUrl = hashMapOf(
            "categoryImageUrl" to categoryImageUrl
        )
        val documentReference: DocumentReference = fStore.collection("users").document(userID)
        return documentReference.collection("category").document(categoryId)
            .collection("CategoryImages")
            .add(categoryUrl)
    }

    //delete an image takes in respective image url , category id and currentImageId
    fun deleteImage(imageUrl: String?, categoryId: String?, currentImageId: String?): MutableLiveData<Result<Boolean>> {
        val result = MutableLiveData<Result<Boolean>>()
        //delete from firebase storage
        val storageReference = FirebaseStorage.getInstance().getReferenceFromUrl(imageUrl!!)
        storageReference.delete().addOnSuccessListener {
            Log.d(TAG, "delete success")
            result.value = Result.success(true)
        }
            .addOnFailureListener {
                Log.d("FAILED", "${it.message}")
                result.value = Result.failure(it)
            }
        //delete from firestore
        if (categoryId != null && currentImageId != null) {
            val docRef = fStore.collection("users")
                .document(FirebaseAuth.getInstance().currentUser!!.uid)
                .collection("category")
                .document(categoryId)
                .collection("CategoryImages")
                .document(currentImageId)
            docRef.delete().addOnSuccessListener {
                result.value = Result.success(true)
            }
                .addOnFailureListener {
                    result.value = Result.failure(it)
                }
        }
        return result
    }


    //update user profile image -- takes selected photo uri
    fun updateUserProfile(selectedPhotoUri: Uri?): LiveData<Result<Boolean>> {
        val result = MutableLiveData<Result<Boolean>>()

        val userID = firebaseAuth.currentUser!!.uid
        val filename = UUID.randomUUID().toString() // generates a random file name
        val storageRef = FirebaseStorage.getInstance()
            .getReference("/images/$userID/profile_images/$filename")  // path in firebase storage where image gets stored
        storageRef.putFile(selectedPhotoUri!!)
            .addOnSuccessListener {
                storageRef.downloadUrl.addOnSuccessListener {
                    profileImageUrl = it.toString() //url of new image
                    fStore.collection("users")
                        .document(userID)
                        .update(mapOf("profileImage" to profileImageUrl))
                        .addOnSuccessListener {
                            result.value = Result.success(true)
                        }
                        .addOnFailureListener {
                            result.value = Result.failure(it)
                        }
                }
            }
            .addOnFailureListener {
                result.value = Result.failure(it)
            }
        return result
    }


    fun fetchTimeline(): LiveData<Result<LiveData<List<ImageTime>>>> {
        val result = MutableLiveData<Result<LiveData<List<ImageTime>>>>()
        val userID = firebaseAuth.currentUser!!.uid
        val time1: MutableLiveData<List<ImageTime>> = MutableLiveData()

        val storageRef = FirebaseStorage.getInstance()
            .getReference("/images/$userID/category_images")
        storageRef.listAll()
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
                        result.value = Result.success(time1)
                    }
                }
            }
            .addOnFailureListener {
                result.value = Result.failure(it)
            }
        return result
    }


    fun logout() {
        firebaseAuth.signOut()
    }

    fun isLoggedIn(): LiveData<Boolean> {
        val isLoggedIn = MutableLiveData<Boolean>()
        isLoggedIn.value = FirebaseAuth.getInstance().currentUser != null
        return isLoggedIn
    }


}