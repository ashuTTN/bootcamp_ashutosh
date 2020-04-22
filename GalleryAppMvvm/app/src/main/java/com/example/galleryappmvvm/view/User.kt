package com.example.galleryappmvvm.view

data class User(var userID:String, var userName:String, var email:String) {

    constructor():this("","","")
    fun getUser(): User {
        var user = User(userID, userName, email)
        return user
    }
}