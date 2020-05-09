package com.example.galleryappmvvm.view.modelclass
//User Model Class
data class User(var userID:String, var userName:String, var email:String) {
    constructor():this("","","")
    fun getUser(): User {
        return User(
            userID,
            userName,
            email
        )
    }
}