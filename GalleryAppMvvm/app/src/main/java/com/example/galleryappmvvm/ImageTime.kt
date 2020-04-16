package com.example.galleryappmvvm
import java.io.Serializable
import java.sql.Timestamp

data class ImageTime(val imageUrl: Any?, val timestamp: Any?):Serializable{
    constructor():this("","")
}