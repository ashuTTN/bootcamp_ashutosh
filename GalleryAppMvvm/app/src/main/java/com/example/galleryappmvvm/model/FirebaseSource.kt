package com.example.galleryappmvvm.model

import android.app.Activity
import android.net.Uri
import android.util.Log
import android.widget.Toast
import com.example.galleryappmvvm.view.CategoryActivity
import com.example.galleryappmvvm.view.LoadingDialog
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.*
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.util.*

class FirebaseSource {
    private val TAG = "FIREBASE_SOURCE"
    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private var fStore = FirebaseFirestore.getInstance()
    private var categoryProfileImageUrl: String = ""
    private var profileImageUrl: String = ""
    private var categoryImageUrl: String = ""
    private val userID = firebaseAuth.currentUser!!.uid


    fun fetchUserDetails(): Task<DocumentSnapshot> {
        val documentSnapshot = fStore.collection("users").document(userID).get()
        return documentSnapshot
    }


    fun login(email: String, password: String): Task<AuthResult> {
        return firebaseAuth.signInWithEmailAndPassword(email, password)
    }

    fun signup(name: String, email: String, password: String, selectedPhotoUri: Uri?) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                Log.d(TAG, "Sign up successful")
                uploadUser(selectedPhotoUri, name, email)
            }
            .addOnFailureListener {
                Log.d(TAG, it.message.toString())
            }
    }

    private fun uploadUser(selectedPhotoUri: Uri?, name: String, email: String) {
        val filename = UUID.randomUUID().toString()
        val storageRef = FirebaseStorage.getInstance()
            .getReference("/images/$userID/profile_images/$filename")
        storageRef.putFile(selectedPhotoUri!!)
            .addOnSuccessListener {
                storageRef.downloadUrl.addOnSuccessListener {
                    Log.d(TAG, it.toString())
                    profileImageUrl = it.toString()
                    saveUserToFireStoreDatabase(name, email)
                }
            }
            .addOnFailureListener {
                Log.d(TAG, "profile image upload failed ${it.message}")
            }
    }

    fun updateUserProfile(selectedPhotoUri: Uri?) {
        val filename = UUID.randomUUID().toString()
        val storageRef = FirebaseStorage.getInstance()
            .getReference("/images/$userID/profile_images/$filename")
        storageRef.putFile(selectedPhotoUri!!)
            .addOnSuccessListener {
                storageRef.downloadUrl.addOnSuccessListener {
                    profileImageUrl = it.toString()
                    fStore.collection("users")
                        .document(userID)
                        .update(mapOf("profileImage" to profileImageUrl))
                }
            }
    }


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

    fun logout() = firebaseAuth.signOut()

    fun currentUser() = firebaseAuth.currentUser

    fun addCategory(activity: Activity, selectedPhotoUri: Uri?, categoryName: String) {
        val loadingDialog =
            LoadingDialog(activity)
        loadingDialog.startLoadingAnimation()
        val filename = UUID.randomUUID().toString()
        val storageRef = FirebaseStorage.getInstance()
            .getReference("/images/$userID/category_profile_images/${categoryName}/$filename")
        storageRef.putFile(selectedPhotoUri!!)
            .addOnSuccessListener {
                storageRef.downloadUrl.addOnSuccessListener {
                    categoryProfileImageUrl = it.toString()
                    saveCategoryToFireStore(categoryName)
                    loadingDialog.dismissDialog()
                }
            }
            .addOnFailureListener {
                Log.d(TAG, "category profile image upload failed ${it.message}")
                Toast.makeText(
                    CategoryActivity(),
                    "category profile image upload failed ${it.message}",
                    Toast.LENGTH_LONG
                ).show()
                loadingDialog.dismissDialog()
            }
    }

    fun saveCategoryToFireStore(categoryName: String) {
        val category = hashMapOf(
            "name" to categoryName,
            "categoryProfileImage" to categoryProfileImageUrl
        )
        val documentReference: DocumentReference = fStore.collection("users").document(userID)
        documentReference.collection("category").add(category)
    }

    fun getSavedCategories(): CollectionReference {
        val collectionReference = fStore.collection("users")
            .document(userID)
            .collection("category")
        return collectionReference
    }

    fun fetchTimeline(): StorageReference {
        val storageRef = FirebaseStorage.getInstance()
            .getReference("/images/$userID/category_images")
        return storageRef

    }

    fun fetchCategoryInfo(categoryId: String): CollectionReference {
        val collectionReference = fStore.collection("users")
            .document(userID)
            .collection("category").document(categoryId).collection("CategoryImages")
        return collectionReference
    }


    fun uploadCategoryImage(selectedPhotoUri: Uri?, categoryId: String) {
        val filename = UUID.randomUUID().toString()
        val storageRef = FirebaseStorage.getInstance()
            .getReference("/images/$userID/category_images/$filename")
        storageRef.putFile(selectedPhotoUri!!)
            .addOnSuccessListener {
                storageRef.downloadUrl.addOnSuccessListener {
                    categoryImageUrl = it.toString()
                    saveCategoryImageUrlToFirestore(categoryImageUrl, categoryId)
                }
            }
            .addOnFailureListener {
                Log.d(TAG, "category profile image upload failed ${it.message}")
            }
    }

    private fun saveCategoryImageUrlToFirestore(categoryImageUrl: String, categoryId: String) {
        val categoryUrl = hashMapOf(
            "categoryImageUrl" to categoryImageUrl
        )
        val documentReference: DocumentReference = fStore.collection("users").document(userID)
        documentReference.collection("category").document(categoryId).collection("CategoryImages")
            .add(categoryUrl)
    }

    fun deleteImage(imageUrl: String?, categoryId: String?, currentImageId: String?) {

        //delete from firebase storage
        val storageReference = FirebaseStorage.getInstance().getReferenceFromUrl(imageUrl!!)
        storageReference.delete().addOnSuccessListener {
            Log.d(TAG, "delete success")
        }
            .addOnFailureListener {
                Log.d("FAILED", "${it.message}")
            }
        //delete from firestore
        if (categoryId != null && imageUrl != null && currentImageId != null) {
            val docRef = fStore.collection("users")
                .document(FirebaseAuth.getInstance().currentUser!!.uid)
                .collection("category")
                .document(categoryId)
                .collection("CategoryImages")
                .document(currentImageId)
            docRef.delete()
        }
    }
}