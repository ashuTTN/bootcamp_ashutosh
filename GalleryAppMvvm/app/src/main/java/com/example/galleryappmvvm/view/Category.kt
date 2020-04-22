package com.example.galleryappmvvm.view

import java.io.Serializable

data class Category(val name: Any?, val categoryProfileImage: Any?,val categoryID:Any):Serializable{
    constructor():this("","","")
}