package com.example.galleryappmvvm.viewmodel

import androidx.lifecycle.ViewModel
import com.example.galleryappmvvm.model.Repository
import com.google.firebase.auth.FirebaseUser

private val TAG = FirebaseViewModel::class.java.simpleName
class FirebaseViewModel() : ViewModel() {
    private var repository: Repository = Repository()
    fun currentUser(): FirebaseUser? {
        return repository.currentUser()
    }
}