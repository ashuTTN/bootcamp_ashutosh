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



    //Login User
    fun login(email: String, password: String): Task<AuthResult> {
        val auth =  firebaseAuth.signInWithEmailAndPassword(email, password)
        return auth
    }


    //Signup on Auth and upload selected photo , name and email on firestore
    fun signup(name: String, email: String, password: String, selectedPhotoUri: Uri) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                uploadUser(selectedPhotoUri, name, email)
            }
            .addOnFailureListener {
                Log.d(TAG, it.message.toString())
            }
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

    //Add a new Category
    fun addCategory(activity: Activity, selectedPhotoUri: Uri?, categoryName: String) {
        val userID = firebaseAuth.currentUser!!.uid
        val loadingDialog = LoadingDialog(activity)
        loadingDialog.startLoadingAnimation()
        val filename = UUID.randomUUID().toString()
        val storageRef = FirebaseStorage.getInstance()
            .getReference("/images/$userID/category_profile_images/${categoryName}/$filename")  //Save category profile image to FirebaseStorage
        storageRef.putFile(selectedPhotoUri!!)
            .addOnSuccessListener {
                storageRef.downloadUrl.addOnSuccessListener {
                    categoryProfileImageUrl = it.toString()  //gets then url of category profile image from firebase storage
                    saveCategoryToFireStore(categoryName)  //Saves category to firestore
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

    //Return collection reference of category in which all categories are saved
    fun getSavedCategories(): CollectionReference {
        val userID = firebaseAuth.currentUser!!.uid
        val collectionReference = fStore.collection("users")
            .document(userID)
            .collection("category")
        return collectionReference
    }

    //returns collection reference -- categoryImages
    fun fetchCategoryInfo(categoryId: String): CollectionReference {
        val userID = firebaseAuth.currentUser!!.uid
        val collectionReference = fStore.collection("users")
            .document(userID)
            .collection("category").document(categoryId).collection("CategoryImages")
        return collectionReference
    }

    //Upload category Image to Firebase Storage
    fun uploadCategoryImage(selectedPhotoUri: Uri?, categoryId: String) {
        val userID = firebaseAuth.currentUser!!.uid
        val filename = UUID.randomUUID().toString()
        val storageRef = FirebaseStorage.getInstance()
            .getReference("/images/$userID/category_images/$filename")
        storageRef.putFile(selectedPhotoUri!!)
            .addOnSuccessListener {
                storageRef.downloadUrl.addOnSuccessListener {
                    categoryImageUrl = it.toString()
                    saveCategoryImageUrlToFirestore(categoryImageUrl, categoryId) // save category image in firestore under respective categoryId
                }
            }
            .addOnFailureListener {
                Log.d(TAG, "category profile image upload failed ${it.message}")
            }
    }

    // save category image in firestore under respective categoryId
    private fun saveCategoryImageUrlToFirestore(categoryImageUrl: String, categoryId: String) {
        val userID = firebaseAuth.currentUser!!.uid
        val categoryUrl = hashMapOf(
            "categoryImageUrl" to categoryImageUrl
        )
        val documentReference: DocumentReference = fStore.collection("users").document(userID)
        documentReference.collection("category").document(categoryId).collection("CategoryImages")
            .add(categoryUrl)
    }

    //delete an image takes in respective image url , category id and currentImageId
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

    //Fetch user details return Document Snapshot
    fun fetchUserDetails(): Task<DocumentSnapshot> {
        val userID = firebaseAuth.currentUser!!.uid
        val documentSnapshot = fStore.collection("users").document(userID).get()
        return documentSnapshot
    }

    //update user profile image -- takes selected photo uri
    fun updateUserProfile(selectedPhotoUri: Uri?) {
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
                        .update(mapOf("profileImage" to profileImageUrl)) //update in firestore
                }
            }
    }


    fun fetchTimeline(): StorageReference {
        val userID = firebaseAuth.currentUser!!.uid
        val storageRef = FirebaseStorage.getInstance()
            .getReference("/images/$userID/category_images")
        return storageRef

    }


    fun logout() = firebaseAuth.signOut()

}