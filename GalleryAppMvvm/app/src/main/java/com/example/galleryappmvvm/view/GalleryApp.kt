package com.example.galleryappmvvm.view

import android.app.Application

class GalleryApp : Application() {
    override fun onCreate() {
        super.onCreate()
        mInstance = this
    }
    companion object{
        lateinit var mInstance:GalleryApp
        fun getInstance(): GalleryApp {      // Provides an instance of the gallery app
            return mInstance
        }
    }
}