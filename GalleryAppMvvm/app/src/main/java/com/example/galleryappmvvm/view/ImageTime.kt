package com.example.galleryappmvvm.view
import java.io.Serializable

data class ImageTime(val imageUrl: Any?, val timestamp: Any?):Serializable{
    constructor():this("","")
}